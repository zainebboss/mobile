/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Seance;
import com.mycompany.myapp.services.ServiceSeance;


/**
 *
 * @author ASUS
 */
public class AddSeanceForm extends Form {

    public AddSeanceForm(Form previous) {
        
        setTitle("Add a new seance");
        setLayout(BoxLayout.y());
        
        TextField tfID_formation = new TextField("","SeanceID_formation");
        TextField tflien = new TextField("","Seancelien");
        TextField tfdescription = new TextField("","Seancedescription");
        TextField tfDate_seance = new TextField("","SeanceDate_seance");
        Button btnValider = new Button("add seance");
        btnValider.addActionListener(new ActionListener(){
            @Override
        public void actionPerformed(ActionEvent evt){
            if ((tfID_formation.getText().length()==0) ||(tflien.getText().length()==0) || (tfdescription.getText().length()==0) || (tfDate_seance.getText().length()==0))
                Dialog.show("Alert" ,"please fill all the fields",new Command("OK"));
            else
            {
            try{
            
            Seance s = new Seance();
            s.setLien(tflien.getText());
            s.setDescription(tfdescription.getText());
            s.setDateSeance(tfDate_seance.getText());
            s.setIdFormation(Integer.parseInt(tfID_formation.getText()));
            if(new ServiceSeance().addSeance(s))
                Dialog.show("Success" ,"sEANCE ADDED",new Command("OK"));
            else
                Dialog.show("ERROR" ,"Server Error", new Command("OK"));
            } catch (NumberFormatException e){
                Dialog.show("ERROR" ,"ID must be a number", new Command("OK"));
            }
        }  
            
        }
        });
        addAll(tfID_formation,tflien,tfdescription,tfDate_seance,btnValider);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
        
    }
    
}
