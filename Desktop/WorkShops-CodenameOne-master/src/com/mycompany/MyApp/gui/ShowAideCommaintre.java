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
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.MyApp.Service.ServiceAide;
import com.mycompany.MyApp.Service.ServiceAidecom;
import com.mycompany.MyApp.entites.Aide;
import com.mycompany.MyApp.entites.Aidecom;
import java.util.ArrayList;

/**
 *
 * @author nidha
 */
public class ShowAideCommaintre extends Form{
    Form current;
    public ShowAideCommaintre(Resources res) {  
        super("Ajouter Commaintre",BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current =this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        getContentPane().setScrollVisible(false);
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
        ArrayList<Aidecom> alSub = ServiceAidecom.getInstance().displaySubject();
        for(Aidecom c : alSub) {
            String urlImage="img.jpg";
            Image placeHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            addButton(urlim,c,res);
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
        
        
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
    
    private void addButton(Image img, Aidecom c, Resources res) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width,height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
//        System.out.println(c.toString());
        Label subjectTxt = new Label("Commentaire : "+c.getCommaintre(),"NewTopLine2");
        Label problemeTxt = new Label("Numéro Sujet : "+c.getId_sujet(),"NewTopLine2");
      //  Label mailTxt = new Label("Mail : "+c.getMail(),"NewTopLine2");

//        System.out.println(classe.toString());
   
        
//     User user = SubjectService.getInstance().detailsUser(c.getId_teacher());
//        System.out.println(user.toString());
//        Label idTeacherTxt = new Label("Enseignant : "+user.getName()+" "+user.getLastName(),"NewTopLine2");
        
        
        
        Label lSupp = new Label(" ");
        lSupp.setUIID("NewsTopLine");
        Style supprimerStyle = new Style(lSupp.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lSupp.setIcon(supprimerImage);
        lSupp.setTextPosition(RIGHT);
        
        Label lActions = new Label(" ");
        lSupp.setUIID("NewsTopLine");
        Style actionsStyle = new Style(lActions.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        FontImage actionsImage = FontImage.createMaterial(FontImage.MATERIAL_DASHBOARD, actionsStyle);
        lActions.setIcon(actionsImage);
//        lActions.setTextPosition(CENTER);
        
        lSupp.addPointerPressedListener(l -> {         
            if (ServiceAidecom.getInstance().deleteSubjectcom(c.getId()))
                new ShowAideCommaintre(res).show();   
            });
            lActions.addPointerPressedListener(l -> {          
                         new Home().show();
            });
            
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(subjectTxt),BoxLayout.encloseX(problemeTxt),BoxLayout.encloseXRight(lSupp),BoxLayout.encloseXRight(lActions)));
        add(cnt);
    }
}
