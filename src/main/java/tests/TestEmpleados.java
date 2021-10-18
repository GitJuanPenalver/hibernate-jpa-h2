package tests;

import es.makigas.hibernate.modelo.Direccion;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.makigas.hibernate.modelo.Empleado;
import java.time.LocalDate;


public class TestEmpleados {

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        /*Cear el gestor de persistencia*/
        emf = Persistence.createEntityManagerFactory("pu");
        manager = emf.createEntityManager();
        
        insertar();
        manager.getTransaction().begin();
        Empleado e = manager.find(Empleado.class, 10L);
        e.setNombre("David");
        e.setDireccion(new Direccion(15L, "Calle Falsa, 123", "Springfield", "Springfield", "EEUU"));
//        manager.merge(e);
        manager.getTransaction().commit();

        imprimirTodo();

    }

    private static void insertar() {
        Empleado e = new Empleado(10L, "Perez", "Pepito", LocalDate.of(2000, 11, 18));
        manager.getTransaction().begin();
        manager.persist(e);
        e.setApellidos("Lukaku");
        manager.getTransaction().commit();
    }

    private static void imprimirTodo() {
        List<Empleado> emps = (List<Empleado>) manager.createQuery("FROM Empleado").getResultList();
        System.out.println("En esta base de datos hay " + emps.size() + " empleados");
        for (Empleado emp : emps) {
            System.out.println(emp.toString());
        }
    }
}
