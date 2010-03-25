package testStr;

import java.util.List;
/**
 * This interface is made for matching categories list by a keyword.<br>
 * The list input must implements the interface "Comparable", because
 * We'll sort the list when return it.
 * 
 * @author Cathy Wu 2008.07.15
 * @param <T>
 */
public interface MatchingUtil<T> {

	public List<T> getMatchingList(List<T> list, String keyword);
	
	public List<T> getMatchingList(List<T> list, String keyword, int endIndex);
}
