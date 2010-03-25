/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-11-27      Cathy Wu        Create
 */

package testObject.completeTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Team<T> implements TeamMember<T> {
    
    private Map<String, T> membersMap = new HashMap<String, T>();

    public T getMemberBean(String memberName) {
        // TODO Auto-generated method stub
        return this.membersMap.get(memberName);
    }

    public List<T> getMembers() {
        List<T> list = new ArrayList<T>();
        for (Iterator<Entry<String, T>> it = membersMap.entrySet().iterator(); it.hasNext();) {
            Entry<String, T> e = it.next();
            list.add(e.getValue());
        }
        return list;
    }
    
    public void addMembers(T member) {
        this.membersMap.put(member.toString(), member);
    }

}
