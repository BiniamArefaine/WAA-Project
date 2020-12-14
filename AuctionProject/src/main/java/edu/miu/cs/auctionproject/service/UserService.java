package edu.miu.cs.auctionproject.service;

import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(User user);
    void deleteUser(Long id);
    Optional<User> findUserById(Long id);
    List<User> findAllUsers();
    List<User> findAllNonVerifiedUsers();

    User getUserByPasswordAndUserName(String password, String userName);

    User getUserByEmailAndFirstName(String email, String firstName);

    User findUserByEmail(String email);

    User finduserByName(String userName);

    User findUserByProductId(long productId);

//
//    User findByCredential_UserNameOrRole(Credential credential1);

}
