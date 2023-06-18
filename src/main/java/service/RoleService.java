package service;

import model.Role;
import repository.RoleRepository;

import java.util.List;

public class RoleService {
    RoleRepository roleRepository = new RoleRepository();
    public List<Role> findAllRole() {
        return roleRepository.findAllRole();
    }

    public boolean insertRole(String name, String desc) {
        return roleRepository.insertRole(name, desc);
    }

}
