package com.netflexity.ws.qflex;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import netflexity.schema.software.nfx_wmq.types._1.OSTypeEnum;
import netflexity.schema.software.qflex.messages._1.ApplicationAlertSummaryArrayType;
import netflexity.schema.software.qflex.messages._1.ApplicationAlertSummaryType;
import netflexity.schema.software.qflex.messages._1.ApplicationsStatisticsSummaryArrayType;
import netflexity.schema.software.qflex.messages._1.ApplicationsStatisticsSummaryType;
import netflexity.schema.software.qflex.types._1.AlertArrayType;
import netflexity.schema.software.qflex.types._1.AlertType;
import netflexity.schema.software.qflex.types._1.CompanyType;
import netflexity.schema.software.qflex.types._1.MonitorType;
import netflexity.schema.software.qflex.types._1.PrincipalType;
import netflexity.schema.software.qflex.types._1.QmanagerType;

import org.apache.commons.lang.StringUtils;

import com.netflexity.qflex.business.dao.beans.ApplicationAlertSummary;
import com.netflexity.qflex.business.dao.beans.ApplicationsStatisticsSummary;
import com.netflexity.qflex.domain.Alert;
import com.netflexity.qflex.domain.Company;
import com.netflexity.qflex.domain.Monitor;
import com.netflexity.qflex.domain.Principals;
import com.netflexity.qflex.domain.Qmanager;
import netflexity.schema.software.qflex.types._1.CompanyArrayType;

/**
 * @author MAX
 *
 */
public final class SchemaConvertionUtils {

    //public static final String RELEASE_ID = "1.0";
    //public static final String ACTION_CODE_ALWAYS = "Always";
    //public static ObjectFactory oagisObjectFactory = new ObjectFactory();
    public static netflexity.schema.software.qflex.messages._1.ObjectFactory qflexObjectMessageFactory = new netflexity.schema.software.qflex.messages._1.ObjectFactory();
    public static netflexity.schema.software.qflex.types._1.ObjectFactory qflexObjectTypeFactory = new netflexity.schema.software.qflex.types._1.ObjectFactory();
    public static netflexity.schema.software.nfx_wmq.types._1.ObjectFactory wmqObjectTypeFactory = new netflexity.schema.software.nfx_wmq.types._1.ObjectFactory();

    /*
    public static XMLGregorianCalendar toXMLGregorianCalendar(DateTime dateTime) throws DatatypeConfigurationException {
    DatatypeFactory factory = DatatypeFactory.newInstance();
    return factory.newXMLGregorianCalendar(dateTime.toGregorianCalendar());
    }

    public static DateTime toDateTime(XMLGregorianCalendar calendar) {
    int timeZoneMinutes = calendar.getTimezone();
    DateTimeZone timeZone = DateTimeZone.forOffsetHoursMinutes(timeZoneMinutes / 60, timeZoneMinutes % 60);
    return new DateTime(calendar.getYear(), calendar.getMonth(), calendar.getDay(), calendar.getHour(),
    calendar.getMinute(), calendar.getSecond(), calendar.getMillisecond(), timeZone);
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(LocalDate localDate)
    throws DatatypeConfigurationException {
    DatatypeFactory factory = DatatypeFactory.newInstance();
    return factory.newXMLGregorianCalendarDate(localDate.getYear(), localDate.getMonthOfYear(),
    localDate.getDayOfMonth(), DatatypeConstants.FIELD_UNDEFINED);
    }

    public static LocalDate toLocalDate(XMLGregorianCalendar calendar) {
    return new LocalDate(calendar.getYear(), calendar.getMonth(), calendar.getDay());
    }
     */
    /**
     * @param value
     * @return
     */
    public static BigInteger toBigInteger(long value) {
        return new BigInteger(String.valueOf(value));
    }

    public static BigInteger toBigInteger(Integer integer) {
        if (integer == null) {
            return null;
        }
        return new BigInteger(integer.toString());
    }

    public static Integer toInteger(BigInteger integer) {
        if (integer == null) {
            return null;
        }
        return new Integer(integer.intValue());
    }

    public static Long toLong(BigInteger integer) {
        if (integer == null) {
            return null;
        }
        return integer.longValue();
    }

    public static long toLong(String str) {
        if (str == null) {
            return 0;
        }
        return Long.parseLong(str);
    }

