package com.netflexity.ws.qflex.blazeds.domain;

import java.util.ArrayList;
import java.util.List;
import org.netflexity.api.mq.MqChannel;

public class ChannelWrapper {

    private List<ChannelWrapper> children = new ArrayList<ChannelWrapper>();
    private String alterationDate;
    private String alterationTime;
    private Integer batchHeartbeat;
    private Integer batchInterval;
    private Integer batchSize;
    private String channelDesc;
    private String channelName;
    private Integer channelType;
    private String clusterName;
    private String clusterNamelist;
    private String connectionName;
    private Integer dataConversion;
    private Integer discInterval;
    private Integer heartbeatInterval;
    private String localAddress;
    private Integer longRetryCount;
    private Integer longRetryInterval;
    private Integer maxMsgLength;
    private String mcaName;
    private Integer mcaType;
    private String mcaUserIdentifier;
    private String modeName;
    private String msgExit;
    private Integer msgRetryCount;
    private String msgRetryExit;
    private Integer msgRetryInterval;
    private String msgRetryUserData;
    private String msgUserData;
    private Integer networkPriority;
    private Integer nonPersistentMsgSpeed;
    private String password;
    private String tpName;
    private String qmanagerName;
    private String receiveExit;
    private String receiveUserData;
    private String securityExit;
    private String securityUserData;
    private String sendExit;
    private String sendUserData;
    private Integer seqNumberWrap;
    private Integer shortRetryCount;
    private Integer shortRetryInterval;
    private String sslCipherSpec;
    private Integer sslClientAuth;
    private String sslPeerName;
    private Integer transportType;
    private String userIdentifier;
    private String xmitQueueName;
    private Integer channelStatus;
    private Integer channelInstanceType;
    private String channelStartDate;
    private String channelStartTime;
    private Integer connectionCount;

    public ChannelWrapper(MqChannel channel) {
        alterationDate = channel.getAlterationDate();
        alterationTime = channel.getAlterationTime();
        batchHeartbeat = channel.getBatchHeartbeat();
        batchInterval = channel.getBatchInterval();
        batchSize = channel.getBatchSize();
        channelDesc = channel.getChannelDesc();
        channelName = channel.getChannelName();
        channelType = channel.getChannelType();
        clusterName = channel.getClusterName();
        clusterNamelist = channel.getClusterNamelist();
        connectionName = channel.getConnectionName();
        dataConversion = channel.getDataConversion();
        discInterval = channel.getDiscInterval();
        heartbeatInterval = channel.getHeartbeatInterval();
        localAddress = channel.getLocalAddress();
        longRetryCount = channel.getLongRetryCount();
        longRetryInterval = channel.getLongRetryInterval();
        maxMsgLength = channel.getMaxMsgLength();
        mcaName = channel.getMcaName();
        mcaType = channel.getMcaType();
        mcaUserIdentifier = channel.getMcaUserIdentifier();
        modeName = channel.getModeName();
        msgExit = channel.getMsgExit();
        msgRetryCount = channel.getMsgRetryCount();
        msgRetryExit = channel.getMsgRetryExit();
        msgRetryInterval = channel.getMsgRetryInterval();
        msgRetryUserData = channel.getMsgRetryUserData();
        msgUserData = channel.getMsgUserData();
        networkPriority = channel.getNetworkPriority();
        nonPersistentMsgSpeed = channel.getNonPersistentMsgSpeed();
        password = channel.getPassword();
        tpName = channel.getTpName();
        qmanagerName = channel.getQmanagerName();
        receiveExit = channel.getReceiveExit();
        receiveUserData = channel.getReceiveUserData();
        securityExit = channel.getSecurityExit();
        securityUserData = channel.getSecurityUserData();
        sendExit = channel.getSendExit();
        sendUserData = channel.getSendUserData();
        seqNumberWrap = channel.getSeqNumberWrap();
        shortRetryCount = channel.getShortRetryCount();
        shortRetryInterval = channel.getShortRetryInterval();
        sslCipherSpec = channel.getSslCipherSpec();
        sslClientAuth = channel.getSslClientAuth();
        sslPeerName = channel.getSslPeerName();
        transportType = channel.getTransportType();
        userIdentifier = channel.getUserIdentifier();
        xmitQueueName = channel.getXmitQueueName();
        channelStatus = channel.getChannelStatus();
        channelInstanceType = channel.getChannelInstanceType();
        channelStartDate = channel.getChannelStartDate();
        channelStartTime = channel.getChannelStartTime();
        connectionCount = channel.getConnectionCount();
    }

    public List<ChannelWrapper> getChildren() {
        return children;
    }

