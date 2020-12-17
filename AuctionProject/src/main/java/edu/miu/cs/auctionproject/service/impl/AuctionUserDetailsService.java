package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.controller.CategoryController;
import edu.miu.cs.auctionproject.controller.HomeController;
import edu.miu.cs.auctionproject.domain.Credential;
import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.javaMailApi.SendEmailClass;
import edu.miu.cs.auctionproject.repository.ICredentialRepository;

import edu.miu.cs.auctionproject.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Collection;

@Component
@Transactional
public class AuctionUserDetailsService implements UserDetailsService {

    @Autowired
    private ICredentialRepository credentialRepository;
//
    @Autowired
    private HttpSession session;


    @RequestMapping
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        limitedLogIns(session.getId());
        session.setAttribute("newUser",username);
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
        if(counts==0){ session.setAttribute("counts", counts); }
        counts++;
        if(sessionIdent.equals(sessId) && counts==3){
            session.setAttribute("counts", counts);
            System.out.println("-------session-----");
            counts = 0;
        }
        sessionIdent = sessId;

    }



}

