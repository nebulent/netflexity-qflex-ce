/*
 *  2008 Netflexity, Ltd. All Rights Reserved.
 *
 * CONFIDENTIAL BUSINESS INFORMATION
 *
 * THIS PROGRAM IS PROPRIETARY INFORMATION OF NETFLEXITY, LTD. AND
 * IS NOT TO BE COPIED, REPRODUCED, LENT, OR DISPOSED OF, NOR USED FOR ANY
 * OTHER PURPOSE, UNLESS THE WRITTEN PERMISSION FROM THE STATED ABOVE CORPORATION
 * IS GIVEN.
 */
package com.netflexity.ws.wmq;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool.KeyedObjectPool;
import org.netflexity.api.mq.MqChannel;
import org.netflexity.api.mq.MqMessage;
import org.netflexity.api.mq.MqMessageHeader;
import org.netflexity.api.mq.MqQmanager;
import org.netflexity.api.mq.MqQmanagerManagerContext;
import org.netflexity.api.mq.MqQueue;
import org.netflexity.api.mq.MqQueueStat;
import org.netflexity.api.mq.MqTopic;
import org.netflexity.api.mq.ibm.IBMMqChannel;
import org.netflexity.api.mq.ibm.IBMMqMessage;
import org.netflexity.api.mq.ibm.IBMMqMessageHeader;
import org.netflexity.api.mq.ibm.IBMMqQmanager;
import org.netflexity.api.mq.ibm.IBMMqQueue;
import org.netflexity.api.mq.ibm.IBMMqQueueStat;
import org.netflexity.api.mq.ibm.IBMMqTopic;
import org.netflexity.api.mq.ibm.enums.MqChannelAttributeEnum;
import org.netflexity.api.mq.ibm.enums.MqMessageHeaderCOAReportOptionTypeEnum;
import org.netflexity.api.mq.ibm.enums.MqMessageHeaderCODReportOptionTypeEnum;
import org.netflexity.api.mq.ibm.enums.MqMessageHeaderExceptionReportOptionTypeEnum;
import org.netflexity.api.mq.ibm.enums.MqMessageHeaderExpirationReportOptionTypeEnum;
import org.netflexity.api.mq.ibm.enums.MqMessageHeaderMessageTypeEnum;
import org.netflexity.api.mq.ibm.enums.MqMessageHeaderPersistenceTypeEnum;
import org.netflexity.api.mq.ibm.enums.MqMessageHeaderSegmentTypeEnum;
import org.netflexity.api.mq.ibm.enums.MqQmanagerAttributeEnum;
import org.netflexity.api.mq.ibm.enums.MqQueueAttributeEnum;
import org.netflexity.api.mq.ibm.enums.MqTopicAttributeEnum;
import org.netflexity.api.mq.ibm.enums.WebsphereMQVersionEnum;
import org.netflexity.api.mq.ibm.enums.WebspherePlatformEnum;
import org.netflexity.api.mq.ibm.enums.WebsphereSSLCipherSpecEnum;

import com.netflexity.schema.software.nfx_wmq.types._1.BaseChannelType;
import com.netflexity.schema.software.nfx_wmq.types._1.BaseQmanagerType;
import com.netflexity.schema.software.nfx_wmq.types._1.BaseQueueType;
import com.netflexity.schema.software.nfx_wmq.types._1.ChannelType;
import com.netflexity.schema.software.nfx_wmq.types._1.ConnectionType;
import com.netflexity.schema.software.nfx_wmq.types._1.MqMessageArrayType;
import com.netflexity.schema.software.nfx_wmq.types._1.MqMessageHeaderType;
import com.netflexity.schema.software.nfx_wmq.types._1.MqMessageType;
import com.netflexity.schema.software.nfx_wmq.types._1.QmanagerType;
import com.netflexity.schema.software.nfx_wmq.types._1.QueueStatType;
import com.netflexity.schema.software.nfx_wmq.types._1.QueueType;
import com.netflexity.schema.software.nfx_wmq.types._1.SegmentTypeEnum;
import com.netflexity.schema.software.nfx_wmq.types._1.SslType;
import com.netflexity.schema.software.nfx_wmq.types._1.StringArrayType;
import com.netflexity.schema.software.nfx_wmq.types._1.TopicType;

/**
 * @author MAX
 *
 */
public final class SchemaConvertionUtils {

    public static com.netflexity.schema.software.nfx_wmq.types._1.ObjectFactory objectTypeFactory = new com.netflexity.schema.software.nfx_wmq.types._1.ObjectFactory();
    public static com.netflexity.schema.software.nfx_wmq.messages._1.ObjectFactory objectMessageFactory = new com.netflexity.schema.software.nfx_wmq.messages._1.ObjectFactory();
    
    public static DateFormat ibmFullDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
    public static DateFormat ibmDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat ibmTimeFormat = new SimpleDateFormat("HH.mm.ss");
    
    /**
     * @param dateAsString
     * @return
     */
    public static synchronized Calendar toCalendar(String dateAsString){
    	if(StringUtils.isNotBlank(dateAsString)){
    		try {
				Date date = ibmFullDateTimeFormat.parse(dateAsString);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				return cal;
			} catch (ParseException e) {
				
			}
    	}
    	return null;
    }
    
    /**
     * @param cal
     * @return
     */
    public static synchronized String toDateString(Calendar cal){
    	if(cal != null){
    		return ibmDateFormat.format(cal.getTime());
    	}
    	return null;
    }
    
    /**
     * @param cal
     * @return
     */
    public static synchronized String toTimeString(Calendar cal){
    	if(cal != null){
    		return ibmTimeFormat.format(cal.getTime());
    	}
    	return null;
    }
    
    /*
    public static XMLGregorianCalendar toXMLGregorianCalendar(DateTime dateTime) throws DatatypeConfigurationException {
        DatatypeFactory factory = DatatypeFactory.newInstance();
        return factory.newXMLGregorianCalendar(dateTime.toGregorianCalendar());
    }

    public static DateTime toDateTime(XMLGregorianCalendar calendar) {
        int timeZoneMinutes = calendar.getTimezone();
        DateTimeZone timeZone = DateTimeZone.forOffsetHoursMinutes(timeZoneMinutes / 60, timeZoneMinutes % 60);
        return new DateTime(calendar.getYear(), calendar.getMonth(), calendar.getDay(), calendar.getHour(),
                calendar.getMinute(), calendar.getSecond(), calendar.getMillisecond(), timeZone);
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(LocalDate localDate)
            throws DatatypeConfigurationException {
        DatatypeFactory factory = DatatypeFactory.newInstance();
        return factory.newXMLGregorianCalendarDate(localDate.getYear(), localDate.getMonthOfYear(),
                localDate.getDayOfMonth(), DatatypeConstants.FIELD_UNDEFINED);
    }

    public static LocalDate toLocalDate(XMLGregorianCalendar calendar) {
        return new LocalDate(calendar.getYear(), calendar.getMonth(), calendar.getDay());
    }
    */
    
    /**
     * @param millis
     * @return
     */
    public static Calendar toCalendar(Long millis){
    	if(millis != null && millis > 0){
    		return toCalendar(new Date(millis));
    	}
    	return null;
    }
    
    /**
     * @param date
     * @return
     */
    public static Calendar toCalendar(Date date){
    	if(date != null){
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(date);
    		return cal;
    	}
    	return null;
    }
    
    /**
     * @param date
     * @return
     */
    public static Date toDate(Calendar cal){
    	if(cal != null){
    		return cal.getTime();
    	}
    	return null;
    }
    
    /**
     * @param value
     * @return
     */
    public static BigInteger toBigInteger(long value){
        return new BigInteger(String.valueOf(value));
    }

