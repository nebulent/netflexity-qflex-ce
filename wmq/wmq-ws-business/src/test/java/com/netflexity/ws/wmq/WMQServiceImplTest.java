package com.netflexity.ws.wmq;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllChannels;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllChannelsOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllMessages;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllMessagesOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllQueues;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllQueuesOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetChannel;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetChannelOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetQmanager;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetQmanagerOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetQueue;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetQueueOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetQueueStats;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetQueueStatsOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.IsCommandServerUp;
import com.netflexity.schema.software.nfx_wmq.messages._1.IsCommandServerUpOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.IsQmanagerUp;
import com.netflexity.schema.software.nfx_wmq.messages._1.IsQmanagerUpOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.RemoveChannel;
import com.netflexity.schema.software.nfx_wmq.messages._1.RemoveQueue;
import com.netflexity.schema.software.nfx_wmq.types._1.ChannelType;
import com.netflexity.schema.software.nfx_wmq.types._1.ConnectionType;
import com.netflexity.schema.software.nfx_wmq.types._1.MqMessageType;
import com.netflexity.schema.software.nfx_wmq.types._1.QueueStatType;
import com.netflexity.schema.software.nfx_wmq.types._1.QueueType;
import com.netflexity.software.service.wmq._1_0.WMQ;

/**
 * 
 * @author Alexei
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/test-nfx-wmq-context.xml"})
public class WMQServiceImplTest {

	@Autowired
	WMQ wmqServiceBean;
	
	private ConnectionType connectionType;
	
	private ConnectionType getConnectionType() {
		if (connectionType == null) {
			connectionType = new ConnectionType();
			connectionType.setQmanagerName("NF01");
			connectionType.setHost("ec2-54-211-72-194.compute-1.amazonaws.com");
			connectionType.setPort(1414);
			connectionType.setChannelName("QFLEX01.SVRCONN");
			connectionType.setOs("Windows");
			connectionType.setWmqVersion("7.X");
		}
		return connectionType;
	}
	
	private void checkException(String msg) {
		System.out.println("Error Msg = " + msg);
		if(msg == null || !msg.contains("Reason 20")) {
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllChannels() {
		try {
			GetAllChannels getAllBaseChannels = new GetAllChannels();
			getAllBaseChannels.setConnection(getConnectionType());
			GetAllChannelsOut rsp = wmqServiceBean.getAllChannels(getAllBaseChannels);
			for(ChannelType channel : rsp.getChannels().getChannel()) {
				System.out.println("Channel Name = " + channel.getName());
			}
		} catch (Exception exc) {
			checkException(exc.getMessage());
		}
	}
	
	@Test
	public void testGetAllQueues() {
		try {
			GetAllQueues request = new GetAllQueues();
			request.setConnection(getConnectionType());
			GetAllQueuesOut rsp = wmqServiceBean.getAllQueues(request);
			for(QueueType queue : rsp.getQueues().getQueue()) {
				System.out.println("Queue Name = "  + queue.getName());
			}
		} catch (Exception exc) {
			checkException(exc.getMessage());		
		}
	}
	
	@Test
	public void testGetAllMessages() {
		try {
			GetAllMessages request = new GetAllMessages();
			request.setConnection(getConnectionType());
			request.setQueueName("NFX_MOLDOVANE");
			GetAllMessagesOut rsp = wmqServiceBean.getAllMessages(request);
			for(MqMessageType message : rsp.getMessages().getMessage()) {
				System.out.println("MsgId = " + message.getId());
			}
		} catch (Exception exc) {
			checkException(exc.getMessage());		
		}
	}
	
	@Test
	public void testGetChannel() {
		try {
			GetChannel request = new GetChannel();
			request.setConnection(getConnectionType());
			request.setChannelName("SYSTEM.DEF.CLNTCONN");
			GetChannelOut rsp = wmqServiceBean.getChannel(request);
			System.out.println("Channel = " + rsp.getChannel().getName());
		} catch (Exception exc) {
			checkException(exc.getMessage());
		}
	}
	
	@Test
	public void testGetQmanager() {
		try {
			GetQmanager request = new GetQmanager();
			request.setConnection(getConnectionType());
			GetQmanagerOut rsp = wmqServiceBean.getQmanager(request);
			System.out.println("Qmanager = " + rsp.getQmanager().getName());
		} catch (Exception exc) {
			checkException(exc.getMessage());
		}
	}

	@Test
	public void testGetQueue() {
		try {
			GetQueue request = new GetQueue();
			request.setConnection(getConnectionType());
			request.setQueueName("VIDEO_XML_IN");
			GetQueueOut rsp = wmqServiceBean.getQueue(request);
			System.out.println("Queue Name = " + rsp.getQueue().getName());
		} catch (Exception exc) {
			checkException(exc.getMessage());
		}
	}
	
	@Test
	public void testGetQueueStats() {
		try {
			GetQueueStats request = new GetQueueStats();
			request.setConnection(getConnectionType());
			GetQueueStatsOut rsp = wmqServiceBean.getQueueStats(request);
			for(QueueStatType stat : rsp.getQueueStats().getQueueStat()) {
				System.out.println("Queue Name = " + stat.getQueueName());
			}
		} catch (Exception exc) {
			checkException(exc.getMessage());
		}
	}
	
	@Test
	public void testIsCommandServerUp() {
		try {
			IsCommandServerUp request = new IsCommandServerUp();
			request.setConnection(getConnectionType());
			IsCommandServerUpOut rsp = wmqServiceBean.isCommandServerUp(request);
			System.out.println("IsCommandServerUp = " + rsp.isResult());
		} catch (Exception exc) {
			checkException(exc.getMessage());
		}
	}
	
	@Test
	public void testIsQmanagerUp() {
		try {
			IsQmanagerUp request = new IsQmanagerUp();
			request.setConnection(getConnectionType());
			IsQmanagerUpOut rsp = wmqServiceBean.isQmanagerUp(request);
			System.out.println("IsQmanagerUp = " + rsp.isResult());
		} catch (Exception exc) {
			checkException(exc.getMessage());
		}
	}
	
	@Test
	public void testRemoveChannel() {
		try {
			RemoveChannel request = new RemoveChannel();
			request.setConnection(getConnectionType());
			request.setChannelName("VMS");
			wmqServiceBean.removeChannel(request);
		} catch (Exception exc) {
			checkException(exc.getMessage());
		}
	}
	
	@Test
	public void testRemoveQueue() {
		try {
			RemoveQueue request = new RemoveQueue();
			request.setConnection(getConnectionType());
			request.setQueueName("BRANCH_REQUESTS.1");
			wmqServiceBean.removeQueue(request);
		} catch (Exception exc) {
			checkException(exc.getMessage());
		}
	}
	
	@Test
	public void testSetChannel() {
		//TODO
		/*
		try {
			SetChannel request = new SetChannel();
			request.setConnection(getConnectionType());
			ChannelType channelType = new ChannelType();
			channelType.setName("SYSTEM.DEF.SVRCONN_TEST");
			request.setChannel(channelType);
			wmqServiceClient.setChannel(request);
		} catch (Exception exc) {
			System.out.println("Error Msg = " + exc.getMessage());
			Assert.fail();
		}
		*/
	}
	
	@Test
	public void testSetQmanager() {
		//TODO
	}
	
	@Test
	public void testSetQueue() {
		//TODO
	}

}

















