package com.netflexity.qflex.business.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflexity.qflex.domain.MonitorsAlertTypes;

public class MonitorsAlertTypesDaoImpl extends AbstractGenericsDao<MonitorsAlertTypes, Long> {

    protected static final Log log = LogFactory.getLog(MonitorsAlertTypesDaoImpl.class);
    public static final String MONITORS_ALERT_TYPES_ID = "monitorsAlertTypesId";
    public static final String ALERT_TYPES = "alertTypes";
    public static final String MONITOR_ID = "monitor.monitorId";
    public static final String MONITOR_TEMPLATE_ID = "monitorTemplateId";
    public static final String MODIFIED_TM = "modifiedTm";
    public static final String MODIFIED_BY = "modifiedBy";
}
