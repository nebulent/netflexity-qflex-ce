/**
 * 
 */
package com.netflexity.ws.wmq;

import java.util.List;
import java.util.Locale;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.KeyedObjectPool;
import org.netflexity.api.mq.MqChannel;
import org.netflexity.api.mq.MqException;
import org.netflexity.api.mq.MqMessage;
import org.netflexity.api.mq.MqQmanager;
import org.netflexity.api.mq.MqQmanagerManager;
import org.netflexity.api.mq.MqQmanagerManagerContext;
import org.netflexity.api.mq.MqQueue;
import org.netflexity.api.mq.MqQueueStat;
import org.netflexity.api.mq.MqTopic;
import org.netflexity.api.mq.ibm.IBMMqQmanagerManagerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllBaseChannels;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllBaseChannelsOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllBaseQueues;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllBaseQueuesOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllChannels;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllChannelsOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllMessages;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllMessagesOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllQueues;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllQueuesOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllTopics;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetAllTopicsOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetChannel;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetChannelOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetQmanager;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetQmanagerOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetQueue;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetQueueOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetQueueStats;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetQueueStatsOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetTopic;
import com.netflexity.schema.software.nfx_wmq.messages._1.GetTopicOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.IsCommandServerUp;
import com.netflexity.schema.software.nfx_wmq.messages._1.IsCommandServerUpOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.IsQmanagerUp;
import com.netflexity.schema.software.nfx_wmq.messages._1.IsQmanagerUpOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.RemoveChannel;
import com.netflexity.schema.software.nfx_wmq.messages._1.RemoveChannelOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.RemoveQueue;
import com.netflexity.schema.software.nfx_wmq.messages._1.RemoveQueueOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.RemoveTopic;
import com.netflexity.schema.software.nfx_wmq.messages._1.RemoveTopicOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.SetChannel;
import com.netflexity.schema.software.nfx_wmq.messages._1.SetChannelOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.SetQmanager;
import com.netflexity.schema.software.nfx_wmq.messages._1.SetQmanagerOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.SetQueue;
import com.netflexity.schema.software.nfx_wmq.messages._1.SetQueueOut;
import com.netflexity.schema.software.nfx_wmq.messages._1.WMQFault;
import com.netflexity.schema.software.nfx_wmq.types._1.BaseChannelArrayType;
import com.netflexity.schema.software.nfx_wmq.types._1.BaseChannelType;
import com.netflexity.schema.software.nfx_wmq.types._1.BaseQueueArrayType;
import com.netflexity.schema.software.nfx_wmq.types._1.ChannelArrayType;
import com.netflexity.schema.software.nfx_wmq.types._1.ChannelType;
import com.netflexity.schema.software.nfx_wmq.types._1.ConnectionType;
import com.netflexity.schema.software.nfx_wmq.types._1.QueueArrayType;
import com.netflexity.schema.software.nfx_wmq.types._1.QueueStatArrayType;
import com.netflexity.schema.software.nfx_wmq.types._1.TopicArrayType;
import com.netflexity.software.service.wmq._1_0.WMQ;
import com.netflexity.software.service.wmq._1_0.WMQFaultMessage;

/**
 * @author Alexei
 *
 */
@WebService(targetNamespace = "http://netflexity.com/software/service/wmq/1.0", name = "WMQ")
public class WMQServiceImpl implements WMQ {
	
    private static final Log logger = LogFactory.getLog(WMQServiceImpl.class);
    private KeyedObjectPool pool;
    private ResourceBundleMessageSource serviceErrorsMessageSource;
	
