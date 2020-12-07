package edu.miu.cs.auctionproject.service;

import edu.miu.cs.auctionproject.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    void saveRole(Role role);
    void deleteRole(Long id);
    Optional<Role> findRoleById(Long id);
    List<Role> findAllRoles();
}
