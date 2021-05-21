/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Seance;
import com.mycompany.myapp.services.ServiceSeance;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ListSeancesForm extends Form {
Form current;
    public ListSeancesForm(Form previous) {
        current=this;
        setTitle("List Seances");
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
        ArrayList<Seance> Seances;
        Seances = ServiceSeance.getInstance().getAllSeances();
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Seance s : Seances) {
            Label idSeance = new Label("" + s.getIdSeance());
            Label idFormation = new Label("ID de la formation :  " + s.getIdFormation());
            Label lien = new Label("Le lien associé :  " + s.getLien());
            Label description = new Label("La description :  " + s.getDescription());
            Label date = new Label("La Date  " + s.getDateSeance());
              
                
            
            
            
            idSeance.setVisible(false);
            c1.add(idSeance);
            c1.add(idFormation);
            c1.add(lien);
            c1.add(description);
            c1.add(date);
            Button edit = new Button("Edit");
            Button Delete = new Button("Delete");
            
            c1.add(edit);
            c1.add(Delete);
            Delete.addActionListener((evt) -> {
                if (ServiceSeance.getInstance().DeleteCommande(s.getIdSeance())){
                   this.show();
                } 
            }  
                );
            
            
            

        }
        
        
        add(c1);
                      

    }
    }
    