    public static BigInteger toBigInteger(Integer integer) {
        if (integer == null) return null;
        return new BigInteger(integer.toString());
    }

    public static Integer toInteger(BigInteger integer) {
        if (integer == null) return null;
        //TODO: no check for overflow
        return new Integer(integer.intValue());
    }

    public static Long toLong(BigInteger integer) {
        if (integer == null) return null;
        //TODO: no check for overflow
        return integer.longValue();
    }

    /**
     * @param pool
     * @param connection
     * @return
     */
    public static MqQmanagerManagerContext toMqQmanagerManagerContext(KeyedObjectPool pool, ConnectionType connection){
    	MqQmanagerManagerContext context = new MqQmanagerManagerContext(//pool, 
    																	connection.getQmanagerName(), 
    																	connection.getHost(), 
    																	connection.getPort(), 
    																	connection.getChannelName(), 
    																	connection.getCommandServerQueueName(), 
    																	connection.getCommandServerReplyQueueName(), 
    																	WebspherePlatformEnum.getEnum(connection.getOs()), 
    																	WebsphereMQVersionEnum.getEnum(connection.getWmqVersion()));
		SslType ssl = connection.getSsl();
		if(ssl != null) {
			context.setSslKeyStore(ssl.getKeystore());
			context.setSslKeyStorePassword(ssl.getKeystorePassword());
			context.setSslTrustStore(ssl.getTruststore());
			context.setSslTrustStorePassword(ssl.getTruststorePassword());
			context.setSslCipherSpec(WebsphereSSLCipherSpecEnum.getEnum(ssl.getCipherSuite()));
			context.setSslPeerName(ssl.getPeerName());
		}
    	return context;
    }
    
    /**
     * @param StringArrayType
     * @return
     */
    public static String[] toDomainType(StringArrayType stringArrayType){
        if(stringArrayType == null) return null;
        List<String> stringList = stringArrayType.getValue();
        if(stringList != null && !stringList.isEmpty()){
        	return stringList.toArray(new String[stringList.size()]);
        }
        return null;
    }
    
    /**
     * @param stringList
     * @return
     */
    public static StringArrayType toXmlType(List<String> stringList) {
        if(stringList == null) return null;
        StringArrayType stringArray = objectTypeFactory.createStringArrayType();
        stringArray.getValue().addAll(stringList);
        return stringArray;
    }
    
    /**
     * @param queueStatType
     * @return
     */
    public static IBMMqQueueStat toDomainType(QueueStatType queueStatType){
        if(queueStatType == null) return null;
        IBMMqQueueStat queueStat = new IBMMqQueueStat();
        queueStat.setHighDepth(queueStatType.getHighDepth());
        queueStat.setMsgDeqCount(queueStatType.getMsgDeqCount());
        queueStat.setMsgEnqCount(queueStatType.getMsgEnqCount());
        queueStat.setQueueName(queueStatType.getQueueName());
        queueStat.setTimeSinceReset(new Long(queueStatType.getTimeSinceReset()));
        return queueStat;
    }
    
    /**
     * @param queueStat
     * @return
     */
    public static QueueStatType toXmlType(MqQueueStat queueStat){
        if(queueStat == null) return null;
        QueueStatType queueStatType = objectTypeFactory.createQueueStatType();
        queueStatType.setHighDepth(queueStat.getHighDepth());
        queueStatType.setMsgDeqCount(queueStat.getMsgDeqCount());
        queueStatType.setMsgEnqCount(queueStat.getMsgEnqCount());
        queueStatType.setQueueName(queueStat.getQueueName());
        queueStatType.setTimeSinceReset(queueStat.getTimeSinceReset().intValue());
        return queueStatType;
    }

    /**
     * @param queue
     * @return
     */
    public static QueueType toXmlType(MqQueue queue){
        if(queue == null) return null;
        QueueType queueType = objectTypeFactory.createQueueType();
        queueType.setAlterationDate(toCalendar(queue.getAlterationDate() + " " + queue.getAlterationTime()));
        queueType.setBackoutRequeueName(queue.getBackoutRequeueName());
        queueType.setBackoutThreshold(queue.getBackoutThreshold());
        queueType.setBaseQueueName(queue.getBaseQueueName());
        queueType.setClusterName(queue.getClusterName());
        queueType.setClusterNamelist(queue.getClusterNamelist());
        queueType.setCurrentDepth(queue.getCurrentQueueDepth());
        queueType.setDefBind(queue.getDefBind());
        queueType.setDefInputOpenOption(queue.getDefInputOpenOption());
        queueType.setDefPersistence(queue.getDefPersistence());
        queueType.setDefPriority(queue.getDefPriority());
        queueType.setDefType(queue.getDefType());
        queueType.setDistLists(queue.getDistLists());
        queueType.setHardenGetBackout(queue.getHardenGetBackout());
        queueType.setInhibitGet(queue.getInhibitGet());
        queueType.setInhibitPut(queue.getInhibitPut());
        queueType.setInitiationQueueName(queue.getInitiationQueueName());
        queueType.setMaxMsgLength(queue.getMaxMsgLength());
        queueType.setMaxDepth(queue.getMaxQueueDepth());
        queueType.setMsgDeliverySequence(queue.getMsgDeliverySequence());
        queueType.setOpenInputCount(queue.getOpenInputCount());
        queueType.setOpenOutputCount(queue.getOpenOutputCount());
        queueType.setProcessName(queue.getProcessName());
        queueType.setDepthHighEvent(queue.getQueueDepthHighEvent());
        queueType.setDepthHighLimit(queue.getQueueDepthHighLimit());
        queueType.setDepthLowEvent(queue.getQueueDepthLowEvent());
        queueType.setDepthLowLimit(queue.getQueueDepthLowLimit());
        queueType.setDepthMaxEvent(queue.getQueueDepthMaxEvent());
        queueType.setDescription(queue.getQueueDesc());
        queueType.setName(queue.getQueueName());
        queueType.setServiceInterval(queue.getQueueServiceInterval());
        queueType.setServiceIntervalEvent(queue.getQueueServiceIntervalEvent());
        queueType.setType(queue.getQueueType());
        queueType.setRetentionInterval(queue.getRetentionInterval());
        queueType.setScope(queue.getScope());
        queueType.setShareability(queue.getShareability());
        queueType.setTriggerControl(queue.getTriggerControl());
        queueType.setTriggerData(queue.getTriggerData());
        queueType.setTriggerDepth(queue.getTriggerDepth());
        queueType.setTriggerMsgPriority(queue.getTriggerMsgPriority());
        queueType.setTriggerType(queue.getTriggerType());
        queueType.setUsage(queue.getUsage());
        
        return queueType;
    }

    /**
     * @param queue
     * @return
     */
    public static BaseQueueType toBaseXmlType(MqQueue queue){
        if(queue == null) return null;
        BaseQueueType queueType = objectTypeFactory.createBaseQueueType();
        queueType.setCurrentDepth(queue.getCurrentQueueDepth());
        queueType.setDefType(queue.getDefType());
        queueType.setInhibitGet(queue.getInhibitGet());
        queueType.setInhibitPut(queue.getInhibitPut());
        queueType.setMaxMsgLength(queue.getMaxMsgLength());
        queueType.setMaxDepth(queue.getMaxQueueDepth());
        queueType.setOpenInputCount(queue.getOpenInputCount());
        queueType.setOpenOutputCount(queue.getOpenOutputCount());
        queueType.setDescription(queue.getQueueDesc());
        queueType.setName(queue.getQueueName());
        queueType.setType(queue.getQueueType());
        queueType.setUsage(queue.getUsage());
        
        return queueType;
    }

