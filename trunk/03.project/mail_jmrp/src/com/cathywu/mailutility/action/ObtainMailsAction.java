/*
 * All Rights Reserved. Copyright(C) 2008 - 2010 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-4-13      Cathy Wu        Create
 */

package com.cathywu.mailutility.action;

import java.util.List;

import com.cathywu.mailutility.util.monitor;
import com.opensymphony.xwork2.ActionSupport;

public class ObtainMailsAction extends ActionSupport {

private final String address = "imap.gmail.com";
    
    private String email;
    
    private String password;
    
    private final String folderName = "Inbox";
    
    private List<String> emailList;
    
    public List<String> getEmailList() {
        return emailList;
    }

    @Override
    public String execute() throws Exception {
        emailList = monitor.callJmrpUtil(address, email, password, folderName);
        return SUCCESS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }
}
