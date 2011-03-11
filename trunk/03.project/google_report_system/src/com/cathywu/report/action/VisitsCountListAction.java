package com.cathywu.report.action;

import com.cathywu.report.common.GlobalAccountService;
import com.opensymphony.xwork2.ActionSupport;

public class VisitsCountListAction extends ActionSupport {

    @Override
    public String execute() throws Exception {
        try {
            if (!GlobalAccountService.isServiceAvailable()) {
                return "back_to_index";
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
}
