/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author Hp Omen
 */
public class SessionManager {
    public static Preferences pref;
    private int id;
    private String email;
    private String password;
    private String role;
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;
    private boolean enable;
    private String [] roles;
    public static Preferences getPref(){
         return pref;
    }
    public static void setPref(Preferences pref){
         SessionManager.pref=pref;
    }

    public static int getId() {
        return pref.get("id",0);
        
    }
     public static void setId(int id) {
        pref.set("id",id);
    }
     
    public static  String getEmail() {
        return pref.get("email","");
        
    }
    public static void setEmail(String email) {
        pref.set("email",email);
    }
    public  static String getNom() {
        return pref.get("nom","");
        
    }
    public static void setNom(String nom) {
        pref.set("nom",nom);
    }
    public static  String getPrenom() {
        return pref.get("prenom","");
        
    }
    public static void setPrenom(String prenom) {
        pref.set("prenom",prenom);
    }
    public  static String getTelephone() {
        return pref.get("telephone","");
        
    }
    public static void setTelephone(String telephone) {
        pref.set("telephone",telephone);
    }
    public static String getAdresse() {
        return pref.get("adresse","");
        
    }
    public static void setAdresse(String adresse) {
        pref.set("adresse",adresse);
    }
    
     
     
    
    
    

    
           
    
}
