/*
 *  2005 Netflexity, Ltd. All Rights Reserved.
 *
 * CONFIDENTIAL BUSINESS INFORMATION
 *
 * THIS PROGRAM IS PROPRIETARY INFORMATION OF NETFLEXITY, LTD. AND
 * IS NOT TO BE COPIED, REPRODUCED, LENT, OR DISPOSED OF, NOR USED FOR ANY
 * OTHER PURPOSE, UNLESS THE WRITTEN PERMISSION FROM THE STATED ABOVE CORPORATION
 * IS GIVEN.
 */
package com.netflexity.qflex.domain.enums;


/**
 * Enumeration for all criticality types.
 * 
 * @author MAX
 * 
 */
public enum ApplicationDestinationPurposeTypes{
    
	INPUT(1),
	OUTPUT(2),
	ERROR(3);
    
    private Integer type;
    
    /**
     * @param arg0
     */
    private ApplicationDestinationPurposeTypes(int type) {
        this.type = new Integer(type);
    }

    /**
     * @return
     */
    public int getType(){
        return type.intValue();
    }
    
    /**
     * @param type
     * @return
     */
    public static ApplicationDestinationPurposeTypes getEnum(int type){
        for (ApplicationDestinationPurposeTypes en : values()) {
            if(type == en.getType()){
                return en;
            }
        }
        return null;
    }
}
