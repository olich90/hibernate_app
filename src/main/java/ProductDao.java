import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    SessionFactory sessionFactory;

    public ProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product findById(Long id) {
        Product product;
        try (Session session = sessionFactory.getCurrentSession()) { // запрос сессии, сессия - единица работы с БД
            session.getTransaction().begin(); // все действия в Hibernate производятся в транзакции

            product = session.find(Product.class, 1L);

            session.getTransaction().commit(); // все действия в Hibernate производятся в транзакции
        }
        return product;
    }

    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();

            productList = session
                    .createQuery("select p from Product p", Product.class) // либо .createNativeQuery("select * from product", Product.class)
                    .getResultList();

            session.getTransaction().commit();
        }
        return productList;
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();

            Product product = session.get(Product.class, id);
            session.delete(product);

            session.getTransaction().commit();
        }
    }

    public Product saveOrUpdate(Product product) {
        Product p;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();

            if (product.getId() == null) {// объект новый - хотим сохранить в БД
                session.save(product);
                return product;
            } else {
                p = session.find(Product.class, product.getId()); // попробуем достать из БД продукт по ID
            }

            if (p != null) { // продукт найден по ID
                p.setTitle(product.getTitle());
                p.setPrice(product.getPrice());
            } else {
                return null;
            }
            session.getTransaction().commit();
        }
        return p;
    }
}
