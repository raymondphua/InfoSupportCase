package com.infosupport.controller;

import com.infosupport.Database.CursistRepository;
import com.infosupport.domain.Cursist;

import java.util.List;

/**
 * Created by Raymond Phua on 12-10-2016.
 */
public class CursistController {

    private CursistRepository cursistRepository;

    public CursistController() {
        this.cursistRepository = new CursistRepository();
    }

    public List<Cursist> getCursisten() {
        return cursistRepository.getAllCursisten();
    }

}
