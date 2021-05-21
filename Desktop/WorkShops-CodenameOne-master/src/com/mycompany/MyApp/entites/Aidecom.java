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
public class Aidecom {


    
    private int  id;
    private String commaintre,id_sujet;

    public Aidecom(int id, String commaintre, String id_sujet) {
        this.id = id;
        this.commaintre = commaintre;
        this.id_sujet = id_sujet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommaintre() {
        return commaintre;
    }

    public void setCommaintre(String commaintre) {
        this.commaintre = commaintre;
    }

    public String getId_sujet() {
        return id_sujet;
    }

    public void setId_sujet(String id_sujet) {
        this.id_sujet = id_sujet;
    }
    public Aidecom() {
    }
    @Override
    public String toString() {
        return "aidecom{" + "id=" + id + ", commaintre=" + commaintre + ", id_sujet=" + id_sujet + '}';
    }

    
}
