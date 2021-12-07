import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example01Get {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory(); // конфигурация SessionFactory - объект, который выдает нам сесси для работы с БД

        try (Session session = sessionFactory.getCurrentSession()) {// запрос сессии, сессия - единица работы с БД
            session.getTransaction().begin(); // все действия в Hibernate производятся в транзакции

            Student student = session.find(Student.class, 1L); // получение студента по ID
            System.out.println(student);

            session.getTransaction().commit(); // все действия в Hibernate производятся в транзакции
        }

        sessionFactory.close();
    }
}