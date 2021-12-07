import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example06Query {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        //EntityManager entityManager = sessionFactory.createEntityManager();
        //entityManager.getTransaction().begin();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();

            // данный эйчКуЭль код не зависит от БД, будет рабоать на любого типа БД
            var students1 = session
                    .createQuery("select s from Student s where s.score > :score", Student.class) // эйчКуЭль запрос, обращение не к таблицам, а к объектам и манипулируем полями объекта - это препред статемент; Student.class - указываем на что мапим - т.е. что в итоге ожидаем
                    .setParameter("score", 80)
                    .getResultList();

            var students2 = session
                    .createNativeQuery("select * from student where score > :score", Student.class) // NativeQuery - ссылаемся не таблицу БД
                    .setParameter("score", 80)
                    .getResultList();

            var student = session
                    .createQuery("from Student s where s.name = :name", Student.class)
                    .setParameter("name", "Анна")
                    .getSingleResult();

//      session.createQuery("update Student s set s.score = 0") // обнулить скор у всех
//          .executeUpdate();

            var students3 = session
                    .createNamedQuery("studentsWithScore", Student.class) // передаем именованный запрос
                    .setParameter("score", 80)
                    .getResultList();

            students1.forEach(System.out::println);

            System.out.println("------");

            students2.forEach(System.out::println);


            System.out.println("------");

            students3.forEach(System.out::println);

            session.getTransaction().commit();
        }
        sessionFactory.close();
    }
}

