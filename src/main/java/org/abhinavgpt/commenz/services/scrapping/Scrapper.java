package org.abhinavgpt.commenz.services.scrapping;

import java.util.List;

public sealed interface Scrapper permits AmazonScrapper{
	String cleaningURL(String url);
	List<String> scrap(String url);
}