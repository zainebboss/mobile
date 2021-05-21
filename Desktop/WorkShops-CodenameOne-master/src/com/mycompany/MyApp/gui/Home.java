/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.MyApp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author nidha
 */
public class Home extends Form{
    Form current ;
    private Resources theme;
    public Home(){
        current=this;
        setTitle("Espace Aide");
        setLayout(BoxLayout.y());
        add(new Label("chose an option "));
        Button btnAddaide=new Button("Add aide");
        Button btnListaid=new Button("List aide");
        Button btnAddaidecom=new Button("Add aidecom");
        Button btnListaidecom=new Button("List aidecom");
        btnAddaide.addActionListener(e-> new AddaideApprenant(theme).show());
        btnListaid.addActionListener(e-> new showaideApprenant(theme).show());
        btnAddaidecom.addActionListener(e-> new Addaidecom(theme).show());
        btnListaidecom.addActionListener(e-> new ShowAideCommaintre(theme).show());
        addAll(btnAddaide, btnListaid, btnAddaidecom, btnListaidecom);
        
    }
    
}
