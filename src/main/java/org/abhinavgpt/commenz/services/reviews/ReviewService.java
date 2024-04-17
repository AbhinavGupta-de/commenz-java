package org.abhinavgpt.commenz.services.reviews;

import java.util.List;

public sealed interface ReviewService permits ReviewServiceImpl
{
	List<String> getReviews(String url);
}