    /**
     * @param queueType
     * @return
     */
    public static MqQueue toDomainType(QueueType queueType){
        if(queueType == null) return null;
        IBMMqQueue queue = new IBMMqQueue();
//        queueType.setAlterationDate(channel.getAlterationDate());
        queue.addAttribute(MqChannelAttributeEnum.ALTERATION_DATE, toDateString(queueType.getAlterationDate()));
//        queueType.setAlterationTime(channel.getAlterationTime());
        queue.addAttribute(MqChannelAttributeEnum.ALTERATION_TIME, toTimeString(queueType.getAlterationDate()));
//        queueType.setBackoutRequeueName(queue.getBackoutRequeueName());
        queue.addAttribute(MqQueueAttributeEnum.BACKOUT_REQ_Q_NAME, queueType.getBackoutRequeueName());
//        queueType.setBackoutThreshold(integerToBigInteger(queue.getBackoutThreshold()));
        queue.addAttribute(MqQueueAttributeEnum.BACKOUT_THRESHOLD, queueType.getBackoutThreshold());
//        queueType.setBaseQueueName(queue.getBaseQueueName());
        queue.addAttribute(MqQueueAttributeEnum.BASE_Q_NAME, queueType.getBaseQueueName());
//        queueType.setClusterName(queue.getClusterName());
        queue.addAttribute(MqQueueAttributeEnum.CLUSTER_NAME, queueType.getClusterName());
//        queueType.setClusterNamelist(queue.getClusterNamelist());
        queue.addAttribute(MqQueueAttributeEnum.CLUSTER_NAMELIST, queueType.getClusterNamelist());
//        queueType.setCurrentQueueDepth(integerToBigInteger(queue.getCurrentQueueDepth()));
        queue.addAttribute(MqQueueAttributeEnum.CURRENT_Q_DEPTH, queueType.getCurrentDepth());
//        queueType.setDefBind(queue.getDefBind());
        queue.addAttribute(MqQueueAttributeEnum.DEF_BIND, queueType.getDefBind());
//        queueType.setDefInputOpenOption(integerToBigInteger(queue.getDefInputOpenOption()));
        queue.addAttribute(MqQueueAttributeEnum.DEF_INPUT_OPEN_OPTION, queueType.getDefInputOpenOption());
//        queueType.setDefPersistence(integerToBigInteger(queue.getDefPersistence()));
        queue.addAttribute(MqQueueAttributeEnum.DEF_PERSISTENCE, queueType.getDefPersistence());
//        queueType.setDefPriority(integerToBigInteger(queue.getDefPriority()));
        queue.addAttribute(MqQueueAttributeEnum.DEF_PRIORITY, queueType.getDefPriority());
//        queueType.setDefType(integerToBigInteger(queue.getDefType()));
        queue.addAttribute(MqQueueAttributeEnum.DEFINITION_TYPE, queueType.getDefType());
//        queueType.setDistLists(integerToBigInteger(queue.getDistLists()));
        queue.addAttribute(MqQueueAttributeEnum.DIST_LISTS, queueType.getDistLists());
//        queueType.setHardenGetBackout(integerToBigInteger(queue.getHardenGetBackout()));
        queue.addAttribute(MqQueueAttributeEnum.HARDEN_GET_BACKOUT, queueType.getHardenGetBackout());
//        queueType.setInhibitGet(integerToBigInteger(queue.getInhibitGet()));
        queue.addAttribute(MqQueueAttributeEnum.INHIBIT_GET, queueType.getInhibitGet());
//        queueType.setInhibitPut(integerToBigInteger(queue.getInhibitPut()));
        queue.addAttribute(MqQueueAttributeEnum.INHIBIT_PUT, queueType.getInhibitPut());
//        queueType.setInitiationQueueName(queue.getInitiationQueueName());
        queue.addAttribute(MqQueueAttributeEnum.INITIATION_Q_NAME, queueType.getInitiationQueueName());
//        queueType.setMaxMsgLength(integerToBigInteger(queue.getMaxMsgLength()));
        queue.addAttribute(MqQueueAttributeEnum.MAX_MSG_LENGTH, queueType.getMaxMsgLength());
//        queueType.setMaxQueueDepth(integerToBigInteger(queue.getMaxQueueDepth()));
        queue.addAttribute(MqQueueAttributeEnum.MAX_Q_DEPTH, queueType.getMaxDepth());
//        queueType.setMsgDeliverySequence(integerToBigInteger(queue.getMsgDeliverySequence()));
        queue.addAttribute(MqQueueAttributeEnum.MSG_DELIVERY_SEQUENCE, queueType.getMsgDeliverySequence());
//        queueType.setOpenInputCount(integerToBigInteger(queue.getOpenInputCount()));
        queue.addAttribute(MqQueueAttributeEnum.OPEN_INPUT_COUNT, queueType.getOpenInputCount());
//        queueType.setOpenOutputCount(integerToBigInteger(queue.getOpenOutputCount()));
        queue.addAttribute(MqQueueAttributeEnum.OPEN_OUTPUT_COUNT, queueType.getOpenOutputCount());
//        queueType.setProcessName(queue.getProcessName());
        queue.addAttribute(MqQueueAttributeEnum.PROCESS_NAME, queueType.getProcessName());
//        queueType.setQueueDepthHighEvent(integerToBigInteger(queue.getQueueDepthHighEvent()));
        queue.addAttribute(MqQueueAttributeEnum.Q_DEPTH_HIGH_EVENT, queueType.getDepthHighEvent());
//        queueType.setQueueDepthHighLimit(integerToBigInteger(queue.getQueueDepthHighLimit()));
        queue.addAttribute(MqQueueAttributeEnum.Q_DEPTH_HIGH_LIMIT, queueType.getDepthHighLimit());
//        queueType.setQueueDepthLowEvent(integerToBigInteger(queue.getQueueDepthLowEvent()));
        queue.addAttribute(MqQueueAttributeEnum.Q_DEPTH_LOW_EVENT, queueType.getDepthLowEvent());
//        queueType.setQueueDepthLowLimit(integerToBigInteger(queue.getQueueDepthLowLimit()));
        queue.addAttribute(MqQueueAttributeEnum.Q_DEPTH_LOW_LIMIT, queueType.getDepthLowLimit());
//        queueType.setQueueDepthMaxEvent(integerToBigInteger(queue.getQueueDepthMaxEvent()));
        queue.addAttribute(MqQueueAttributeEnum.Q_DEPTH_MAX_EVENT, queueType.getDepthMaxEvent());
//        queueType.setQueueDesc(queue.getQueueDesc());
        queue.addAttribute(MqQueueAttributeEnum.Q_DESC, queueType.getDescription());
//        queueType.setQueueName(queue.getQueueName());
        queue.addAttribute(MqQueueAttributeEnum.Q_NAME, queueType.getName());
//        queueType.setQueueServiceInterval(integerToBigInteger(queue.getQueueServiceInterval()));
        queue.addAttribute(MqQueueAttributeEnum.Q_SERVICE_INTERVAL, queueType.getServiceInterval());
//        queueType.setQueueServiceIntervalEvent(integerToBigInteger(queue.getQueueServiceIntervalEvent()));
        queue.addAttribute(MqQueueAttributeEnum.Q_SERVICE_INTERVAL_EVENT, queueType.getServiceIntervalEvent());
//        queueType.setQueueType(integerToBigInteger(queue.getQueueType()));
        queue.addAttribute(MqQueueAttributeEnum.Q_TYPE, queueType.getType());
//        queueType.setRetentionInterval(integerToBigInteger(queue.getRetentionInterval()));
        queue.addAttribute(MqQueueAttributeEnum.RETENTION_INTERVAL, queueType.getRetentionInterval());
//        queueType.setScope(integerToBigInteger(queue.getScope()));
        queue.addAttribute(MqQueueAttributeEnum.SCOPE, queueType.getScope());
//        queueType.setShareability(integerToBigInteger(queue.getShareability()));
        queue.addAttribute(MqQueueAttributeEnum.SHAREABILITY, queueType.getShareability());
//        queueType.setTriggerControl(integerToBigInteger(queue.getTriggerControl()));
        queue.addAttribute(MqQueueAttributeEnum.TRIGGER_CONTROL, queueType.getTriggerControl());
//        queueType.setTriggerData(queue.getTriggerData());
        queue.addAttribute(MqQueueAttributeEnum.TRIGGER_DATA, queueType.getTriggerData());
//        queueType.setTriggerDepth(integerToBigInteger(queue.getTriggerDepth()));
        queue.addAttribute(MqQueueAttributeEnum.TRIGGER_DEPTH, queueType.getTriggerDepth());
//        queueType.setTriggerMsgPriority(integerToBigInteger(queue.getTriggerMsgPriority()));
        queue.addAttribute(MqQueueAttributeEnum.TRIGGER_MSG_PRIORITY, queueType.getTriggerMsgPriority());
//        queueType.setTriggerType(integerToBigInteger(queue.getTriggerType()));
        queue.addAttribute(MqQueueAttributeEnum.TRIGGER_TYPE, queueType.getTriggerType());
//        queueType.setUsage(integerToBigInteger(queue.getUsage()));
        queue.addAttribute(MqQueueAttributeEnum.USAGE, queueType.getUsage());
        
        return queue;
    }
    
