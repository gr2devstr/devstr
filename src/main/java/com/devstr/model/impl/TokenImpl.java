package com.devstr.model.impl;

import com.devstr.model.Token;

import java.math.BigInteger;
import java.util.Objects;

public class TokenImpl implements Token {
    private BigInteger tokenId;
    private BigInteger projectId;
    private String serviceName;
    private String tokenEncode;
    private String tokensName;


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
    public String getTokensName() {
        return tokensName;
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
        private String tokensName;

        public Builder(BigInteger projectId, String serviceName, String tokenEncode, String tokensName) {
            this.projectId = projectId;
            this.serviceName = serviceName;
            this.tokenEncode = tokenEncode;
            this.tokensName = tokensName;
        }

        public Builder setTokenId(BigInteger tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        public Token builder(){
            return new TokenImpl(this);
        }
    }

    private TokenImpl(Builder builder) {
        this.tokenId = builder.tokenId;
        this.projectId = builder.projectId;
        this.serviceName = builder.serviceName;
        this.tokenEncode = builder.tokenEncode;
        this.tokensName = builder.tokensName;
    }
}