    /**
     * @param List<ApplicationAlertSummary>
     * @return
     */
    public static ApplicationAlertSummaryArrayType toXmlType(List<ApplicationAlertSummary> summary) {
        ApplicationAlertSummaryArrayType applicationAlertSummaryArrayType = qflexObjectMessageFactory.createApplicationAlertSummaryArrayType();
        for (ApplicationAlertSummary applicationAlertSummary : summary) {
            ApplicationAlertSummaryType applicationAlertSummaryType = qflexObjectMessageFactory.createApplicationAlertSummaryType();
            applicationAlertSummaryType.setApplicationId(String.valueOf(applicationAlertSummary.getApplicationId()));
            applicationAlertSummaryType.setApplicationName(applicationAlertSummary.getApplicationNm());
            if (applicationAlertSummary.getFailures() != null) {
                applicationAlertSummaryType.setFailures(applicationAlertSummary.getFailures().intValue());
            }
            if (applicationAlertSummary.getProblems() != null) {
                applicationAlertSummaryType.setProblems(applicationAlertSummary.getProblems().intValue());
            }
            if (applicationAlertSummary.getWarninigs() != null) {
                applicationAlertSummaryType.setWarnings(applicationAlertSummary.getWarninigs().intValue());
            }
            if (applicationAlertSummary.getInfos() != null) {
                applicationAlertSummaryType.setInfos(applicationAlertSummary.getInfos().intValue());
            }
            applicationAlertSummaryArrayType.getSummary().add(applicationAlertSummaryType);
        }
        return applicationAlertSummaryArrayType;
    }

    /**
     * @param summary
     * @return
     */
    public static ApplicationsStatisticsSummaryType toXmlType(ApplicationsStatisticsSummary summary) {
        if (summary == null) {
            return null;
        }

        ApplicationsStatisticsSummaryType summaryType = qflexObjectMessageFactory.createApplicationsStatisticsSummaryType();
        summaryType.setApplicationId(String.valueOf(summary.getApplicationId()));
        summaryType.setApplicationName(summary.getApplicationName());
        summaryType.setDestinationPurposeType(summary.getDestinationPurposeType().getType());
        summaryType.setInputMessages(toBigInteger(summary.getInputMessages()));
        summaryType.setOutputMessages(toBigInteger(summary.getOutputMessages()));
        return summaryType;
    }

    /**
     * @param summary
     * @return
     */
    public static ApplicationsStatisticsSummaryArrayType toXmlType(List<ApplicationsStatisticsSummary> summary) {
        ApplicationsStatisticsSummaryArrayType summaryArrayType = qflexObjectMessageFactory.createApplicationsStatisticsSummaryArrayType();
        for (ApplicationsStatisticsSummary applicationsStatisticsSummary : summary) {
            summaryArrayType.getStat().add(toXmlType(applicationsStatisticsSummary));
        }
        return summaryArrayType;
    }

    /**
     * @param alerts
     * @return
     */
    public static AlertArrayType toXmlType(List<Alert> alerts) {
        AlertArrayType alertArrayType = qflexObjectTypeFactory.createAlertArrayType();
        for (Alert alert : alerts) {
            alertArrayType.getAlert().add(toXmlType(alert));
        }
        return alertArrayType;
    }

    /**
     * @param alerts
     * @return
     */
    public static List<Alert> toDomainType(AlertArrayType alertArrayType) {
        return toDomainType(alertArrayType, false);
    }

    /**
     * @param alerts
     * @return
     */
    public static List<Alert> toDomainType(AlertArrayType alertArrayType, boolean populateMonitor) {
        List<Alert> alerts = new ArrayList<Alert>();
        for (AlertType alertType : alertArrayType.getAlert()) {
            alerts.add(toDomainType(alertType));
        }
        return alerts;
    }

    /**
     * @param Alert
     * @return
     */
    public static AlertType toXmlType(Alert alert) {
        return toXmlType(alert, false, false);
    }

    /**
     * @param Alert
     * @return
     */
    public static AlertType toXmlType(Alert alert, boolean child, boolean populateMonitor) {
        if (alert == null) {
            return null;
        }

        AlertType alertType = qflexObjectTypeFactory.createAlertType();
        alertType.setID(String.valueOf(alert.getAlertId()));
        alertType.setAlertName(alert.getAlertNm());
        alertType.setAlertText(alert.getAlertText());
        alertType.setCriticalityType((int) alert.getCriticalityTypeId());
        alertType.setOccurrenceStartTime(toBigInteger(alert.getOccurrenceStartTm()));
        if (alert.getOccurrenceEndTm() != null) {
            alertType.setOccurrenceEndTime(toBigInteger(alert.getOccurrenceEndTm()));
        }
        alertType.setStatus((int) alert.getAlertStatusTypeId());
        Monitor monitor = alert.getMonitor();
        if (populateMonitor) {
            if (monitor != null) {
                alertType.setMonitor(toXmlType(monitor));
            }
        } else {
            if (monitor != null) {
                alertType.setMonitorId(String.valueOf(monitor.getMonitorId()));
            }
        }
        alertType.setComments(alert.getComments());
        alertType.setModifiedBy(alert.getModifiedBy());
        if (alert.getModifiedTm() != null) {
            alertType.setModifiedTime(toBigInteger(alert.getModifiedTm()));
        }

        // Populate child alerts.
		/*java.util.Set<Alert> childAlerts = alert.getAlerts();
        if(!child && childAlerts != null && !childAlerts.isEmpty()){
        AlertArrayType childAlertArrayType = qflexObjectTypeFactory.createAlertArrayType();
        alertType.setChildren(childAlertArrayType);
        for (Alert childAlert : childAlerts) {
        childAlertArrayType.getAlert().add(toXmlType(childAlert, true));
        }
        }*/
        return alertType;
    }

