package com.devstr.model;

import java.math.BigInteger;
import java.util.Date;

public interface Review {

    BigInteger getReviewId();

    BigInteger getAuthorId();

    String getAuthorFullName();

    String getComment();

    Date getCreationDate();

}
