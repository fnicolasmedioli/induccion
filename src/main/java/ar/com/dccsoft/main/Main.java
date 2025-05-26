package ar.com.dccsoft.main;

import javax.persistence.*;
import ar.com.dccsoft.model.Group;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadPersistenciaInduccion");
        EntityManager em = emf.createEntityManager();

        try {

            System.out.println("inic transaccion");
            em.getTransaction().begin();

            Group group = new Group();
            group.setName("administradores");
            group.setDescription("grupo de administracion");

            em.persist(group);

            em.getTransaction().commit();

            System.out.println("id nuevo grupo: " + group.getGroupid());
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }


    }
}