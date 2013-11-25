package com.netflexity.qflex.business.dao;

import com.netflexity.qflex.domain.Queue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QueueDaoImpl extends AbstractGenericsDao<Queue, Long> {

    protected static final Log log = LogFactory.getLog(QueueDaoImpl.class);

    public static final String QUEUE_ID = "queueId";
    public static final String QMANAGER_ID = "qmanager.qmanagerId";
    public static final String QUEUE_NM = "queueNm";
    public static final String QUEUE_TP = "queueTp";
    public static final String STATUS = "status";
    public static final String MODIFIED_TM = "modifiedTm";
    public static final String MODIFIED_BY = "modifiedBy";

}
