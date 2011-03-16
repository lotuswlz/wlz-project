package com.cathywu.report.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.cathywu.report.InstanceInvalidException;
import com.cathywu.report.ValidateDataException;
import com.cathywu.report.bean.CampaignLaunchTimeBean;
import com.cathywu.report.bean.QueryParam;
import com.cathywu.report.common.GlobalAccountService;
import com.cathywu.report.util.DataSourceUtils;
import com.google.gdata.data.analytics.DataEntry;
import com.google.gdata.data.analytics.DataFeed;
import com.opensymphony.xwork2.ActionSupport;

public class ViewVisitToEachDealAction extends ActionSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 4877289535433747474L;

    private static Logger log = Logger
            .getLogger(ViewVisitToEachDealAction.class);

    private List<CampaignLaunchTimeBean> campaignList;

    private GlobalAccountService service;
    
    private String[] totalTimeRange;
    
    private String timeRangeStr;

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
                log
                        .error("Google analytics service is not available now, please login again.");
                return "to_login";
            }
            if (this.filePath == null || "".equals(this.filePath.trim())) {
                log.error("File not exist.");
                return "fill_page";
            }
            service = GlobalAccountService.getInstance();
            this.totalTimeRange = new String[2];
            // get campaign launch time map
            this.campaignList = getFileContent();
            LinkedHashMap<String, List<CampaignLaunchTimeBean>> map = pushVisitToEachDeal(sortCampaignList(this.campaignList));
            mergeDataVisit(map);
            for (CampaignLaunchTimeBean b : this.campaignList) {
                System.out.println(b.getCampaignId() + ":" + b.getPageUniqueViews());
            }
            return SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    @Override
    public void validate() {
        if (this.filePath == null || "".equals(this.filePath.trim())) {
            addFieldError("filepath", "Please enter a file path.");
        }
    }

    /**
     * 将csv文件转换成campaign bean list，每个bean包含了campaign id和起止时间。 返回列表
     * 
     * @throws ValidateDataException
     */
    public List<CampaignLaunchTimeBean> getFileContent()
            throws ValidateDataException {
        try {
            File file = new File(filePath);
            if (!file.isFile()) {
                return null;
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            List<CampaignLaunchTimeBean> list = new ArrayList<CampaignLaunchTimeBean>();
            String temp = null;
            String[] result = null;
            CampaignLaunchTimeBean bean = null;
            while ((temp = br.readLine()) != null) {
                if (!"".equals(temp.trim())) {
                    result = temp.split(",");
                    bean = parseCampaignInfo(result);
                    list.add(bean);
                }
            }
            br.close();
            fr.close();
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ValidateDataException("File not found");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ValidateDataException("File cannot be read.");
        } catch (ValidateDataException e) {
            e.printStackTrace();
            throw new ValidateDataException("Wrong Data in File.");
        }
    }

    private CampaignLaunchTimeBean parseCampaignInfo(String[] arr)
            throws ValidateDataException {
        if (arr == null || arr.length < 3) {
            throw new ValidateDataException("Wrong Data in File.");
        }
        try {
            Long campaignId = Long.parseLong(arr[0]);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginDate = sdf.parse(arr[1]);
            Date endDate = sdf.parse(arr[2]);
            CampaignLaunchTimeBean campaign = new CampaignLaunchTimeBean(
                    campaignId, beginDate, endDate);
            return campaign;
        } catch (NumberFormatException e) {
            log.error("wrong campaign id");
            e.printStackTrace();
            throw new ValidateDataException("Wrong Data in File.");
        } catch (ParseException e) {
            log.error("wrong launch time");
            e.printStackTrace();
            throw new ValidateDataException("Wrong Data in File.");
        }
    }

    /**
     * 将杂乱的campaign list排序， 获得最早的开始时间和，最晚的结束时间，作为本次查询时间范围。
     * 将列表按天分成若干子列表，每个列表是当天active的campaign bean。 返回HashMap，key是日期。
     * 
     * @param list
     * @return LinkedHashMap
     * @throws ParseException
     */
    public LinkedHashMap<String, List<CampaignLaunchTimeBean>> sortCampaignList(
            List<CampaignLaunchTimeBean> list) throws ParseException {
        // 按照开始时间排序
        CampaignLaunchTimeBean bean = null;
        Date lastDay = list.get(0).getEndDate();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (lastDay.before(list.get(j).getEndDate())) {
                    lastDay = new Date(list.get(j).getEndDate().getTime());
                }
                if (list.get(i).getBeginDate()
                        .after(list.get(j).getBeginDate())) {
                    bean = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, bean);
                }
            }
        }
        // 得到每一天的日期列表（纯日期）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LinkedHashMap<String, List<CampaignLaunchTimeBean>> map = new LinkedHashMap<String, List<CampaignLaunchTimeBean>>();
        Date firstDay = list.get(0).getBeginDate();
        firstDay = sdf.parse(sdf.format(firstDay));
        
        this.totalTimeRange[0] = sdf.format(firstDay);
        this.totalTimeRange[1] = sdf.format(lastDay);
        
        lastDay = new Date(
                sdf.parse(sdf.format(lastDay)).getTime() + 24 * 3600 * 1000);
        for (long s = firstDay.getTime(); s < lastDay.getTime(); s = s + 24 * 3600 * 1000) {
            map.put(sdf.format(new Date(s)),
                    new ArrayList<CampaignLaunchTimeBean>());
        }
        // 逐天、逐列表将在当天范围内的campaign插入子列表
        Date day = null;
        Date bd = null;
        Date ed = null;
        for (Iterator<Entry<String, List<CampaignLaunchTimeBean>>> it = map
                .entrySet().iterator(); it.hasNext();) {
            Entry<String, List<CampaignLaunchTimeBean>> e = it.next();
            day = sdf.parse(e.getKey());
            for (int i = 0; i < list.size(); i++) {
                bean = list.get(i);
                bd = sdf.parse(sdf.format(bean.getBeginDate()));
                ed = sdf.parse(sdf.format(bean.getEndDate()));
                if (bd.getTime() == day.getTime()
                        || ed.getTime() == day.getTime()
                        || (bd.getTime() < day.getTime() && ed.getTime() > day
                                .getTime())) {
                    e.getValue().add(bean);
                }
            }
            /** begin: test */
            String tmp = "";
            for (int j = 0; j < e.getValue().size(); j++) {
                tmp += e.getValue().get(j).getCampaignId() + ",";
            }
            log.info(e.getKey() + ":" + tmp);
            /** end: test */
        }
        return map;
    }

    private List<CampaignLaunchTimeBean> fetchData(String[] timeRange,
            String key, List<CampaignLaunchTimeBean> cList)
            throws InstanceInvalidException, CloneNotSupportedException {
        if (cList == null || cList.size() == 0) {
            return cList;
        }

        String campaignIds = "";

        for (CampaignLaunchTimeBean b : cList) {
            campaignIds += b.getCampaignId() + "|";
        }
        campaignIds = campaignIds.substring(0, campaignIds.length() - 1);

        campaignIds = "(" + campaignIds + ")";

        QueryParam query = DataSourceUtils.getQueryParamMap().get(key).clone();
        query.setFilters(query.getFilters() + ";ga:pagePath=~^.*/"
                + campaignIds);
        log.info("query filter: " + query.getFilters());
        query.setStartDate(timeRange[0]);
        query.setEndDate(timeRange[1]);
        DataFeed dataFeed = null;

        dataFeed = service.getDataFeed(query);
        int maxResult = dataFeed.getTotalResults();
        int pageSize = new BigDecimal(maxResult).divide(new BigDecimal(1000),
                RoundingMode.UP).intValue();
        dataFeed.setEntries(pagedList(query, pageSize));

        // time/visits on groupbuy deal page
        String regex = null;
        if (key.equals("3")) {
            regex = "^/groupbuy/[^/]+/([0-9]+)\\??.*$";
        } else {
            regex = "^/groupserve/[^/]+/([0-9]+)\\??.*$";
        }
        String path = null;
        Integer uniqPageViews = 0;
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (DataEntry de : dataFeed.getEntries()) {
            path = de.stringValueOf("ga:pagePath");
            if (!path.matches(regex)) {
                continue;
            }
            path = path.replaceAll(regex, "$1");
            uniqPageViews = map.get(path);
            if (uniqPageViews == null) {
                uniqPageViews = 0;
            }
            uniqPageViews += Integer.parseInt(de
                    .stringValueOf("ga:uniquePageviews"));
            map.put(path, uniqPageViews);
        }

        CampaignLaunchTimeBean t = null;
        List<CampaignLaunchTimeBean> listtemp = new ArrayList<CampaignLaunchTimeBean>();
        for (int i = 0; i < cList.size(); i++) {
            t = cList.get(i).clone();
            if (map.get(String.valueOf(t.getCampaignId())) != null) {
                t.setPageUniqueViews(map.get(String.valueOf(t.getCampaignId())));
                System.out.println(t.getCampaignId() + ":" + t.getPageUniqueViews());
            }
            listtemp.add(t);
        }

        return listtemp;
    }

    private LinkedHashMap<String, List<CampaignLaunchTimeBean>> pushVisitToEachDeal(
            LinkedHashMap<String, List<CampaignLaunchTimeBean>> map)
            throws ValidateDataException {

        try {
            String startTime = null;
            List<CampaignLaunchTimeBean> list = null;
            List<CampaignLaunchTimeBean> list1 = null;
            List<CampaignLaunchTimeBean> list2 = null;
            CampaignLaunchTimeBean temp = null;
            for (Iterator<Entry<String, List<CampaignLaunchTimeBean>>> it = map
                    .entrySet().iterator(); it.hasNext();) {
                Entry<String, List<CampaignLaunchTimeBean>> e = it.next();
                startTime = e.getKey();
                list = e.getValue();
                list1 = fetchData(new String[] { startTime, startTime }, "3",
                        list);
                list2 = fetchData(new String[] { startTime, startTime }, "4",
                        list);

                List<CampaignLaunchTimeBean> list_new = new ArrayList<CampaignLaunchTimeBean>();
                for (int i = 0; i < list.size(); i++) {
                    temp = list.get(i).clone();
                    temp.setPageUniqueViews(list1.get(i).getPageUniqueViews()
                            + list2.get(i).getPageUniqueViews());
                    list_new.add(temp);
                }
                map.put(startTime, list_new);
                System.out.println(startTime + ":" + list_new.size());
            }
            return map;
        } catch (InstanceInvalidException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new ValidateDataException(e.getMessage());
        } catch (CloneNotSupportedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new ValidateDataException(e.getMessage());
        }
    }
    
    private void mergeDataVisit(LinkedHashMap<String, List<CampaignLaunchTimeBean>> map) {
        List<CampaignLaunchTimeBean> list = new ArrayList<CampaignLaunchTimeBean>();
        for (Iterator<Entry<String, List<CampaignLaunchTimeBean>>> it = map
                .entrySet().iterator(); it.hasNext();) {
            Entry<String, List<CampaignLaunchTimeBean>> e = it.next();
            list.addAll(e.getValue());
        }
        CampaignLaunchTimeBean bean = null;
        for (CampaignLaunchTimeBean b : list) {
            for (int i = 0; i < this.campaignList.size(); i++) {
                bean = this.campaignList.get(i);
                if (bean.getCampaignId() == b.getCampaignId()) {
                    bean.setPageUniqueViews(bean.getPageUniqueViews() + b.getPageUniqueViews());
                    this.campaignList.set(i, bean);
                }
            }
        }
    }

    private List<DataEntry> pagedList(QueryParam query, int pageSize)
            throws InstanceInvalidException {
        List<DataEntry> list = new ArrayList<DataEntry>();
        query.setMaxResults(1000);
        DataFeed dataFeed = null;
        for (int i = 0; i < pageSize; i++) {
            query.setStartIndex(i * 1000 + 1);
            dataFeed = service.getDataFeed(query);
            list.addAll(dataFeed.getEntries());
        }
        return list;
    }

    public List<CampaignLaunchTimeBean> getCampaignList() {
        return campaignList;
    }

    public static void main(String[] args) {
        ViewVisitToEachDealAction action = new ViewVisitToEachDealAction();
        action
                .setFilePath("E:/project/JAVA/wlz-project/03.project/test_google_report/test.csv");

        try {
            LinkedHashMap<String, List<CampaignLaunchTimeBean>> map = action
                    .sortCampaignList(action.getFileContent());
            String key = null;
            for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
                key = it.next();
                System.out.print(key + ":");
                if (map.get(key).size() > 0) {
                    for (CampaignLaunchTimeBean b : map.get(key)) {
                        System.out.print(b.getCampaignId() + ",");
                    }
                }
                System.out.println();
            }
        } catch (ValidateDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String[] getTotalTimeRange() {
        return totalTimeRange;
    }

    public String getTimeRangeStr() {
        return timeRangeStr;
    }

    public void setTimeRangeStr(String timeRangeStr) {
        this.timeRangeStr = timeRangeStr;
    }
}