    /**
     * @param topic
     * @return
     */
    public static TopicType toXmlType(MqTopic topic) {
    	if(topic == null) return null;
    	TopicType topicType = new TopicType();
    	
        /*
        topicType.setDefPersistence(topic.getDefPersistence());
        topicType.setDefPriority(topic.getDefPriority());
        topicType.setDefPutResponse(topic.getDefPutResponse());
        topicType.setInhibitPublications(topic.getInhibitPublications());
        topicType.setInhibitSubscriptions(topic.getInhibitSubscriptions());
        topicType.setAlterationDate(toCalendar(topic.get));
        */
        topicType.setDescription(topic.getTopicDesc());
        topicType.setName(topic.getTopicName());
        //topicType.setType(topic.getTopicType());
        //topicType.setStatus(topic.getTopicStatus());
		return topicType;
	}
    
    /**
     * @param topicType
     * @return
     */
    public static MqTopic toDomainType(TopicType topicType) {
    	if(topicType == null) return null;
    	IBMMqTopic topic = new IBMMqTopic();
    	
        /*
        topicType.setDefPersistence(topic.getDefPersistence());
        topicType.setDefPriority(topic.getDefPriority());
        topicType.setDefPutResponse(topic.getDefPutResponse());
        topicType.setInhibitPublications(topic.getInhibitPublications());
        topicType.setInhibitSubscriptions(topic.getInhibitSubscriptions());
        topicType.setAlterationDate(toCalendar(topic.get));
        */
        topic.addAttribute(MqTopicAttributeEnum.TOPIC_NAME, topicType.getName());
        topic.addAttribute(MqTopicAttributeEnum.TOPIC_DESC, topicType.getDescription());
        topic.addAttribute(MqTopicAttributeEnum.TOPIC_STATUS, topicType.getStatus());
        
        //topicType.setType(topic.getTopicType());
        //topicType.setStatus(topic.getTopicStatus());
		return topic;
	}
    
    /**
     * @param channel
     * @return
     */
    public static ChannelType toXmlType(MqChannel channel){
        if(channel == null) return null;
        ChannelType channelType = objectTypeFactory.createChannelType();
        channelType.setAlterationDate(toCalendar(channel.getAlterationDate() + " " + channel.getAlterationTime()));
        channelType.setBatchHeartbeat(channel.getBatchHeartbeat());
        channelType.setBatchInterval(channel.getBatchInterval());
        channelType.setBatchSize(channel.getBatchSize());
        channelType.setDescription(channel.getChannelDesc());
        channelType.setInstanceType(channel.getChannelInstanceType());
        channelType.setName(channel.getChannelName());
        channelType.setStartDate(toCalendar(channel.getChannelStartDate() + " " + channel.getChannelStartTime()));
        channelType.setStatus(channel.getChannelStatus());
        channelType.setType(channel.getChannelType().intValue());
        channelType.setClusterName(channel.getClusterName());
        channelType.setClusterNamelist(channel.getClusterNamelist());
        channelType.setConnectionCount(channel.getConnectionCount());
        channelType.setConnectionName(channel.getConnectionName());
        channelType.setDataConversion(channel.getDataConversion());
        channelType.setDiscInterval(channel.getDiscInterval());
        channelType.setHeartbeatInterval(channel.getHeartbeatInterval());
        channelType.setLocalAddress(channel.getLocalAddress());
        channelType.setLongRetryCount(channel.getLongRetryCount());
        channelType.setLongRetryInterval(channel.getLongRetryInterval());
        channelType.setMaxMsgLength(channel.getMaxMsgLength());
        channelType.setMcaName(channel.getMcaName());
        channelType.setMcaType(channel.getMcaType());
        channelType.setMcaUserIdentifier(channel.getMcaUserIdentifier());
        channelType.setModeName(channel.getModeName());
        channelType.setMsgExit(channel.getMsgExit());
        channelType.setMsgRetryCount(channel.getMsgRetryCount());
        channelType.setMsgRetryExit(channel.getMsgRetryExit());
        channelType.setMsgRetryInterval(channel.getMsgRetryInterval());
        channelType.setMsgRetryUserData(channel.getMsgRetryUserData());
        channelType.setMsgUserData(channel.getMsgUserData());
        channelType.setNetworkPriority(channel.getNetworkPriority());
        channelType.setNonPersistentMsgSpeed(channel.getNonPersistentMsgSpeed());
        channelType.setPassword(channel.getPassword());
        channelType.setQmanagerName(channel.getQmanagerName());
        channelType.setReceiveExit(channel.getReceiveExit());
        channelType.setReceiveUserData(channel.getReceiveUserData());
        channelType.setSecurityExit(channel.getSecurityExit());
        channelType.setSecurityUserData(channel.getSecurityUserData());
        channelType.setSendExit(channel.getSendExit());
        channelType.setSendUserData(channel.getSendUserData());
        channelType.setSeqNumberWrap(channel.getSeqNumberWrap());
        channelType.setShortRetryCount(channel.getShortRetryCount());
        channelType.setShortRetryInterval(channel.getShortRetryInterval());
        channelType.setSslCipherSpec(channel.getSslCipherSpec());
        channelType.setSslClientAuth(channel.getSslClientAuth());
        channelType.setSslPeerName(channel.getSslPeerName());
        channelType.setTpName(channel.getTpName());
        channelType.setTransportType(channel.getTransportType());
        channelType.setUserIdentifier(channel.getUserIdentifier());
        channelType.setXmitQueueName(channel.getXmitQueueName());
        return channelType;
    }

