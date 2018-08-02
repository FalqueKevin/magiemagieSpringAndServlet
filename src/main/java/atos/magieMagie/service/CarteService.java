/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.service;

import atos.magieMagie.dao.CarteDAO;
import atos.magieMagie.dao.CarteDAOCrud;
import atos.magieMagie.dao.JoueurDAOCrud;
import atos.magieMagie.entity.Carte;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarteService {
    
    @Autowired
    private CarteDAOCrud carteDAOCrud;
    
    public Carte rechercherUneCarteParID(Long carteID) {
        
        return carteDAOCrud.findOne(carteID);
        
    }
    
}
