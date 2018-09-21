/*
 * Free to copy, distribute and change
 */
package functionplotter;

import functionplotter.util.ControllerUtil;
import functionplotter.util.Point;
import functionplotter.view.FPForm;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author white
 */
public class Canvas extends JPanel implements IObserver, ActionListener{
    
    private final String ActionCommand_PLOT = "Plot";
    private final String ActionCommand_RESET = "Reset";
    private final String TextInput_FUNCTION = "function";
    private final String TextInput_XMAX = "xMaxValue";
    private final String TextInput_YMAX = "yMaxValue";
    private final String TextInput_XSTEP = "xStep";
    
    private IModel model;
    private IController controller;
    
    private FPForm mainWindow;
    
    private boolean paintLines = false;
    
    
    public void init(IModel model, IController controller, FPForm mainWindow){
        this.model = model;
        this.controller = controller;
        this.mainWindow = mainWindow;
        model.registerObserver(this);
    }
   
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(paintLines){
            System.out.println("painting");
            for(Line r : model.getLines()) {
                System.out.println(r.x1+", "+r.y1+" _to_ "+r.x2+", "+r.y2);
                r.paint(g);
            }
        }
        
    }

    @Override
    public void update() {
        paintLines = true;
        
        paintComponent(this.getGraphics());
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals(ActionCommand_PLOT)){
            String function = getText(TextInput_FUNCTION);
            String xMax = getText(TextInput_XMAX);
            String yMax = getText(TextInput_YMAX);
            String xStep = getText(TextInput_XSTEP);
            
            controller.plot(function, xMax, yMax, xStep, this.getWidth(), this.getHeight());
        }
        else if(ae.getActionCommand().equals(ActionCommand_RESET)){
            controller.reset();
        }
        
    }
    
    private String getText(String componentName){
        return ((JTextComponent)ControllerUtil.getComponentByName(mainWindow, componentName)).getText();
      
    }
    
    public static class Line {
    public final int x1;
    public final int x2;
        public final int y1;
        public final int y2;
        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
        public Line(Point p0, Point p1){
            this.x1 = (int)p0.x;
            this.y1 = (int)p0.y;
            this.x2 = (int)p1.x;
            this.y2 = (int)p1.y;
        }
        public void paint(Graphics g) {
            g.drawLine(this.x1, this.y1, this.x2, this.y2);
        }
    }
    
}
