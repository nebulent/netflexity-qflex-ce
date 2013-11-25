package com.netflexity.qflex.business.dao;

import com.netflexity.qflex.domain.MonitorsPollingPolicies;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MonitorsPollingPoliciesDaoImpl extends AbstractGenericsDao<MonitorsPollingPolicies, Long> {

    protected static final Log log = LogFactory.getLog(MonitorsPollingPoliciesDaoImpl.class);

    public static final String MONITORS_POLLING_POLICIES_ID = "monitorsPollingPoliciesId";
    public static final String MONITOR_ID = "monitor.monitorId";
    public static final String POLLING_POLICIES = "pollingPolicies";
    public static final String MONITOR_TEMPLATE_ID = "monitorTemplateId";
    public static final String MODIFIED_TM = "modifiedTm";
    public static final String MODIFIED_BY = "modifiedBy";
}
