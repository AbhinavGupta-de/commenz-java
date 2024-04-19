package org.abhinavgpt.commenz.services.summary;

import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public String getSummary(final List<String> review) {
        return chatClient.call("I have collected some reviews about a product. " +
                "Can you read them and summarize what people are saying about the product? " +
                "Is it worth it or people are saying that it is bad not worth it. " +
                "Are there some specific issues with the product that people mentioned?" + review.toString());
    }
}