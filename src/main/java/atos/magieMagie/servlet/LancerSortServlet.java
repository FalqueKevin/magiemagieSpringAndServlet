/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.servlet;

import atos.magieMagie.entity.Carte;
import atos.magieMagie.entity.Joueur;
import atos.magieMagie.service.CarteService;
import atos.magieMagie.service.JoueurService;
import atos.magieMagie.spring.AutowireServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
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
//@WebServlet(name = "LancerSortServlet", urlPatterns = {"/lancer-sort"})
//public class LancerSortServlet extends AutowireServlet {
//    
//    @Autowired
//    private CarteService carteService;
//    @Autowired
//    private JoueurService joueurService;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        Carte carteIngredient1 = carteService.rechercherUneCarteParID(Long.parseLong(req.getParameter("id1")));
//        Carte carteIngredient2 = carteService.rechercherUneCarteParID(Long.parseLong(req.getParameter("id2")));
//        String combinaisonDesCartes = (carteIngredient1.getIngredient().toString() + carteIngredient2.getIngredient().toString());
//        switch (combinaisonDesCartes){
//            case "LAPIS_LAZULIAILE_CHAUVE_SOURIS":
//                List<Joueur> joueursSoumis = joueurService.sortDivination(Long.parseLong(req.getSession().getAttribute("idPartie").toString()));
//                req.setAttribute("joueursSoumis", joueursSoumis);
//                break;
//            case "MANDRAGOREAILE_CHAUVE_SOURIS":
//                String pseudoJoueurVictimeSommeilProfond = s1.nextLine();
//                joueurService.sortSommeilProfond(joueurService.rechercherParPseudo(pseudoJoueurVictimeSommeilProfond).getId());
//                break;
//            case "CORNE_LICORNEMANDRAGORE":
//                String pseudoJoueurVictimeFiltreAmour = s2.nextLine();
//                joueurService.sortFiltreAmour(Long.parseLong(req.getSession().getAttribute("idPartie").toString()),
//                                                Long.parseLong(req.getSession().getAttribute("idJoueur").toString()),
//                                                joueurService.rechercherParPseudo(pseudoJoueurVictimeFiltreAmour).getId());
//                break;
//            case "CORNE_LICORNEBAVE_CRAPAUD":
//                joueurService.sortInvisibilité(Long.parseLong(req.getSession().getAttribute("idPartie").toString()),
//                                                Long.parseLong(req.getSession().getAttribute("idJoueur").toString()));
//                break;
//            case "BAVE_CRAPAUDLAPIS_LAZULI":
//                joueurService.sortHypnose(Long.parseLong(req.getSession().getAttribute("idPartie").toString()),
//                                            idCarteDefossee,
//                                            Long.parseLong(req.getSession().getAttribute("idJoueur").toString()),
//                                            joueurService.rechercherParPseudo(pseudoJoueurVictimeHypnose).getId());
//                break;
//            default:
//                break;
//        }
//        joueurService.detruireCartesUtiliseesPourSort(Long.parseLong(req.getSession().getAttribute("idPartie").toString()),
//                                                        carteIngredient1.getId(),
//                                                        carteIngredient2.getId());
//        resp.sendRedirect("ecran-jeu");
//
//    }
//
//}
