/*
 * All Rights Reserved. Copyright(C) 2008 - 2010 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-3-26      Cathy Wu        Create
 */

package com.cathywu.test;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 
 * @author Administrator
 */
public class TestRss {
    private String urlStr;

    /** Creates a new instance of TestRss */
    public TestRss(String rssUrl) {
        this.urlStr = rssUrl;
    }

    /**
     * 链接rss
     */
    public void rss() {
        try {
            URL feedUrl = new URL(urlStr);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));
            this.show(feed);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (FeedException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * 通过代理链接rss
     */
    public void agentRss() {
        try {
            URLConnection feedUrl = new URL(urlStr).openConnection();
            feedUrl.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (FeedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 设置HTTP代理
     */
    public void setHttpProxy() {
        Properties systemSettings = System.getProperties();
        systemSettings.put("http.proxyHost", "192.168.40.80");
        systemSettings.put("http.proxyPort", "80");
        System.setProperties(systemSettings);
    }

    /**
     * 显示信息
     */
    public void show(SyndFeed feed) {
        List list = feed.getEntries();
        for (int i = 0; i < list.size(); i++) {
            SyndEntry entry = (SyndEntry) list.get(i);
            System.out.println(entry.getTitle() + " | " + entry.getPublishedDate() + " | " + entry.getAuthor()
                    + " | " + entry.getLink());
        }
    }
    
    /**
     * Get rss list.
     * @return List&lt;SyndEntry&gt;
     */
    @SuppressWarnings("finally")
    public List<SyndEntry> getRssList() {
        List<SyndEntry> result = new ArrayList<SyndEntry>();
        try {
            URL feedUrl = new URL(urlStr);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));
            show(feed);
            List list = feed.getEntries();
            for (int i = 0; i < list.size(); i++) {
                result.add((SyndEntry) list.get(i));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        TestRss m = new TestRss(
                "http://www.xinhuanet.com/english2010/rss/chinarss.xml");
        m.setHttpProxy();
        m.rss();
    }
}
