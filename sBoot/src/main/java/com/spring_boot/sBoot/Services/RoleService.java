package com.spring_boot.sBoot.Services;


import com.spring_boot.sBoot.model.Role;
import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> findAllRole();
    void addDefaultRole();
    Set<Role> findByIdRoles(List<Long>roles);
}

