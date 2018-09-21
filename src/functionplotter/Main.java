/*
 * 
 */
package functionplotter;

import functionplotter.util.NumericEvaluator;
import functionplotter.view.FPForm;

/**
 *
 * @author white
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Model model = new Model();
        Controller controller = new Controller(model);
        controller.initView();
       //System.out.println(NumericEvaluator.eval("0.0^2"));
       //System.out.println(Math.pow(0, 2));
    }
    
}
