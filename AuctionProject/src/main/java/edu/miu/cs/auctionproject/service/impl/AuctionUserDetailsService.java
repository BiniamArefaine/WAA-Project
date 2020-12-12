package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.controller.CategoryController;
import edu.miu.cs.auctionproject.domain.Credential;
import edu.miu.cs.auctionproject.repository.ICredentialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class AuctionUserDetailsService implements UserDetailsService {

    @Autowired
    private ICredentialRepository credentialRepository;
//
    @Autowired
    private HttpSession session;

    @Autowired
    private CategoryController userController;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        limitedLogIns(session.getId());
        Credential user = credentialRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        session.setAttribute("userId", user.getUser().getId());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Credential user) {
        String userRoles = user.getUser().getRole().getName();
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }

    int counts = 0;
    static String sessionIdent="temp";
    private void limitedLogIns(String sessId){
        counts++;
        if(sessionIdent.equals(sessId) && counts==3){
            System.out.println("-------session-----");
            counts = 0;
            userController.logInFailed();
            return;
        }
        sessionIdent = sessId;
    }

}

