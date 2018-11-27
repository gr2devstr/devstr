package com.devstr.model;

import java.math.BigInteger;

public interface UserReview extends Review {
    int getCodeQuality();

    int getCodeAmount();

    int getCommunication();

    BigInteger getProjectId();

    float getAverageMark();
}
