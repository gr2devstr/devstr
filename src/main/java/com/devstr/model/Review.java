package com.devstr.model;

import java.util.Date;

public abstract class Review {

    private int reviewId;
    private int authorId;
    private String authorFullName;
    private String comment;
    private Date creationDate;

    Review() {
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public String getComment() {
        return comment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        return getReviewId() == review.getReviewId();
    }

    @Override
    public int hashCode() {
        return getReviewId();
    }

    class ReviewBuilder {

        ReviewBuilder() {
        }

        public ReviewBuilder setReviewId(int id) {
            Review.this.reviewId = id;
            return this;
        }

        public ReviewBuilder setAuthorId(int id) {
            Review.this.authorId = id;
            return this;
        }


        public ReviewBuilder setAuthorFullName(String fullName) {
            Review.this.authorFullName = fullName;
            return this;
        }

        public ReviewBuilder setComment(String comment) {
            Review.this.comment = comment;
            return this;
        }

        public ReviewBuilder setCreationDate(Date creationDate) {
            Review.this.creationDate = creationDate;
            return this;
        }

        public Review build() {
            return Review.this;
        }

    }

}
