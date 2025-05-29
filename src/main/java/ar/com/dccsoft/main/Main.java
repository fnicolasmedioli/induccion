package ar.com.dccsoft.main;

import javax.persistence.*;

import ar.com.dccsoft.dao.UserDAO;
import ar.com.dccsoft.model.Group;
import ar.com.dccsoft.model.Operation;
import ar.com.dccsoft.model.User;
import ar.com.dccsoft.seed.DataSeeder;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void generarDatos(boolean romper, EntityManagerFactory emf) {

        DataSeeder ds = new DataSeeder();

        List<User> users = ds.getUsers();
        List<Group> groups = ds.getGroups();
        List<Operation> operations = ds.getOperations();

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            groups.forEach(em::persist);
            users.forEach(em::persist);
            operations.forEach(em::persist);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            em.close();
            return;
        }

        try {
            em.getTransaction().begin();

            User userPrueba = new User();
            userPrueba.setLogin("nicolas");
            userPrueba.setPassword("nicolas123");
            userPrueba.setGroups(Arrays.asList(groups.get(2)));

            em.persist(userPrueba);

            if (romper) {
                throw new Exception("Error programatico");
            }

            User userPrueba2 = new User();
            userPrueba2.setLogin("user2");
            userPrueba2.setPassword("user2_123");
            userPrueba2.setGroups(Arrays.asList(groups.get(2)));

            em.persist(userPrueba2);

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            em.close();
            return;
        }
        em.close();

        System.out.println("Datos generados");
    }


    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadPersistenciaInduccion");

        generarDatos(false, emf);

        UserDAO userDAO = new UserDAO(emf.createEntityManager());


        List<User> usersHQL = userDAO.queryEjemploHQL();
        List<User> usersProg = userDAO.queryEjemploProgramatico("Operacion_B");

        System.out.println("Query HQL");
        usersHQL.forEach(System.out::println);

        System.out.println("Query prog");
        usersProg.forEach(System.out::println);

        emf.close();

    }
}