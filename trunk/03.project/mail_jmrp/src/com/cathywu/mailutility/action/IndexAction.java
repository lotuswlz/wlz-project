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

public class IndexAction extends ActionSupport {
    
    @Override
    public String execute() throws Exception {
        System.out.println("Enter into jmrp tools...");
        return SUCCESS;
    }
}
