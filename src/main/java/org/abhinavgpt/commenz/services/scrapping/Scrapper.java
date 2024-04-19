package org.abhinavgpt.commenz.services.scrapping;

import org.abhinavgpt.commenz.exceptions.InvalidURLException;

import java.util.List;

public sealed interface Scrapper permits AmazonScrapper{
	List<String> scrap(String url) throws InvalidURLException;
}