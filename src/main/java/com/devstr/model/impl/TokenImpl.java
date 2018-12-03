package com.devstr.model.impl;

import com.devstr.model.Token;

import java.math.BigInteger;
import java.util.Objects;

public class TokenImpl implements Token {
    private BigInteger tokenId;
    private BigInteger projectId;
    private String serviceName;
    private String tokenEncode;

    @Override
    public BigInteger getTokenId() {
        return tokenId;
    }

    @Override
    public BigInteger getProjectId() {
        return projectId;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getTokenEncode() {
        return tokenEncode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenImpl token = (TokenImpl) o;

        return getProjectId().equals(token.tokenId);
    }

    @Override
    public int hashCode() {

        return getTokenId().hashCode();
    }

    public static class Builder{
        private BigInteger tokenId;
        private BigInteger projectId;
        private String serviceName;
        private String tokenEncode;

        public Builder(BigInteger projectId, String serviceName, String tokenEncode) {
            this.projectId = projectId;
            this.serviceName = serviceName;
            this.tokenEncode = tokenEncode;
        }

        public Builder setTokenId(BigInteger tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        public Token builder(){
            return new TokenImpl(this);
        }
    }

    public TokenImpl(Builder builder) {
        this.tokenId = builder.tokenId;
        this.projectId = builder.projectId;
        this.serviceName = builder.serviceName;
        this.tokenEncode = builder.tokenEncode;
    }
}
