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

    List<Product> findAllProducts(long userId);
}
