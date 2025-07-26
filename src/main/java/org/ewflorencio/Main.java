package org.ewflorencio;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.ewflorencio.control.BookControler;
import org.ewflorencio.model.Book;

public class Main {
    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8081);

        Context context = tomcat.addContext("", null);


        Tomcat.addServlet(context,"BookServlet", new BookControler());


        context.addServletMappingDecoded("/book", "BookServlet");


        tomcat.start();
        tomcat.getServer().await();

    }
}