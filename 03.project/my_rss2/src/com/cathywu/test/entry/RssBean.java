/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-3-26      Cathy Wu        Create
 */

package com.cathywu.test.entry;

public class RssBean {

    private String typeName;
    private String link;
    private String catId;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public RssBean() {

    }

    public RssBean(String typeName, String link, String catId) {
        this.typeName = typeName;
        this.link = link;
        this.catId = catId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    @Override
    public String toString() {
        return "name: " + this.typeName + "; catId: " + this.catId
                + "; link: " + this.link;
    }
}
