package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
