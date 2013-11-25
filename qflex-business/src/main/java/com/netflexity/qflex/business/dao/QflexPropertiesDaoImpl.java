package com.netflexity.qflex.business.dao;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflexity.qflex.domain.QflexProperties;

public class QflexPropertiesDaoImpl extends AbstractGenericsDao<QflexProperties, Long> {
    
	protected static final Log log = LogFactory.getLog(QflexPropertiesDaoImpl.class);
    
	public static final String QFLEX_PROPERTY_ID = "qflexPropertyId";
	public static final String COMPANY_ID = "companyId";
	public static final String PROPERTY_TYPE = "propertyType";
	public static final String PROPERTY_NM = "propertyNm";
	public static final String PROPERTY_VAL = "propertyVal";
	public static final String MODIFIED_TM = "modifiedTm";
	public static final String MODIFIED_BY = "modifiedBy";
	
}