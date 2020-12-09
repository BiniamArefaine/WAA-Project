package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.Credential;
import edu.miu.cs.auctionproject.repository.ICredentialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
//@SessionAttributes("userId")
public class AuctionUserDetailsService implements UserDetailsService {

    @Autowired
    private ICredentialRepository credentialRepository;

    @Autowired
    ServletContext servletContext;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential user = credentialRepository.findByUserName(username)
                     .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
//        getId(user.getUser().getId());
        servletContext.setAttribute("userId", user.getUser().getId());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
               getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Credential user) {
        //to be modified

        String userRoles = user.getUser().getRole().getName();

        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }



//    private static void getId(Long id){
//        AuctionUserDetailsService yes = new AuctionUserDetailsService();
//        yes.servletContext.setAttribute("userId", id);
//        System.out.println("----" + id);
//    }



}
