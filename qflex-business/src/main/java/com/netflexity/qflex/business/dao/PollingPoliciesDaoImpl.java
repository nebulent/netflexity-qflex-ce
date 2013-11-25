package com.netflexity.qflex.business.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflexity.qflex.domain.PollingPolicies;
import java.util.List;

public class PollingPoliciesDaoImpl extends AbstractGenericsDao<PollingPolicies, Long> {

    protected static final Log log = LogFactory.getLog(PollingPoliciesDaoImpl.class);
    public static final String POLLING_POLICY_ID = "pollingPolicyId";
    public static final String POLLING_POLICY_NM = "pollingPolicyNm";
    public static final String COMPANY_ID = "companyId";
    public static final String MINUTES = "minutes";
    public static final String HOURS = "hours";
    public static final String DAYS_OF_WEEK = "daysOfWeek";
    public static final String DAYS_OF_MONTH = "daysOfMonth";
    public static final String MONTHS = "months";
    public static final String SECONDS = "seconds";
    public static final String MODIFIED_TM = "modifiedTm";
    public static final String MODIFIED_BY = "modifiedBy";

}
