package com.netflexity.ws.qflex;

import java.util.List;

import netflexity.schema.software.qflex.messages._1.AcknowledgeChangeAlertsStatus;
import netflexity.schema.software.qflex.messages._1.GetApplicationAlertSummary;
import netflexity.schema.software.qflex.messages._1.GetApplicationAlerts;
import netflexity.schema.software.qflex.messages._1.GetDailyApplicationsStatisticsSummary;
import netflexity.schema.software.qflex.messages._1.GetUserPermissions;
import netflexity.schema.software.qflex.messages._1.ProcessChangeAlertsStatus;
import netflexity.schema.software.qflex.messages._1.ShowApplicationAlertSummary;
import netflexity.schema.software.qflex.messages._1.ShowApplicationAlerts;
import netflexity.schema.software.qflex.messages._1.ShowDailyApplicationsStatisticsSummary;
import netflexity.schema.software.qflex.messages._1.ShowUserPermissions;
import netflexity.schema.software.qflex.types._1.CompanyType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import com.netflexity.qflex.business.dao.beans.ApplicationAlertSummary;
import com.netflexity.qflex.business.dao.beans.ApplicationsStatisticsSummary;
import com.netflexity.qflex.business.service.QflexServiceException;
import com.netflexity.qflex.business.service.QflexServiceImpl;
import com.netflexity.qflex.domain.Alert;
import com.netflexity.qflex.domain.Principals;

/**
 * Endpoint that handles the WMQ Web Service messages using JAXB2 marshalling.
 * <p/>
 * @author Max Fedorov
 */
@Endpoint
public class MarshallingQflexEndpoint implements QflexServiceConstants{

    public static final Log logger = LogFactory.getLog(MarshallingQflexEndpoint.class);
    
    protected QflexServiceImpl qflexService;
    
    /**
     * @param qflexService
     */
    public MarshallingQflexEndpoint(QflexServiceImpl qflexService) {
    	this.qflexService = qflexService;
    }

    /**
	 * @return the qflexService
	 */
	public QflexServiceImpl getQflexService() {
		return qflexService;
	}

	/**
	 * @param qflexService the qflexService to set
	 */
	public void setQflexService(QflexServiceImpl qflexService) {
		this.qflexService = qflexService;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws QflexWebServiceException
	 */
	@PayloadRoot(localPart = GET_APPLICATION_ALERT_SUMMARY, namespace = NAMESPACE_QFLEXMSG)
    public ShowApplicationAlertSummary getApplicationAlertSummary(final GetApplicationAlertSummary request) throws QflexWebServiceException{
		ShowApplicationAlertSummary response = SchemaConvertionUtils.qflexObjectMessageFactory.createShowApplicationAlertSummary();
    	
    	// Call service and collect results.
    	List<ApplicationAlertSummary> summary = null;
    	try {
			summary = qflexService.getApplicationAlertSummary();
			response.setApplicationAlertSummary(SchemaConvertionUtils.toXmlType(summary));
		} 
    	catch (QflexServiceException e) {
			throw new QflexWebServiceException(e.getMessage());
		}
    	return response;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws QflexWebServiceException
	 */
	@PayloadRoot(localPart = GET_APPLICATION_ALERTS, namespace = NAMESPACE_QFLEXMSG)
    public ShowApplicationAlerts getApplicationAlerts(final GetApplicationAlerts request) throws QflexWebServiceException{
		long applicationId = SchemaConvertionUtils.toLong(request.getApplicationId());
		
		ShowApplicationAlerts response = SchemaConvertionUtils.qflexObjectMessageFactory.createShowApplicationAlerts();
    	
    	// Call service and collect results.
    	try {
    		List<Alert> alerts = qflexService.getApplicationAlerts(applicationId);
			response.setAlerts(SchemaConvertionUtils.toXmlType(alerts));
		}
    	catch (QflexServiceException e) {
			throw new QflexWebServiceException(e.getMessage());
		}
    	return response;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws QflexWebServiceException
	 */
	@PayloadRoot(localPart = PROCESS_CHANGE_ALERTS_STATUS, namespace = NAMESPACE_QFLEXMSG)
    public AcknowledgeChangeAlertsStatus processChangeAlertsStatus(final ProcessChangeAlertsStatus request) throws QflexWebServiceException{
		List<Alert> alerts = SchemaConvertionUtils.toDomainType(request.getAlerts());
		AcknowledgeChangeAlertsStatus response = SchemaConvertionUtils.qflexObjectMessageFactory.createAcknowledgeChangeAlertsStatus();
    	
    	// Call service.
    	try {
			qflexService.changeAlertsStatus(alerts);
		}
    	catch (QflexServiceException e) {
			throw new QflexWebServiceException(e.getMessage());
		}
    	return response;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws QflexWebServiceException
	 */
	@PayloadRoot(localPart = GET_DAILY_APPLICATIONS_STATISTICS_SUMMARY, namespace = NAMESPACE_QFLEXMSG)
    public ShowDailyApplicationsStatisticsSummary getDailyApplicationsStatisticsSummary(final GetDailyApplicationsStatisticsSummary request) throws QflexWebServiceException{
		CompanyType company = request.getCompany();
		
		ShowDailyApplicationsStatisticsSummary response = SchemaConvertionUtils.qflexObjectMessageFactory.createShowDailyApplicationsStatisticsSummary();
    	
    	// Call service and collect results.
    	List<ApplicationsStatisticsSummary> statisticsSummary = null;
    	if(company != null){
	    	try {
	    		statisticsSummary = qflexService.getDailyApplicationsStatisticsSummary(SchemaConvertionUtils.toLong(company.getID()));
				response.setStatistics(SchemaConvertionUtils.toXmlType(statisticsSummary));
			} 
	    	catch (QflexServiceException e) {
				throw new QflexWebServiceException(e.getMessage());
			}
    	}
    	return response;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws QflexWebServiceException
	 */
	@PayloadRoot(localPart = GET_USER_PERMISSIONS, namespace = NAMESPACE_QFLEXMSG)
    public ShowUserPermissions getUserPermissions(final GetUserPermissions request) throws QflexWebServiceException{
		String username = request.getUsername();
		String password = request.getPassword();
		
		ShowUserPermissions response = SchemaConvertionUtils.qflexObjectMessageFactory.createShowUserPermissions();
    	
    	// Call service and collect results.
    	try {
    		Principals principal = qflexService.authenticate(username, password);
			response.setPrincipal(SchemaConvertionUtils.toXmlType(principal));
		} 
    	catch (QflexServiceException e) {
			throw new QflexWebServiceException(e.getMessage());
		}
    	return response;
	}
}
