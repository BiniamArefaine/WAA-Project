package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
