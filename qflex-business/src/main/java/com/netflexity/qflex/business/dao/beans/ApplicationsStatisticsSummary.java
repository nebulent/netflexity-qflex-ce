/**
 * 
 */
package com.netflexity.qflex.business.dao.beans;

import com.netflexity.qflex.domain.enums.ApplicationDestinationPurposeTypes;

/**
 * @author Administrator
 *
 */
public class ApplicationsStatisticsSummary {
	
	private long inputMessages;
    private long outputMessages;
    private ApplicationDestinationPurposeTypes destinationPurposeType;
    private long applicationId;
    private String applicationName;
    
	/**
	 * @param applicationId
	 * @param applicationName
	 * @param destinationPurposeType
	 * @param outputMessages
	 * @param inputMessages
	 */
	public ApplicationsStatisticsSummary(long applicationId, String applicationName,
			int destinationPurposeType,
			long outputMessages, long inputMessages) {
		this.applicationId = applicationId;
		this.applicationName = applicationName;
		setDestinationPurposeType(destinationPurposeType);
		this.outputMessages = outputMessages;
		this.inputMessages = inputMessages;
	}
	/**
	 * @return the inputMessages
	 */
	public long getInputMessages() {
		return inputMessages;
	}
	/**
	 * @param inputMessages the inputMessages to set
	 */
	public void setInputMessages(long inputMessages) {
		this.inputMessages = inputMessages;
	}
	/**
	 * @return the outputMessages
	 */
	public long getOutputMessages() {
		return outputMessages;
	}
	/**
	 * @param outputMessages the outputMessages to set
	 */
	public void setOutputMessages(long outputMessages) {
		this.outputMessages = outputMessages;
	}
	/**
	 * @return the destinationPurposeType
	 */
	public ApplicationDestinationPurposeTypes getDestinationPurposeType() {
		return destinationPurposeType;
	}
	/**
	 * @param destinationPurposeType the destinationPurposeType to set
	 */
	public void setDestinationPurposeType(int destinationPurposeType) {
		this.destinationPurposeType = ApplicationDestinationPurposeTypes.getEnum(destinationPurposeType);
	}
	/**
	 * @return the applicationId
	 */
	public long getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}
	/**
	 * @param applicationName the applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}
