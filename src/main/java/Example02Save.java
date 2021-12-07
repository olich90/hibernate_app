import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example02Save {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory(); // конфигурация SessionFactory - объект, который выдает нам сесси для работы с БД

        try (Session session = sessionFactory.getCurrentSession()) {// запрос сессии, сессия - единица работы с БД
            session.getTransaction().begin(); // все действия в Hibernate производятся в транзакции

            Student student = new Student("Михаил", 99);
            System.out.println(student);

            // в данный момент студен не связан с БД

            session.save(student); // сохранение объекта в БД

            // после вызова метода save объект-студент уже привязан к БД

            System.out.println(student);

            session.getTransaction().commit(); // все действия в Hibernate производятся в транзакции
        }

        sessionFactory.close();
    }
}