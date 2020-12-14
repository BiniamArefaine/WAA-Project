package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;
//import edu.miu.cs.auctionproject.javaMailApi.SendEmailClass;
import edu.miu.cs.auctionproject.repository.UserRepository;
import edu.miu.cs.auctionproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        System.out.println("inside finduserie---");
        return userRepository.findById(id);
    }


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllNonVerifiedUsers() {
        return userRepository.findAllByVerificationFalse();
    }

    @Override
    public User getUserByPasswordAndUserName(String password, String userName) {
        System.out.println("------------");
        return userRepository.getUserByCredential_PasswordAndCredential_UserName(password, userName);
    }

    @Override
    public User getUserByEmailAndFirstName(String email, String firstName) {
        return userRepository.findAllByEmailAndFirstName(email, firstName);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User finduserByName(String userName) {
        return userRepository.findUserByCredentialUserName(userName);
    }

    @Override
    public User findUserByProductId(long productId) {
        return userRepository.findUserByproductId(productId);
    }

//    @Override
//    public String logInAttempt() {
//        return null;
//    }


//    @Override
//    public User findByCredential_UserNameOrRole(Credential credential1) {
//        return userRepository.getUserByCredential(credential1);
//    }
//
//    @Override
//    public UserDetails getUser(String username) {
//        return loadUserByUsername(username);
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        return (UserDetails) userRepository.findUserByFirstName(username);
//    }
}
