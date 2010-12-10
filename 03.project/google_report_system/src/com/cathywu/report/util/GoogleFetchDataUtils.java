package com.cathywu.report.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gdata.client.analytics.AnalyticsService;
import com.google.gdata.client.analytics.DataQuery;
import com.google.gdata.data.analytics.AccountEntry;
import com.google.gdata.data.analytics.AccountFeed;
import com.google.gdata.data.analytics.DataEntry;
import com.google.gdata.data.analytics.DataFeed;
import com.google.gdata.data.analytics.Metric;
import com.google.gdata.util.ServiceException;

public class GoogleFetchDataUtils {

	public static final String ACCOUNTS_URL = "https://www.google.com/analytics/feeds/accounts/default";

	public static final String DATA_URL = "https://www.google.com/analytics/feeds/data";

	/**
	 * Returns a data feed containing the accounts that the user logged in to
	 * the given AnalyticsService has access to.
	 * 
	 * @param myService
	 *            The AnalyticsService to request accounts from
	 * @return An AccountFeed containing an entry for each profile the logged-in
	 *         user has access to
	 * @throws IOException
	 *             If an error occurs while trying to communicate with the
	 *             Analytics server
	 * @throws ServiceException
	 *             If the API cannot fulfill the user request for any reason
	 */
	public static AccountFeed getAvailableAccounts(AnalyticsService myService)
			throws IOException, ServiceException {

		URL feedUrl = new URL(ACCOUNTS_URL);
		return myService.getFeed(feedUrl, AccountFeed.class);
	}

	/**
	 * Displays the accounts in the given account feed.
	 */
	public static void printAccounts(AccountFeed accountFeed) {
		System.out.println(accountFeed.getTitle().getPlainText());
		for (AccountEntry entry : accountFeed.getEntries()) {
			System.out.println("\t" + entry.getTitle().getPlainText() + ": "
					+ entry.getTableId().getValue());
		}
		System.out.println();
	}

	/**
	 * Gets a very basic data query request for the given table.
	 * 
	 * @param tableId
	 *            The ID of the table to request data from
	 * @return A basic query for browser, visits, and bounce information from
	 *         the given table
	 * @throws MalformedURLException
	 *             If the URL used to request data is malformed
	 */
	public static DataQuery getBasicQuery(String tableId)
			throws MalformedURLException {
		// Set up the request (we could alternately construct a URL manually
		// with all query parameters
		// set)
		DataQuery query = new DataQuery(new URL(DATA_URL));
		query.setIds(tableId);
		query.setStartDate("2010-11-25");
		query.setEndDate("2010-12-01");
		query.setDimensions("ga:source,ga:medium");
		query.setMetrics("ga:visits,ga:timeOnSite,ga:bounces");

		return query;
	}

	/**
	 * Prints the contents of a data feed.
	 * 
	 * @param title
	 *            A header to print before the results
	 * @param dataFeed
	 *            The data feed containing data to print. Assumed to contain
	 *            ga:browser, ga:visits, and ga:bounces information.
	 */
	public static void printData(String title, DataFeed dataFeed) {
		System.out.println(title);
		for (DataEntry entry : dataFeed.getEntries()) {
			System.out.println("\tBrowser: "
					+ entry.stringValueOf("ga:browser"));
			System.out.println("\t\tVisits: "
					+ entry.stringValueOf("ga:visits"));
			System.out.println("\t\tBounces: "
					+ entry.stringValueOf("ga:bounces"));
			System.out.println("\t\tBounce rate: "
					+ entry.longValueOf("ga:bounces")
					/ (double) entry.longValueOf("ga:visits"));
		}
		System.out.println();
	}

	public static void printAllData(String title, DataFeed dataFeed) {
		System.out.println(title);
		System.out.println(dataFeed.getEntries().size());
		System.out.println(dataFeed.getAggregates().getMetrics().size());
		System.out.println("----------------total-------------------");
		for (Metric m : dataFeed.getAggregates().getMetrics()) {
			System.out.println(m.getName() + ": " + m.getValue());
		}
		for (DataEntry entry : dataFeed.getEntries()) {
			System.out.println(entry.stringValueOf("ga:source") + "(" + entry.stringValueOf("ga:medium") + ")");
			System.out.println("\t" + entry.stringValueOf("ga:visits") + "\t\t" + entry.stringValueOf("ga:timeOnSite") + "\t\t" + entry.stringValueOf("ga:bounces"));
		}
	}

	/**
	 * Runs through all the examples using the given GoogleService instance.
	 * 
	 * @param myService
	 *            An unauthenticated AnalyticsService object
	 * @throws ServiceException
	 *             If the service is unable to handle the request
	 * @throws IOException
	 *             If there is an error communicating with the server
	 */
	public static void run(AnalyticsService myService, String username,
			String password) throws ServiceException, IOException {

		// Authenticate using ClientLogin
		myService.setUserCredentials(username, password);

		// Print a list of all accessible accounts
		AccountFeed accountFeed = getAvailableAccounts(myService);
		printAccounts(accountFeed);

		if (accountFeed.getEntries().isEmpty()) {
			return;
		}

		// Each entry in the account feed represents an individual profile
		AccountEntry profile = accountFeed.getEntries().get(0);
		System.out.println("There are " + accountFeed.getEntries().size() + " profiles");
		String tableId = profile.getTableId().getValue();

		// Print the results of a basic request
		DataQuery basicQuery = getBasicQuery(tableId);
		basicQuery.setSort("-ga:visits");
		//basicQuery.setFilters("ga:pagePath=~^/groupbuy;ga:pagePath=@/1040;ga:pagePath!@order;ga:pagePath!@input;ga:pagePath!@transaction;ga:pagePath!~^/signin;ga:pagePath!~^/search");
		basicQuery.setFilters("ga:source!@192.168.1.151;ga:source!@211.144.118.170");
		basicQuery.setMaxResults(10);
		DataFeed basicData = myService.getFeed(basicQuery, DataFeed.class);
		// printData("BASIC RESULTS", basicData);
		printAllData("BASIC RESULTS", basicData);

		// Ask Analytics to return the data sorted in descending order of visits
		// DataQuery sortedQuery = getBasicQuery(tableId);
		// sortedQuery.setSort("-ga:visits");
		// DataFeed sortedData = myService.getFeed(sortedQuery, DataFeed.class);
		// printData("SORTED RESULTS", sortedData);

		// Ask Analytics to filter out browsers that contain the word "Explorer"
		// DataQuery filteredQuery = getBasicQuery(tableId);
		// filteredQuery.setFilters("ga:browser!@Explorer");
		// DataFeed filteredData = myService.getFeed(filteredQuery,
		// DataFeed.class);
		// printData("FILTERED RESULTS", filteredData);
	}

	/**
	 * Uses the command line arguments to authenticate the GoogleService and
	 * build the basic feed URI, then invokes all the other methods to
	 * demonstrate how to interface with the Analytics service.
	 * 
	 * @param args
	 *            See the usage method.
	 */
	public static void main(String[] args) {

		// Set username, password and feed URI from command-line arguments.
		String userName = "cathywu@iwanttobuy.com.au";
		String userPassword = "4666lotuswlz";
		AnalyticsService myService = new AnalyticsService(
				"exampleCo-exampleApp-1");

		try {
			run(myService, userName, userPassword);
		} catch (ServiceException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
