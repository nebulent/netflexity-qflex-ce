package com.netflexity.qflex.business.dao;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflexity.qflex.domain.Monitor;

public class MonitorDaoImpl extends AbstractGenericsDao<Monitor, Long> {
    
	protected static final Log log = LogFactory.getLog(MonitorDaoImpl.class);

	public static final String MONITOR_ID = "monitorId";
	public static final String QMANAGER_BY_MONITOR_OBJECT_ID = "qmanagerByMonitorObjectId.qmanagerId";
	public static final String CHANNEL_ID =  "channel.channelId";
	public static final String QMANAGER_BY_QMANAGER_ID = "qmanagerByQmanagerId.qmanagetId";
        public static final String COMPANY_ID = "company.companyId";
        public static final String QUEUE_ID = "queue.queueId";
	public static final String MONITOR_TYPE_ID = "monitorTypeId";
	public static final String TRIG_CONDITION_TYPE_ID = "trigConditionTypeId";
	public static final String TRIG_CONDITION_PARAM = "trigConditionParam";
	public static final String CRITICALITY_TYPE_ID = "criticalityTypeId";
	public static final String MONITOR_NM = "monitorNm";
	public static final String MESSAGE_TEXT = "messageText";
	public static final String RECOVERY_TEXT = "recoveryText";
	public static final String OCCURRENCE_INTERVAL = "occurrenceInterval";
	public static final String POLLING_INTERVAL = "pollingInterval";
	public static final String LOG_FILE = "logFile";
	public static final String STATUS = "status";
	public static final String EMAIL_LIST = "emailList";
	public static final String MODIFIED_TM = "modifiedTm";
	public static final String MODIFIED_BY = "modifiedBy";
	
    /**
     * @param obj
     */
    public Monitor insertMonitor(Monitor obj){
        return save(obj);
    }
    
}