    public void setChildren(List<ChannelWrapper> children) {
        this.children = children;
    }

    /**
     * @return the alterationDate
     */
    public String getAlterationDate() {
        return alterationDate;
    }

    /**
     * @param alterationDate the alterationDate to set
     */
    public void setAlterationDate(String alterationDate) {
        this.alterationDate = alterationDate;
    }

    /**
     * @return the alterationTime
     */
    public String getAlterationTime() {
        return alterationTime;
    }

    /**
     * @param alterationTime the alterationTime to set
     */
    public void setAlterationTime(String alterationTime) {
        this.alterationTime = alterationTime;
    }

    /**
     * @return the batchHeartbeat
     */
    public int getBatchHeartbeat() {
        return batchHeartbeat;
    }

    /**
     * @param batchHeartbeat the batchHeartbeat to set
     */
    public void setBatchHeartbeat(int batchHeartbeat) {
        this.batchHeartbeat = batchHeartbeat;
    }

    /**
     * @return the batchInterval
     */
    public int getBatchInterval() {
        return batchInterval;
    }

    /**
     * @param batchInterval the batchInterval to set
     */
    public void setBatchInterval(int batchInterval) {
        this.batchInterval = batchInterval;
    }

    /**
     * @return the batchSize
     */
    public int getBatchSize() {
        return batchSize;
    }

    /**
     * @param batchSize the batchSize to set
     */
    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    /**
     * @return the channelDesc
     */
    public String getChannelDesc() {
        return channelDesc;
    }

