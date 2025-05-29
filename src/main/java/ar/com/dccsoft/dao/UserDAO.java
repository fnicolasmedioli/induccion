package ar.com.dccsoft.dao;

import ar.com.dccsoft.model.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAO {

    EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public List<User> queryEjemploHQL() {

        String hql = "select distinct u from User u join u.groups g join g.operations op where op.name = 'Operacion_B'";

        return em.createQuery(hql, User.class).getResultList();
    }

    public List<User> queryEjemploProgramatico(String operacion) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);

        Join<Object, Object> group = user.join("groups");
        Join<Object, Object> operation = group.join("operations");

        cq.select(user).distinct(true).where(cb.equal(operation.get("name"), operacion));

        return em.createQuery(cq).getResultList();
    }

}
