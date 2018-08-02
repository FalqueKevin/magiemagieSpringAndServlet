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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrateur
 */
@Service
public class JoueurService {
    
    @Autowired
    private PartieDAOCrud partieDAOCrud;
    @Autowired
    private JoueurDAOCrud joueurDAOCrud;
    @Autowired
    private CarteDAOCrud carteDAOCrud;
    
    public Joueur rejoindrePartie(String nomJoueur, String avatar, Long partieID){
        
        // Recherche si le joueur existe déjà
        Joueur j = joueurDAOCrud.findOneByPseudo(nomJoueur);
        
        if (j == null){
            //Le joueur n'existe pas encore
            j = new Joueur();
            j.setPseudo(nomJoueur);
            j.setNbPartiesGagnees(0L);
            j.setNbPartiesJouees(0L);
        }
        j.setAvatar(avatar);
        j.setEtatJoueur(Joueur.etat.N_A_PAS_LA_MAIN);
        if (joueurDAOCrud.rechercherOrdreNouveauJoueur(partieID)==null){
            j.setOrdre(1L);
        }else{
            j.setOrdre(joueurDAOCrud.rechercherOrdreNouveauJoueur(partieID));
        }
        Partie p = partieDAOCrud.findOne(partieID);
        j.setPartie(p);
        p.getJoueurs().add(j);
        
        joueurDAOCrud.save(j);
        return j;
        
    }
    
    
    public void detruireCartesUtiliseesPourSort(Long idPartie, Long ingredient1, Long ingredient2){
        
        Carte carteIngredient1 = carteDAOCrud.findOne(ingredient1);
        Carte carteIngredient2 = carteDAOCrud.findOne(ingredient2);
        carteDAOCrud.delete(carteIngredient1);
        carteDAOCrud.delete(carteIngredient2);
        List<Joueur> joueurs = joueurDAOCrud.findByPartieId(idPartie);
        for(Joueur j : joueurs){
            if (j.getCartes().size()==0){
                j.setEtatJoueur(Joueur.etat.PERDU);
                j.setNbPartiesJouees(j.getNbPartiesJouees()+1);
                joueurDAOCrud.save(j);
            }
        }
       
    }
    
    public List<Joueur> sortDivination(Long idPartie) {

        List<Joueur> joueurs = joueurDAOCrud.findByPartieId(idPartie);
        return joueurs;

    }

    public void sortSommeilProfond(Long idJoueurVictime) {

        Joueur j = joueurDAOCrud.findOne(idJoueurVictime);
        j.setEtatJoueur(Joueur.etat.SOMMEIL_PROFOND);
        joueurDAOCrud.save(j);

    }

    public void sortFiltreAmour(Long idPartie, Long idJoueurLanceur, Long idJoueurVictime) {
        
        Joueur joueurLanceur = joueurDAOCrud.findOne(idJoueurLanceur);
        Joueur joueurVictime = joueurDAOCrud.findOne(idJoueurVictime);
        List<Carte> cartesDuJoueurVictime = joueurVictime.getCartes();
        if (cartesDuJoueurVictime.size()==1){
            joueurVictime.setEtatJoueur(Joueur.etat.PERDU);
            joueurVictime.setNbPartiesJouees(joueurVictime.getNbPartiesJouees()+1);
            joueurDAOCrud.save(joueurVictime);
            carteDAOCrud.delete(cartesDuJoueurVictime.get(0));
            return;
        }
        if(joueurVictime.getCartes().size()>0){
            int nbCartesVolees = cartesDuJoueurVictime.size() / 2;
            for(int i = 0; i < nbCartesVolees; i++){
                this.volerUneCarteAuHasard(joueurLanceur.getId(), joueurVictime.getId());
            }
        }
        
    }

    public void sortInvisibilité(Long idPartie, Long idJoueurLanceur) {
        
        Joueur joueurLanceur = joueurDAOCrud.findOne(idJoueurLanceur);
        List<Joueur> joueurs = partieDAOCrud.findOne(idPartie).getJoueurs();
        for(Joueur joueurVictime : joueurs){
            if (joueurVictime.getCartes().size()>0){
                this.volerUneCarteAuHasard(joueurLanceur.getId(), joueurVictime.getId());
            }
        }
        
    }

    public void sortHypnose(Long idPartie, Long IDcarteDonnee, Long IDjoueurLanceur, Long IDjoueurVictime) {

        Joueur joueurLanceur = joueurDAOCrud.findOne(IDjoueurLanceur);
        Joueur joueurVictime = joueurDAOCrud.findOne(IDjoueurVictime);
        List<Carte> cartesDuJoueurVictime = joueurVictime.getCartes();
        for(int i = 0; i < 3; i++){
            if (cartesDuJoueurVictime.size()>0){
                this.volerUneCarteAuHasard(joueurLanceur.getId(), joueurVictime.getId());
            }
        }
        this.donnerUneCarteDeSonChoix(IDcarteDonnee, IDjoueurVictime);

    }

    public void volerUneCarteAuHasard(Long IDjoueurLanceur, Long IDjoueurVictime) {
        
        Joueur joueurLanceur = joueurDAOCrud.findOne(IDjoueurLanceur);
        Joueur joueurVictime = joueurDAOCrud.findOne(IDjoueurVictime);     
        Carte c = new Carte();
        Random r = new Random();
        int n = r.nextInt(joueurVictime.getCartes().size());
        c = (joueurVictime.getCartes().get(n));
        c.setJoueur(joueurLanceur);
        joueurVictime.getCartes().add(c);
        carteDAOCrud.save(c);
        
    }

    public void donnerUneCarteDeSonChoix(Long IDcarteDonnee, Long IDjoueurVictime) {
        
        Carte carteDonnee = carteDAOCrud.findOne(IDcarteDonnee);
        Joueur joueurVictime = joueurDAOCrud.findOne(IDjoueurVictime);
        carteDonnee.setJoueur(joueurVictime);
        joueurVictime.getCartes().add(carteDonnee);
        carteDAOCrud.save(carteDonnee);

    }
    
    public Joueur rechercherParID(Long joueurID) {
        
        return joueurDAOCrud.findOne(joueurID);
        
    }
    
    public Joueur rechercherParPseudo(String pseudo){
        
        return joueurDAOCrud.findOneByPseudo(pseudo);
        
    }
    
}
