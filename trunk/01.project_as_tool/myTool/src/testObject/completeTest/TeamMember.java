/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-11-27      Cathy Wu        Create
 */

package testObject.completeTest;

import java.util.List;

public interface TeamMember<T> {

    T getMemberBean(String memberName);
    List<T> getMembers();
}