    /**
     * @param Alert
     * @return
     */
    public static Alert toDomainType(AlertType alertType) {
        Alert alert = new Alert();
        if (StringUtils.isNotBlank(alertType.getID())) {
            alert.setAlertId(toLong(alertType.getID()));
        }
        alert.setAlertNm(alertType.getAlertName());
        alert.setAlertText(alertType.getAlertText());
        alert.setCriticalityTypeId(alertType.getCriticalityType());
        if (alertType.getParentId() != null) {
            Alert parent = new Alert();
            parent.setAlertId(toLong(alertType.getParentId()));
            alert.setAlert(parent);
        }
        if (alertType.getMonitorId() != null) {
            Monitor monitor = new Monitor();
            monitor.setMonitorId(toLong(alertType.getMonitorId()));
            alert.setMonitor(monitor);
        }
        if (alertType.getOccurrenceStartTime() != null) {
            alert.setOccurrenceStartTm(alertType.getOccurrenceStartTime().longValue());
        }
        if (alertType.getOccurrenceEndTime() != null) {
            alert.setOccurrenceEndTm(alertType.getOccurrenceEndTime().longValue());
        }
        alert.setAlertStatusTypeId(alertType.getStatus());
        alert.setComments(alertType.getComments());
        alert.setModifiedBy(alertType.getModifiedBy());
        if (alertType.getModifiedTime() != null) {
            alert.setModifiedTm(alertType.getModifiedTime().longValue());
        }
        return alert;
    }

    /**
     * @param Monitor
     * @return
     */
    public static MonitorType toXmlType(Monitor monitor) {
        if (monitor == null) {
            return null;
        }

        MonitorType monitorType = qflexObjectTypeFactory.createMonitorType();
        monitorType.setID(String.valueOf(monitor.getMonitorId()));
        monitorType.setMonitorName(monitor.getMonitorNm());
        monitorType.setMessageText(monitor.getMessageText());
        monitorType.setCriticalityType((int) monitor.getCriticalityTypeId());
        monitorType.setMonitorType((int) monitor.getMonitorTypeId());
        monitorType.setOccurrenceInterval(monitor.getOccurrenceInterval());
        monitorType.setPollingInterval(toBigInteger(monitor.getPollingInterval()));
        monitorType.setTrigConditionParam(toBigInteger(monitor.getTrigConditionParam()));
        monitorType.setTrigConditionType((int) monitor.getTrigConditionTypeId());
        Qmanager qmanager = monitor.getQmanagerByQmanagerId();
        if (qmanager != null) {
            monitorType.setQmanagerId(String.valueOf(qmanager.getQmanagerId()));
            monitorType.setQmanager(toXmlType(qmanager));
        }
        // TODO: MonitorObject
        // TODO: Company
        return monitorType;
    }

    /**
     * @param Monitor
     * @return
     */

    /*
        TODO: is it correct method? see method above
     */
    public static MonitorType toDomainType(Monitor monitor) {
        if (monitor == null) {
            return null;
        }

        MonitorType monitorType = qflexObjectTypeFactory.createMonitorType();
        monitorType.setID(String.valueOf(monitor.getMonitorId()));
        monitorType.setMonitorName(monitor.getMonitorNm());
        monitorType.setMessageText(monitor.getMessageText());
        monitorType.setCriticalityType((int) monitor.getCriticalityTypeId());
        monitorType.setMonitorType((int) monitor.getMonitorTypeId());
        monitorType.setOccurrenceInterval(monitor.getOccurrenceInterval());
        monitorType.setPollingInterval(toBigInteger(monitor.getPollingInterval()));
        monitorType.setTrigConditionParam(toBigInteger(monitor.getTrigConditionParam()));
        monitorType.setTrigConditionType((int) monitor.getTrigConditionTypeId());
        //monitorType.setQmanager(toXmlType(monitor.getQmanagerByQmanagerId()));
        return monitorType;
    }

