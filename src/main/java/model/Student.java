package model;

import javax.persistence.*;

@Entity // класс является сущностью
@Table(name = "student") // указать к какой таблице эта сущность относится
@NamedQueries({
        @NamedQuery(name = "studentsWithScore", query = "select s from Student s where s.score > :score") // именованый запрос, query д.б. эйчКуЭль
})
public class Student {

    @Id // указать что поле является первичным ключом
    @GeneratedValue(strategy = GenerationType.IDENTITY) // за проставление первичного ключа отвечает БД
    @Column(name= "id") // указываем имя колонки в таблице
    private Long id;

    @Column(name= "name") // указываем имя колонки в таблице
    private String name;

    @Column(name= "score") // указываем имя колонки в таблице
    private Integer score;


    public Student(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    // конструктор по умолчанию необходим для Hibernate, его осталяем
    public Student() {
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
