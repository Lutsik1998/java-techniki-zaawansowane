package sample;

import java.awt.*;
import java.beans.*;
public class Controller implements VetoableChangeListener,PropertyChangeListener{

    @Override
    public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {
        Color newColor = (Color) e.getNewValue();
        Color val = newColor;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if(evt.getOldValue() instanceof String)
        {
            System.out.println("Zmieniono tekst na: " + evt.getNewValue());
        }
        else if(evt.getOldValue() instanceof Color)
        {
            System.out.println("Zmieniono kolor na: " + evt.getNewValue());
        }
    }


}
