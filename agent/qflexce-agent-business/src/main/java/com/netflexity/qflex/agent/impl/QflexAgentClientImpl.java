package com.netflexity.qflex.agent.impl;

import java.util.Calendar;
import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Marshaller;

import com.netflexity.qflex.agent.CallProcessor;
import com.netflexity.qflex.agent.utils.MarshallerUtils;
import com.netflexity.schema.software.qflex.agent.messages._1.ProcessTransaction;
import com.netflexity.schema.software.qflex.agent.messages._1.ShowTransaction;
import com.netflexity.schema.software.qflex.agent.types._1.CallType;
import com.netflexity.schema.software.qflex.agent.types._1.OperationType;
import com.netflexity.software.service.qflex.agent._1_0.AgentFaultMessage;
import com.netflexity.software.service.qflex.agent._1_0.QflexAgentClient;

@WebService(targetNamespace = "urn:com:netflexity:software:service:qflex:agent:1.0", name = "QflexAgentClient")
public class QflexAgentClientImpl implements QflexAgentClient {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Marshaller marshaller;
	private CallProcessor<OperationType> processor;
	
    /* (non-Javadoc)
     * @see com.netflexity.software.service.qflex.agent._1_0.QflexAgentClient#processTransaction(com.netflexity.schema.software.qflex.agent.messages._1.ProcessTransactionType)
     */
    public ShowTransaction processTransaction(ProcessTransaction request) throws AgentFaultMessage {
    	ShowTransaction rsp = new ShowTransaction();
    	
    	if(request == null) {
    		if(logger.isDebugEnabled()) {
    			logger.debug("No message is set.");
    		}
			return rsp;
		}
    	
    	logTransaction(request);
		
		List<CallType> calls = request.getCall();
		if(calls == null || calls.size() == 0) {
			if(logger.isDebugEnabled()) {
				logger.debug("No WMQ commands to execute.");
			}
			return rsp;
		}
		
		request.setStarted(Calendar.getInstance());
		for (CallType call : calls) {
			if(logger.isDebugEnabled()) {
				logger.debug("Executing call ID:" + call.getId());
			}
			OperationType operation = processor.processCall(call.getOperation().get(0));
			call.getOperation().get(0).setResult(operation.getResult());
			call.getOperation().get(0).setError(operation.getError());
			
			rsp.getCall().add(call);
		}
		request.setEnded(Calendar.getInstance());
		
		if(logger.isDebugEnabled()) {
			logger.debug("WMQ transaction for ID:" + request.getIp() + " started on " + request.getStarted() + " and completed on:" + request.getEnded());
		}
		logTransaction(rsp);
		return rsp;
	}

	/**
	 * @param transaction
	 */
	private void logTransaction(ProcessTransaction transaction) {
		if(logger.isDebugEnabled()) {
			byte[] xmlAsBytes = MarshallerUtils.marshallUsingStaxResult(marshaller, transaction);
			if(xmlAsBytes != null && xmlAsBytes.length > 0){
				logger.debug(new String(xmlAsBytes));
			}
		}
	}

	/**
	 * @param transaction
	 */
	private void logTransaction(ShowTransaction transaction) {
		if(logger.isDebugEnabled()) {
			byte[] xmlAsBytes = MarshallerUtils.marshallUsingStaxResult(marshaller, transaction);
			if(xmlAsBytes != null && xmlAsBytes.length > 0){
				logger.debug(new String(xmlAsBytes));
			}
		}
	}
	
	/**
	 * @param processor the processor to set
	 */
	public void setProcessor(CallProcessor<OperationType> processor) {
		this.processor = processor;
	}

	/**
	 * @return
	 */
	public CallProcessor<OperationType> getProcessor() {
		return processor;
	}
	
	/**
	 * @return
	 */
	public Marshaller getMarshaller() {
		return marshaller;
	}

	/**
	 * @param marshaller
	 */
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
}
