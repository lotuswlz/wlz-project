/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-11-27      Cathy Wu        Create
 */

package testObject.TestData;

public class SettingBean<T> {

    private long settingId;
    private DataType<T> type;
    public long getSettingId() {
        return settingId;
    }
    public void setSettingId(long settingId) {
        this.settingId = settingId;
    }
    public DataType<T> getType() {
        return type;
    }
    public void setType(DataType<T> type) {
        this.type = type;
    }
}
