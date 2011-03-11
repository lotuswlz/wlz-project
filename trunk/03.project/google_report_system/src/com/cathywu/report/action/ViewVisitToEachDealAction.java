package com.cathywu.report.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.cathywu.report.ValidateDataException;
import com.cathywu.report.bean.CampaignLaunchTimeBean;
import com.cathywu.report.common.GlobalAccountService;
import com.opensymphony.xwork2.ActionSupport;

public class ViewVisitToEachDealAction extends ActionSupport {

    private static Logger log = Logger.getLogger(ViewVisitToEachDealAction.class);
    
    private List<CampaignLaunchTimeBean> campaignList;
    
    // input
    private String filePath;
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public String execute() throws Exception {
        try {
        if (!GlobalAccountService.isServiceAvailable()) {
            log.error("Google analytics service is not available now, please login again.");
            return ERROR;
        }
        return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }
    
    @Override
    public void validate() {
        if (this.filePath == null || "".equals(this.filePath.trim())) {
            addFieldError("filepath", "Please enter a file path.");
        }
    }
    
    private void getFileContent() {
        try {
            File file = new File(filePath);
            if (!file.isFile()) {
                return;
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            List<String[]> list = new ArrayList<String[]>();
            String temp = null;
            String[] result = null;
            while ((temp = br.readLine()) != null) {
                if (!"".equals(temp.trim())) {
                    result = temp.split(",");
                }
            }
            br.close();
            fr.close();
            if (list.size() > 0) {
                int len = list.size();
                String[][] array = new String[len][];
                for (int i = 0; i < len; i++) {
                    array[i] = list.get(i);
                }
                return;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private CampaignLaunchTimeBean parseCampaignInfo(String[] arr) throws ValidateDataException {
        if (arr == null || arr.length < 3) {
            throw new ValidateDataException("Wrong Data in File.");
        }
        try {
            Long campaignId = Long.parseLong(arr[0]);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mi:ss");
            Date beginDate = sdf.parse(arr[1]);
            Date endDate = sdf.parse(arr[2]);
            CampaignLaunchTimeBean campaign = new CampaignLaunchTimeBean(campaignId, beginDate, endDate);
            return campaign;
        } catch (NumberFormatException e) {
            log.error("wrong campaign id");
            throw new ValidateDataException("Wrong Data in File.");
        } catch (ParseException e) {
            log.error("wrong launch time");
            throw new ValidateDataException("Wrong Data in File.");
        }
    }
    public List<CampaignLaunchTimeBean> getCampaignList() {
        return campaignList;
    }
}
