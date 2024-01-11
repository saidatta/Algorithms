package leetcode.design.product;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/web-crawler/description/
import java.util.*;
import java.net.*;

public class WebCrawler {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        String hostname = extractHostname(startUrl);
        Set<String> visited = new HashSet<>(); // To track visited URLs
        Queue<String> queue = new LinkedList<>(); // Queue for BFS
        List<String> result = new ArrayList<>(); // To store the final result

        queue.offer(startUrl);
        visited.add(startUrl);

        while (!queue.isEmpty()) {
            String currentUrl = queue.poll();
            result.add(currentUrl);

            // Get all URLs from the current page and process them
            for (String url : htmlParser.getUrls(currentUrl)) {
                if (url.contains(hostname) && visited.add(url)) {
                    queue.offer(url);
                }
            }
        }

        return result;
    }

    private String extractHostname(String url) {
        try {
            // Extract the hostname from URL using URL class
            return new URI(url).getHost();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // Dummy HtmlParser interface for compilation
    interface HtmlParser {
        List<String> getUrls(String url);
    }
}
