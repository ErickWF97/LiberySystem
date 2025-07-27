package org.ewflorencio;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.ewflorencio.control.AuthController;
import org.ewflorencio.control.BookControler;
import org.ewflorencio.dao.UserDao;
import org.ewflorencio.model.Book;
import org.ewflorencio.model.User;
import org.ewflorencio.service.AuthService;

public class Main {
    public static void main(String[] args) throws LifecycleException {

        var sessionFactory = new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(User.class)
                .configure()
                .buildSessionFactory();

        var userDao = new UserDao(sessionFactory);
        var authService = new AuthService(userDao);

        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8081);

        Context context = tomcat.addContext("", null);


        Tomcat.addServlet(context,"BookServlet", new BookControler());
        Tomcat.addServlet(context,"auth", new AuthController(authService));

        context.addServletMappingDecoded("/book", "BookServlet");
        context.addServletMappingDecoded("/login", "auth");

        tomcat.start();
        tomcat.getServer().await();

    }
}