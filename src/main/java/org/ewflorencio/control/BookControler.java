package org.ewflorencio.control;

import org.ewflorencio.model.Book;
import org.ewflorencio.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BookControler extends HttpServlet {

    private BookService bookService = new BookService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");

        if (id == null || id.isEmpty()) {

            List<Book> books = bookService.findAll();
            for (Book b : books) {
                out.println(b.toString());
            }
        } else {

            Book b = bookService.findById(Integer.parseInt(id));
            if (b != null) {
                out.println(b);
            } else {
                resp.setStatus(404);
                out.println("Book not found!!!");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        int idCategory = Integer.parseInt(req.getParameter("idCategory"));
        int idWriter = Integer.parseInt(req.getParameter("idWriter"));
        int idStatus = Integer.parseInt(req.getParameter("idStatus"));
        int nuPages = Integer.parseInt(req.getParameter("nuPages"));

        Book newBook = bookService.create(name, idCategory, idWriter, idStatus, nuPages);

        resp.setContentType("application/json");
        resp.getWriter().println("Created: " + newBook.toString() + "!!!");

    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int id = Integer.parseInt(req.getParameter("id"));

        String name = req.getParameter("name");
        int idCategory = Integer.parseInt(req.getParameter("idCategory"));
        int idWriter = Integer.parseInt(req.getParameter("idWriter"));
        int idStatus = Integer.parseInt(req.getParameter("idStatus"));
        int nuPages = Integer.parseInt(req.getParameter("nuPages"));

        boolean bookChange = bookService.update(id,name, idCategory, idWriter, idStatus, nuPages);


        resp.setContentType("application/json");
        if (bookChange) {
            resp.setStatus(200);
            resp.getWriter().println("Change the book with success!!!");
        } else {
            resp.setStatus(404);
            resp.getWriter().println("Error to change the book!!!");
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        boolean bookChange = bookService.delete(id);


        resp.setContentType("application/json");
        if (bookChange) {
            resp.setStatus(200);
            resp.getWriter().println("The book delete with success!!!");
        } else {
            resp.setStatus(404);
            resp.getWriter().println("Book not found!!!");
        }

    }
}
