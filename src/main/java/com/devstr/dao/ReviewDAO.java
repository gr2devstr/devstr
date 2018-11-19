package com.devstr.dao;

import com.devstr.model.Review;

import java.util.Set;

public interface ReviewDAO {

    void createReview(Review review);

    Review readReviewById(int id);

    Set<Review> readReviewsById(Set<Integer> ids);

}
