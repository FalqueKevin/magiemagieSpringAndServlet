/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.dao;

import atos.magieMagie.entity.Joueur;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrateur
 */
public interface JoueurDAOCrud extends CrudRepository<Joueur, Long>{
    
    
    
}
