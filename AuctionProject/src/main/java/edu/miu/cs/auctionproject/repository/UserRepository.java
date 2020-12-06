package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
