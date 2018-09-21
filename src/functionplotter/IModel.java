/*
 * Free to copy, distribute and change
 */
package functionplotter;

import functionplotter.Canvas.Line;
import functionplotter.view.FPForm;
import java.util.ArrayList;

/**
 *
 * @author white
 */
public interface IModel {
    
    public void reset();
    public void computeLinesForFunction(String function, double maxX, double maxY, double stepX, int canvasSizeX, int canvasSizeY);
  
    public void setMainWindow(FPForm window);
    public FPForm getMainWindow();
    public ArrayList<Line> getLines();
    
    public void registerObserver(IObserver observer);
}
