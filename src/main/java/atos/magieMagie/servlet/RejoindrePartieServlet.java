/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.servlet;

import atos.magieMagie.entity.Joueur;
import atos.magieMagie.service.JoueurService;
import atos.magieMagie.service.PartieService;
import atos.magieMagie.spring.AutowireServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "RejoindrePartieServlet", urlPatterns = {"/rejoindre-partie"})
public class RejoindrePartieServlet extends AutowireServlet {
    
    @Autowired
    private JoueurService joueurService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.getRequestDispatcher("rejoindre-partie.jsp").forward(req, resp);

    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().setAttribute("idJoueur", joueurService.rejoindrePartie(req.getParameter("login"), req.getParameter("avatar"), Long.parseLong(req.getSession().getAttribute("idPartie").toString())).getId());
        resp.sendRedirect("lister-joueurs");

    }

}
