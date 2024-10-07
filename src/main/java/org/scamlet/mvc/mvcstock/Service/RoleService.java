package org.scamlet.mvc.mvcstock.Service;

import org.scamlet.mvc.mvcstock.Entity.Role;
import org.scamlet.mvc.mvcstock.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role getRole(String roleName) {
        return roleRepository.findByRole(roleName);
    }

}
