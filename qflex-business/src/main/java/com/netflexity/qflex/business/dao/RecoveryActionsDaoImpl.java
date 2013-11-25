package com.netflexity.qflex.business.dao;

import com.netflexity.qflex.domain.RecoveryActions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RecoveryActionsDaoImpl extends AbstractGenericsDao<RecoveryActions, Long> {

    protected static final Log log = LogFactory.getLog(RecoveryActionsDaoImpl.class);

	public static final String RECOVERY_ACTION_ID = "recoveryActionId";
	public static final String RECOVERY_ACTION_TYPE_ID = "recoveryActionTypeId";
	public static final String COMPANY_ID = "companyId";
	public static final String ACTION_NM = "actionNm";
	public static final String JAVA_CLASS = "javaClass";
	public static final String COMMAND = "command";
	public static final String COMMAND_ARGS = "commandArgs";
	public static final String MODIFIED_TM = "modifiedTm";
	public static final String MODIFIED_BY = "modifiedBy";

}
