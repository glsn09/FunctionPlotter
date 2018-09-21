/*
 * Free to copy, distribute and change
 */
package functionplotter;

/**
 *
 * @author white
 */
public interface IController {
    
    public void reset();
    
    public void plot(String function, String xMax, String yMax, String xStep, int canvasSizeX, int canvasSizeY);
    
}
