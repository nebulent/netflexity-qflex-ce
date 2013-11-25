package com.netflexity.qflex.business.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflexity.qflex.business.dao.beans.ApplicationAlertSummary;
import com.netflexity.qflex.business.dao.beans.ApplicationsStatisticsSummary;
import com.netflexity.qflex.domain.Alert;
import com.netflexity.qflex.domain.Application;

public class ApplicationDaoImpl extends AbstractGenericsDao<Application, Long> {

    public static final String APPLICATION_ID_PARAM = "applicationId";
//    public static final String COMPANY_ID_PARAM = "company.companyId";
    public static final String COMPANY_ID_PARAM = "companyId";
    public static final String GIVEN_TM_PARAM = "givenTm";
    protected static final Log log = LogFactory.getLog(ApplicationDaoImpl.class);
    public static final String FIND_APPLICATION_ALERT_SUMMARY_QRY = "findApplicationAlertSummary";
    public static final String FIND_APPLICATIONS_STATISTICS_SUMMARY_QRY = "findApplicationsStatisticsSummary";
    public static final String FIND_APPLICATION_ALERTS_QRY = "findApplicationAlerts";
    // property constants
    public static final String APPLICATION_TYPE = "applicationType";
    public static final String APPLICATION_NM = "applicationNm";
    public static final String DESCRIPTION = "description";
    public static final String MODIFIED_TM = "modifiedTm";
    public static final String MODIFIED_BY = "modifiedBy";

    /**
     * @param obj
     */
    public Application insertApplication(Application obj) {
        return save(obj);
    }

    /**
     * @return
     * 
     */
    @SuppressWarnings("unchecked")
    public List<Application> findAllApplications() {
        return findAll(APPLICATION_NM);
    }

    /**
     * Find applications by company
     */
    @SuppressWarnings("unchecked")
    public List<Application> findAllApplicationsForCompany(long companyId) {
//        return find(COMPANY_ID_PARAM, companyId);
        return find("company.companyId", companyId);
    }

    /**
     * Find application alert summary.
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<ApplicationAlertSummary> findApplicationAlertSummary() {
        List<ApplicationAlertSummary> result = getHibernateTemplate().findByNamedQuery(FIND_APPLICATION_ALERT_SUMMARY_QRY);
        return result;
    }

    /**
     * Find application statistics summary.
     * 
     * @param companyId
     * @param givenTime
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<ApplicationsStatisticsSummary> findApplicationsStatisticsSummary(long companyId, long givenTime) {
        List<ApplicationsStatisticsSummary> result = getHibernateTemplate().findByNamedQueryAndNamedParam(FIND_APPLICATIONS_STATISTICS_SUMMARY_QRY, new String[]{COMPANY_ID_PARAM, GIVEN_TM_PARAM}, new Object[]{companyId, givenTime});
        return result;
    }

    /**
     * @param applicationId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Alert> findApplicationAlerts(long applicationId) {
        List<Alert> result = getHibernateTemplate().findByNamedQueryAndNamedParam(FIND_APPLICATION_ALERTS_QRY, new String[]{APPLICATION_ID_PARAM}, new Object[]{applicationId});
        return result;
    }
}
