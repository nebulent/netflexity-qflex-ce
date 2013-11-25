package com.netflexity.ws.qflex.blazeds;

import com.netflexity.qflex.domain.Alert;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BlazeDsQflexServiceImplTest {

    private static ApplicationContext context = new ClassPathXmlApplicationContext("qflex-context-test.xml");
    private static BlazeDsQflexService blazedsService = (BlazeDsQflexService) context.getBean("blazedsService");

    @Test
    public void testGetTopology() {
        String qManagerName = "FP01";
        String hostName = "184.73.219.202";
        int port = 1414;
        String channelName = "SYSTEM.DEF.SVRCONN";
//        try {
//            List<ChannelWrapper> topology = blazedsService.getTopology(qManagerName, hostName, port, channelName);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail();
//        }
    }

    @Test
    public void testQmanagers() {
        Map<String, List<String>> list = blazedsService.getChannelsList("FP02");
        for (String qm : list.keySet()) {
            System.out.println("qmanager: " + qm);
            List<String> channels = list.get(qm);
            for (String c : channels) {
                System.out.println("\t" + c);
            }
        }
    }

    @Test
    public void testGetAlerts() {
        try {
            List<Alert> alerts = blazedsService.viewAlerts(-1);
            for (Alert alert : alerts) {
                System.out.println(alert.getAlertNm() + " " + alert.getMonitor().getMonitorNm() + " " + alert.getMonitor().getQmanagerByQmanagerId().getQmanagerNm());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
