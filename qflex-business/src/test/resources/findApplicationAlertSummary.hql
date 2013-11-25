select
  new com.netflexity.qflex.business.dao.beans.ApplicationAlertDetails(app.applicationId, app.applicationNm, monitorAlerts.criticalityTypeId, count(monitorAlerts.alertId) ) 
 from
  Application app join app.applicationDestinations as appDests join appDests.queue as queues join queues.monitors queueMonitors join queueMonitors.alerts as monitorAlerts
 where
  monitorAlerts.alertStatusTypeId = 5
 group by app.applicationId, app.applicationNm, monitorAlerts.criticalityTypeId