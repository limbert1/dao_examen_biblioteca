
package com.emergentes.controlador;

import com.emergentes.dao.LibroDao;
import com.emergentes.dao.LibroDaoImpl;
import com.emergentes.modelo.Libro;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "LibroController", urlPatterns = {"/LibroController"})
public class LibroController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               try {
            int id;
            Libro avi = new Libro();
            LibroDao dao = new LibroDaoImpl();

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("libro", avi);
                    request.getRequestDispatcher("frmlibro.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    avi = dao.getById(id);

                    request.setAttribute("libro", avi);
                    request.getRequestDispatcher("frmlibro.jsp").forward(request, response);

                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("Inicio");
                    break;
                case "view":
                    List<Libro> lista = dao.getAll();
                    request.setAttribute("libro", lista);
                    request.getRequestDispatcher("libros.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("ERROR:" + ex.getMessage());
        }
     
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               LibroDao dao = new LibroDaoImpl();

        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String disponible = request.getParameter("disponible");
        String categoria = request.getParameter("categoria");

        Libro pro = new Libro();
        pro.setId(id);
        pro.setTitulo(titulo);
        pro.setAutor(autor);
        pro.setDisponible(disponible);
        pro.setCategoria(categoria);
        
      
        try {
            if (id == 0) {

                dao.insert(pro);

            } else {
                dao.update(pro);
            }

        } catch (Exception ex) {
            System.out.println("ERROR al guardar datos...");
        }

        response.sendRedirect("Inicio");

    }

  

}
