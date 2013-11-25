package com.netflexity.qflex.agent.impl;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflexity.qflex.agent.CallProcessor;
import com.netflexity.schema.software.qflex.agent.types._1.ErrorType;
import com.netflexity.schema.software.qflex.agent.types._1.OperationType;
import com.netflexity.schema.software.qflex.agent.types._1.OperationType.Request;
import com.netflexity.software.service.wmq._1_0.WMQFaultMessage;

/**
 * @author mfedorov
 *
 */
public class CamelCallProcessor implements CallProcessor<OperationType> {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private CamelContext camelContext;
	private String route;
	
	/* (non-Javadoc)
	 * @see com.netflexity.qflex.agent.CallProcessor#processCall(java.lang.Object)
	 */
	@Override
	public OperationType processCall(final OperationType operation) {
		ProducerTemplate template = camelContext.createProducerTemplate();
		Exchange exchange = template.request(route, new Processor() {
			
			/* (non-Javadoc)
			 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
			 */
			public void process(Exchange exchange) throws Exception {
				Request operationRequest = operation.getRequest();
				Object obj = operationRequest.getAny();
				exchange.getIn().setBody(obj);
			}
		});
		
		OperationType.Result result = new OperationType.Result();
		operation.setResult(result);
		
		Message message = exchange.getOut();
		if(message != null){
			logger.debug("Response:" + message.getBody());
			if(exchange.getException() != null){
				logger.error("Fault processing operation:", exchange.getException());
				ErrorType error = new ErrorType();
				operation.setError(error);
				if(exchange.getException() instanceof WMQFaultMessage){
					WMQFaultMessage exc = (WMQFaultMessage)exchange.getException();
					if(exc.getFaultInfo() != null){
						error.setCode(exc.getFaultInfo().getReasonCode());
						error.setDescription(exc.getFaultInfo().getDescription());
					}
					else{
						error.setDescription(exc.getMessage());
					}
				}
				else {
					error.setDescription(exchange.getException().getMessage());
				}
			}
			else{
				result.setAny(message.getBody());
			}
			message.setBody(operation);
		}
		
		return operation;
	}

	/**
	 * @param camelContext the camelContext to set
	 */
	public void setCamelContext(CamelContext camelContext) {
		this.camelContext = camelContext;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(String route) {
		this.route = route;
	}
}
