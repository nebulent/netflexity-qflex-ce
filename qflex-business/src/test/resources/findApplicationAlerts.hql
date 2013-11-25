select monitorAlerts
from Application app join app.applicationDestinations as appDests join appDests.queue as queues join queues.monitors queueMonitors join queueMonitors.alerts as monitorAlerts
where monitorAlerts.alertStatusTypeId = 5 and app.applicationId = :applicationId and monitorAlerts.alert is null
order by monitorAlerts.occurrenceStartTm desc