    /* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#removeTopic(com.netflexity.schema.software.nfx_wmq.messages._1.RemoveTopic)
	 */
	@Override
	public RemoveTopicOut removeTopic(final RemoveTopic request) throws WMQFaultMessage {
		execute(request.getConnection(), new ManagerCallbackWithoutResult() {	
			public void callManager(MqQmanagerManager manager) throws MqException {
				manager.deleteQueue(request.getTopicName());
			}
		});
		RemoveTopicOut response = SchemaConvertionUtils.objectMessageFactory.createRemoveTopicOut();
    	response.setResult(true);
    	return response;
	}

	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#getTopic(com.netflexity.schema.software.nfx_wmq.messages._1.GetTopic)
	 */
	@Override
	public GetTopicOut getTopic(final GetTopic request) throws WMQFaultMessage {
		MqTopic topic = execute(request.getConnection(), new ManagerCallback<MqTopic>() {
			public MqTopic callManager(MqQmanagerManager manager) throws MqException {
				return manager.getTopic(request.getTopicName());
			}
		});
		GetTopicOut response = SchemaConvertionUtils.objectMessageFactory.createGetTopicOut();
    	response.setTopic(SchemaConvertionUtils.toXmlType(topic));
    	return response;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws WMQFaultMessage
	 */
	public GetAllBaseChannelsOut getAllChannels(GetAllBaseChannels request) throws WMQFaultMessage {
		List<BaseChannelType> channels = execute(request.getConnection(), new ManagerCallback<List<BaseChannelType>>() {
			public List<BaseChannelType> callManager(MqQmanagerManager manager) throws MqException {
				return SchemaConvertionUtils.toXmlBaseChannelList(manager.getAllChannels());
			}
		});
    	GetAllBaseChannelsOut response = SchemaConvertionUtils.objectMessageFactory.createGetAllBaseChannelsOut();
    	BaseChannelArrayType channelArray = SchemaConvertionUtils.objectTypeFactory.createBaseChannelArrayType();
    	response.setChannels(channelArray);
    	channelArray.getChannel().addAll(channels);
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#getAllChannels(com.netflexity.schema.software.nfx_wmq.messages._1.GetAllChannels)
	 */
	@Override
	public GetAllChannelsOut getAllChannels(GetAllChannels request) throws WMQFaultMessage {
		List<ChannelType> channels = execute(request.getConnection(), new ManagerCallback<List<ChannelType>>() {
			public List<ChannelType> callManager(MqQmanagerManager manager) throws MqException {
				return SchemaConvertionUtils.toXmlChannelList(manager.getAllChannels());
			}
		});
    	GetAllChannelsOut response = SchemaConvertionUtils.objectMessageFactory.createGetAllChannelsOut();
    	ChannelArrayType channelArray = SchemaConvertionUtils.objectTypeFactory.createChannelArrayType();
    	response.setChannels(channelArray);
    	channelArray.getChannel().addAll(channels);
    	return response;
	}

	/**
	 * @param request
	 * @return
	 * @throws WMQFaultMessage
	 */
	public GetAllBaseQueuesOut getAllQueues(GetAllBaseQueues request) throws WMQFaultMessage {
		List<MqQueue> queues = execute(request.getConnection(), new ManagerCallback<List<MqQueue>>() {
				public List<MqQueue> callManager(MqQmanagerManager manager) throws MqException {
					return manager.getAllQueues();
				}
		});
    	GetAllBaseQueuesOut response = SchemaConvertionUtils.objectMessageFactory.createGetAllBaseQueuesOut();
    	BaseQueueArrayType queueArray = SchemaConvertionUtils.objectTypeFactory.createBaseQueueArrayType();
    	response.setQueues(queueArray);
    	for (MqQueue queue : queues) {
        	queueArray.getQueue().add(SchemaConvertionUtils.toBaseXmlType(queue));
		}
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#getAllQueues(com.netflexity.schema.software.nfx_wmq.messages._1.GetAllQueues)
	 */
	@Override
	public GetAllQueuesOut getAllQueues(GetAllQueues request) throws WMQFaultMessage {
		List<MqQueue> queues = execute(request.getConnection(), new ManagerCallback<List<MqQueue>>() {
			public List<MqQueue> callManager(MqQmanagerManager manager) throws MqException {
				return manager.getAllQueues();
			}
		});
		GetAllQueuesOut response = SchemaConvertionUtils.objectMessageFactory.createGetAllQueuesOut();
		QueueArrayType queueArray = SchemaConvertionUtils.objectTypeFactory.createQueueArrayType();
		response.setQueues(queueArray);
		for (MqQueue queue : queues) {
	    	queueArray.getQueue().add(SchemaConvertionUtils.toXmlType(queue));
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#getAllTopics(com.netflexity.schema.software.nfx_wmq.messages._1.GetAllTopics)
	 */
	@Override
	public GetAllTopicsOut getAllTopics(GetAllTopics request) throws WMQFaultMessage {
		List<MqTopic> topics = execute(request.getConnection(), new ManagerCallback<List<MqTopic>>() {
			public List<MqTopic> callManager(MqQmanagerManager manager) throws MqException {
				return manager.getAllTopics();
			}
		});
		GetAllTopicsOut response = SchemaConvertionUtils.objectMessageFactory.createGetAllTopicsOut();
		TopicArrayType topicArray = SchemaConvertionUtils.objectTypeFactory.createTopicArrayType();
		response.setTopics(topicArray);
		for (MqTopic topic : topics) {
	    	topicArray.getTopic().add(SchemaConvertionUtils.toXmlType(topic));
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#getAllMessages(com.netflexity.schema.software.nfx_wmq.messages._1.GetAllMessages)
	 */
	@Override
	public GetAllMessagesOut getAllMessages(final GetAllMessages request) throws WMQFaultMessage {
		List<MqMessage> messages = execute(request.getConnection(), 
			new ManagerCallback<List<MqMessage>>() {
				public List<MqMessage> callManager(MqQmanagerManager manager) throws MqException {
					return manager.getAllMessages(request.getQueueName());
				}
		});
    	GetAllMessagesOut response = SchemaConvertionUtils.objectMessageFactory.createGetAllMessagesOut();
        response.setMessages(SchemaConvertionUtils.toXmlMessageList(messages));
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#getChannel(com.netflexity.schema.software.nfx_wmq.messages._1.GetChannel)
	 */
	@Override
	public GetChannelOut getChannel(final GetChannel request) throws WMQFaultMessage {
		MqChannel channel = execute(request.getConnection(), new ManagerCallback<MqChannel>() {
			public MqChannel callManager(MqQmanagerManager manager) throws MqException {
				return manager.getChannel(request.getChannelName());
			}
		});
    	GetChannelOut response = SchemaConvertionUtils.objectMessageFactory.createGetChannelOut();
    	response.setChannel(SchemaConvertionUtils.toXmlType(channel));
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#getQmanager(com.netflexity.schema.software.nfx_wmq.messages._1.GetQmanager)
	 */
	@Override
	public GetQmanagerOut getQmanager(GetQmanager request) throws WMQFaultMessage {
		MqQmanager qmanager = execute(request.getConnection(), new ManagerCallback<MqQmanager>() {
			public MqQmanager callManager(MqQmanagerManager manager) throws MqException {
				return manager.getQmanager();
			}
		});
    	GetQmanagerOut response = SchemaConvertionUtils.objectMessageFactory.createGetQmanagerOut();
    	response.setQmanager(SchemaConvertionUtils.toXmlType(qmanager));
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#getQueue(com.netflexity.schema.software.nfx_wmq.messages._1.GetQueue)
	 */
	@Override
	public GetQueueOut getQueue(final GetQueue request) throws WMQFaultMessage {
		MqQueue queue = execute(request.getConnection(), new ManagerCallback<MqQueue>() {
			public MqQueue callManager(MqQmanagerManager manager) throws MqException {
				return manager.getQueue(request.getQueueName());
			}
		});
    	GetQueueOut response = SchemaConvertionUtils.objectMessageFactory.createGetQueueOut();
    	response.setQueue(SchemaConvertionUtils.toXmlType(queue));
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#getQueueStats(com.netflexity.schema.software.nfx_wmq.messages._1.GetQueueStats)
	 */
	@Override
	public GetQueueStatsOut getQueueStats(GetQueueStats request) throws WMQFaultMessage {
    	final String excludeQueueNames [] = SchemaConvertionUtils.toDomainType(request.getExcludeQueueNames());
    	final String includeQueueNames [] = SchemaConvertionUtils.toDomainType(request.getIncludeQueueNames());
    	List<MqQueueStat> queueStats = execute(request.getConnection(), new ManagerCallback<List<MqQueueStat>>() {
					public List<MqQueueStat> callManager(MqQmanagerManager manager) throws MqException {
						return manager.getQueueStats(excludeQueueNames, includeQueueNames);
					}
				});
    	GetQueueStatsOut response = SchemaConvertionUtils.objectMessageFactory.createGetQueueStatsOut();
    	QueueStatArrayType queueStatsArray = SchemaConvertionUtils.objectTypeFactory.createQueueStatArrayType();
    	response.setQueueStats(queueStatsArray);
    	queueStatsArray.getQueueStat().addAll(SchemaConvertionUtils.toXmlQueueStatsList(queueStats));
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#isCommandServerUp(com.netflexity.schema.software.nfx_wmq.messages._1.IsCommandServerUp)
	 */
	@Override
	public IsCommandServerUpOut isCommandServerUp(IsCommandServerUp request) throws WMQFaultMessage {
		Boolean isServerUp = execute(request.getConnection(), new ManagerCallback<Boolean>() {
			public Boolean callManager(MqQmanagerManager manager) throws MqException {
				return manager.isCommandServerOpen();
			}
		});
    	IsCommandServerUpOut response = SchemaConvertionUtils.objectMessageFactory.createIsCommandServerUpOut();
    	response.setResult(isServerUp);
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#isQmanagerUp(com.netflexity.schema.software.nfx_wmq.messages._1.IsQmanagerUp)
	 */
	@Override
	public IsQmanagerUpOut isQmanagerUp(IsQmanagerUp request) throws WMQFaultMessage {
    	Boolean isServerUp = execute(request.getConnection(), new ManagerCallback<Boolean>() {
			public Boolean callManager(MqQmanagerManager manager) throws MqException {
				return manager.isOpen();
			}
		});
    	IsQmanagerUpOut response = SchemaConvertionUtils.objectMessageFactory.createIsQmanagerUpOut();
    	response.setResult(isServerUp);
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#removeChannel(com.netflexity.schema.software.nfx_wmq.messages._1.RemoveChannel)
	 */
	@Override
	public RemoveChannelOut removeChannel(final RemoveChannel request) throws WMQFaultMessage {
		RemoveChannelOut response = SchemaConvertionUtils.objectMessageFactory.createRemoveChannelOut();
		execute(request.getConnection(), new ManagerCallbackWithoutResult() {	
			public void callManager(MqQmanagerManager manager) throws MqException {
				manager.deleteChannel(request.getChannelName());
			}
		});
    	response.setResult(true);
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#removeQueue(com.netflexity.schema.software.nfx_wmq.messages._1.RemoveQueue)
	 */
	@Override
	public RemoveQueueOut removeQueue(final RemoveQueue request) throws WMQFaultMessage {
    	execute(request.getConnection(), new ManagerCallbackWithoutResult() {	
			public void callManager(MqQmanagerManager manager) throws MqException {
				manager.deleteQueue(request.getQueueName());
			}
		});
    	RemoveQueueOut response = SchemaConvertionUtils.objectMessageFactory.createRemoveQueueOut();
    	response.setResult(true);
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#setChannel(com.netflexity.schema.software.nfx_wmq.messages._1.SetChannel)
	 */
	@Override
	public SetChannelOut setChannel(final SetChannel request) throws WMQFaultMessage {
    	execute(request.getConnection(), new ManagerCallbackWithoutResult() {	
			public void callManager(MqQmanagerManager manager) throws MqException {
				manager.setChannel(SchemaConvertionUtils.toDomainType(request.getChannel()));
			}
		});
    	SetChannelOut response = SchemaConvertionUtils.objectMessageFactory.createSetChannelOut();
    	response.setChannel(request.getChannel());
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#setQmanager(com.netflexity.schema.software.nfx_wmq.messages._1.SetQmanager)
	 */
	@Override
	public SetQmanagerOut setQmanager(final SetQmanager request) throws WMQFaultMessage {
    	execute(request.getConnection(), new ManagerCallbackWithoutResult() {	
			public void callManager(MqQmanagerManager manager) throws MqException {
				manager.setQmanager(SchemaConvertionUtils.toDomainType(request.getQmanager()));
			}
		});
    	SetQmanagerOut response = SchemaConvertionUtils.objectMessageFactory.createSetQmanagerOut();
    	response.setQmanager(request.getQmanager());
    	return response;
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.software.service.wmq._1_0.WMQ#setQueue(com.netflexity.schema.software.nfx_wmq.messages._1.SetQueue)
	 */
	@Override
	public SetQueueOut setQueue(final SetQueue request) throws WMQFaultMessage {
    	execute(request.getConnection(), new ManagerCallbackWithoutResult() {	
			public void callManager(MqQmanagerManager manager) throws MqException {
				manager.setQueue(SchemaConvertionUtils.toDomainType(request.getQueue()));
			}
		});
    	SetQueueOut response = SchemaConvertionUtils.objectMessageFactory.createSetQueueOut();
    	response.setQueue(request.getQueue());
    	return response;
	}
	
	/**
	 * @param connection
	 * @param callback
	 * @return
	 * @throws WMQFaultMessage
	 */
	private <T>T execute(ConnectionType connection, ManagerCallback<T> callback) throws WMQFaultMessage{
    	MqQmanagerManagerContext context = SchemaConvertionUtils.toMqQmanagerManagerContext(pool, connection);
    	MqQmanagerManager manager = null;
		try {
			manager = IBMMqQmanagerManagerFactory.getFactory().createMqQmanagerManager(context);
			if(manager == null){
	    		throw createServiceException(WMQServiceConstants.FAILED_TO_CONNECT_ERROR, new Object[]{connection.getQmanagerName()});
	    	}
    		return callback.callManager(manager);
		} 
		catch (MqException e) {
			throw createWMQFaultMessage(e);
		}
    	finally{
    		if(manager != null){
    			try {
					manager.close();
				} 
    			catch (MqException e) {
					logger.warn(e.getMessage(), e);
				}
    		}
    	}
	}
	
	/**
	 * @param connection
	 * @param callback
	 * @throws WMQFaultMessage
	 */
	private void execute(ConnectionType connection, ManagerCallbackWithoutResult callback) throws WMQFaultMessage{
    	MqQmanagerManagerContext context = SchemaConvertionUtils.toMqQmanagerManagerContext(pool, connection);
    	MqQmanagerManager manager = null;
		try {
			manager = IBMMqQmanagerManagerFactory.getFactory().createMqQmanagerManager(context);
			if(manager == null){
	    		throw createServiceException(WMQServiceConstants.FAILED_TO_CONNECT_ERROR, new Object[]{connection.getQmanagerName()});
	    	}
    		callback.callManager(manager);
		} 
		catch (MqException e) {
			throw createWMQFaultMessage(e);
		}
    	finally{
    		if(manager != null){
    			try {
					manager.close();
				} 
    			catch (MqException e) {
					logger.warn(e.getMessage(), e);
				}
    		}
    	}
	}
    
	/**
	 * @param e
	 * @throws WMQFaultMessage
	 */
	private WMQFaultMessage createWMQFaultMessage(MqException e) {
		logger.error(e.getMessage(), e);
		WMQFault fault = new WMQFault();
		fault.setDescription(e.getMessage());
		fault.setReason(e.getLocalizedMessage());
		fault.setReasonCode(String.valueOf(e.getErrorCode()));
		WMQFaultMessage faultMessage = new WMQFaultMessage(e.getMessage(), fault, e);
		return faultMessage;
		//throw new RuntimeException(e.getMessage(), e);
	}
	
    /**
     * @author mfedorov
     *
     * @param <T>
     */
    private interface ManagerCallback <T> {
    	/**
    	 * @param manager
    	 * @return
    	 * @throws MqException
    	 */
    	T callManager(MqQmanagerManager manager) throws MqException;
    }

    /**
     * @author mfedorov
     *
     */
    private interface ManagerCallbackWithoutResult {
    	/**
    	 * @param manager
    	 * @throws MqException
    	 */
    	void callManager(MqQmanagerManager manager) throws MqException;
    }
    
    /**
     * @param errorCode
     * @param args
     * @return
     */
    protected WMQFaultMessage createServiceException(int errorCode, Object[] args) {
    	MqException e = new MqException(serviceErrorsMessageSource.getMessage(String.valueOf(errorCode), args, Locale.getDefault()));
    	return createWMQFaultMessage(e);
    }

	/**
	 * @return
	 */
	public KeyedObjectPool getPool() {
		return pool;
	}

	/**
	 * @param pool
	 */
	public void setPool(KeyedObjectPool pool) {
		this.pool = pool;
	}

	/**
	 * @return
	 */
	public ResourceBundleMessageSource getServiceErrorsMessageSource() {
		return serviceErrorsMessageSource;
	}

	/**
	 * @param serviceErrorsMessageSource
	 */
	public void setServiceErrorsMessageSource(ResourceBundleMessageSource serviceErrorsMessageSource) {
		this.serviceErrorsMessageSource = serviceErrorsMessageSource;
	}
}
