package org.abhinavgpt.commenz.services.summary;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
final class SummaryServiceImpl implements SummaryService {
    private final ChatClient chatClient;

    @Value("classpath:/prompt/summarize_reviews.st")
    private Resource reviewSummaryPromptTemplateString;

    public SummaryServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getSummary(final List<String> reviews) {
        PromptTemplate promptTemplate = new PromptTemplate(reviewSummaryPromptTemplateString);
        Prompt prompt = promptTemplate.create(Map.of("reviews", reviews));
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
}