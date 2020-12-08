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

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class AuctionUserDetailsService implements UserDetailsService {

    @Autowired
    private ICredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential user = credentialRepository.findByUserName(username)
                     .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
               getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Credential user) {
        //to be modified
        String userRoles = user.getUser().getRole().getName();
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }

}
