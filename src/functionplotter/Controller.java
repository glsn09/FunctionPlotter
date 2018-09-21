/*
 * Free to copy, distribute and change
 */
package functionplotter;

import functionplotter.util.ControllerUtil;
import functionplotter.view.FPForm;
import javax.swing.JButton;

/**
 *
 * @author white
 */
public class Controller implements IController{
    
    
    private final String Component_View = "jPanel5";
    
    private IModel model;
    private Canvas view;
    
    public Controller(IModel model){
        this.model = model;
        
    }
    
    public void initView(){
        FPForm mainWindow = new FPForm();
        mainWindow.setVisible(true);
       
        view = ControllerUtil.getComponentByName(mainWindow, Component_View);
        view.init(model, this, mainWindow);
        model.setMainWindow(mainWindow);
        registerListeners();
    }
    
    private void registerListeners(){
        JButton plotButton = ControllerUtil.getComponentByName(model.getMainWindow(), "plotButton");
        plotButton.addActionListener(view);
        
        JButton resetButton = ControllerUtil.getComponentByName(model.getMainWindow(), "resetButton");
        resetButton.addActionListener(view);
    }
    

    

    @Override
    public void reset() {
        model.reset();
    }

    @Override
    public void plot(String function, String xMax, String yMax, String xStep, int canvasSizeX, int canvasSizeY) {
        model.computeLinesForFunction(
                function, 
                Double.parseDouble(xMax), 
                Double.parseDouble(yMax), 
                Double.parseDouble(xStep),
                canvasSizeX,
                canvasSizeY);
        
    }
    
}
