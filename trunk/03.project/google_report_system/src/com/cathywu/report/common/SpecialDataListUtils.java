package com.cathywu.report.common;

import com.cathywu.report.InstanceInvalidException;


/**
 * 用于解决所有groupbuy在各自有效时间范围内的访问次数，超出此时间访问不计算
 * @author Cathy
 *
 */
public class SpecialDataListUtils {

    private GlobalAccountService service;
    
    public SpecialDataListUtils() throws InstanceInvalidException {
        service = GlobalAccountService.getInstance();        
    }
    
    
}
