package org.abhinavgpt.commenz.services.reviews;

import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements  ReviewService{

    private final ChatClient chatClient;

    public ReviewServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getReviews(String url) {
        return "ReviewServiceImpl.getReviews() called with url: " + url;
    }
}
