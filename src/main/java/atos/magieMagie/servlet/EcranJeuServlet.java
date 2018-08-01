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
@WebServlet(name = "EcranJeuServlet", urlPatterns = {"/ecran-jeu"})
public class EcranJeuServlet extends AutowireServlet {
    
    @Autowired
    private PartieService partieService;
    @Autowired
    private JoueurService joueurService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Joueur monJoueur = joueurService.rechercherParID(Long.parseLong(req.getSession().getAttribute("idJoueur").toString()));
        req.setAttribute("monJoueur", monJoueur);
        List<Joueur> adversaires = partieService.rechercherJoueursParID(Long.parseLong(req.getSession().getAttribute("idPartie").toString()));
        req.setAttribute("adversaires", adversaires);
        req.getRequestDispatcher("ecran-jeu.jsp").forward(req, resp);

    }

}
