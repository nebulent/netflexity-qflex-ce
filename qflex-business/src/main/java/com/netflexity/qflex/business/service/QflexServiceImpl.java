/*
 *  2006 Netflexity, Ltd. All Rights Reserved.
 *
 * CONFIDENTIAL BUSINESS INFORMATION
 *
 * THIS PROGRAM IS PROPRIETARY INFORMATION OF NETFLEXITY, LTD. AND
 * IS NOT TO BE COPIED, REPRODUCED, LENT, OR DISPOSED OF, NOR USED FOR ANY
 * OTHER PURPOSE, UNLESS THE WRITTEN PERMISSION FROM THE STATED ABOVE CORPORATION
 * IS GIVEN.
 */
package com.netflexity.qflex.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import org.netflexity.api.util.MessageDigesterException;
import org.netflexity.api.util.MessageDigesterFactory;
import org.netflexity.api.util.StringConstants;

import com.netflexity.qflex.business.dao.AlertDaoImpl;
import com.netflexity.qflex.business.dao.ApplicationDaoImpl;
import com.netflexity.qflex.business.dao.PrincipalDaoImpl;
import com.netflexity.qflex.business.dao.QmanagerDaoImpl;
import com.netflexity.qflex.business.dao.beans.ApplicationAlertDetails;
import com.netflexity.qflex.business.dao.beans.ApplicationAlertSummary;
import com.netflexity.qflex.business.dao.beans.ApplicationsStatisticsSummary;
import com.netflexity.qflex.domain.Alert;
import com.netflexity.qflex.domain.Application;
import com.netflexity.qflex.domain.Company;
import com.netflexity.qflex.domain.Monitor;
import com.netflexity.qflex.domain.Principals;
import com.netflexity.qflex.domain.Qmanager;
import com.netflexity.qflex.domain.enums.AlertStatuses;
import com.netflexity.qflex.domain.enums.CriticalityTypes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import java.util.Set;
import org.hibernate.Hibernate;

import org.dozer.Mapper;

/**
 * @author MAX
 *
 */
public class QflexServiceImpl extends AbstractQflexService implements QflexServiceErrors {

    public static final int MILLIS_IN_HOUR = 3600000 * 24;
    private ResourceBundleMessageSource serviceErrorsMessageSource;
    private AlertDaoImpl alertDao;
    private ApplicationDaoImpl applicationDao;
    private PrincipalDaoImpl principalDao;
    private QmanagerDaoImpl qmanagerDao;
    private Mapper mapper;

    /**
     * @return the serviceErrorsMessageSource
     */
    public ResourceBundleMessageSource getServiceErrorsMessageSource() {
        return serviceErrorsMessageSource;
    }

    /**
     * @param serviceErrorsMessageSource the serviceErrorsMessageSource to set
     */
    public void setServiceErrorsMessageSource(ResourceBundleMessageSource serviceErrorsMessageSource) {
        this.serviceErrorsMessageSource = serviceErrorsMessageSource;
    }

