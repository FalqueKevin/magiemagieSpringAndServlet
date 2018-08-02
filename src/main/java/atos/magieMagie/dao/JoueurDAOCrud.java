/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.dao;

import atos.magieMagie.entity.Carte;
import atos.magieMagie.entity.Joueur;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrateur
 */
public interface JoueurDAOCrud extends CrudRepository<Joueur, Long>{
    
    public Joueur findOneByPseudo(String pseudo);
    
    public List<Joueur> findByPartieId(Long idPartie);

    @Query("SELECT MAX(j.ordre) FROM Joueur j JOIN j.partie p WHERE p.id =?1")
    public Long rechercherOrdreNouveauJoueur(Long partieID);

    @Query("SELECT j FROM Joueur j JOIN Partie p WHERE j.ordre =?1 AND p.id =?2")
    public Joueur rechercherJoueurSuivant(Long ordreJoueurSuivant, Long partieID);
    
}