    /**
     * @param channelType
     * @return
     */
    public static MqChannel toDomainType(ChannelType channelType){
        if(channelType == null) return null;
        IBMMqChannel channel = new IBMMqChannel();
//        channelType.setAlterationDate(channel.getAlterationDate());
        channel.addAttribute(MqChannelAttributeEnum.ALTERATION_DATE, toDateString(channelType.getAlterationDate()));
//        channelType.setAlterationTime(channel.getAlterationTime());
        channel.addAttribute(MqChannelAttributeEnum.ALTERATION_TIME, toTimeString(channelType.getAlterationDate()));
//        channelType.setBatchHeartbeat(integerToBigInteger(channel.getBatchHeartbeat()));
        channel.addAttribute(MqChannelAttributeEnum.BATCH_HB,  
        		channelType.getBatchHeartbeat());
//        channelType.setBatchInterval(integerToBigInteger(channel.getBatchInterval()));
        channel.addAttribute(MqChannelAttributeEnum.BATCH_INTERVAL,  
        		channelType.getBatchInterval());
//        channelType.setBatchSize(integerToBigInteger(channel.getBatchSize()));
        channel.addAttribute(MqChannelAttributeEnum.BATCH_SIZE,  
        		channelType.getBatchSize());
//        channelType.setChannelDesc(channel.getChannelDesc());
        channel.addAttribute(MqChannelAttributeEnum.CHANNEL_DESC, channelType.getDescription());
//        channelType.setChannelInstanceType(integerToBigInteger(channel.getChannelInstanceType()));
        channel.addAttribute(MqChannelAttributeEnum.CHANNEL_INSTANCE_TYPE,  
        		channelType.getInstanceType());
//        channelType.setChannelName(channel.getChannelName());
        channel.addAttribute(MqChannelAttributeEnum.CHANNEL_NAME, channelType.getName());
//        channelType.setChannelStartDate(channel.getChannelStartDate());
        channel.addAttribute(MqChannelAttributeEnum.CHANNEL_START_DATE, channelType.getStartDate());
//        channelType.setChannelStartTime(channel.getChannelStartTime());
        channel.addAttribute(MqChannelAttributeEnum.CHANNEL_START_TIME, channelType.getStartTime());
//        channelType.setChannelStatus(integerToBigInteger(channel.getChannelStatus()));
        channel.addAttribute(MqChannelAttributeEnum.CHANNEL_STATUS, channelType.getStatus());
//        channelType.setChannelType(integerToBigInteger(channel.getChannelType()));
        channel.addAttribute(MqChannelAttributeEnum.CHANNEL_TYPE, channelType.getType());
//        channelType.setClusterName(channel.getClusterName());
        channel.addAttribute(MqChannelAttributeEnum.CLUSTER_NAME, channelType.getClusterName());
//        channelType.setClusterNamelist(channel.getClusterNamelist());
        channel.addAttribute(MqChannelAttributeEnum.CLUSTER_NAMELIST, channelType.getClusterNamelist());
//        channelType.setConnectionCount(integerToBigInteger(channel.getConnectionCount()));
        channel.setConnectionCount(channelType.getConnectionCount());
//        channelType.setConnectionName(channel.getConnectionName());
        channel.addAttribute(MqChannelAttributeEnum.CONNECTION_NAME, channelType.getConnectionName());
//        channelType.setDataConversion(integerToBigInteger(channel.getDataConversion()));
        channel.addAttribute(MqChannelAttributeEnum.DATA_CONVERSION, channelType.getDataConversion());
//        channelType.setDiscInterval(integerToBigInteger(channel.getDiscInterval()));
        channel.addAttribute(MqChannelAttributeEnum.DISC_INTERVAL, channelType.getDiscInterval());
//        channelType.setHeartbeatInterval(integerToBigInteger(channel.getHeartbeatInterval()));
        channel.addAttribute(MqChannelAttributeEnum.HB_INTERVAL, channelType.getHeartbeatInterval());
//        channelType.setLocalAddress(channel.getLocalAddress());
        channel.addAttribute(MqChannelAttributeEnum.LOCAL_ADDRESS, channelType.getLocalAddress());
//        channelType.setLongRetryCount(integerToBigInteger(channel.getLongRetryCount()));
        channel.addAttribute(MqChannelAttributeEnum.LONG_RETRY, channelType.getLongRetryCount());
//        channelType.setLongRetryInterval(integerToBigInteger(channel.getLongRetryInterval()));
        channel.addAttribute(MqChannelAttributeEnum.LONG_TIMER, channelType.getLongRetryInterval());
//        channelType.setMaxMsgLength(integerToBigInteger(channel.getMaxMsgLength()));
        channel.addAttribute(MqChannelAttributeEnum.MAX_MSG_LENGTH_4CHANNEL, channelType.getMaxMsgLength());
//        channelType.setMcaName(channel.getMcaName());
        channel.addAttribute(MqChannelAttributeEnum.MCA_NAME, channelType.getMcaName());
//        channelType.setMcaType(integerToBigInteger(channel.getMcaType()));
        channel.addAttribute(MqChannelAttributeEnum.MCA_TYPE, channelType.getMcaType());
//        channelType.setMcaUserIdentifier(channel.getMcaUserIdentifier());
        channel.addAttribute(MqChannelAttributeEnum.MCA_USER_ID, channelType.getMcaUserIdentifier());
//        channelType.setModeName(channel.getModeName());
        channel.addAttribute(MqChannelAttributeEnum.MODE_NAME, channelType.getModeName());
//        channelType.setMsgExit(channel.getMsgExit());
        channel.addAttribute(MqChannelAttributeEnum.MSG_EXIT_NAME, channelType.getMsgExit());
//        channelType.setMsgRetryCount(integerToBigInteger(channel.getMsgRetryCount()));
        channel.addAttribute(MqChannelAttributeEnum.MR_COUNT, channelType.getMsgRetryCount());
//        channelType.setMsgRetryExit(channel.getMsgRetryExit());
        channel.addAttribute(MqChannelAttributeEnum.MR_EXIT_NAME, channelType.getMsgRetryExit());
//        channelType.setMsgRetryInterval(integerToBigInteger(channel.getMsgRetryInterval()));
        channel.addAttribute(MqChannelAttributeEnum.MR_INTERVAL, channelType.getMsgRetryInterval());
//        channelType.setMsgRetryUserData(channel.getMsgRetryUserData());
        channel.addAttribute(MqChannelAttributeEnum.MR_EXIT_USER_DATA, channelType.getMsgRetryUserData());
//        channelType.setMsgUserData(channel.getMsgUserData());
        channel.addAttribute(MqChannelAttributeEnum.MSG_EXIT_USER_DATA, channelType.getMsgUserData());
//        channelType.setNetworkPriority(integerToBigInteger(channel.getNetworkPriority()));
        channel.addAttribute(MqChannelAttributeEnum.NETWORK_PRIORITY, channelType.getNetworkPriority());
//        channelType.setNonPersistentMsgSpeed(integerToBigInteger(channel.getNonPersistentMsgSpeed()));
        channel.addAttribute(MqChannelAttributeEnum.NPM_SPEED, channelType.getNonPersistentMsgSpeed());
//        channelType.setPassword(channel.getPassword());
        channel.addAttribute(MqChannelAttributeEnum.PASSWORD, channelType.getPassword());
//        channelType.setQmanagerName(channel.getQmanagerName());
        channel.addAttribute(MqChannelAttributeEnum.Q_MGR_NAME, channelType.getQmanagerName());
//        channelType.setReceiveExit(channel.getReceiveExit());
        channel.addAttribute(MqChannelAttributeEnum.RCV_EXIT_NAME, channelType.getReceiveExit());
//        channelType.setReceiveUserData(channel.getReceiveUserData());
        channel.addAttribute(MqChannelAttributeEnum.RCV_EXIT_USER_DATA, channelType.getReceiveUserData());
//        channelType.setSecurityExit(channel.getSecurityExit());
        channel.addAttribute(MqChannelAttributeEnum.SEC_EXIT_NAME, channelType.getSecurityExit());
//        channelType.setSecurityUserData(channel.getSecurityUserData());
        channel.addAttribute(MqChannelAttributeEnum.SEC_EXIT_USER_DATA, channelType.getSecurityUserData());
//        channelType.setSendExit(channel.getSendExit());
        channel.addAttribute(MqChannelAttributeEnum.SEND_EXIT_NAME, channelType.getSendExit());
//        channelType.setSendUserData(channel.getSendUserData());
        channel.addAttribute(MqChannelAttributeEnum.SEC_EXIT_USER_DATA, channelType.getSendUserData());
//        channelType.setSeqNumberWrap(integerToBigInteger(channel.getSeqNumberWrap()));
        channel.addAttribute(MqChannelAttributeEnum.SEQUENCE_NUMBER_WRAP,  
        		channelType.getSeqNumberWrap());
//        channelType.setShortRetryCount(integerToBigInteger(channel.getShortRetryCount()));
        channel.addAttribute(MqChannelAttributeEnum.SHORT_RETRY,  
        		channelType.getShortRetryCount());
//        channelType.setShortRetryInterval(integerToBigInteger(channel.getShortRetryInterval()));
        channel.addAttribute(MqChannelAttributeEnum.SHORT_TIMER,  
        		channelType.getShortRetryInterval());
//        channelType.setSslCipherSpec(channel.getSslCipherSpec());
        channel.addAttribute(MqChannelAttributeEnum.SSL_CIPHER_SPEC, channelType.getSslCipherSpec());
//        channelType.setSslClientAuth(integerToBigInteger(channel.getSslClientAuth()));
        channel.addAttribute(MqChannelAttributeEnum.SSL_CLIENT_AUTH,  
        		channelType.getSslClientAuth());
//        channelType.setSslPeerName(channel.getSslPeerName());
        channel.addAttribute(MqChannelAttributeEnum.SSL_PEER_NAME, channelType.getSslPeerName());
//        channelType.setTpName(channel.getTpName());
        channel.addAttribute(MqChannelAttributeEnum.TP_NAME, channelType.getTpName());
//        channelType.setTransportType(integerToBigInteger(channel.getTransportType()));
        channel.addAttribute(MqChannelAttributeEnum.XMIT_PROTOCOL_TYPE,  
        		channelType.getTransportType());
//        channelType.setUserIdentifier(channel.getUserIdentifier());
        channel.addAttribute(MqChannelAttributeEnum.USER_ID, channelType.getUserIdentifier());
//        channelType.setXmitQueueName(channel.getXmitQueueName());
        channel.addAttribute(MqChannelAttributeEnum.XMIT_Q_NAME_4CHANNEL, channelType.getXmitQueueName());
        return channel;
    }

