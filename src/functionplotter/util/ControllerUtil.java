/*
 * Free to copy, distribute and change
 */
package functionplotter.util;

import java.awt.Component;
import java.awt.Window;
import java.lang.reflect.Field;
import javax.swing.JFormattedTextField;

/**
 *
 * @author white
 */
public class ControllerUtil {
    
    static public <T extends Component> T getComponentByName(Window window, String name) {
        // loop through all of the class fields on that form
        for (Field field : window.getClass().getDeclaredFields()) {

            try {
                // let us look at private fields, please
                field.setAccessible(true);

                // compare the variable name to the name passed in
                if (name.equals(field.getName())) {
                    // get a potential match (assuming correct &lt;T&gt;ype)
                    final Object potentialMatch = field.get(window);

                    // cast and return the component
                    return (T) potentialMatch;
                }

            } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
                // ignore exceptions
            }
        }
        // no match found
        return null;
    }
   
}
