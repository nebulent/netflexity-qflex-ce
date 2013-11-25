package com.netflexity.qflex.business.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflexity.qflex.domain.QueueStat;

public class QueueStatDaoImpl extends AbstractGenericsDao<QueueStat, Long> {

    protected static final Log log = LogFactory.getLog(QueueStatDaoImpl.class);
    // property constants
    	public static final String QUEUE_STAT_ID = "queueStatId";
	public static final String QUEUE  = "queue.queueId";
	public static final String MSG_IN = "msgIn";
	public static final String MSG_OUT = "msgOut";
	public static final String HIGH_DEPTH = "highDepth";
	public static final String FETCH_START_TM = "fetchStartTm";
	public static final String FETCH_END_TM = "fetchEndTm";
	public static final String TIME_SINCE_RESET = "timeSinceReset";
	public static final String MODIFIED_TM = "modifiedTm";
	public static final String MODIFIED_BY = "modifiedBy";
    
    /**
     * @param obj
     */
    public QueueStat insertQueueStat(QueueStat obj) {
        return save(obj);
    }

    /**
     * @return
     * 
     */
    @SuppressWarnings("unchecked")
    public List<QueueStat> findAllQueueStat() {
        return findAll(FETCH_START_TM);
    }

    /**
     * @param occurrenceStartTm
     * @return
     */
    public List<QueueStat> findByQueue(Object queueId) {
        return find(QUEUE, queueId);
    }

}