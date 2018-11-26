package com.devstr.model.impl;

import com.devstr.model.Token;

import java.math.BigInteger;

public class TokenImpl implements Token {
    private BigInteger projectId;
    private String serviceName;
    private String tokenEncode;

    private TokenImpl() {
    }

    public BigInteger getProjectId() {
        return projectId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getTokenEncode() {
        return tokenEncode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenImpl token1 = (TokenImpl) o;

        if (!projectId.equals(token1.projectId)) return false;
        return tokenEncode.equals(token1.tokenEncode);
    }

    @Override
    public int hashCode() {
        return projectId.hashCode() * tokenEncode.hashCode();
    }

    public static TokenBuilder builder() {
        return new TokenImpl().new TokenBuilder();
    }

    public class TokenBuilder {
        private TokenBuilder() {
        }

        public TokenBuilder setProjectId(BigInteger projectId) {
            TokenImpl.this.projectId = projectId;
            return this;
        }

        public TokenBuilder setServiceName(String serviceName) {
            TokenImpl.this.serviceName = serviceName;
            return this;
        }

        public TokenBuilder setTokenEncode(String tokenEncode) {
            TokenImpl.this.tokenEncode = tokenEncode;
            return this;
        }

        public TokenImpl build() {
            return TokenImpl.this;
        }

    }

}
