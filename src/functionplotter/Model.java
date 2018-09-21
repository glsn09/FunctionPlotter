/*
 * Free to copy, distribute and change
 */
package functionplotter;

import functionplotter.util.Point;
import functionplotter.util.NumericEvaluator;
import functionplotter.Canvas.Line;
import functionplotter.view.FPForm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author white
 */
public class Model implements IModel{
    private double maxX, maxY;
    private double stepX, stepY;
    private String function;
    private int canvasSizeX, canvasSizeY;
    private FPForm mainWindow;
    
    private ArrayList<Line> lines;
    
    private final List<IObserver> observers;
    
    public Model(){
        lines = new ArrayList<>();
        observers = new ArrayList<>();
    }

    @Override
    public void reset() {
        while(lines.size() > 2){
            lines.remove(2);
        }
        notifyObservers();
    }
    
    private void notifyObservers(){
        for(IObserver observer : observers){
            observer.update();
        }
    }
  
    private void makeAxisLines(){
        Point beginX = new Point(canvasSizeX / 2, 0);
        Point endX = new Point(canvasSizeX / 2, canvasSizeY);
        lines.add(new Line((beginX), (endX)));
        
        //y
        Point beginY = new Point(0, canvasSizeY / 2);
        Point endY = new Point(canvasSizeX, canvasSizeY / 2);
        lines.add(new Line((beginY), (endY)));
    }
    
    private void makeLines(){
        if( function.contains("x") ){
            Point prevPoint = new Point(0-maxX, NumericEvaluator.eval(function.replace("x", "("+(0-maxX)+")")));
            
            for(double i = (0-maxX+stepX); i <= maxX; i+= stepX){
                String replaced = function.replace("x", "("+Math.floor(i*100)/100+")");
                Point nextPoint = new Point(i, NumericEvaluator.eval(replaced));
                
               lines.add(new Line(normalizePoint(prevPoint), normalizePoint(nextPoint)));
                prevPoint = nextPoint;
            }
        }
        else{
            double constant = Double.parseDouble(function);

            Point startingPoint = new Point(-maxX, constant);
            Point endingPoint = new Point(maxX, constant);
            Point normalizedStartingPoint = normalizePoint(startingPoint);
            Point normalizedEndingPoint = normalizePoint(endingPoint);
            
            lines.add(new Line(normalizedStartingPoint, normalizedEndingPoint));
        }
       
        notifyObservers();
    }
    
    /*
    To translate relatives coordinates (relative to the axis) to absolute ones (in the canvas)
    */
    private Point normalizePoint(Point p){
        double normalizedX = canvasSizeX/2 + ((canvasSizeX / 2) / maxX) * p.x;
        double normalizedY = canvasSizeY/2 + ((canvasSizeY / 2) / maxY) * p.y;
                
        return new Point(normalizedX, canvasSizeY - normalizedY);
    }

        
        
    @Override
    public void computeLinesForFunction(String function, double maxX, double maxY, double stepX, int canvasSizeX, int canvasSizeY) {
        this.function = function;
        this.maxX = maxX;
        this.maxY = maxY;
        this.stepX = stepX;
        this.stepY = stepY;
        this.canvasSizeX = canvasSizeX;
        this.canvasSizeY = canvasSizeY;
        
        if(lines.size() == 0){
            makeAxisLines();
        }
            makeLines();
    }

 

    @Override
    public ArrayList<Line> getLines() {
        return this.lines;
    }

    @Override
    public void setMainWindow(FPForm window) {
        this.mainWindow = window;
    }

    @Override
    public FPForm getMainWindow() {
        return mainWindow;
    }

    @Override
    public void registerObserver(IObserver observer) {
        this.observers.add(observer);
    }
    
    
}
