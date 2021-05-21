/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.MyApp.entites;

/**
 *
 * @author nidha
 */
public class Aide {
    private int id ;
    private String sujet,probleme,mail ; 


    public Aide( String sujet, String probleme, String mail) {

        this.sujet = sujet;
        this.probleme = probleme;
        this.mail = mail;
    }
    

    public int getId() {
        return id;
    }

    public String getSujet() {
        return sujet; 
    }

    public String getProbleme() {
        return probleme;
    }

    public String getMail() {
        return mail;
    }

    public Aide() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
}
