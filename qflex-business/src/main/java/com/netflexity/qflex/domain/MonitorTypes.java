package com.netflexity.qflex.domain;

// Generated Jun 6, 2008 1:43:53 PM by Hibernate Tools 3.2.1.GA

/**
 * MonitorTypes generated by hbm2java
 */
public class MonitorTypes implements java.io.Serializable {

	private long monitorTypeId;
	private int displaySeqNo;
	private String description;

	public MonitorTypes() {
	}

	public MonitorTypes(long monitorTypeId, int displaySeqNo, String description) {
		this.monitorTypeId = monitorTypeId;
		this.displaySeqNo = displaySeqNo;
		this.description = description;
	}

	public long getMonitorTypeId() {
		return this.monitorTypeId;
	}

	public void setMonitorTypeId(long monitorTypeId) {
		this.monitorTypeId = monitorTypeId;
	}

	public int getDisplaySeqNo() {
		return this.displaySeqNo;
	}

	public void setDisplaySeqNo(int displaySeqNo) {
		this.displaySeqNo = displaySeqNo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}