    /**
     * @param Alert
     * @return
     */
    public static QmanagerType toXmlType(Qmanager qmanager) {
        if (qmanager == null) {
            return null;
        }

        QmanagerType qmanagerType = qflexObjectTypeFactory.createQmanagerType();
        netflexity.schema.software.nfx_wmq.types._1.QmanagerType qmanagerDetailType = wmqObjectTypeFactory.createQmanagerType();
        netflexity.schema.software.nfx_wmq.types._1.ConnectionType connectionType = wmqObjectTypeFactory.createConnectionType();
        netflexity.schema.software.nfx_wmq.types._1.ServerType serverType = wmqObjectTypeFactory.createServerType();
        qmanagerType.setDetails(qmanagerDetailType);
        qmanagerType.setConnection(connectionType);
        qmanagerType.setID(String.valueOf(qmanager.getQmanagerId()));
        qmanagerType.setAlias(qmanager.getQmanagerAlias());
        qmanagerDetailType.setQmanagerName(qmanager.getQmanagerNm());
        connectionType.setQmanagerName(qmanager.getQmanagerNm());
        connectionType.setChannelName(qmanager.getChannelNm());
        connectionType.setCommandServerQueueName(qmanager.getCommandServerQname());
        connectionType.setCommandServerReplyQueueName(qmanager.getCommandServerReplyQueue());
        connectionType.setPort(qmanager.getPort());
        connectionType.setDataRootDirectory(qmanager.getHostDataRoot());
        connectionType.setWmqVersion(qmanager.getMqVersion());
        connectionType.setServer(serverType);
        serverType.setHost(qmanager.getHostNm());
        serverType.setOs(OSTypeEnum.fromValue(qmanager.getOs()));

        return qmanagerType;
    }

    /**
     * @param Principals
     * @return
     */
    public static PrincipalType toXmlType(Principals principal) {
        if (principal == null) {
            return null;
        }

        PrincipalType principalType = qflexObjectTypeFactory.createPrincipalType();
        principalType.setID(String.valueOf(principal.getPrincipalId()));
        principalType.setName(principal.getPrincipalNm());
        principalType.setEmail(principal.getEmail());
        principalType.setPhone(principal.getPhone());
        principalType.setUsername(principal.getUsername());
        principalType.setPassword(principal.getPassword());
        principalType.setRole(principal.getRole());
        Company company = principal.getCompany();
        if (company != null) {
            principalType.setCompanyId(String.valueOf(company.getCompanyId()));
            principalType.setCompany(toXmlType(company));
        }
        return principalType;
    }

    /**
     * @param Company
     * @return
     */
    public static CompanyType toXmlType(Company company) {
        if (company == null) {
            return null;
        }

        CompanyType companyType = qflexObjectTypeFactory.createCompanyType();
        companyType.setID(String.valueOf(company.getCompanyId()));
        companyType.setCompanyName(company.getCompanyNm());
        companyType.setEmail(company.getEmail());
        companyType.setPhone(company.getPhone());
        companyType.setSerialNumber(company.getSerialNo());
        companyType.setState(company.getState());
        companyType.setZip(company.getZip());
        companyType.setCity(company.getCity());
        return companyType;
    }

    /**
     * @param alerts
     * @return
     */
    public static List<Company> toDomainType(CompanyArrayType companyArrayType) {
        List<Company> companies = new ArrayList<Company>();
        for (CompanyType companyType : companyArrayType.getCompany()) {
            companies.add(toDomainType(companyType));
        }
        return companies;
    }


    public static Company toDomainType(CompanyType companyType) {
        if (companyType == null) {
            return null;
        }
        Company rv = new Company();
        rv.setAddr(companyType.getAddress());
        rv.setApplications(null);
        rv.setCity(companyType.getCity());
        rv.setCompanyId(toLong(companyType.getID()));
        rv.setCompanyNm(companyType.getCompanyName());
        rv.setEmail(companyType.getEmail());
        rv.setModifiedBy(companyType.getModifiedBy());
        rv.setModifiedTm(toLong(companyType.getModifiedTime()));
        rv.setMonitors(null);
        rv.setPhone(companyType.getPhone());
        rv.setPrincipalses(null);
        rv.setProcesses(null);
        rv.setQmanagers(null);
        rv.setSerialNo(companyType.getSerialNumber());
        rv.setState(companyType.getState());
        rv.setZip(companyType.getZip());
        return rv;
    }
}