    /**
     * @param qmanager
     * @return
     */
    public static QmanagerType toXmlType(MqQmanager qmanager){
        if(qmanager == null) return null;
        QmanagerType qmanagerType = objectTypeFactory.createQmanagerType();
        qmanagerType.setAlterationDate(toCalendar(qmanager.getAlterationDate() + " " + qmanager.getAlterationTime()));
        qmanagerType.setAuthorityEvent(qmanager.getAuthorityEvent());
        qmanagerType.setChannelAutoDef(qmanager.getChannelAutoDef());
        qmanagerType.setChannelAutoDefEvent(qmanager.getChannelAutoDefEvent());
        qmanagerType.setChannelAutoDefExit(qmanager.getChannelAutoDefExit());
        qmanagerType.setClusterWorkloadData(qmanager.getClusterWorkloadData());
        qmanagerType.setClusterWorkloadExit(qmanager.getClusterWorkloadExit());
        qmanagerType.setClusterWorkloadLength(qmanager.getClusterWorkloadLength());
        qmanagerType.setCodedCharSetId(qmanager.getCodedCharSetId());
        qmanagerType.setCommandInputQName(qmanager.getCommandInputQName());
        qmanagerType.setCommandLevel(qmanager.getCommandLevel());
        qmanagerType.setCreationDate(toCalendar(qmanager.getCreationDate() + " " + qmanager.getCreationTime()));
        qmanagerType.setDeadLetterQName(qmanager.getDeadLetterQName());
        qmanagerType.setDefXmitQName(qmanager.getDefXmitQName());
        qmanagerType.setDistLists(qmanager.getDistLists());
        qmanagerType.setInhibitEvent(qmanager.getInhibitEvent());
        qmanagerType.setLocalEvent(qmanager.getLocalEvent());
        qmanagerType.setMaxHandles(qmanager.getMaxHandles());
        qmanagerType.setMaxMsgLength(qmanager.getMaxMsgLength());
        qmanagerType.setMaxPriority(qmanager.getMaxPriority());
        qmanagerType.setMaxUncommittedMsgs(qmanager.getMaxUncommittedMsgs());
        qmanagerType.setPerformanceEvent(qmanager.getPerformanceEvent());
        qmanagerType.setPlatform(qmanager.getPlatform());
        qmanagerType.setDescription(qmanager.getQmanagerDesc());
        qmanagerType.setIdentifier(qmanager.getQmanagerIdentifier());
        qmanagerType.setName(qmanager.getQmanagerName());
        qmanagerType.setRemoteEvent(qmanager.getRemoteEvent());
        qmanagerType.setRepositoryName(qmanager.getRepositoryName());
        qmanagerType.setRepositoryNameList(qmanager.getRepositoryNameList());
        qmanagerType.setSslCertificateRepositoryList(qmanager.getSslCertificateRepositoryList());
        qmanagerType.setSslKeyRepository(qmanager.getSslKeyRepository());
        qmanagerType.setStartStopEvent(qmanager.getStartStopEvent());
        qmanagerType.setSyncPoint(qmanager.getSyncPoint());
        qmanagerType.setTriggerInterval(qmanager.getTriggerInterval());
        return qmanagerType;
    }
    
    public static MqQmanager toDomainType(QmanagerType qmanagerType){
        if(qmanagerType == null) return null;
        IBMMqQmanager qmanager = new IBMMqQmanager();
//        qmanagerType.setAlterationDate(qmanager.getAlterationDate());
        qmanager.addAttribute(MqQmanagerAttributeEnum.ALTERATION_DATE, toDateString(qmanagerType.getAlterationDate()));
//        qmanagerType.setAlterationTime(qmanager.getAlterationTime());
        qmanager.addAttribute(MqQmanagerAttributeEnum.ALTERATION_TIME, toTimeString(qmanagerType.getAlterationDate()));
//        qmanagerType.setAuthorityEvent(integerToBigInteger(qmanager.getAuthorityEvent()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.AUTHORITY_EVENT,  
        		(qmanagerType.getAuthorityEvent()));
//        qmanagerType.setChannelAutoDef(integerToBigInteger(qmanager.getChannelAutoDef()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.CHANNEL_AUTO_DEF,  
        		(qmanagerType.getChannelAutoDef()));
//        qmanagerType.setChannelAutoDefEvent(integerToBigInteger(qmanager.getChannelAutoDefEvent()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.CHANNEL_AUTO_DEF_EVENT,  
        		(qmanagerType.getChannelAutoDefEvent()));
//        qmanagerType.setChannelAutoDefExit(qmanager.getChannelAutoDefExit());
        qmanager.addAttribute(MqQmanagerAttributeEnum.CHANNEL_AUTO_DEF_EXIT, qmanagerType.getChannelAutoDefExit());
//        qmanagerType.setClusterWorkloadData(qmanager.getClusterWorkloadData());
        qmanager.addAttribute(MqQmanagerAttributeEnum.CLUSTER_WORKLOAD_DATA, qmanagerType.getClusterWorkloadData());
//        qmanagerType.setClusterWorkloadExit(qmanager.getClusterWorkloadExit());
        qmanager.addAttribute(MqQmanagerAttributeEnum.CLUSTER_WORKLOAD_EXIT, qmanagerType.getClusterWorkloadExit());
//        qmanagerType.setClusterWorkloadLength(integerToBigInteger(qmanager.getClusterWorkloadLength()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.CLUSTER_WORKLOAD_LENGTH,  
        		(qmanagerType.getClusterWorkloadLength()));
