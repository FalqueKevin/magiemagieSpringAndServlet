/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.service;

import atos.magieMagie.dao.CarteDAO;
import atos.magieMagie.dao.CarteDAOCrud;
import atos.magieMagie.dao.JoueurDAO;
import atos.magieMagie.dao.JoueurDAOCrud;
import atos.magieMagie.dao.PartieDAO;
import atos.magieMagie.dao.PartieDAOCrud;
import atos.magieMagie.entity.Carte;
import atos.magieMagie.entity.Joueur;
import atos.magieMagie.entity.Partie;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrateur
 */
@Service
public class PartieService {
    
    @Autowired
    private PartieDAOCrud partieDAOCrud;
    private PartieDAO partieDAO = new PartieDAO();
    @Autowired
    private JoueurDAOCrud joueurDAOCrud;
    private JoueurDAO joueurDAO = new JoueurDAO();
    @Autowired
    private CarteDAOCrud carteDAOCrud;
    private CarteDAO carteDAO = new CarteDAO();
    
    public List<Partie> listerPartieNonDemarrees(){
        
        return partieDAOCrud.listerPartieNonDemarrees();
        
    }
    
    public Partie creerNouvellePartie(String nomPartie){
        
        Partie p = new Partie();
        p.setNom(nomPartie);
        partieDAOCrud.save(p);
        return p;
        
    }
    
    @Transactional
    public void demarrerPartie(Long partieID){
        
        Partie p = partieDAOCrud.findOne(partieID);
        if ( p.getJoueurs().size() < 2L){  
            throw new RuntimeException("Il faut au moins 2 joueurs dans la partie");
        }
        for(Joueur j : p.getJoueurs()){
            if (j.getOrdre()==1L){
                j.setEtatJoueur(Joueur.etat.A_LA_MAIN);
                joueurDAOCrud.save(j);
            }
            for(int i = 0; i < 7; i++){
                this.distribuerUneCarteAleatoire(j.getId());
            }
        }
        
    }
    
        public void distribuerUneCarteAleatoire(Long joueurID) {
        
        Joueur j = joueurDAOCrud.findOne(joueurID);
        Carte c = new Carte();
        Carte.typeIngredient[] tabTypeIngredients = Carte.typeIngredient.values();
        Random r = new Random();
        int n = r.nextInt(tabTypeIngredients.length);
        c.setIngredient(tabTypeIngredients[n]);
        j.getCartes().add(c);
        c.setJoueur(j);
        carteDAOCrud.save(c);   
    
    }
    
    public void passerTour(Long partieID, Long joueurID){
        
        Joueur j = joueurDAO.rechercherParID(joueurID);
        this.distribuerUneCarteAleatoire(joueurID);
        this.passerLaMainAuJoueurSuivant(partieID, joueurID);
        
    }
    
    public String passerLaMainAuJoueurSuivant(Long partieID, Long joueurActuel) {
        
        if (partieDAO.rechercheJoueurGagnant(partieID) != null){
            return partieDAO.rechercheJoueurGagnant(partieID);
        }
        Joueur j = joueurDAO.rechercherParID(joueurActuel);
        if (j.getEtatJoueur() != Joueur.etat.PERDU){
            j.setEtatJoueur(Joueur.etat.N_A_PAS_LA_MAIN);
            joueurDAO.modifier(j);
        }
        Joueur joueurSuivant = joueurDAO.rechercherJoueurSuivant(partieID, j.getOrdre());
        while (joueurSuivant.getEtatJoueur() != Joueur.etat.N_A_PAS_LA_MAIN){
            if (joueurSuivant.getEtatJoueur() == Joueur.etat.SOMMEIL_PROFOND){
                joueurSuivant.setEtatJoueur(Joueur.etat.N_A_PAS_LA_MAIN);
                joueurDAO.modifier(joueurSuivant);
                joueurSuivant = joueurDAO.rechercherJoueurSuivant(partieID, joueurSuivant.getOrdre());
            }
            else if (joueurSuivant.getEtatJoueur() == Joueur.etat.PERDU){
                joueurSuivant = joueurDAO.rechercherJoueurSuivant(partieID, joueurSuivant.getOrdre());
            }else if (joueurSuivant.getEtatJoueur() == Joueur.etat.A_LA_MAIN){
                break;
            }
        }
        joueurSuivant.setEtatJoueur(Joueur.etat.A_LA_MAIN);
        joueurDAO.modifier(joueurSuivant);
        return null;
    }
    
    public List<Joueur> rechercherJoueursParID(Long partieID){
        
        return partieDAO.rechercherJoueursParID(partieID);
        
    }

    public String rechercherNomParID(Long idPartie) {

        return partieDAO.rechercherParID(idPartie).getNom();

    }
    
    public Joueur rechercheJoueurQuiALaMainParPartieID(Long partieID){
        
        return partieDAO.rechercheJoueurQuiALaMainParPartieID(partieID);
        
    }

    public String rechercheJoueurGagnant(Long idPartie) {

        return partieDAO.rechercheJoueurGagnant(idPartie);
        
    }
    
}
