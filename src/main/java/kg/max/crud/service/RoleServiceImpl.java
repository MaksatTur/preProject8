package kg.max.crud.service;

import kg.max.crud.dao.RoleDAO;
import kg.max.crud.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public List<String> getAllRolesName() {
        return roleDAO.getAllRolesName();
    }

    @Override
    @Transactional
    public Role getRoleById(long id) {
        return roleDAO.getRoleById(id);
    }
}
