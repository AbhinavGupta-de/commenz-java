package org.abhinavgpt.commenz.services.scrapping;

import java.util.List;

public interface Scrapper {
	String cleaningURL(String url);
	List<String> scrap(String url);
}