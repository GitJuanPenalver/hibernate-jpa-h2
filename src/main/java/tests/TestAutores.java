/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import es.makigas.hibernate.modelo.Autor;
import es.makigas.hibernate.modelo.Libro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jpenalvs
 */
public class TestAutores {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args) {
        crearDatos();
        imprimirDatos();
    }

    static void crearDatos() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Autor autor1 = new Autor(1L, "Pablo", "Española");
        Autor autor2 = new Autor(2L, "Maria", "Española");
        Autor autor3 = new Autor(3L, "Paco", "Francesa");
        em.persist(autor1);
        em.persist(autor2);
        em.persist(autor3);

        em.persist(new Libro(1L, "Programar es sencillo", autor2));
        em.persist(new Libro(2L, "Programar es divertido", autor2));
        em.persist(new Libro(3L, "Programar es bonito", autor1));
        em.persist(new Libro(4L, "Programar es intuitivo", autor3));
        em.getTransaction().commit();
        em.close();
    }

    static void imprimirDatos() {
        EntityManager em = emf.createEntityManager();
        Autor autor2 = em.find(Autor.class, 2L);
        List<Libro> libros = autor2.getLibros();
        for (Libro libro : libros){
            System.out.println("* " + libro.toString());
        }
        System.out.println(autor2);
        em.close();
    }

}
