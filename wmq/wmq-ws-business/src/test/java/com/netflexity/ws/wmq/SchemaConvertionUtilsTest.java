package com.netflexity.ws.wmq;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SchemaConvertionUtilsTest {

    @Test
    public void testToBigInteger() {
        assertTrue(new BigInteger(Long.MAX_VALUE+"").equals(SchemaConvertionUtils.toBigInteger(Long.MAX_VALUE)));
    }

    @Test
    public void testToMqQmanagerManagerContext() {
        //fail("Not yet implemented");
    }

    @Test
    public void testToDomainTypeStringArrayType() {
        //fail("Not yet implemented");
    }

    @Test
    public void testToXmlTypeListOfString() {
        //fail("Not yet implemented");
    }

    @Test
    public void testToDomainTypeQueueStatType() {
        //fail("Not yet implemented");
    }

    @Test
    public void testToXmlTypeMqQueueStat() {
        //fail("Not yet implemented");
    }

    @Test
    public void testToXmlTypeMqQueue() {
        //fail("Not yet implemented");
//        QueueType type = SchemaConvertionUtils.toXmlType(new TestMqQueue1());
//        assertEquals(TestMqQueue1.getBackoutRequeueName, type.getBackoutRequeueName());
//        assertEquals(TestMqQueue1.getBackoutThreshold, type.getBackoutThreshold());
//        assertEquals(TestMqQueue1.getBaseQueueName, type.getBaseQueueName());
//        assertEquals(TestMqQueue1.getClusterName, type.getClusterName());
//        assertEquals(TestMqQueue1.getClusterNamelist, type.getClusterNamelist());
//        assertEquals(TestMqQueue1.getCurrentQueueDepth, type.getCurrentQueueDepth());
//        assertEquals(TestMqQueue1.getDefBind, type.getDefBind());
//        assertEquals(TestMqQueue1.getDefInputOpenOption, type.getDefInputOpenOption());
//        assertEquals(TestMqQueue1.getDefPersistence, type.getDefPersistence());
//        assertEquals(TestMqQueue1.getDefPriority, type.getDefPriority());
//        assertEquals(TestMqQueue1.getDefType, type.getDefType());
//        assertEquals(TestMqQueue1.getDistLists, type.getDistLists());
//        assertEquals(TestMqQueue1.getHardenGetBackout, type.getHardenGetBackout());
//        assertEquals(TestMqQueue1.getInhibitGet, type.getInhibitGet());
//        assertEquals(TestMqQueue1.getInhibitPut, type.getInhibitPut());
//        assertEquals(TestMqQueue1.getInitiationQueueName, type.getInitiationQueueName());
//        assertEquals(TestMqQueue1.getMaxMsgLength, type.getMaxMsgLength());
//        assertEquals(TestMqQueue1.getMaxQueueDepth, type.getMaxQueueDepth());
//        assertEquals(TestMqQueue1.getMsgDeliverySequence, type.getMsgDeliverySequence());
//        assertEquals(TestMqQueue1.getOpenInputCount, type.getOpenInputCount());
//        assertEquals(TestMqQueue1.getOpenOutputCount, type.getOpenOutputCount());
//        assertEquals(TestMqQueue1.getProcessName, type.getProcessName());
//        assertEquals(TestMqQueue1.getQueueDepthHighEvent, type.getQueueDepthHighEvent());
//        assertEquals(TestMqQueue1.getQueueDepthHighLimit, type.getQueueDepthHighLimit());
//        assertEquals(TestMqQueue1.getQueueDepthLowEvent, type.getQueueDepthLowEvent());
//        assertEquals(TestMqQueue1.getQueueDepthLowLimit, type.getQueueDepthLowLimit());
//        assertEquals(TestMqQueue1.getQueueDepthMaxEvent, type.getQueueDepthMaxEvent());
//        assertEquals(TestMqQueue1.getQueueDesc, type.getQueueDesc());
//        assertEquals(TestMqQueue1.getQueueName, type.getQueueName());
//        assertEquals(TestMqQueue1.getQueueServiceInterval, type.getQueueServiceInterval());
//        assertEquals(TestMqQueue1.getQueueServiceIntervalEvent, type.getQueueServiceIntervalEvent());
//        assertEquals(TestMqQueue1.getQueueType, type.getQueueType());
//        assertEquals(TestMqQueue1.getRetentionInterval, type.getRetentionInterval());
//        assertEquals(TestMqQueue1.getScope, type.getScope());
//        assertEquals(TestMqQueue1.getShareability, type.getShareability());
//        assertEquals(TestMqQueue1.getTriggerControl, type.getTriggerControl());
//        assertEquals(TestMqQueue1.getTriggerData, type.getTriggerData());
//        assertEquals(TestMqQueue1.getTriggerDepth, type.getTriggerDepth());
//        assertEquals(TestMqQueue1.getTriggerMsgPriority, type.getTriggerMsgPriority());
//        assertEquals(TestMqQueue1.getTriggerType, type.getTriggerType());
//        assertEquals(TestMqQueue1.getUsage, type.getUsage());
    }

    @Test
    public void testToDomainTypeQueueType() {
        //fail("Not yet implemented");
    }

    @Test
    public void testToXmlTypeMqChannel() {
        //fail("Not yet implemented");
    }

    @Test
    public void testToDomainTypeChannelType() {
        //fail("Not yet implemented");
    }

    @Test
    public void testToXmlTypeMqQmanager() {
        //fail("Not yet implemented");
    }

    @Test
    public void testToDomainTypeQmanagerType() {
        //fail("Not yet implemented");
    }

    @Test
    public void testToXmlTypeMqMessage() {
        //fail("Not yet implemented");
    }

    @Test
    public void testToDomainTypeMqMessageType() {
        //fail("Not yet implemented");
    }

    @Test
    public void testToXmlTypeListOfMqMessage() {
        //fail("Not yet implemented");
    }

    public AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("spring.xml");
    }
    
    
}
