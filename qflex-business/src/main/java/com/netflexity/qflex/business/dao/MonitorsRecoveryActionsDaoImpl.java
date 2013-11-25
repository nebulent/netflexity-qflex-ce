package com.netflexity.qflex.business.dao;

import com.netflexity.qflex.domain.MonitorsRecoveryActions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MonitorsRecoveryActionsDaoImpl extends AbstractGenericsDao<MonitorsRecoveryActions, Long> {

    protected static final Log log = LogFactory.getLog(MonitorsRecoveryActionsDaoImpl.class);
    
    public static final String MONITORS_RECOVERY_ACTION_ID = "monitorsRecoveryActionId";
    public static final String MONITOR_ID = "monitor.monitorId";
    public static final String RECOVERY_ACTIONS = "recoveryActions";
    public static final String MONITOR_TEMPLATE_ID = "monitorTemplateId";
    public static final String MODIFIED_TM = "modifiedTm";
    public static final String MODIFIED_BY = "modifiedBy";
}
