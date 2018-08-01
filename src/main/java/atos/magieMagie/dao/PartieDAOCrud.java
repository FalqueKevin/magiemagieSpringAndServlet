/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.dao;

import atos.magieMagie.entity.Joueur;
import atos.magieMagie.entity.Partie;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrateur
 */
public interface PartieDAOCrud extends CrudRepository<Partie, Long>{
        
    @Query("SELECT p "
            + "FROM Partie p "
            + "EXCEPT "
            + "SELECT p "
            + "FROM Partie p "
            + " JOIN p.joueurs j "
            + "WHERE j.etatJoueur=atos.magieMagie.entity.Joueur.etat.GAGNANT"
            + " OR j.etatJoueur=atos.magieMagie.entity.Joueur.etat.A_LA_MAIN")
    public List<Partie> listerPartieNonDemarrees();
    
}
