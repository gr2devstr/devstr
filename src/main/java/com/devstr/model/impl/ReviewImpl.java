package com.devstr.model.impl;

import com.devstr.model.Review;
import java.math.BigInteger;
import java.util.Date;

class ReviewImpl implements Review {

    private BigInteger reviewId;
    private BigInteger authorId;
    private String authorFullName;
    private String comment;
    private Date creationDate;

    @Override
    public BigInteger getReviewId() {
        return reviewId;
    }

    @Override
    public BigInteger getAuthorId() {
        return authorId;
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

    static class Builder {

        private BigInteger reviewId;
        private BigInteger authorId;
        private String authorFullName;
        private String comment;
        private Date creationDate;

        Builder(BigInteger authorId, String comment) {
            this.authorId = authorId;
            this.comment = comment;
        }

        public Builder setReviewId(BigInteger reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder setAuthorFullName(String authorFullName) {
            this.authorFullName = authorFullName;
            return this;
        }

        public Builder setCreationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Review build() {
            return new ReviewImpl(this);
        }

    }

    ReviewImpl(Builder builder) {
        this.reviewId = builder.reviewId;
        this.authorId = builder.authorId;
        this.authorFullName = builder.authorFullName;
        this.comment = builder.comment;
        this.creationDate = builder.creationDate;
    }

}
