package com.netflexity.ws.qflex.blazeds;

import com.netflexity.qflex.business.dao.beans.ApplicationAlertSummary;
import com.netflexity.qflex.business.dao.beans.ApplicationsStatisticsSummary;
import com.netflexity.qflex.business.service.QflexServiceException;
import com.netflexity.qflex.business.service.QflexServiceImpl;
import com.netflexity.qflex.domain.Alert;
import com.netflexity.qflex.domain.Channel;
import com.netflexity.qflex.domain.Principals;
import com.netflexity.qflex.domain.Qmanager;
import com.netflexity.ws.qflex.blazeds.domain.ChannelWrapper;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.netflexity.api.mq.MqChannel;
import org.netflexity.api.mq.MqException;
import org.netflexity.api.mq.ibm.IBMPCFMqQmanagerManager;
import org.netflexity.api.mq.ibm.net.MqQmanagerAddress;
import org.netflexity.api.mq.ibm.net.MqQmanagerScanner;
import org.netflexity.api.mq.ibm.net.ScientificPortScannerCallback;

public class BlazeDsQflexServiceImpl implements BlazeDsQflexService {

    private QflexServiceImpl qflexService;

    public List<ApplicationsStatisticsSummary> getDailyApplicationsStatisticsSummary(long companyId) throws QflexServiceException {
        return qflexService.getDailyApplicationsStatisticsSummary(companyId);
    }

    public List<ApplicationAlertSummary> getApplicationAlertSummary() throws QflexServiceException {
        return qflexService.getApplicationAlertSummary();
    }

    public List<Alert> getApplicationAlerts(long applicationId) throws QflexServiceException {
        return qflexService.getApplicationAlerts(applicationId);
    }

    public List<Alert> viewAlerts(/*long companyId, */ long lastOccurrenceEndTm) throws QflexServiceException {
        return qflexService.viewAlerts(/*long companyId, */lastOccurrenceEndTm);
    }

    public Map<Long, String> getCriticalityTypes() throws QflexServiceException {
        return qflexService.getCriticalityTypes();
    }

    public Map<Long, String> getAlertStatusTypes() throws QflexServiceException {
        return qflexService.getCriticalityTypes();
    }

    public void changeAlertsStatus(List<Alert> alerts) throws QflexServiceException {
        qflexService.changeAlertsStatus(alerts);
    }

    public Principals authenticate(String username, String password) throws QflexServiceException {
        return qflexService.authenticate(username, password);
    }

    public Map<String, String> getQmanagers(){
        List<Qmanager> list =  qflexService.getQmanagersList();
        Map<String, String> rv = new HashMap<String, String>();
        if(list != null){
            for(Qmanager qm : list){
                rv.put(String.valueOf(qm.getQmanagerId()), qm.getQmanagerNm());
            }
        }
        return rv;
    }

    public List<ChannelWrapper> getTopology(long qmanagerId) throws QflexServiceException {
        Qmanager qm = qflexService.findQmanagerById(qmanagerId);
        return getTopology(qm.getQmanagerNm(), qm.getHostNm(), qm.getPort(), qm.getChannelNm());
    }

    public List<ChannelWrapper> getTopology(String qManagerName, String hostName, int port, String channelName) throws QflexServiceException {
        List<ChannelWrapper> rv = new ArrayList<ChannelWrapper>();
        try {
            IBMPCFMqQmanagerManager manager = createQManager(qManagerName, hostName, port, channelName);
            getTopology(manager, rv);
        } catch (MqException mqe) {
            mqe.printStackTrace();
            throw new QflexServiceException(mqe.getMessage(), mqe);
        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
            throw new QflexServiceException(uhe.getMessage(), uhe);
        }
        return rv;
    }

    private IBMPCFMqQmanagerManager createQManager(String qManagerName, String hostName, int port, String channelName) throws MqException {
        IBMPCFMqQmanagerManager manager = new IBMPCFMqQmanagerManager(
                qManagerName,
                hostName,
                port,
                channelName,
                null);
        return manager;
    }

    private String extractAddress(String input) {
        return input.substring(0, input.indexOf('('));
    }

    private String extractPort(String input) {
        return input.substring(input.indexOf('(') + 1, input.lastIndexOf(')'));
    }

    private void getTopology(IBMPCFMqQmanagerManager mgr, List<ChannelWrapper> container) throws MqException, UnknownHostException {

        List<MqChannel> channels = mgr.getTopology();
        for (MqChannel ch : channels) {
            ChannelWrapper cw = new ChannelWrapper(ch);
            container.add(cw);
            String connectionName = ch.getConnectionName();
            String channelName = ch.getChannelName();
            System.out.println("Adding channel: " + channelName);

            if (connectionName != null && connectionName.trim().length() > 0) {
                String address = extractAddress(connectionName);
                int port = Integer.parseInt(extractPort(connectionName));
                System.out.println("Scanning channel: " + channelName + " [" + address + "] : [" + port + "]");
                MqQmanagerScanner scanner = new MqQmanagerScanner();
                ScientificPortScannerCallback callback = new ScientificPortScannerCallback();
                scanner.setScannerCallback(callback);
                List<MqQmanagerAddress> list = scanner.scan(InetAddress.getByName(address), port, port);
                if (list != null) {
                    System.out.println("found: " + list.size() + " record(s)");
                    for (MqQmanagerAddress qa : list) {
                        System.out.println("explore: " + qa.getQueueManagerName() + " / " + qa.getChannelName() + " -- " + qa.getInetAddress().getHostName() + " : " + qa.getPort());
                        IBMPCFMqQmanagerManager m2 = new IBMPCFMqQmanagerManager(
                                qa.getQueueManagerName(),
                                qa.getInetAddress().getHostName(),
                                qa.getPort(),
                                qa.getChannelName(),
                                null);
                        getTopology(m2, cw.getChildren());
                    }
                }
            }
        }
    }

    public Map<String, List<String>> getChannelsList(String qmanagerName) {
        List<Qmanager> list = qflexService.getChannelsList(qmanagerName);
        Map<String, List<String>> rv = new HashMap<String, List<String>>();
        for(Qmanager qm : list){
            List chnls = new ArrayList<String>();
            rv.put(qm.getQmanagerNm(), chnls);
            Set<Channel> channels = qm.getChannels();
            if(channels != null){
                for(Channel ch : channels){
                    chnls.add(ch.getChannelNm());
                }
            }
        }
        return rv;
    }

    public void setQflexService(QflexServiceImpl qflexService) {
        this.qflexService = qflexService;
    }
}
