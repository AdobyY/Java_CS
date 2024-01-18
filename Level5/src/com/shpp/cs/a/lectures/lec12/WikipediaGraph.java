package com.shpp.cs.a.lectures.lec12;

/*
 * File: WikipediaGraph.java
 * ===================================================
 * A program to rank the pages of Wikipedia by their
 * PageRank score.
 *
 * This program operates on HUGE data files (400MB), so
 * it may take a very long time to finish.  If your
 * computer does not have sufficient memory, it may even
 * fail to run.  If this happens, please contact us and
 * we'll give you a smaller version of the data file.
 */

import java.io.*;
import java.util.*;
import acm.util.*;
import com.shpp.cs.a.console.TextProgram;

public class WikipediaGraph extends TextProgram {
    /* The file containing the names of the articles. */
    private static final String ARTICLES_FILE = "assets/data/wiki-articles.txt";

    /* The file containing the links between the articles. */
    private static final String LINKS_FILE = "assets/data/wiki-links.txt";

    /* How many iterations of the RSM to run. */
    private static final int NUM_ITERATIONS = 80000000;

    /* The probability of choosing a random page. */
    private static final double RANDOM_PROBABILITY = 0.15;

    /* The number of results to display. */
    private static final int NUM_DISPLAYED_RESULTS = 50;

    public void run() {

		/* Read the graph from the files. */
        ArrayList<String> articleNames = readArticleNames();
        HashMap<String, ArrayList<String>> links = readLinks(articleNames);

		/* Rank the articles using PageRank. */
        HashMap<String, Integer> ranking = rankArticles(articleNames, links);

		/* Sort the articles by their rank, then output the top 50. */
        ArrayList<String> sortedArticles = sortArticles(ranking);
        for (int i = 0; i < NUM_DISPLAYED_RESULTS; i++) {
            println((i + 1) + ": " + sortedArticles.get(i));
        }
    }

    /**
     * Reads the articles file and returns a list of the names in the file.
     *
     * @return The list of article titles, in order.
     */
    private ArrayList<String> readArticleNames() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ARTICLES_FILE));
            ArrayList<String> result = new ArrayList<String>();

			/* Use the standard file-reading loop to populate the articles list. */
            while (true) {
                String title = br.readLine();
                if (title == null)
                    break;

                result.add(title);
            }

            br.close();

			/* For reference, let's see how many articles there are. */
            println("Read " + result.size() + " articles.");
            return result;

        } catch (IOException e) {
            throw new ErrorException(e);
        }
    }

    /**
     * Builds up a graph of the articles in Wikipedia.  Since the links are
     * stored as pairs of integers, we need the article titles list to map
     * them back to their names.
     *
     * @param articleTitles The list of all article titles, in order.
     * @return A graph of the links in Wikipedia.
     */
    private HashMap<String, ArrayList<String>> readLinks(ArrayList<String> articleTitles) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(LINKS_FILE));
            HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();

			/* Just for fun, count how many links there are .*/
            int totalLinks = 0;
            while (true) {
				/* Read the start and end pages, stopping if we're done. */
                String startString = br.readLine();
                String endString = br.readLine();
                if (endString == null) break;

				/* Track how many links there are. */
                totalLinks++;

				/* Convert from the string representation of the link to an
				 * integer representation of the link.
				 */
                int startIndex = Integer.parseInt(startString);
                int endIndex = Integer.parseInt(endString);

				/* Map from those integers to the corresponding pages. */
                String startPage = articleTitles.get(startIndex);
                String endPage = articleTitles.get(endIndex);

				/* Add this link to the graph.  Ensure that there is a list
				 * associated with the title, then add it in.
				 */
                if (!result.containsKey(startPage)) {
                    result.put(startPage, new ArrayList<String>());
                }
                result.get(startPage).add(endPage);
            }

            br.close();

			/* See how many links there are. */
            println("Read " + totalLinks + " links.");
            return result;

        } catch (IOException e) {
            throw new ErrorException(e);
        }
    }

    /**
     * Given a list of articles and the graph of the links between them, runs the PageRank
     * algorithm to determine a ranking for each page.
     * <p>
     * In order to efficiently support picking a random article, we require the list of all
     * the article names in addition to the graph.
     *
     * @param articleNames The list of article names.
     * @param links The links between those articles.
     * @return A map from pages to their PageRank.
     */
    private HashMap<String, Integer> rankArticles(ArrayList<String> articleNames, HashMap<String, ArrayList<String>> links) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        RandomGenerator rgen = RandomGenerator.getInstance();

		/* Start at a totally random article. */
        String currentArticle = articleNames.get(rgen.nextInt(0, articleNames.size() - 1));

		/* Keep randomly walking around Wikipedia, periodically warping to a totally
		 * random page.
		 */
        for (int i = 0; i < NUM_ITERATIONS; ++i) {
			/* Mark that we've been to this page one more time. */
            if (!result.containsKey(currentArticle)) {
                result.put(currentArticle, 1);
            } else {
                result.put(currentArticle, result.get(currentArticle) + 1); // ++
            }

			/* Possibly randomly warp to another page. */
            if (rgen.nextBoolean(RANDOM_PROBABILITY)) {
                currentArticle = articleNames.get(rgen.nextInt(0, articleNames.size() - 1));
            }
			/* Alternatively, visit a random linked page. */
            else {
                ArrayList<String> linkedTo = links.get(currentArticle);
                currentArticle = linkedTo.get(rgen.nextInt(0, linkedTo.size() - 1));
            }
        }

        return result;
    }


    /**
     * Given a map of articles to their PageRank scores, returns a list of all
     * pages in descending order of rank.
     *
     * @param ranking The PageRank scores.
     * @return The scores in sorted order.
     */
    private ArrayList<String> sortArticles(HashMap<String, Integer> ranking) {
		/* This process will work in two steps.  First, we will build an inverse map
		 * that associates each ranking with the list of pages that have that ranking.
		 * This will be a TreeMap, so the pages will be stored in ascending order of
		 * rank.
		 */
        TreeMap<Integer, ArrayList<String>> inverseRanking = new TreeMap<Integer, ArrayList<String>>();

		/* Visit each key/value pair. */
        for (String key: ranking.keySet()) {
            int rank = ranking.get(key);

			/* Ensure that there's space in the map for this rank/page pair, then add it in. */
            if (!inverseRanking.containsKey(rank))
                inverseRanking.put(rank, new ArrayList<String>());
            inverseRanking.get(rank).add(key);
        }

		/* Now, we iterate over the inverse ranking map to visit every page.  This will cause
		 * us to visit the pages in ascending rank order.  We'll append everything to a List.
		 */
        ArrayList<String> result = new ArrayList<String>();
        for (Integer rank: inverseRanking.keySet()) {
            for (String article: inverseRanking.get(rank)) {
                result.add(article);
            }
        }

		/* Finally, reverse the list so that the highest-ranked page comes first. */
        Collections.reverse(result);
        return result;
    }
}
