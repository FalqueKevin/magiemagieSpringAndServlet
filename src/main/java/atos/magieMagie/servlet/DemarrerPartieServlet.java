/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.servlet;

import atos.magieMagie.service.PartieService;
import atos.magieMagie.spring.AutowireServlet;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DemarrerPartieServlet", urlPatterns = {"/demarrer-partie"})
public class DemarrerPartieServlet extends AutowireServlet {
    
    @Autowired
    private PartieService partieService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        partieService.demarrerPartie(Long.parseLong(req.getSession().getAttribute("idPartie").toString()));
        resp.sendRedirect("ecran-jeu");
        
    }

}
