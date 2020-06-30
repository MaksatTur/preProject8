package kg.max.crud.dao;

import kg.max.crud.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getAllRolesName() {
        TypedQuery<String> query = entityManager.createQuery("select r.name from Role r", String.class);
        return query.getResultList();
    }

    @Override
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }
}
