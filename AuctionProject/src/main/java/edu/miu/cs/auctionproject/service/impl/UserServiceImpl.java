package edu.miu.cs.auctionproject.service.impl;

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
public class UserServiceImpl implements UserService {
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
        return userRepository.findById(id);
    }


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
