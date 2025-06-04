package ar.com.dccsoft.induccion.repository;

import ar.com.dccsoft.induccion.entity.Group;
import ar.com.dccsoft.induccion.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findWithPermissionsCriteria() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);

        userRoot.fetch("groups", JoinType.LEFT);

        cq.select(userRoot).distinct(true);
        cq.orderBy(cb.asc(userRoot.get("login")));

        return entityManager.createQuery(cq).getResultList();
    }
}
