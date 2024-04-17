package org.abhinavgpt.commenz.services.summary;

import org.abhinavgpt.commenz.services.reviews.ReviewService;
import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

@Service
final class SummaryServiceImpl implements SummaryService {

    private final ChatClient chatClient;

    public SummaryServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String greetingMessage() {
        return chatClient.call("Give me greeting message");
    }

    @Override
    public String getSummary(final String review) {
        return "SummaryServiceImpl.getSummary() called with review: " + review;
    }
}