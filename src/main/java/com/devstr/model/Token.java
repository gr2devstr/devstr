package com.devstr.model;

public class Token {
    private int projectId;
    private String serviceName;
    private String tokenEncode;

    private Token() {
    }

    public int getProjectId() {
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

        Token token1 = (Token) o;

        if (projectId != token1.projectId) return false;
        return tokenEncode.equals(token1.tokenEncode);
    }

    @Override
    public int hashCode() {
        int result = projectId;
        result = 31 * result * tokenEncode.hashCode();
        return result;
    }

    public static TokenBuilder builder() {
        return new Token().new TokenBuilder();
    }

    public class TokenBuilder {
        private TokenBuilder() {
        }

        public TokenBuilder setProjectId(int projectId) {
            Token.this.projectId = projectId;
            return this;
        }

        public TokenBuilder setServiceName(String serviceName) {
            Token.this.serviceName = serviceName;
            return this;
        }

        public TokenBuilder setTokenEncode(String tokenEncode) {
            Token.this.tokenEncode = tokenEncode;
            return this;
        }

        public Token build() {
            return Token.this;
        }
    }
}
