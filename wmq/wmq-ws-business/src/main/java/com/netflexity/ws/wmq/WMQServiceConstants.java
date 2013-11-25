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

/**
 * @author MAX
 *
 */
public interface WMQServiceConstants {

	// Service names.
	
	public static final String GET_QUEUE_STATS = "GetQueueStats";
    public static final String GET_QMANAGER = "GetQmanager";
    public static final String GET_QUEUE = "GetQueue";
    public static final String GET_CHANNEL = "GetChannel";
    public static final String GET_ALL_QUEUES = "GetAllQueues";
    public static final String GET_ALL_CHANNELS = "GetAllChannels";
    public static final String IS_COMMAND_SERVER_UP = "IsCommandServerUp";
    public static final String IS_QMANAGER_UP = "IsQmanagerUp";
    public static final String SET_QMANAGER = "SetQmanager";
    public static final String SET_QUEUE = "SetQueue";
    public static final String SET_CHANNEL = "SetChannel";
    public static final String GET_ALL_MESSAGES = "GetAllMessages";
    public static final String GET_SELECTED_MESSAGES = "GetSelectedMessages";
    public static final String GET_MESSAGE_BY_HEX_ID = "GetMessageByHexId";
    public static final String GET_MESSAGES_BY_IDS = "GetMessagesByIds";
    public static final String GET_MESSAGE_IDS = "GetMessageIds";
    public static final String CONSUME_MESSAGES_BY_IDS = "ConsumeMessagesByIds";
    public static final String CONSUME_REQUEST = "ConsumeRequest";
    public static final String PUT_MESSAGE = "PutMessage";
    
    
    public static final String NAMESPACE = "urn:com:netflexity:schema:software:nfx-wmq:messages:1.0";
    
    // Service Errors.
    
    public static final int FAILED_TO_CONNECT_ERROR = 99;
}
