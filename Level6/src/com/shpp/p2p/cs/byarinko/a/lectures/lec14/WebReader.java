package com.shpp.p2p.cs.byarinko.a.lectures.lec14;

import com.shpp.cs.a.console.TextProgram;

import java.io.*;
import java.net.*;

public class WebReader extends TextProgram {
	public void run() {	
		while (true) {
			String query = readLine("Enter a query: ");
			println(getNumResults(query));
		}
	}

	public boolean debug = true;
	
	/**
	 * Does a Google search and reports how many results were
	 * found.
	 * 
	 * @param query The query to execute.
	 * @return The number of search results.
	 */
	private String getNumResults(String query) {
		/* Do a Google search. */
		String googleText = doGoogleSearch(query);
		if (googleText == null) {
			return "Something went wrong.";
		}
		
		/* The text format is About NNN results, so we want to
		 * look for "About " and "results."
		 */

		googleText = doGoogleSearch2(googleText);
		if (googleText == null) {
			return "Something went wrong.";
		}

		if (debug)
			println(googleText);
		int aboutIndex = googleText.indexOf("About ");
		if (aboutIndex == -1) {
			return "Google doesn't like us.";
		}
		
		int resultIndex = googleText.indexOf("results", aboutIndex);
		if (resultIndex == -1) {
			return "Google still doesn't like us.";
		}

		return googleText.substring(aboutIndex, resultIndex) + " search results";
	}

	/**
	 * We were redirected... have to extract new URL and make new HTTP request
	 */
	private String doGoogleSearch2(String googleText) {
		int loc = googleText.indexOf("Location: ");
		if (loc != -1) {
			println("Google has redirected us!");
			if (debug) {
				println("Let's see the whole message: ");
				println(googleText);
				println();
			}
			println("...Have to extract 'Location' header!");

			int end = googleText.indexOf('\n', loc);
			if (end != -1) {
				String sub = googleText.substring(loc, end);
				String x = "Location: ";
				sub = sub.substring(sub.indexOf("/", 8+x.length()));
				println("So we were redirected to: "+sub);
				return doRequest(sub);
			}
		}
		else
			return googleText;

		return "";
	}

	/**
	 * Performs the specified search query on Google, returning
	 * the text of the response page.
	 * 
	 * @param query The query to perform.
	 * @return The full text of the response page.
	 */
	private String doGoogleSearch(String query) {
		String q = "/search?q=" + query;
		return doRequest(q);
	}

	private String doRequest(String request) {
		/* Google queries, at the HTTP level, can't have spaces
		 * in them.  Replace all spaces with the + character.
		 */
		request = request.replace(' ', '+');
		try {
			/* Open a connection to Google. */
			Socket s = new Socket("www.google.com", 80);
			
			/* Create an HTTP request to do a search for the given
			 * query, then send it.
			 */
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			pw.println("GET " + request+ " HTTP/1.0");
			pw.println("User-Agent: shppbrowser1.0");
			pw.println();
			pw.println();
			pw.flush();
			
			/* Read the response and reconstruct it one line at a time. */
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = "";
			
			while (true) {
				String line = br.readLine();
				if (line == null) break;
				
				result += line + '\n';
			}
			
			s.close();
			return result;
			
		} catch (IOException e) {
			return null;
		}
	}
}
