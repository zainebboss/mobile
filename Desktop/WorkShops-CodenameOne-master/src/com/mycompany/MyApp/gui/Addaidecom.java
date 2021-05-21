/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.MyApp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.MyApp.Service.ServiceAide;
import com.mycompany.MyApp.Service.ServiceAidecom;
import com.mycompany.MyApp.entites.Aide;
import com.mycompany.MyApp.entites.Aidecom;


/**
 *
 * @author nidha
 */
public class Addaidecom extends Form {
    
    /*public AddaideApprenant(Form previous){
    setTitle("add aide ");
    setLayout(BoxLayout.y());
    TextField tfSujet=new TextField("","Sujet");
    TextField tfProbleme=new TextField("","probleme");
    TextField tfMail=new TextField("","mail");
    Button btnvalider=new Button("Add Probleme");
    
    btnvalider.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
            if ((tfSujet.getText().length()==0)||(tfProbleme.getText().length()==0 )||(tfMail.getText().length()==0 ))
                Dialog.show("Alert","Please fill all the fields",new Command("ok"));
            else
            {
                try{
                    Aide a=new Aide(tfSujet.getText(),tfProbleme.getText(),tfMail.getText()); 
                    if(new ServiceAide().addAIde(a))
                        Dialog.show("succes", "server error", new Command("ok"));
                    else
                        Dialog.show("ERROR", "server error", new Command("ok")); 
                }catch(NumberFormatException e){
                    Dialog.show("ERROR", "server error", new Command("ok")); 
                    
                }
            }
        }
    });
    addAll(tfSujet,tfProbleme,tfMail,btnvalider);
    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    */
    
    
    Form current;
    public Addaidecom(Resources res) {  
        super("Ajout Commentaire",BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current =this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        getContentPane().setScrollVisible(false);
        
        TextField subject = new TextField("","entrer commaintre");
        subject.setUIID("TextFiealdBlack");
        addStringValue("Commentaire",subject);
        
        TextField probleme = new TextField("","entrer   NumeroSujet");
        probleme.setUIID("TextFiealdBlack");
        addStringValue("NumeroSujet",probleme);
        

        Button btnRetour = new Button("Retour");
        addStringValue("", btnRetour);
        
        btnRetour.addActionListener((e) -> {
            try {
                new Home().show();
            }
            catch ( Exception ex) {
                ex.printStackTrace();
            }
        });
        Button btnAjout = new Button("Ajouter");
        addStringValue("", btnAjout);
        
        btnAjout.addActionListener((e) -> {
            try {
                if(subject.getText()=="") {
                    Dialog.show("Veuillez vérifier les données","", "Annuler", "ok");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    Aidecom aidecom = new Aidecom();
                    aidecom.setCommaintre(subject.getText().toString());
                    aidecom.setId_sujet(probleme.getText().toString());
                
//                    subject.setId_class(Integer.parseInt(idClass.getText().toString()));
//                    subject.setId_teacher(Integer.parseInt(idTeacher.getText().toString()));
                    
                    
                    ServiceAidecom.getInstance().addAIdecom(aidecom);
                    
                    iDialog.dispose();
                 Dialog.show("Successful","", "Annuler", "ok");
                    //new AddaideApprenant(res).show();
                    
                    refreshTheme();
                }
            }
            catch ( Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public void addTab(Tabs swipe,Label spacer, Image image, String string, String text , Resources res){
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (image.getHeight()<size){
            image = image.scaledHeight(size);
        }
        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overLay = new Label("","ImageOverLay");
        Container page1 = 
                LayeredLayout.encloseIn(
                        imageScale,
                        overLay,
                        BorderLayout.south(
                                BoxLayout.encloseY(new SpanLabel(text,"LargeWhiteText"),
                                            FlowLayout.encloseIn(),
                                            spacer 
                                )
                        )
                );
        swipe.addTab("",res.getImage("img.jpg"), page1);
    }
    public void bindButtonSelection(Button btn, Label l ) {
        btn.addActionListener(e -> {
            if (btn.isSelected()) {
                updateArrowPosition(btn, l);
            }
        });
    }
    
    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT , btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }
     
        private DefaultRenderer buildCategoryRenderer(int[] colors)     {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    for (double value : values) {
        series.add("Project " + ++k, value);
    }

    return series;
}
    
    public Form createPieChartForm() {
    // Generate the values
    double[] values = new double[]{12, 14, 11, 10, 19};

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);
    Button b = new Button("Back");
    b.addActionListener(e -> {
        current.show();
    });

    // Create a form and show it.
    Form f = new Form("Budget", new BorderLayout());
    f.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseY(b),BoxLayout.encloseY(c)));
    return f;

}
}
