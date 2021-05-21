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
import com.mycompany.MyApp.entites.Aidecom;
import com.mycompany.MyApp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ServiceAidecom {
        public boolean resultOK;
    public ConnectionRequest req;

    public static ServiceAidecom instance = null;
    
    public static ServiceAidecom getInstance(){
        if (instance==null)
            instance = new ServiceAidecom();
        return instance;
    }
    
    public ServiceAidecom(){
        req = new ConnectionRequest();
    }
    
    
    public void addAIdecom(Aidecom a){
        String url =Statics.BASE_URL+"/testtest/addCommaintre?commaintre="+a.getCommaintre()+"&id_sujet="+a.getId_sujet();
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
    
    public ArrayList<Aidecom> displaySubject() {
        ArrayList<Aidecom> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/testtest/displayCommaintre";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            public void actionPerformed(NetworkEvent evnt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String,Object> mapAide = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapAide.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Aidecom s = new Aidecom();
                        double id = (double) obj.get("id");
                        String commaintre = obj.get("commaintre").toString();
                        String id_sujet = obj.get("idSujet").toString();
                    
                        
                        s.setId((int) id);
                        s.setCommaintre(commaintre);
                        s.setId_sujet(id_sujet);
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
          public boolean deleteSubjectcom(int id){
        String url = Statics.BASE_URL+"/testtest/deleteAidecom?id="+id;
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
          
    public ArrayList<Aidecom> displayComById(int id) {
        ArrayList<Aidecom> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/testtest/displayComById?id="+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            public void actionPerformed(NetworkEvent evnt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String,Object> mapAide = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapAide.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Aidecom s = new Aidecom();
                        String commaintre = obj.get("commaintre").toString();
                        String id_sujet = obj.get("idSujet").toString();
                    
                        
                        s.setId((int) id);
                        s.setCommaintre(commaintre);
                        s.setId_sujet(id_sujet);
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
    
    
    
      
}
