package com.devstr.services;

import java.math.BigInteger;

public interface StatisticUpdateService {

    void updateStatisticByTravis(BigInteger projectId);

    void updateStatisticByView(BigInteger projectId);
}
