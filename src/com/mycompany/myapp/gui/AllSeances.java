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
public class AllSeances extends Form {

    public AllSeances() {
        
        setTitle("List Seances");
        
       
        
        ArrayList<Seance> Seances;
        Seances = ServiceSeance.getInstance().getAllSeances();
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (Seance s : Seances) {
            Label idSeance = new Label();
            Label idFormation = new Label("" + s.getIdFormation());
            Label lien = new Label("" + s.getLien());
            Label description = new Label("" + s.getDescription());
            Label date = new Label("" + s.getDateSeance());
              
                
            
            Button edit = new Button("Edit");
            Button Delete = new Button("Delete");
            
            idSeance.setVisible(false);
            c1.add(idFormation);
            c1.add(lien);
            c1.add(description);
            c1.add(date);
            c1.add(edit);
            c1.add(Delete);
            

        }
        add(c1);
                       

    }
    }
    

