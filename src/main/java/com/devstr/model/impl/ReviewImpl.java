package com.devstr.model.impl;

import com.devstr.model.Review;
import java.math.BigInteger;
import java.util.Date;

abstract class ReviewImpl implements Review {

    BigInteger reviewId;
    BigInteger authorId;
    BigInteger receiverId;
    String authorFullName;
    String comment;
    Date creationDate;

    @Override
    public BigInteger getReviewId() {
        return reviewId;
    }

    @Override
    public BigInteger getAuthorId() {
        return authorId;
    }

    @Override
    public BigInteger getReceiverId(){
        return receiverId;
    }

    @Override
    public String getAuthorFullName() {
        return authorFullName;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        return getReviewId().equals(review.getReviewId());
    }

    @Override
    public int hashCode() {
        return getReviewId().hashCode();
    }

}
