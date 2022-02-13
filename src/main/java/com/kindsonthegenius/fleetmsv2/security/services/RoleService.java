package com.kindsonthegenius.fleetmsv2.security.services;

import com.kindsonthegenius.fleetmsv2.security.models.Role;
import com.kindsonthegenius.fleetmsv2.security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    //Get All Roles
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    //Get Role By Id
    public Role findById(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    //Delete Role
    public void delete(int id) {
        roleRepository.deleteById(id);
    }

    //Update Role
    public void save(Role role) {
        roleRepository.save(role);
    }
}
