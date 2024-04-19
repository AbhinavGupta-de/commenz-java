package org.abhinavgpt.commenz.services.summary;

import java.util.List;

public sealed interface SummaryService permits SummaryServiceImpl {
    String greetingMessage();

    String getSummary(List<String> reviews);
}