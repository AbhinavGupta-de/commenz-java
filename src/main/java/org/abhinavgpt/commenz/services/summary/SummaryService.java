package org.abhinavgpt.commenz.services.summary;

public sealed interface SummaryService permits SummaryServiceImpl
{
	String greetingMessage();
	String getSummary(String review);
}