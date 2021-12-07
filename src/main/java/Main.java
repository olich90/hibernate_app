import model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory(); // конфигурация SessionFactory - объект, который выдает нам сесси для работы с БД

        ProductDao dao = new ProductDao(sessionFactory);

        System.out.println("1 ------------");
        System.out.println(dao.findById(1L));

        System.out.println("2 ------------");
        dao.findAll().forEach(System.out::println);

        System.out.println("3 ------------");
        dao.deleteById(1L);
        System.out.println("3 ------------ check after delete method");
        dao.findAll().forEach(System.out::println);

        System.out.println("4 ------------");
        System.out.println(dao.saveOrUpdate(new Product("Notebook MSI", 1200)));
        System.out.println(dao.saveOrUpdate(new Product(5L,"Notebook Asus_new", 1500)));
        System.out.println("4 ------------ check after saveOrUpdate method");
        dao.findAll().forEach(System.out::println);


        sessionFactory.close();

    }
}


/* SQL
drop table if exists product;
create table if not exists product(
	id bigserial,
	title varchar(255),
	price int
);

insert into product (title, price)
values('Notebook Lenovo', 1000),
('Notebook Dell', 900),
('Notebook Acer', 1100),
('Notebook HP', 800),
('Notebook Asus', 600);

select * from product;
*/