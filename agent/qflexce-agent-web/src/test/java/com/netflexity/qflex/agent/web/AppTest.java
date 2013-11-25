package com.netflexity.qflex.agent.web;

import java.io.ByteArrayOutputStream;

import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netflexity.schema.software.nfx_wmq.messages._1.GetQueue;
import com.netflexity.schema.software.nfx_wmq.types._1.ConnectionType;
import com.netflexity.schema.software.qflex.agent.messages._1.ShowTransaction;
import com.netflexity.schema.software.qflex.agent.types._1.CallType;
import com.netflexity.schema.software.qflex.agent.types._1.OperationType;
import com.netflexity.schema.software.qflex.agent.types._1.OperationType.Request;
import com.netflexity.software.service.qflex.agent._1_0.QflexAgentClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/test-qflex-agent-web-context.xml"})
public class AppTest {
	
	//@Autowired
	QflexAgentClient qflexAgentServiceClient;
	@Autowired
	Marshaller marshaller;
	ConnectionType connectionType;
	
	@Test
	public void testGetQueue() {
		try {
			ShowTransaction showTransaction = new ShowTransaction();
			CallType call = new CallType();
			showTransaction.getCall().add(call);
			OperationType oper = new OperationType();
			call.getOperation().add(oper);
			Request req = new Request();
			oper.setRequest(req);
			
			GetQueue getQueue = new GetQueue();
			req.setAny(getQueue);
			getQueue.setConnection(getConnectionType());
			getQueue.setQueueName("testQname");
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			StreamResult result = new StreamResult(baos);			
			marshaller.marshal(showTransaction, result);
			System.out.println("RESPONSE\n" + baos.toString());
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	private ConnectionType getConnectionType() {
		if (connectionType == null) {
			connectionType = new ConnectionType();
			connectionType.setQmanagerName("LD02");
			connectionType.setHost("127.0.0.1");
			connectionType.setPort(1415);
			connectionType.setChannelName("SYSTEM.DEF.SVRCONN");
			connectionType.setOs("Linux");
			connectionType.setWmqVersion("7.X");
		}
		return connectionType;
	}
}
