package com.netflexity.ws.qflex.blazeds;

import com.netflexity.qflex.business.dao.beans.ApplicationAlertSummary;
import com.netflexity.qflex.business.dao.beans.ApplicationsStatisticsSummary;
import com.netflexity.qflex.business.service.QflexServiceException;
import com.netflexity.qflex.domain.Alert;
import com.netflexity.qflex.domain.Principals;
import com.netflexity.ws.qflex.blazeds.domain.ChannelWrapper;
import java.util.List;
import java.util.Map;

public interface BlazeDsQflexService {

    public List<ApplicationsStatisticsSummary> getDailyApplicationsStatisticsSummary(long companyId) throws QflexServiceException;

    public List<ApplicationAlertSummary> getApplicationAlertSummary() throws QflexServiceException;

    public List<Alert> getApplicationAlerts(long applicationId) throws QflexServiceException;

    public void changeAlertsStatus(List<Alert> alerts) throws QflexServiceException;

    public Principals authenticate(String username, String password) throws QflexServiceException;

    public List<ChannelWrapper> getTopology(String qManagerName, String hostName, int port, String channelName) throws QflexServiceException;

    public Map<String, List<String>> getChannelsList(String qmanagerName);

    public Map<String, String> getQmanagers();

    public List<ChannelWrapper> getTopology(long qmanagerId) throws QflexServiceException;

    public List<Alert> viewAlerts(/*long companyId, */ long lastOccurrenceEndTm) throws QflexServiceException;

    public Map<Long, String> getCriticalityTypes() throws QflexServiceException;

    public Map<Long, String> getAlertStatusTypes() throws QflexServiceException;

}
