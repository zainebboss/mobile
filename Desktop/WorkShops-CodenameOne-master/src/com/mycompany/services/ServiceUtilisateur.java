/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import static com.codename1.io.Log.e;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Utilisateur;
import com.mycompany.gui.NewsfeedForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.SignInForm;
import com.mycompany.utils.Connexion;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Hp Omen
 */
public class ServiceUtilisateur {
    
    public static ServiceUtilisateur instance=null;
    public static boolean resultOK =true;
    private ConnectionRequest req;
    
    public static ServiceUtilisateur getInstance(){
        if(instance==null)
            instance=new ServiceUtilisateur();
        return instance;
    }
    public ServiceUtilisateur(){
        req=new ConnectionRequest();
    }
    
    public void signup(Utilisateur u, Resources res){        
       String url=Connexion.BASE_URL+"/api/utilisateur/addApprenant?email="+ u.getEmail().trim()
               +"&password="+u.getPassword().trim()+"&nom="+u.getNom().trim()
               +"&prenom="+u.getPrenom().trim()+"&telephone="+u.getTelephone().trim()
               +"&adresse="+u.getAdresse().trim();
       //String url=Connexion.BASE_URL+"/api/v1/login?email=zaineb@esprit.tn&password=Zaineb0000";
       System.out.println(url);
       req.setUrl(url);
        req.addResponseListener((e) -> {
                        Map<String,Object> result;
           
            try {
                result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                Map<String, String> response = (Map<String, String>)result.get("data");
                System.out.println(response);
                Integer code = Integer.parseInt(response.get("code"));
                System.out.println(code);
                if(code==1){
                   // Dialog.show("Inscription",response.get("message"), "Ok", "Annuler");
                   new SignInForm(res).show();
                }
                else{ 
                    Dialog.show("Inscription",response.get("message"), "Ok", null);
                    
                }

;


            } catch (IOException ex) {
                System.out.println(ex);
            } 
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
       
    }
    public void login(String email,String password, Resources res){
       
        String url=Connexion.BASE_URL+"/api/v1/login?email="+email+"&password="+password;
        req.setUrl(url);
        req.addResponseListener((e) -> {
        Map<String,Object> resultlogin;
           
            try {
                resultlogin = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                Map<String, String> responselogin = (Map<String, String>)resultlogin.get("data");
                Integer codelogin = Integer.parseInt(responselogin.get("code"));
                if(codelogin == 1)Dialog.show("Login","Mot de passe incorrect", "Ok", null);
                else if(codelogin == 2)Dialog.show("Login","Email n'existe pas", "Ok", null);
                else if(codelogin == 3){
                    SessionManager.setNom(responselogin.get("nom"));
                    SessionManager.setPrenom(responselogin.get("prenon"));
                    SessionManager.setTelephone(responselogin.get("tel"));
                    SessionManager.setAdresse(responselogin.get("adresse"));
                    SessionManager.setId(Integer.parseInt(responselogin.get("id")));
                    new NewsfeedForm(res).show();
                }
;


            } catch (IOException ex) {
                System.out.println(ex);
            }  
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public boolean profil(Utilisateur u){
       String url = Connexion.BASE_URL+"/api/utilisateur/profile?userId="+SessionManager.getId()
               +"&nom="+u.getNom()
               +"&prenom="+u.getPrenom()
               +"&telephone="+u.getTelephone()
               +"&adresse="+u.getAdresse()
               ;
  
       System.out.println(url);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) -> {
            Map<String,Object> result;
           
            try {
                result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                Map<String, String> response = (Map<String, String>)result.get("data");
                Dialog.show("Profil",response.get("message"), "Ok", null);

;


            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
        
    }
        
        
    
}
    
            
            

