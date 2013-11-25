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
public enum CriticalityTypes{
    
	FAILURE(1),
	PROBLEM(2),
	WARNING(3),
	INFO(4);
    
    private Long type;
    
    /**
     * @param arg0
     */
    private CriticalityTypes(long type) {
        this.type = new Long(type);
    }

    /**
     * @return
     */
    public long getType(){
        return type.longValue();
    }
    
    /**
     * @return
     */
    public Long getLongType(){
        return type;
    }
    
    /**
     * @param type
     * @return
     */
    public static CriticalityTypes getEnum(Long type){
        for (CriticalityTypes en : values()) {
            if(type.equals(en.getLongType())){
                return en;
            }
        }
        return null;
    }
}
