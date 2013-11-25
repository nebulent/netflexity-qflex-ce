/**
 * 
 */
package com.netflexity.qflex.business.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netflexity.qflex.domain.Alert;
import com.netflexity.qflex.domain.Monitor;
import com.netflexity.qflex.domain.Principals;

/**
 * @author Administrator
 *
 */
public class QflexServiceTest {

    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    /**
     * Test method for {@link com.netflexity.qflex.business.service.QflexServiceImpl#getDailyApplicationsStatisticsSummary(long)}.
     */
    @Test
    public void testGetDailyApplicationsStatisticsSummary() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.netflexity.qflex.business.service.QflexServiceImpl#getApplicationAlertSummary()}.
     */
    @Test
    public void testGetApplicationAlertSummary() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.netflexity.qflex.business.service.QflexServiceImpl#getApplicationAlerts(long)}.
     */
    @Test
    public void testGetApplicationAlerts() {
        QflexServiceImpl qflexService = (QflexServiceImpl) context.getBean("qflexService");
        long applicationId = 1747445931963727872L;
        try {
            List<Alert> alerts = qflexService.getApplicationAlerts(applicationId);
            for (Alert alert : alerts) {
                System.out.println("Alert (" + alert.getAlertNm() + ")");
                Monitor monitor = alert.getMonitor();
                if (monitor != null) {
                    System.out.println("Monitor id=" + monitor.getMonitorId());
                }
            }
        } catch (QflexServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.netflexity.qflex.business.service.QflexServiceImpl#changeAlertsStatus(java.util.List)}.
     */
    @Test
    public void testChangeAlertsStatus() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.netflexity.qflex.business.service.QflexServiceImpl#authenticate(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testAuthenticate() {
        QflexServiceImpl qflexService = (QflexServiceImpl) context.getBean("qflexService");
        try {
            Principals pr = qflexService.authenticate("admin", "admin");
            System.out.println("username : " + pr.getUsername());
            System.out.println("password : " + pr.getPassword());
            System.out.println("company : " + pr.getCompany().getCompanyNm());
            
        } catch (QflexServiceException qse) {
            fail("auth failed");
        }

    }
}
