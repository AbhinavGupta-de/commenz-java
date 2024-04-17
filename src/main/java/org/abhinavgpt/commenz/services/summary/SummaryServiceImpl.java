package org.abhinavgpt.commenz.services.summary;

import org.abhinavgpt.commenz.services.reviews.ReviewService;
import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SummaryServiceImpl implements SummaryService {

    private final ReviewService reviewService;
    private final ChatClient chatClient;

    public SummaryServiceImpl(ReviewService reviewService, ChatClient chatClient) {
        this.reviewService = reviewService;
        this.chatClient = chatClient;
    }

    @Override
    public String greetingMessage() {
        return chatClient.call("Give me greeting message");
    }

    @Override
    public String getSummary(String url) {

        return "SummaryServiceImpl.getSummary() called with url: " + url;
    }
}
