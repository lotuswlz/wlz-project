package com.cathywu.report.common;

import com.cathywu.report.InstanceInvalidException;


/**
 * ���ڽ������groupbuy�ڸ�����Чʱ�䷶Χ�ڵķ��ʴ�����������ʱ����ʲ�����
 * @author Cathy
 *
 */
public class SpecialDataListUtils {

    private GlobalAccountService service;
    
    public SpecialDataListUtils() throws InstanceInvalidException {
        service = GlobalAccountService.getInstance();        
    }
    
    
}
