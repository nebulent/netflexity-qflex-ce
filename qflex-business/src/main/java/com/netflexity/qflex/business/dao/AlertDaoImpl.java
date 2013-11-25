package com.netflexity.qflex.business.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.netflexity.qflex.domain.Alert;
import com.netflexity.qflex.domain.enums.AlertStatuses;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

public class AlertDaoImpl extends AbstractGenericsDao<Alert, Long> {

    protected static final Log log = LogFactory.getLog(AlertDaoImpl.class);
    // property constants
    public static final String ALERT_STATUS_TYPE_ID = "alertStatusTypeId";
    public static final String CRITICALITY_TYPE_ID = "criticalityTypeId";
    public static final String ALERT_NM = "alertNm";
    public static final String OCCURRENCE_START_TM = "occurrenceStartTm";
    public static final String OCCURRENCE_END_TM = "occurrenceEndTm";
    public static final String ALERT_TEXT = "alertText";
    public static final String COMMENTS = "comments";
    public static final String MODIFIED_TM = "modifiedTm";
    public static final String MODIFIED_BY = "modifiedBy";
    public static final String PARENT = "alert";
    public static final String MONITOR = "monitor.monitorId";

    /**
     * @param obj
     */
    public Alert insertAlert(Alert obj) {
        return save(obj);
    }

    /**
     * @return
     * 
     */
    @SuppressWarnings("unchecked")
    public List<Alert> findAllAlerts() {
        return findAll(OCCURRENCE_START_TM);
    }

    /**
     * @param criticalityTypeId
     * @return
     */
    public List<Alert> findByCriticalityTypeId(Object criticalityTypeId) {
        return find(CRITICALITY_TYPE_ID, criticalityTypeId);
    }

    /**
     * @param alertNm
     * @return
     */
    public List<Alert> findByAlertNm(Object alertNm) {
        return find(ALERT_NM, alertNm);
    }

    /**
     * @param occurrenceStartTm
     * @return
     */
    public List<Alert> findByOccurrenceStartTm(Object occurrenceStartTm) {
        return find(OCCURRENCE_START_TM, occurrenceStartTm);
    }

    /**
     * @param occurrenceEndTm
     * @return
     */
    public List<Alert> findByOccurrenceEndTm(Object occurrenceEndTm) {
        return find(OCCURRENCE_END_TM, occurrenceEndTm);
    }

    /**
     * @param name
     * @return
     */
    public List<Alert> findAlertsByText(String name) {
        return findByCriteria(Restrictions.like(ALERT_TEXT, name, MatchMode.ANYWHERE));
    }

    /**
     * @return
     */
    public List<Alert> findActiveAlerts() {
        return findByCriteria(
                1500,
                new Order[]{Order.desc(OCCURRENCE_START_TM)},
                Restrictions.le(ALERT_STATUS_TYPE_ID, AlertStatuses.NEW.getLongType()),
                Restrictions.isNull(PARENT));
        /*
        queryBuilder.addCriteria(queryBuilder.getLessThanEqualsCriteria(Alert.ALERT_STATUS_TYPE_ID_COL, Alert.NEW_STATUS));
        queryBuilder.addCriteria(queryBuilder.getEqualityCriteria(Alert.PARENT_ID_COL, null));
        queryBuilder.addOrderByColumn(Alert.OCCURRENCE_START_TM_COL);
        queryBuilder.setOrderByDesc(true);
        return findRecords(queryBuilder, 1500);
         */
    }

    /**
     * @param occurrence_end_tm
     * @return
     */
    public List<Alert> findActiveAlertsAfter(long occurrence_end_tm) {
        return findByCriteria(
                1500,
                new Order[]{Order.desc(OCCURRENCE_START_TM)},
                Restrictions.le(ALERT_STATUS_TYPE_ID, AlertStatuses.NEW.getLongType()),
                Restrictions.isNull(PARENT),
                Restrictions.gt(OCCURRENCE_END_TM, occurrence_end_tm));
        /*
        queryBuilder.addCriteria(queryBuilder.getLessThanEqualsCriteria(Alert.ALERT_STATUS_TYPE_ID_COL, Alert.NEW_STATUS));
        queryBuilder.addCriteria(queryBuilder.getEqualityCriteria(Alert.PARENT_ID_COL, null));
        queryBuilder.addCriteria(queryBuilder.getGreaterThanCriteria(Alert.OCCURRENCE_END_TM_COL, occurrence_end_tm));
        queryBuilder.addOrderByColumn(Alert.OCCURRENCE_START_TM_COL);
        queryBuilder.setOrderByDesc(true);
        return findRecords(queryBuilder, 1500);
         */
    }

    public int changeAlertStatus(final List<Long> ids, final long status) {


//Due to bug in hibernate we can`t set collection parameter to 'in' clause
//So, rewriting hibernate sources...

//        String updateHql = "update alerts set alertStatusTypeId=? where alertId in ?";
//        return getHibernateTemplate().bulkUpdate(updateHql, new Object[]{status, ids});


        final String updateHql = "update com.netflexity.qflex.domain.Alert set alertStatusTypeId=:status where alertId in (:ids)";

        final boolean isCacheQueries = getHibernateTemplate().isCacheQueries();
        final String queryCacheRegion = getHibernateTemplate().getQueryCacheRegion();
        final int fetchSize  = getHibernateTemplate().getFetchSize();
        final int maxResults = getHibernateTemplate().getMaxResults();

        Integer updateCount = (Integer) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.createQuery(updateHql);
                if (isCacheQueries) {
                    queryObject.setCacheable(true);
                    if (queryCacheRegion != null) {
                        queryObject.setCacheRegion(queryCacheRegion);
                    }
                }
                if (fetchSize > 0) {
                    queryObject.setFetchSize(fetchSize);
                }
                if (maxResults > 0) {
                    queryObject.setMaxResults(maxResults);
                }
                SessionFactoryUtils.applyTransactionTimeout(queryObject, getSessionFactory());
                queryObject.setParameter("status", status);
                queryObject.setParameterList("ids", ids);
                return new Integer(queryObject.executeUpdate());
            }
        });
        return updateCount.intValue();





    }
}
