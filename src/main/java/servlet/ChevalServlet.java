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

    Connection cnx ;
    
    /**
     * Initialise la servlet en récupérant le contexte et la connexion à la base de données.
     * Le contexte de la servlet est utilisé pour obtenir la connexion stockée comme attribut.
     * Affiche le chemin du contexte et le schéma de la base de données pour le débogage.
     */
    public void init() {
        ServletContext servletContext=getServletContext();
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

        /**
         * Vérifie si l'URL se termine par "/list"
         * Si c'est le cas, on récupère la liste de tous les chevaux et on les affiche grâce à la vue
         */
        if ("/list".equals(path))
        {
            ArrayList<Cheval> lesChevaux = DaoCheval.getLesChevaux(cnx);
            request.setAttribute("pLesChevaux", lesChevaux);
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/cheval/list.jsp").forward(request, response);
        }

        /**
         * Vérifie si l'URL se termine par "/show"
         * Ce bloc gère l'affichage des détails d'un cheval spécifique
         */
        else if ("/show".equals(path)) {
            try {
                int idCheval = Integer.parseInt(request.getParameter("idCheval"));
                Cheval leCheval = DaoCheval.getLeCheval(cnx, idCheval);

            /**
             * Test imbriqué : vérifie si le cheval existe dans la base
             * Si oui : affiche les détails du cheval
             * Si non : redirige vers la liste des chevaux
             */
            if (leCheval != null) {
                request.setAttribute("pLeCheval", leCheval);
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/cheval/show.jsp").forward(request, response);
            } else {
                // Redirection vers une page d'erreur ou la liste si le cheval n'existe pas
                response.sendRedirect(request.getContextPath() + "/cheval-servlet/lister");
            }
        } catch (NumberFormatException e) {
            // Gestion de l'erreur si l'id n'est pas un nombre valide
            System.out.println("Erreur : l'id du cheval n'est pas un nombre valide");
            response.sendRedirect(request.getContextPath() + "/cheval-servlet/lister");
        }
    }
}

    public void destroy() {
    }
}