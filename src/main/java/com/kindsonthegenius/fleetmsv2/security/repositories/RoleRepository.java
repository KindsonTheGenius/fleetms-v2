package com.kindsonthegenius.fleetmsv2.security.repositories;

import com.kindsonthegenius.fleetapp_v2.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "SELECT * FROM role WHERE id NOT IN (SELECT role_id FROM user_role WHERE user_id = ?1)", nativeQuery = true)
    List<Role> getUserNotRoles(Integer userId);

    @Query(
            value = "SELECT * FROM role WHERE id IN (SELECT role_id FROM user_role WHERE user_id = ?1)",
            nativeQuery = true
    )
    List<Role> getUserRoles(Integer userId);

}
