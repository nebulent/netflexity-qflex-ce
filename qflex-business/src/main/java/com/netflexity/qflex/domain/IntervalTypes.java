package com.netflexity.qflex.domain;

// Generated Jun 6, 2008 1:43:53 PM by Hibernate Tools 3.2.1.GA

/**
 * IntervalTypes generated by hbm2java
 */
public class IntervalTypes implements java.io.Serializable {

	private long intervalTypeId;
	private long millis;
	private int displaySeqNo;
	private String description;

	public IntervalTypes() {
	}

	public IntervalTypes(long intervalTypeId, long millis, int displaySeqNo,
			String description) {
		this.intervalTypeId = intervalTypeId;
		this.millis = millis;
		this.displaySeqNo = displaySeqNo;
		this.description = description;
	}

	public long getIntervalTypeId() {
		return this.intervalTypeId;
	}

	public void setIntervalTypeId(long intervalTypeId) {
		this.intervalTypeId = intervalTypeId;
	}

	public long getMillis() {
		return this.millis;
	}

	public void setMillis(long millis) {
		this.millis = millis;
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
