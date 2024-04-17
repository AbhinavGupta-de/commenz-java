package org.abhinavgpt.commenz.services.scrapping;

public sealed interface Scrapper permits ScrapperImpl
{
	String scrap(String url);
}