/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
/**
 *
 * @author ASUS
 */
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("choose an option"));
        
        Button btnAddSeance = new Button("Add Seance");
        Button btnListSeances = new Button("List Seances");
        btnAddSeance.addActionListener(e-> new AddSeanceForm(current).show());
        btnListSeances.addActionListener(e-> new ListSeancesForm(current).show());
        addAll(btnAddSeance,btnListSeances);
    }

 
}
