package com.netflexity.qflex.business.dao;


import com.netflexity.qflex.domain.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ChannelDaoImpl extends AbstractGenericsDao<Channel, Long> {
    
	protected static final Log log = LogFactory.getLog(ChannelDaoImpl.class);
    
    // property constants
	public static final String CHANNEL_ID = "channelId";
	public static final String QMANAGER_ID = "qmanager.qmanagerId";
	public static final String CHANNEL_NM = "channelNm";
	public static final String CHANNEL_TP = "channelTp";
	public static final String STATUS = "status";
	public static final String MODIFIED_TM = "modifiedTm";
	public static final String MODIFIED_BY = "modifiedBy";

}