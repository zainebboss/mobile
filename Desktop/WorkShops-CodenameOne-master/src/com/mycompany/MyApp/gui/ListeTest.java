/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.MyApp.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

/**
 *
 * @author nidha
 */
public class ListeTest extends Form{

public ListeTest(Form previous){
    setTitle("add aide ");
    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
}
