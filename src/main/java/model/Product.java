package model;

import javax.persistence.*;

@Entity // класс является сущностью
@Table(name = "product") // указать к какой таблице эта сущность относится
public class Product {

    @Id // указать что поле является первичным ключом
    @GeneratedValue(strategy = GenerationType.IDENTITY) // за проставление первичного ключа отвечает БД
    @Column(name = "id") // указываем имя колонки в таблице
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
