/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-3-26      Cathy Wu        Create
 */

package com.cathywu.test.entry;

public class RssBean {

    private String typeName;
    private String link;
    private String category;
    private String catId;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public RssBean() {

    }

    public RssBean(String typeName, String link, String category, String catId) {
        this.typeName = typeName;
        this.link = link;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "name: " + this.typeName + "; category: " + this.category
                + "; link: " + this.link;
    }
}