//        qmanagerType.setCodedCharSetId(integerToBigInteger(qmanager.getCodedCharSetId()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.CODED_CHAR_SET_ID,  
        		(qmanagerType.getCodedCharSetId()));
//        qmanagerType.setCommandInputQName(qmanager.getCommandInputQName());
        qmanager.addAttribute(MqQmanagerAttributeEnum.COMMAND_INPUT_Q_NAME, qmanagerType.getCommandInputQName());
//        qmanagerType.setCommandLevel(integerToBigInteger(qmanager.getCommandLevel()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.COMMAND_LEVEL,  
        		(qmanagerType.getCommandLevel()));
//        qmanagerType.setCreationDate(qmanager.getCreationDate());
        qmanager.addAttribute(MqQmanagerAttributeEnum.CREATION_DATE, toDateString(qmanagerType.getCreationDate()));
//        qmanagerType.setCreationTime(qmanager.getCreationTime());
        qmanager.addAttribute(MqQmanagerAttributeEnum.CREATION_TIME, toTimeString(qmanagerType.getCreationDate()));
//        qmanagerType.setDeadLetterQName(qmanager.getDeadLetterQName());
        qmanager.addAttribute(MqQmanagerAttributeEnum.DEAD_LETTER_Q_NAME, qmanagerType.getDeadLetterQName());
//        qmanagerType.setDefXmitQName(qmanager.getDefXmitQName());
        qmanager.addAttribute(MqQmanagerAttributeEnum.DEF_XMIT_Q_NAME, qmanagerType.getDefXmitQName());
//        qmanagerType.setDistLists(integerToBigInteger(qmanager.getDistLists()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.DIST_LISTS,  
        		(qmanagerType.getDistLists()));
//        qmanagerType.setInhibitEvent(integerToBigInteger(qmanager.getInhibitEvent()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.INHIBIT_EVENT,  
        		(qmanagerType.getInhibitEvent()));
//        qmanagerType.setLocalEvent(integerToBigInteger(qmanager.getLocalEvent()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.LOCAL_EVENT,  
        		(qmanagerType.getLocalEvent()));
//        qmanagerType.setMaxHandles(integerToBigInteger(qmanager.getMaxHandles()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.MAX_HANDLES,  
        		(qmanagerType.getMaxHandles()));
//        qmanagerType.setMaxMsgLength(integerToBigInteger(qmanager.getMaxMsgLength()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.MAX_MSG_LENGTH,  
        		(qmanagerType.getMaxMsgLength()));
//        qmanagerType.setMaxPriority(integerToBigInteger(qmanager.getMaxPriority()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.MAX_PRIORITY,  
        		(qmanagerType.getMaxPriority()));
//        qmanagerType.setMaxUncommittedMsgs(integerToBigInteger(qmanager.getMaxUncommittedMsgs()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.MAX_UNCOMMITTED_MSGS,  
        		(qmanagerType.getMaxUncommittedMsgs()));
//        qmanagerType.setPerformanceEvent(integerToBigInteger(qmanager.getPerformanceEvent()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.PERFORMANCE_EVENT,  
        		(qmanagerType.getPerformanceEvent()));
//        qmanagerType.setPlatform(integerToBigInteger(qmanager.getPlatform()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.PLATFORM,  
        		(qmanagerType.getPlatform()));
//        qmanagerType.setQmanagerDesc(qmanager.getQmanagerDesc());
        qmanager.addAttribute(MqQmanagerAttributeEnum.Q_MGR_DESC, qmanagerType.getDescription());
//        qmanagerType.setQmanagerIdentifier(qmanager.getQmanagerIdentifier());
        qmanager.addAttribute(MqQmanagerAttributeEnum.Q_MGR_IDENTIFIER, qmanagerType.getIdentifier());
//        qmanagerType.setQmanagerName(qmanager.getQmanagerName());
        qmanager.addAttribute(MqQmanagerAttributeEnum.Q_MGR_NAME, qmanagerType.getName());
//        qmanagerType.setRemoteEvent(integerToBigInteger(qmanager.getRemoteEvent()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.REMOTE_EVENT,  
        		(qmanagerType.getRemoteEvent()));
//        qmanagerType.setRepositoryName(qmanager.getRepositoryName());
        qmanager.addAttribute(MqQmanagerAttributeEnum.REPOSITORY_NAME, qmanagerType.getRepositoryName());
//        qmanagerType.setRepositoryNameList(qmanager.getRepositoryNameList());
        qmanager.addAttribute(MqQmanagerAttributeEnum.REPOSITORY_NAMELIST, qmanagerType.getRepositoryNameList());
//        qmanagerType.setSslCertificateRepositoryList(qmanager.getSslCertificateRepositoryList());
        qmanager.addAttribute(MqQmanagerAttributeEnum.SSL_CRL_NAMELIST, qmanagerType.getSslCertificateRepositoryList());
//        qmanagerType.setSslKeyRepository(qmanager.getSslKeyRepository());
        qmanager.addAttribute(MqQmanagerAttributeEnum.SSL_KEY_REPOSITORY, qmanagerType.getSslKeyRepository());
//        qmanagerType.setStartStopEvent(integerToBigInteger(qmanager.getStartStopEvent()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.START_STOP_EVENT,  
        		(qmanagerType.getStartStopEvent()));
