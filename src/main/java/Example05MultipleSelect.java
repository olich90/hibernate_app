import javax.persistence.EntityManager;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example05MultipleSelect {

  public static void main(String[] args) {
    SessionFactory sessionFactory = new Configuration()
        .configure("hibernate.cfg.xml")
        .buildSessionFactory();

    Student student1;

    // при открытии сессии создается контекст постоянства (внутри блока try)
    // сначала в контексте ничего нет
    // когда мы получем студента по айди - student1 = session.get(Student.class, 1L), хибернейт проверяет, что есть в контексте постоянства
    // пока там ничего нет. тогда он делает селект в БД, получает объект по айди и закидывает объект в контекст постоянства, но закидывает его
    // сразу в 2х экземплярах, которые связаны между собой. первый экземпляр нам недоступен. второй - тот, с которым мы работаем (student1).
    // когда мы делаем session.getTransaction().commit(), мы говорим, что всё, что мы сделали с доступным нам объектом (student1), мы хотим вернуть
    // наверх, в БД. хибернейт увидит, что наш измененный объект (student1) отличается от исходного, это для него сигнал о том, что объект изменили
    // и его нужно привести в новое состояние и происходит обновление - "выталкивание контекста в БД"
    // (в разных контекстах постоянства одни и те же объекты, вытещенные по одному и тому же айди, не равны)

    // можно вручную закидвать объекты в КП, чтобы хибернейт следил за определенным объектом


    try (Session session = sessionFactory.getCurrentSession()) {
      session.getTransaction().begin();

      // в данный момент объект является транзиент - им не управляет хибернейт
      Student student = new Student();
      // при вызове методов сэйв или сэйвОрАпдэйт объект привязывается к КП и становится Персистент и управляется хибернейтом


      student1 = session.get(Student.class, 1L);
      Student student2 = session.get(Student.class, 1L);

      Student s = new Student("BBB", 15);

      session.save(s);

      session.evict(s); // метод для отсоединения объекта от КП

      s.setScore(150);

      System.out.println(student1 == student2);

      session.getTransaction().commit();
    }

    // здесь объект student1 перешел в состояние детачт - отвязался от КП
    // дальше может создать еще один КП и закинуть его туда и он снова привяжется к другому КП, например, при методе сейв

    System.out.println(student1);

    sessionFactory.close();
  }
}
