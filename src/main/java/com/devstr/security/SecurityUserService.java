package com.devstr.security;

import com.devstr.dao.UserDAO;
import com.devstr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User securityUser = userDAO.readBasicUserByLogin(login);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(securityUser.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(
                securityUser.getLogin(),
                securityUser.getPassword(),
                authorities);
    }

}
