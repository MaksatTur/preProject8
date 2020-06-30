package kg.max.crud.dao;

import kg.max.crud.model.Role;

import java.util.List;

public interface RoleDAO {
    List<String> getAllRolesName();

    Role getRoleById(long id);
}
