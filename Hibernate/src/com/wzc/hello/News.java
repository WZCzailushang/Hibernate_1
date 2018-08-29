package com.wzc.hello;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;//引入这个类就正确了
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * Hibernate 不要求持久化类继承任何父类或实现接口，这可以保证代码不被污染。
 * 这就是Hibernate被称为低侵入式设计的原因
 * @author WZC
 *
 */
@Entity
public class News {
    private int id;
    private String title;
    private String author;
    private Date date;

    public News() {
    }

    public News(String title, String author, Date date) {
        this.title = title;
        this.author = author;
        this.date = date;
    }



    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                Objects.equals(title, news.title) &&
                Objects.equals(author, news.author) &&
                Objects.equals(date, news.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, date);
    }
}