//        qmanagerType.setSyncPoint(integerToBigInteger(qmanager.getSyncPoint()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.SYNCPOINT, qmanagerType.getSyncPoint());
//        qmanagerType.setTriggerInterval(integerToBigInteger(qmanager.getTriggerInterval()));
        qmanager.addAttribute(MqQmanagerAttributeEnum.TRIGGER_INTERVAL,  
        		(qmanagerType.getTriggerInterval()));
        return qmanager;
    }
    
    /**
     * @param message
     * @return
     */
    public static MqMessageType toXmlType(MqMessage message){
        if(message == null) return null;
        MqMessageType messageType = objectTypeFactory.createMqMessageType();
        messageType.setId(message.getId());
        messageType.setData(message.getData());
        
        MqMessageHeader header = message.getMessageHeader();
        MqMessageHeaderType headerType = objectTypeFactory.createMqMessageHeaderType();
        messageType.setHeader(headerType);
        headerType.setAccountingToken(header.getAccountingToken());
        headerType.setApplicationId(header.getApplicationId());
        headerType.setApplicationOrigin(header.getApplicationOrigin());
        headerType.setBackoutCount(header.getBackoutCount());
        headerType.setCharacterSet(header.getCharacterSet());
        headerType.setCoa(header.getCoa().getValue());
        headerType.setCod(header.getCod().getValue());
        headerType.setCodePage(header.getCodePage());
        headerType.setCorrelationId(header.getCorrelationId());
        headerType.setEncoding(header.getEncoding());
        headerType.setException(header.getException().getValue());
        headerType.setExpiration(header.getExpiration().getValue());
        headerType.setExpiry(header.getExpiry());
        headerType.setFeedback(header.getFeedback());
        headerType.setGroupId(header.getGroupId());
        headerType.setMessageFlags(header.getMessageFlags());
        headerType.setMessageFormat(header.getMessageFormat());
        headerType.setMessageSequenceNumber(header.getMessageSequenceNumber());
        headerType.setMessageType(header.getMessageType().getValue());
        //TODO: headerType.setNan(header.get);
        headerType.setOffset(header.getOffset());
        headerType.setOriginalLength(header.getOriginalLength());
        //TODO: headerType.setPan(header.get);
        //TODO: headerType.setPassCorrelationId(header.get);
        headerType.setPersistence(header.getPersistence().getValue());
        headerType.setPriority(header.getPriority());
        headerType.setPutApplicationName(header.getPutApplicationName());
        headerType.setPutTime(toCalendar(header.getPutTime()));
        headerType.setReplyToQmanagerName(header.getReplyToQmanagerName());
        headerType.setReplyToQueueName(header.getReplyToQueueName());
        
        //TODO: think of better way for converting enums
        if (header.getSegment().equals(MqMessageHeaderSegmentTypeEnum.LAST)) {
            headerType.setSegment(SegmentTypeEnum.LAST.ordinal());
        } else {
            headerType.setSegment(SegmentTypeEnum.YES.ordinal());
        }
        headerType.setUserId(header.getUserId());
        return messageType;
    }

    /**
     * @param messageType
     * @return
     */
    public static MqMessage toDomainType(MqMessageType messageType){
        if(messageType == null) return null;
        MqMessage message = new IBMMqMessage();
//        messageType.setId(message.getId());
        message.setId(messageType.getId());
//        messageType.setData(message.getData());
        message.setData(messageType.getData());
//        
//        MqMessageHeader header = message.getMessageHeader();
        MqMessageHeaderType headerType = messageType.getHeader();
//        MqMessageHeaderType headerType = objectTypeFactory.createMqMessageHeaderType();
        MqMessageHeader header = new IBMMqMessageHeader();
//        messageType.setHeader(headerType);
        message.setMessageHeader(header);
        header.setAccountingToken(headerType.getAccountingToken());
        header.setApplicationId(headerType.getApplicationId());
        header.setApplicationOrigin(headerType.getApplicationOrigin());
        header.setBackoutCount(headerType.getBackoutCount());
        header.setCharacterSet(headerType.getCharacterSet());
        header.setCoa(MqMessageHeaderCOAReportOptionTypeEnum.getEnum(headerType.getCoa()));
        header.setCod(MqMessageHeaderCODReportOptionTypeEnum.getEnum(headerType.getCod()));
        header.setCodePage(headerType.getCodePage());
        header.setCorrelationId(headerType.getCorrelationId());
        header.setEncoding(headerType.getEncoding());
        header.setException(MqMessageHeaderExceptionReportOptionTypeEnum.getEnum(headerType.getException()));
        header.setExpiration(MqMessageHeaderExpirationReportOptionTypeEnum.getEnum(headerType.getExpiration()));
        header.setExpiry(headerType.getExpiry());
        header.setFeedback(headerType.getFeedback());
        header.setGroupId(headerType.getGroupId());
        header.setMessageFlags(headerType.getMessageFlags());
        header.setMessageFormat(headerType.getMessageFormat());
        header.setMessageSequenceNumber(headerType.getMessageSequenceNumber());
        header.setMessageType(MqMessageHeaderMessageTypeEnum.getEnum(headerType.getMessageType()));
        //TODO: headerType.setNan(header.get);
        header.setOffset(headerType.getOffset());
        header.setOriginalLength(headerType.getOriginalLength());
        //TODO: headerType.setPan(header.get);
        //TODO: headerType.setPassCorrelationId(header.get);
        header.setPersistence(MqMessageHeaderPersistenceTypeEnum.getEnum(headerType.getPersistence()));
        header.setPriority(headerType.getPriority());
        header.setPutApplicationName(headerType.getPutApplicationName());
        
        Date putTime = toDate(headerType.getPutTime());
        if(putTime != null){
        	header.setPutTime(putTime.getTime());
        }
        header.setReplyToQmanagerName(headerType.getReplyToQmanagerName());
        header.setReplyToQueueName(headerType.getReplyToQueueName());
        if (headerType.getSegment().equals(SegmentTypeEnum.LAST)) {
            header.setSegment(MqMessageHeaderSegmentTypeEnum.LAST);
        } else {
            header.setSegment(MqMessageHeaderSegmentTypeEnum.YES);
        }
        headerType.setUserId(header.getUserId());
        return message;
    }

    /**
     * @param messages
     * @return
     */
    public static MqMessageArrayType toXmlMessageList(List<MqMessage> messages) {
        if(messages == null) return null;
        MqMessageArrayType messagesArray = SchemaConvertionUtils.objectTypeFactory.createMqMessageArrayType();
        List<MqMessageType> messagesList = messagesArray.getMessage();
        for (MqMessage mqMessage : messages) {
            messagesList.add(SchemaConvertionUtils.toXmlType(mqMessage));
        }
        return messagesArray;
    }
    
    /**
     * @param channels
     * @return
     */
    public static List<ChannelType> toXmlChannelList(List<MqChannel> channels) {
    	List<ChannelType> channelTypes = Collections.emptyList();
        if(channels == null) return channelTypes;
        channelTypes = new ArrayList<ChannelType>(channels.size());
        for (MqChannel mqChannel : channels) {
        	channelTypes.add(toXmlType(mqChannel));
		}
        return channelTypes;
    }
    
    /**
     * @param channels
     * @return
     */
    public static List<BaseChannelType> toXmlBaseChannelList(List<MqChannel> channels) {
    	List<BaseChannelType> channelTypes = Collections.emptyList();
        if(channels == null) return channelTypes;
        channelTypes = new ArrayList<BaseChannelType>(channels.size());
        for (MqChannel mqChannel : channels) {
        	channelTypes.add(toXmlType(mqChannel));
		}
        return channelTypes;
    }
    
    /**
     * @param queues
     * @return
     */
    public static List<QueueType> toXmlQueueList(List<MqQueue> queues) {
    	List<QueueType> queueTypes = Collections.emptyList();
        if(queues == null) return queueTypes;
        queueTypes = new ArrayList<QueueType>(queues.size());
        for (MqQueue mqQueue : queues) {
        	queueTypes.add(toXmlType(mqQueue));
		}
        return queueTypes;
    }
    
    /**
     * @param queues
     * @return
     */
    public static List<BaseQueueType> toXmlBaseQueueList(List<MqQueue> queues) {
    	List<BaseQueueType> queueTypes = Collections.emptyList();
        if(queues == null) return queueTypes;
        queueTypes = new ArrayList<BaseQueueType>(queues.size());
        for (MqQueue mqQueue : queues) {
        	queueTypes.add(toXmlType(mqQueue));
		}
        return queueTypes;
    }
    
    /**
     * @param qmanagers
     * @return
     */
    public static List<QmanagerType> toXmlQmanagerList(List<MqQmanager> qmanagers) {
    	List<QmanagerType> qmTypes = Collections.emptyList();
        if(qmanagers == null) return qmTypes;
        qmTypes = new ArrayList<QmanagerType>(qmanagers.size());
        for (MqQmanager mqQmanager : qmanagers) {
        	qmTypes.add(toXmlType(mqQmanager));
		}
        return qmTypes;
    }
    
    /**
     * @param queueStats
     * @return
     */
    public static List<QueueStatType> toXmlQueueStatsList(List<MqQueueStat> queueStats) {
    	List<QueueStatType> qsTypes = Collections.emptyList();
        if(qsTypes == null) return qsTypes;
        qsTypes = new ArrayList<QueueStatType>(queueStats.size());
        for (MqQueueStat qs : queueStats) {
        	qsTypes.add(toXmlType(qs));
		}
        return qsTypes;
    }
    
    /**
     * @param qmanagers
     * @return
     */
    public static List<BaseQmanagerType> toXmlBaseQmanagerList(List<MqQmanager> qmanagers) {
    	List<BaseQmanagerType> qmTypes = Collections.emptyList();
        if(qmanagers == null) return qmTypes;
        qmTypes = new ArrayList<BaseQmanagerType>(qmanagers.size());
        for (MqQmanager mqQmanager : qmanagers) {
        	qmTypes.add(toXmlType(mqQmanager));
		}
        return qmTypes;
    }
}
