package org.ewflorencio.service;

import org.ewflorencio.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookService {

    Session session;
    SessionFactory sessionFactory;
    Transaction trans;


    public BookService(){



    }


    private void connection(){
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(Book.class)
                .configure()
                .buildSessionFactory();
        this.session = sessionFactory.openSession();
        this.trans = session.beginTransaction();
    }

    private void close(){
        this.session.close();
        this.sessionFactory.close();

    }

    public List<Book> findAll() {

        connection();

        List<Book> bookList = session.createQuery("from Book", Book.class).getResultList();

        this.trans.commit();
        close();

        return bookList;

    }

    public Book findById(int id) {

        connection();

        Book book = session.get(Book.class,id);

        this.trans.commit();
        close();

        return book;
    }



    public Book create(String name, int idCategory, int idWriter, int idStatus, int nuPages) {

        connection();

        Book book = new Book();

        book.setName(name);
        book.setIdCategory(idCategory);
        book.setIdWriter(idWriter);
        book.setIdStatus(idStatus);
        book.setNuPages(nuPages);


        session.persist(book);

        this.trans.commit();
        close();

        return book;
    }

    public boolean update(int id, String name, int idCategory, int idWriter, int idStatus, int nuPages) {

        connection();

        Book book = new Book();

        book.setId(id);
        book.setName(name);
        book.setIdCategory(idCategory);
        book.setIdWriter(idWriter);
        book.setIdStatus(idStatus);
        book.setNuPages(nuPages);

        if (session.get(Book.class,id) == null){
            close();
            return false;
        }

        session.merge(book);

        this.trans.commit();
        close();


        return true;
    }

    public boolean delete(int id) {

        connection();

        Book book = session.get(Book.class,id);

        if (book == null){
            close();
            return false;
        }

        session.remove(book);

        this.trans.commit();
        close();


        return true;
    }
}
