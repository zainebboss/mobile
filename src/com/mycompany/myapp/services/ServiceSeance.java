/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Seance;
import com.mycompany.myapp.utils.Statics;



import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceSeance {

    public ArrayList<Seance> Seances;
    private ConnectionRequest req;
    public static ServiceSeance instance = null;
    public boolean resultOK;
    public static ServiceSeance getInstance() {
        if (instance == null) {
            instance = new ServiceSeance();
        }
        return instance;
    }
    

    public boolean addSeance(Seance s) {
        String url = Statics.BASE_URL + "/addSeanceMobile?idFormation=" + s.getIdFormation() + "&Lien=" + s.getLien() + "&description=" + s.getDescription() + "&date=" + s.getDateSeance();
        ConnectionRequest req = new ConnectionRequest(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code http 200 OK
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }

    public ArrayList<Seance> getAllSeances() {
        String url = Statics.BASE_URL + "/affichSeance";
        System.out.println("hi url : " + url);
        ConnectionRequest req = new ConnectionRequest(url);
       req.setUrl(url);
        System.out.println("hi url : " + url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String res = new String(req.getResponseData());

                Seances = parseChannell(res);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Seances;
    }

    public ArrayList<Seance> parseChannell(String jsonText) {
        try {
            Seances = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du r??sultat json

            /*
                On doit convertir notre r??ponse texte en CharArray ?? fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilit?? de- new CharArrayReader(json.toCharArray())
            
            La m??thode parse json retourne une MAP<String,Object> ou String est 
            la cl?? principale de notre r??sultat.
            Dans notre cas la cl?? principale n'est pas d??finie cela ne veux pas
            dire qu'elle est manquante mais plut??t gard??e ?? la valeur par defaut
            qui est root.
            En fait c'est la cl?? de l'objet qui englobe la totalit?? des objets 
                    c'est la cl?? d??finissant le tableau de t??ches.
             */
           Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            /* Ici on r??cup??re l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une t??che.               
            
            Le format Json impose que l'objet soit d??finit sous forme
            de cl?? valeur avec la valeur elle m??me peut ??tre un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus ad??quate en Java pour stocker des couples Key/Value. 
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
             */
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des t??ches Json
            for (Map<String, Object> obj : list) {
                //Cr??ation des t??ches et r??cup??ration de leurs donn??es
                Seance t = new Seance();
                if (obj.get("idSeance") != null) {
                    float id = Float.parseFloat(obj.get("idSeance").toString());
                    t.setIdSeance((int) id);
                }

                float id = Float.parseFloat(obj.get("idSeance").toString());
                t.setIdSeance((int) id);
                float idx = Float.parseFloat(obj.get("idFormation").toString());
                t.setIdFormation((int) idx);
                t.setLien(obj.get("lien").toString());
                t.setDescription(obj.get("description").toString());
                t.setDateSeance(obj.get("dateSeance").toString());

                //Ajouter la t??che extraite de la r??ponse Json ?? la liste
                Seances.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex);

        }
        /*
            A ce niveau on a pu r??cup??rer une liste des t??ches ?? partir
        de la base de donn??es ?? travers un service web
                String url = Statics.BASE_URL + "consultation/consultation/mobile";

         */
        return Seances;

    }
            public boolean DeleteCommande(int id) {
        String url = Statics.BASE_URL + "/deleteSeance/"+id;
          ConnectionRequest req = new ConnectionRequest(url);
        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
            public void AddSMS()
            {
                    
     String accountSID = "ACd75ddc9db0ffd0305fd239a56d37bcd0";
String authToken = "1a4cbd9fcc4b82a355d96482e5801ac7";
String fromPhone = "+12564019788";
String destinationPhone="+21621247066";
        setTitle("List seance");
      Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
        queryParam("To", destinationPhone).
        queryParam("From", fromPhone).
        queryParam("Body", "iiiw").
        basicAuth(accountSID, authToken).getAsJsonMap();
      if(result.getResponseData() != null) {
    String error = (String)result.getResponseData().get("error_message");
    if(error != null) {
        ToastBar.showErrorMessage(error);
    }
}
                    

}
}
