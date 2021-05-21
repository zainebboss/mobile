/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author ASUS
 */
public class Seance {
    private int idSeance;
    private int idFormation;
    private String lien;
    private String description;
    private String dateSeance;

    public Seance(int idSeance, int idFormation, String lien, String description, String dateSeance) {
        this.idSeance = idSeance;
        this.idFormation = idFormation;
        this.lien = lien;
        this.description = description;
        this.dateSeance = dateSeance;
    }

    public int getIdSeance() {
        return idSeance;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public String getLien() {
        return lien;
    }

    public String getDescription() {
        return description;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateSeance(String dateSeance) {
        this.dateSeance = dateSeance;
    }

    public String getDateSeance() {
        return dateSeance;
    }

    public Seance() {
    }
    
 
       
}
