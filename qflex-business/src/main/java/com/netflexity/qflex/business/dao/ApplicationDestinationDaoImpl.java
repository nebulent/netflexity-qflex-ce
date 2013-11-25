package com.netflexity.qflex.business.dao;


import com.netflexity.qflex.domain.ApplicationDestination;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ApplicationDestinationDaoImpl extends AbstractGenericsDao<ApplicationDestination, Long> {
    
	protected static final Log log = LogFactory.getLog(ApplicationDestinationDaoImpl.class);
    
    // property constants
        public static final String APPLICATION_DESTINATION_ID = "applicationDestinationId";
	public static final String QUEUE_ID = "queue.queueId";
	public static final String APPLICATION_ID = "application.applicationId";
	public static final String DESTINATION_TYPE_ID = "destinationTypeId";
	public static final String DESTINATION_PURPOSE_TYPE_ID = "destinationPurposeTypeId";
	public static final String DESTINATION_NM = "destinationNm";
	public static final String MODIFIED_TM = "modifiedTm";
	public static final String MODIFIED_BY = "modifiedBy";

}