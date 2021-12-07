import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example04Delete {

  public static void main(String[] args) {
    SessionFactory sessionFactory = new Configuration()
        .configure("hibernate.cfg.xml")
        .buildSessionFactory();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.getTransaction().begin();

      Student student = session.get(Student.class, 4L);
      session.delete(student);

      session.getTransaction().commit();
    }

    sessionFactory.close();
  }
}
