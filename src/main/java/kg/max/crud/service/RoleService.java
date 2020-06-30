package kg.max.crud.service;

import kg.max.crud.model.Role;

import java.util.List;

public interface RoleService {
    List<String> getAllRolesName();

    Role getRoleById(long id);
}
