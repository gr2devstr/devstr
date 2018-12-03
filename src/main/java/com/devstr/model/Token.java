package com.devstr.model;

import java.math.BigInteger;

public interface Token {
    BigInteger getTokenId();

    BigInteger getProjectId();

    String getServiceName();

    String getTokenEncode();

}
