package com.netflexity.qflex.domain;

// Generated Jun 6, 2008 1:43:53 PM by Hibernate Tools 3.2.1.GA

/**
 * FrequencyTypes generated by hbm2java
 */
public class FrequencyTypes implements java.io.Serializable {

	private long frequencyTypeId;
	private long minIntervalTypeId;
	private long millis;
	private int displaySeqNo;
	private String description;

	public FrequencyTypes() {
	}

	public FrequencyTypes(long frequencyTypeId, long minIntervalTypeId,
			long millis, int displaySeqNo, String description) {
		this.frequencyTypeId = frequencyTypeId;
		this.minIntervalTypeId = minIntervalTypeId;
		this.millis = millis;
		this.displaySeqNo = displaySeqNo;
		this.description = description;
	}

	public long getFrequencyTypeId() {
		return this.frequencyTypeId;
	}

	public void setFrequencyTypeId(long frequencyTypeId) {
		this.frequencyTypeId = frequencyTypeId;
	}

	public long getMinIntervalTypeId() {
		return this.minIntervalTypeId;
	}

	public void setMinIntervalTypeId(long minIntervalTypeId) {
		this.minIntervalTypeId = minIntervalTypeId;
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
