/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-4-18      Cathy Wu        Create
 */

package myPractiseForClass;

public class ForeignMember extends Member {

    public String getFullName() {
        return this.name + " " + this.familyName;
    }

}