    /**
     * @param channelDesc the channelDesc to set
     */
    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }

    /**
     * @return the channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * @param channelName the channelName to set
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * @return the channelType
     */
    public int getChannelType() {
        return channelType;
    }

    /**
     * @param channelType the channelType to set
     */
    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    /**
     * @return the clusterName
     */
    public String getClusterName() {
        return clusterName;
    }

    /**
     * @param clusterName the clusterName to set
     */
    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    /**
     * @return the clusterNamelist
     */
    public String getClusterNamelist() {
        return clusterNamelist;
    }

    /**
     * @param clusterNamelist the clusterNamelist to set
     */
    public void setClusterNamelist(String clusterNamelist) {
        this.clusterNamelist = clusterNamelist;
    }

    /**
     * @return the connectionName
     */
    public String getConnectionName() {
        return connectionName;
    }

    /**
     * @param connectionName the connectionName to set
     */
    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    /**
     * @return the dataConversion
     */
    public int getDataConversion() {
        return dataConversion;
    }

    /**
     * @param dataConversion the dataConversion to set
     */
    public void setDataConversion(int dataConversion) {
        this.dataConversion = dataConversion;
    }

    /**
     * @return the discInterval
     */
    public int getDiscInterval() {
        return discInterval;
    }

    /**
     * @param discInterval the discInterval to set
     */
    public void setDiscInterval(int discInterval) {
        this.discInterval = discInterval;
    }

    /**
     * @return the heartbeatInterval
     */
    public int getHeartbeatInterval() {
        return heartbeatInterval;
    }

    /**
     * @param heartbeatInterval the heartbeatInterval to set
     */
    public void setHeartbeatInterval(int heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
    }

    /**
     * @return the localAddress
     */
    public String getLocalAddress() {
        return localAddress;
    }

    /**
     * @param localAddress the localAddress to set
     */
    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    /**
     * @return the longRetryCount
     */
    public int getLongRetryCount() {
        return longRetryCount;
    }

    /**
     * @param longRetryCount the longRetryCount to set
     */
    public void setLongRetryCount(int longRetryCount) {
        this.longRetryCount = longRetryCount;
    }

    /**
     * @return the longRetryInterval
     */
    public int getLongRetryInterval() {
        return longRetryInterval;
    }

    /**
     * @param longRetryInterval the longRetryInterval to set
     */
    public void setLongRetryInterval(int longRetryInterval) {
        this.longRetryInterval = longRetryInterval;
    }

    /**
     * @return the maxMsgLength
     */
    public int getMaxMsgLength() {
        return maxMsgLength;
    }

    /**
     * @param maxMsgLength the maxMsgLength to set
     */
    public void setMaxMsgLength(int maxMsgLength) {
        this.maxMsgLength = maxMsgLength;
    }

    /**
     * @return the mcaName
     */
    public String getMcaName() {
        return mcaName;
    }

    /**
     * @param mcaName the mcaName to set
     */
    public void setMcaName(String mcaName) {
        this.mcaName = mcaName;
    }

    /**
     * @return the mcaType
     */
    public int getMcaType() {
        return mcaType;
    }

    /**
     * @param mcaType the mcaType to set
     */
    public void setMcaType(int mcaType) {
        this.mcaType = mcaType;
    }

    /**
     * @return the mcaUserIdentifier
     */
    public String getMcaUserIdentifier() {
        return mcaUserIdentifier;
    }

    /**
     * @param mcaUserIdentifier the mcaUserIdentifier to set
     */
    public void setMcaUserIdentifier(String mcaUserIdentifier) {
        this.mcaUserIdentifier = mcaUserIdentifier;
    }

    /**
     * @return the modeName
     */
    public String getModeName() {
        return modeName;
    }

    /**
     * @param modeName the modeName to set
     */
    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    /**
     * @return the msgExit
     */
    public String getMsgExit() {
        return msgExit;
    }

    /**
     * @param msgExit the msgExit to set
     */
    public void setMsgExit(String msgExit) {
        this.msgExit = msgExit;
    }

    /**
     * @return the msgRetryCount
     */
    public int getMsgRetryCount() {
        return msgRetryCount;
    }

    /**
     * @param msgRetryCount the msgRetryCount to set
     */
    public void setMsgRetryCount(int msgRetryCount) {
        this.msgRetryCount = msgRetryCount;
    }

    /**
     * @return the msgRetryExit
     */
    public String getMsgRetryExit() {
        return msgRetryExit;
    }

    /**
     * @param msgRetryExit the msgRetryExit to set
     */
    public void setMsgRetryExit(String msgRetryExit) {
        this.msgRetryExit = msgRetryExit;
    }

    /**
     * @return the msgRetryInterval
     */
    public int getMsgRetryInterval() {
        return msgRetryInterval;
    }

    /**
     * @param msgRetryInterval the msgRetryInterval to set
     */
    public void setMsgRetryInterval(int msgRetryInterval) {
        this.msgRetryInterval = msgRetryInterval;
    }

    /**
     * @return the msgRetryUserData
     */
    public String getMsgRetryUserData() {
        return msgRetryUserData;
    }

    /**
     * @param msgRetryUserData the msgRetryUserData to set
     */
    public void setMsgRetryUserData(String msgRetryUserData) {
        this.msgRetryUserData = msgRetryUserData;
    }

    /**
     * @return the msgUserData
     */
    public String getMsgUserData() {
        return msgUserData;
    }

    /**
     * @param msgUserData the msgUserData to set
     */
    public void setMsgUserData(String msgUserData) {
        this.msgUserData = msgUserData;
    }

    /**
     * @return the networkPriority
     */
    public int getNetworkPriority() {
        return networkPriority;
    }

    /**
     * @param networkPriority the networkPriority to set
     */
    public void setNetworkPriority(int networkPriority) {
        this.networkPriority = networkPriority;
    }

    /**
     * @return the nonPersistentMsgSpeed
     */
    public int getNonPersistentMsgSpeed() {
        return nonPersistentMsgSpeed;
    }

    /**
     * @param nonPersistentMsgSpeed the nonPersistentMsgSpeed to set
     */
    public void setNonPersistentMsgSpeed(int nonPersistentMsgSpeed) {
        this.nonPersistentMsgSpeed = nonPersistentMsgSpeed;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tpName
     */
    public String getTpName() {
        return tpName;
    }

    /**
     * @param tpName the tpName to set
     */
    public void setTpName(String tpName) {
        this.tpName = tpName;
    }

    /**
     * @return the qmanagerName
     */
    public String getQmanagerName() {
        return qmanagerName;
    }

    /**
     * @param qmanagerName the qmanagerName to set
     */
    public void setQmanagerName(String qmanagerName) {
        this.qmanagerName = qmanagerName;
    }

    /**
     * @return the receiveExit
     */
    public String getReceiveExit() {
        return receiveExit;
    }

    /**
     * @param receiveExit the receiveExit to set
     */
    public void setReceiveExit(String receiveExit) {
        this.receiveExit = receiveExit;
    }

    /**
     * @return the receiveUserData
     */
    public String getReceiveUserData() {
        return receiveUserData;
    }

    /**
     * @param receiveUserData the receiveUserData to set
     */
    public void setReceiveUserData(String receiveUserData) {
        this.receiveUserData = receiveUserData;
    }

    /**
     * @return the securityExit
     */
    public String getSecurityExit() {
        return securityExit;
    }

    /**
     * @param securityExit the securityExit to set
     */
    public void setSecurityExit(String securityExit) {
        this.securityExit = securityExit;
    }

    /**
     * @return the securityUserData
     */
    public String getSecurityUserData() {
        return securityUserData;
    }

    /**
     * @param securityUserData the securityUserData to set
     */
    public void setSecurityUserData(String securityUserData) {
        this.securityUserData = securityUserData;
    }

    /**
     * @return the sendExit
     */
    public String getSendExit() {
        return sendExit;
    }

    /**
     * @param sendExit the sendExit to set
     */
    public void setSendExit(String sendExit) {
        this.sendExit = sendExit;
    }

    /**
     * @return the sendUserData
     */
    public String getSendUserData() {
        return sendUserData;
    }

    /**
     * @param sendUserData the sendUserData to set
     */
    public void setSendUserData(String sendUserData) {
        this.sendUserData = sendUserData;
    }

    /**
     * @return the seqNumberWrap
     */
    public int getSeqNumberWrap() {
        return seqNumberWrap;
    }

    /**
     * @param seqNumberWrap the seqNumberWrap to set
     */
    public void setSeqNumberWrap(int seqNumberWrap) {
        this.seqNumberWrap = seqNumberWrap;
    }

    /**
     * @return the shortRetryCount
     */
    public int getShortRetryCount() {
        return shortRetryCount;
    }

    /**
     * @param shortRetryCount the shortRetryCount to set
     */
    public void setShortRetryCount(int shortRetryCount) {
        this.shortRetryCount = shortRetryCount;
    }

    /**
     * @return the shortRetryInterval
     */
    public int getShortRetryInterval() {
        return shortRetryInterval;
    }

    /**
     * @param shortRetryInterval the shortRetryInterval to set
     */
    public void setShortRetryInterval(int shortRetryInterval) {
        this.shortRetryInterval = shortRetryInterval;
    }

    /**
     * @return the sslCipherSpec
     */
    public String getSslCipherSpec() {
        return sslCipherSpec;
    }

    /**
     * @param sslCipherSpec the sslCipherSpec to set
     */
    public void setSslCipherSpec(String sslCipherSpec) {
        this.sslCipherSpec = sslCipherSpec;
    }

    /**
     * @return the sslClientAuth
     */
    public int getSslClientAuth() {
        return sslClientAuth;
    }

    /**
     * @param sslClientAuth the sslClientAuth to set
     */
    public void setSslClientAuth(int sslClientAuth) {
        this.sslClientAuth = sslClientAuth;
    }

    /**
     * @return the sslPeerName
     */
    public String getSslPeerName() {
        return sslPeerName;
    }

    /**
     * @param sslPeerName the sslPeerName to set
     */
    public void setSslPeerName(String sslPeerName) {
        this.sslPeerName = sslPeerName;
    }

    /**
     * @return the transportType
     */
    public int getTransportType() {
        return transportType;
    }

    /**
     * @param transportType the transportType to set
     */
    public void setTransportType(int transportType) {
        this.transportType = transportType;
    }

    /**
     * @return the userIdentifier
     */
    public String getUserIdentifier() {
        return userIdentifier;
    }

    /**
     * @param userIdentifier the userIdentifier to set
     */
    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    /**
     * @return the xmitQueueName
     */
    public String getXmitQueueName() {
        return xmitQueueName;
    }

    /**
     * @param xmitQueueName the xmitQueueName to set
     */
    public void setXmitQueueName(String xmitQueueName) {
        this.xmitQueueName = xmitQueueName;
    }

    /**
     * @return the channelStatus
     */
    public int getChannelStatus() {
        return channelStatus;
    }

    /**
     * @param channelStatus the channelStatus to set
     */
    public void setChannelStatus(int channelStatus) {
        this.channelStatus = channelStatus;
    }

    /**
     * @return the channelInstanceType
     */
    public int getChannelInstanceType() {
        return channelInstanceType;
    }

    /**
     * @param channelInstanceType the channelInstanceType to set
     */
    public void setChannelInstanceType(int channelInstanceType) {
        this.channelInstanceType = channelInstanceType;
    }

    /**
     * @return the channelStartDate
     */
    public String getChannelStartDate() {
        return channelStartDate;
    }

    /**
     * @param channelStartDate the channelStartDate to set
     */
    public void setChannelStartDate(String channelStartDate) {
        this.channelStartDate = channelStartDate;
    }

    /**
     * @return the channelStartTime
     */
    public String getChannelStartTime() {
        return channelStartTime;
    }

    /**
     * @param channelStartTime the channelStartTime to set
     */
    public void setChannelStartTime(String channelStartTime) {
        this.channelStartTime = channelStartTime;
    }

    /**
     * @return the connectionCount
     */
    public int getConnectionCount() {
        return connectionCount;
    }

    /**
     * @param connectionCount the connectionCount to set
     */
    public void setConnectionCount(int connectionCount) {
        this.connectionCount = connectionCount;
    }
}
