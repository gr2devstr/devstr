package com.devstr.dao.impl;

import com.devstr.dao.TokenDAO;
import com.devstr.model.Token;
import com.devstr.model.impl.TokenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

@Transactional
@Repository
public class TokenDAOImpl implements TokenDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createToken(int projectId, String tokensName, String serviceName, String tokenEncode) {
        jdbcTemplate.update(CREATE_TOKEN, new Object[]{tokensName, serviceName, tokenEncode, projectId});
    }

    @Override
    public Token readTokenByProject(int projectId) {
        RowMapper<Token> mapper = new TokenMapper();
        jdbcTemplate.update(READ_TOKEN_BY_PROJECT_ID, mapper, projectId);
        return null;
    }

    @Override
    public void updateToken(int projectId, String serviceName, String tokenEncode) {
        jdbcTemplate.update(UPDATE_TOKEN, new Object[]{tokenEncode,serviceName, projectId});
    }

    class TokenMapper implements RowMapper<Token>{

        @Override
        public Token mapRow(ResultSet resultSet, int i) throws SQLException {
            return new TokenImpl.Builder(BigInteger.valueOf(resultSet.getLong(1))
                    ,resultSet.getString(2)
                    ,resultSet.getString(3)
                    ,resultSet.getString(4))
                    .setTokenId(BigInteger.valueOf(resultSet.getLong(5)))
                    .builder();
        }
    }
}
