package com.cathywu.report.action;

import org.apache.log4j.Logger;

import com.cathywu.report.common.GlobalAccountService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

    /**
     * 
     */
    private static final long serialVersionUID = -7685417256399746110L;
    private String username;
    private String password;
    
    private static Logger log = Logger.getLogger(LoginAction.class);
    
    @Override
    public String execute() throws Exception {
        try {
            if (!GlobalAccountService.isServiceAvailable()
                    && this.username != null && this.password != null
                    && !this.username.trim().equals("")
                    && !this.password.trim().equals("")) {
                GlobalAccountService.getInstance(username, password);
            } else if (!GlobalAccountService.isServiceAvailable()
                    && (this.username == null || this.password == null
                            || this.username.trim().equals("") || this.password
                            .trim().equals(""))) {
                return "to_login";
            } else if (GlobalAccountService.isServiceAvailable()) {
                log.info("Google analytics instance available.");
                return SUCCESS;
            } else {
                log.info("Login failure");
                return ERROR;
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
