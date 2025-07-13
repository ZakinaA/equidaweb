package servlet;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.DaoCheval;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Cheval;

@WebServlet(name = "chevalServlet", value = "/cheval-servlet/*")
public class ChevalServlet extends HttpServlet {

    Connection cnx;
    
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection)servletContext.getAttribute("connection");
        try {
            System.out.println("INIT SERVLET=" + cnx.getSchema());
        } catch (SQLException ex) {
            Logger.getLogger(ChevalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getPathInfo();
        System.out.println("PathInfo: " + path);

        if ("/list".equals(path)) {
            ArrayList<Cheval> lesChevaux = DaoCheval.getLesChevaux(cnx);
            request.setAttribute("pLesChevaux", lesChevaux);
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/cheval/list.jsp").forward(request, response);
        }
        else if ("/show".equals(path)) {
            try {
                int idCheval = Integer.parseInt(request.getParameter("idCheval"));
                Cheval leCheval = DaoCheval.getLeCheval(cnx, idCheval);

                if (leCheval != null) {
                    request.setAttribute("pLeCheval", leCheval);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/views/cheval/show.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/cheval-servlet/lister");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : l'id du cheval n'est pas un nombre valide");
                response.sendRedirect(request.getContextPath() + "/cheval-servlet/lister");
            }
        }
    }

    public void destroy() {
    }
}