    public List<Application> getApplications(final long companyId) {
        List<Application> rv = (List<Application>) getTransactionTemplate().execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                List<Application> rv = applicationDao.findAllApplicationsForCompany(companyId);
//                for (Application app : rv) {
//                    Hibernate.initialize(app.getApplicationDestinations());
//                    if (app.getApplicationDestinations() != null) {
//                        for (ApplicationDestination ad : app.getApplicationDestinations()) {
//                            Hibernate.initialize(ad.getQueue());
//                        }
//                    }
//                }
                return rv;
            }
        });
        return rv;
    }

    public Application createApplication(final Application application) {
        Application rv = (Application) getTransactionTemplate().execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                return applicationDao.save(application);
            }
        });
        return rv;
    }

    /**
     * @param companyId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<ApplicationsStatisticsSummary> getDailyApplicationsStatisticsSummary(final long companyId) throws QflexServiceException {
        final long minus24Hours = System.currentTimeMillis() - MILLIS_IN_HOUR;
        return (List<ApplicationsStatisticsSummary>) getTransactionTemplate().execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                return applicationDao.findApplicationsStatisticsSummary(companyId, minus24Hours);
            }
        });
    }

    /**
     * @return
     * @throws QflexServiceException
     */
    @SuppressWarnings("unchecked")
    public List<ApplicationAlertSummary> getApplicationAlertSummary() throws QflexServiceException {
        List<ApplicationAlertSummary> summary = new ArrayList<ApplicationAlertSummary>();
        List<ApplicationAlertDetails> applicationAlertDetails = (List<ApplicationAlertDetails>) getTransactionTemplate().execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                return applicationDao.findApplicationAlertSummary();
            }
        });

        ApplicationAlertSummary tempSummary = null;
        int count = 0;
        for (ApplicationAlertDetails applicationAlertDetail : applicationAlertDetails) {
            count++;
            if (tempSummary == null) {
                tempSummary = new ApplicationAlertSummary(applicationAlertDetail.getApplicationId(), applicationAlertDetail.getApplicationNm());
            }

            // Is this the data for another application?
            if (applicationAlertDetail.getApplicationId() != tempSummary.getApplicationId()) {
                summary.add(tempSummary);
                tempSummary = new ApplicationAlertSummary(applicationAlertDetail.getApplicationId(), applicationAlertDetail.getApplicationNm());
            }

            long total = applicationAlertDetail.getTotal();
            if (CriticalityTypes.getEnum(applicationAlertDetail.getCriticalityTypeId()) == CriticalityTypes.FAILURE) {
                long failures = tempSummary.getFailures() == null ? 0L : tempSummary.getFailures();
                tempSummary.setFailures(failures + total);
            } else if (CriticalityTypes.getEnum(applicationAlertDetail.getCriticalityTypeId()) == CriticalityTypes.PROBLEM) {
                long problems = tempSummary.getProblems() == null ? 0L : tempSummary.getProblems();
                tempSummary.setProblems(problems + total);
            } else if (CriticalityTypes.getEnum(applicationAlertDetail.getCriticalityTypeId()) == CriticalityTypes.WARNING) {
                long warnings = tempSummary.getWarninigs() == null ? 0L : tempSummary.getWarninigs();
                tempSummary.setWarninigs(warnings + total);
            } else if (CriticalityTypes.getEnum(applicationAlertDetail.getCriticalityTypeId()) == CriticalityTypes.INFO) {
                long infos = tempSummary.getInfos() == null ? 0L : tempSummary.getInfos();
                tempSummary.setInfos(infos + total);
            }

            // Is this the last record in the collection?
            if (applicationAlertDetails.size() == count) {
                summary.add(tempSummary);
            }
        }
        return summary;
    }

    /**
     * @param applicationId
     * @return
     * @throws QflexServiceException
     */
    @SuppressWarnings("unchecked")
    public List<Alert> getApplicationAlerts(final long applicationId) throws QflexServiceException {
        List<Alert> alerts = (List<Alert>) getTransactionTemplate().execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                List<Alert> alerts = applicationDao.findApplicationAlerts(applicationId);
                List<Alert> rv = new ArrayList<Alert>(alerts.size());
                for (Alert a : alerts) {
                    Alert a2 = new Alert();
                    a2.setAlertId(a.getAlertId());
                    a2.setAlertNm(a.getAlertNm());
                    a2.setAlertStatusTypeId(a.getAlertStatusTypeId());
                    a2.setAlertText(a.getAlertText());
                    a2.setComments(a.getComments());
                    a2.setCriticalityTypeId(a.getCriticalityTypeId());
                    a2.setAlert(new Alert());
                    a2.setAlerts(new HashSet());
                    a2.setMonitor(new Monitor());
                    rv.add(a2);
                }
                return rv;
            }
        });


        return alerts;
    }

    @SuppressWarnings("unchecked")
    public List<Alert> viewAlerts(/*long companyId, */final long lastOccurrenceEndTm) {
        List<Alert> alerts = (List<Alert>) getTransactionTemplate().execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                List<Alert> alerts = null;

                if (lastOccurrenceEndTm > 0) {
                    alerts = alertDao.findActiveAlertsAfter(lastOccurrenceEndTm);
                } else {
                    alerts = alertDao.findActiveAlerts();
                }

                List<Alert> rv = new ArrayList<Alert>(alerts.size());

                for (Alert alert : alerts) {
                    Alert a2 = mapper.map(alert, Alert.class);
                    a2.setAlert(new Alert());
                    a2.setAlerts(new HashSet());
                    a2.setMonitor(new Monitor());
                    rv.add(a2);


//                    Set<Alert> children = alert.getAlerts();
//                    for (Alert childAlert : children) {
//                        childAlert.setAlerts(null);
//                    }
//                    Monitor monitor = alert.getMonitor();
//                    if (monitor != null) {
//                        Qmanager qm = monitor.getQmanagerByQmanagerId();
//                        if (qm != null) {
//                            qm.setChannels(null);
//                            qm.setMonitorsForMonitorObjectId(null);
//                            qm.setMonitorsForQmanagerId(null);
//                            qm.setQueues(null);
//                        }
//                        monitor.setMonitorsAlertTypeses(null);
//                        monitor.setMonitorsPollingPolicieses(null);
//                        monitor.setMonitorsRecoveryActionses(null);
//                        monitor.setAlerts(null);
////                        Hibernate.initialize(monitor.getQmanagerByQmanagerId());
//                    }

                }
                return rv;
            }
        });
        return alerts;
    }

    public Map<Long, String> getCriticalityTypes() {
        Map<Long, String> rv = new HashMap<Long, String>();
        for (CriticalityTypes ct : CriticalityTypes.values()) {
            if (ct == CriticalityTypes.FAILURE) {
                rv.put(ct.getLongType(), "Failure");
            } else if (ct == CriticalityTypes.INFO) {
                rv.put(ct.getLongType(), "Info");
            } else if (ct == CriticalityTypes.PROBLEM) {
                rv.put(ct.getLongType(), "Problem");
            } else if (ct == CriticalityTypes.WARNING) {
                rv.put(ct.getLongType(), "Warning");
            }
        }
        return rv;
    }

    public Map<Long, String> getAlertStatusTypes() {
        Map<Long, String> rv = new HashMap<Long, String>();
        for (AlertStatuses as : AlertStatuses.values()) {
            if (as == AlertStatuses.ASSIGNED) {
                rv.put(as.getLongType(), "Assigned");
            } else if (as == AlertStatuses.NEW) {
                rv.put(as.getLongType(), "New");
            } else if (as == AlertStatuses.RESOLVED) {
                rv.put(as.getLongType(), "Resolved");
            }
        }
        return rv;
    }

    /**
     * @param alerts
     * @throws QflexServiceException
     */
    public void changeAlertsStatus(final List<Alert> alerts) throws QflexServiceException {
        getTransactionTemplate().execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                for (Alert alert : alerts) {
                    alertDao.save(alert);
                }
            }
        });
    }

    public int updateAlertsStatus(final List<Long> ids, final long newStatus) throws QflexServiceException {
        Integer rv = (Integer) getTransactionTemplate().execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                return new Integer(alertDao.changeAlertStatus(ids, newStatus));
            }
        });
        return rv;
    }

    /**
     * @param username
     * @param password
     * @return
     * @throws QflexServiceException
     */
    public Principals authenticate(final String username, final String password) throws QflexServiceException {
        Principals principal = (Principals) getTransactionTemplate().execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {

                    String hashedPassword = StringConstants.EMPTY;
                    try {
                        hashedPassword = MessageDigesterFactory.getInstance().createDigest(password.trim());
                    } catch (MessageDigesterException e) {
                        //throw new CommandException(e.getMessage(), e);
                    }

                    List<Principals> principals = principalDao.findPrincipalBy(username, hashedPassword);
                    for (Principals principal : principals) {
                        Company company = principal.getCompany();
                        logger.debug("Loading company " + company.getCompanyNm() + " with serial " + company.getSerialNo());
                        return principal;
                    }
                }
                return null;
            }
        });
        return principal;
    }

    public List<Qmanager> getChannelsList(final String qmanagerName) {
        return (List<Qmanager>) getTransactionTemplate().execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                List<Qmanager> list = qmanagerDao.findQmanagers(qmanagerName);

                for (Qmanager qm : list) {
                    Hibernate.initialize(qm.getChannels());
                }
                return list;
            }
        });
    }

    public List<Qmanager> getQmanagersList() {
        return (List<Qmanager>) getTransactionTemplate().execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                List<Qmanager> list = qmanagerDao.findAll();
                return list;
            }
        });
    }

    public Qmanager findQmanagerById(final long id) {
        return (Qmanager) getTransactionTemplate().execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                Qmanager rv = qmanagerDao.find(id);
                return rv;
            }
        });
    }

    /**
     * @return the alertDao
     */
    public AlertDaoImpl getAlertDao() {
        return alertDao;
    }

    /**
     * @param alertDao the alertDao to set
     */
    public void setAlertDao(AlertDaoImpl alertDao) {
        this.alertDao = alertDao;
    }

    /**
     * @return the applicationDao
     */
    public ApplicationDaoImpl getApplicationDao() {
        return applicationDao;
    }

    /**
     * @param applicationDao the applicationDao to set
     */
    public void setApplicationDao(ApplicationDaoImpl applicationDao) {
        this.applicationDao = applicationDao;
    }

    /**
     * @return the principalDao
     */
    public PrincipalDaoImpl getPrincipalDao() {
        return principalDao;
    }

    /**
     * @param principalDao the principalDao to set
     */
    public void setPrincipalDao(PrincipalDaoImpl principalDao) {
        this.principalDao = principalDao;
    }

    /**
     * @param errorCode
     * @param args
     * @return
     */
    public QflexServiceException createServiceException(int errorCode, Object[] args) {
        return new QflexServiceException(serviceErrorsMessageSource.getMessage(String.valueOf(errorCode), args, Locale.getDefault()));
    }

    /**
     * @param qmanagerDao the qmanagerDao to set
     */
    public void setQmanagerDao(QmanagerDaoImpl qmanagerDao) {
        this.qmanagerDao = qmanagerDao;
    }

    /**
     * @param mapper the mapper to set
     */
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
