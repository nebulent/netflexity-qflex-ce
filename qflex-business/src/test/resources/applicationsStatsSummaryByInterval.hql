select new com.netflexity.qflex.business.dao.beans.ApplicationsStatisticsSummary(app.applicationId, app.applicationNm, appDests.destinationPurposeTypeId, sum(qs.msgOut), sum(qs.msgIn) ) 
from
  	Application app join app.applicationDestinations as appDests join appDests.queue as queues join queues.queueStats as qs
where 
	app.company.companyId = :companyId
	and ( (:givenTm BETWEEN qs.fetchStartTm AND qs.fetchEndTm) OR qs.fetchStartTm >= :givenTm)
group by app.applicationId, app.applicationNm, appDests.destinationPurposeTypeId
order by app.applicationNm, appDests.destinationPurposeTypeId