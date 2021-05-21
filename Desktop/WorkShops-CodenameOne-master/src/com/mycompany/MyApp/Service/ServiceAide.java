/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.MyApp.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.MyApp.entites.Aide;
import com.mycompany.MyApp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ServiceAide {
        public boolean resultOK;
    public ConnectionRequest req;

    public static ServiceAide instance = null;
    
    public static ServiceAide getInstance(){
        if (instance==null)
            instance = new ServiceAide();
        return instance;
    }
    
    public ServiceAide(){
        req = new ConnectionRequest();
    }
    
    
    public void addAIde(Aide a){
        String url =Statics.BASE_URL+"/testtest/addReclamation?sujet="+a.getSujet()+"&probleme="+a.getProbleme()+"&mail="+a.getMail();
       // System.out.println(url);
        /*ConnectionRequest req =new ConnectionRequest(url);
         req.addResponseListener(new ActionListener<NetworkEvent>(){
             @Override
            public void actionPerformed(NetworkEvent evt){
                resultOK=req.getResponseCode()==200;
            }
            
        });*/
        req.setUrl(url);
        req.addResponseListener((e) -> {
                String str = new String(req.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //return resultOK ;  
    }
    
    public ArrayList<Aide> displaySubject() {
        ArrayList<Aide> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/testtest/displayReclamations";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            public void actionPerformed(NetworkEvent evnt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String,Object> mapAide = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapAide.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Aide s = new Aide();
                        double id = (double) obj.get("id");
                        String sujet = obj.get("sujet").toString();
                        
                        String probleme = obj.get("probleme").toString();
                        String mail = obj.get("mail").toString();
                        
                        s.setId((int) id);
                        s.setSujet(sujet);
                        s.setProbleme(probleme);
                        s.setMail(mail);
                        result.add(s);
                    }
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
      public boolean deleteSubject(int id){
        String url = Statics.BASE_URL+"/testtest/deleteAide?id="+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
      
      /* public Aide detailsUser(int id){
        String url = Statics.BASE_URL+"/testtest/detailCommaintre?id="+id;
        Aide s = new Aide();
        req.setUrl(url);
        String str = new String(req.getResponseData());
        req.addResponseListener((e) -> {
        JSONParser jsonp;
        jsonp = new JSONParser();
        try {
            Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
//            System.out.println(obj.toString());
            List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) obj.get("root");
            
//            for(Map<String, Object> objj : listOfMaps) {
                        String sujet = obj.get("sujet").toString();
                        
                        String probleme = obj.get("probleme").toString();
                        String mail = obj.get("mail").toString();
                        
                        s.setId((int) id);
                        s.setSujet(sujet);
                        s.setProbleme(probleme);
                        s.setMail(mail);
//                c.setId((int) idd);
//                System.out.println("test f user "+c.toString());
//            }
        }catch(IOException  ex){
            System.out.println("error related to sql "+ex.getMessage());
        }
//
        });
        System.out.println("datevvv ==="+str);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return s;
    } 
      */ 
       
}
