package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.Address;
import edu.miu.cs.auctionproject.domain.Credential;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByVerificationFalse();
      User getUserByCredential(Credential credential);
    User getUserByCredential_PasswordAndCredential_UserName(String password, String userName);

    User findAllByEmailAndFirstName(String email, String firstName);


    User findUserByEmail(String email);
    User findUserByCredentialUserName(String userName);

    User findUserByproductId(Long pId);
}

