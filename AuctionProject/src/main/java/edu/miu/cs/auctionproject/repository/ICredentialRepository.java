package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.Credential;
import edu.miu.cs.auctionproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("credentialRepository")
public interface ICredentialRepository extends JpaRepository<Credential, Long> {

    @Query("select c from Credential c where c.user.pinCode=true and c.userName= :username")
    Optional<Credential> findByUserName(String username);


}
