package sample;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class Bean extends JPanel implements Serializable {


    private String beanString;
    private Color beanColor;

    protected PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    protected VetoableChangeSupport vetoSupport = new VetoableChangeSupport(this);

    protected EventListenerList listenerList = new EventListenerList();

    public Bean() {

        setBorder(BorderFactory.createEtchedBorder());
        beanString = "Lab4";
        beanColor = new Color(1,0,0);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(beanColor);
        if(beanString != null && !beanString.equals("")){
            g.drawString(beanString, 15, 15);
        }

    }




    public void setBeanString(String newString) throws PropertyVetoException  {
        String oldString = this.beanString;
        beanString = newString;
        changeSupport.firePropertyChange("beanString", oldString, newString);
    }

    public String getBeanString() {
        return beanString;
    }

    public void setBeanColor(Color color) throws PropertyVetoException {
        Color oldColor = beanColor;
        vetoSupport.fireVetoableChange("beanFont", oldColor, color);
        beanColor = color;
        changeSupport.firePropertyChange("beanFont", oldColor, color);
    }

    public Color getBeanColor() {
        return beanColor;
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
        changeSupport.addPropertyChangeListener(l);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
        changeSupport.removePropertyChangeListener(l);
    }

    public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
        vetoSupport.addVetoableChangeListener(l);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
        vetoSupport.removeVetoableChangeListener(l);
    }

    public synchronized void addChangeListener(ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    public synchronized void removeChangeListener(ChangeListener l) {
        listenerList.remove(ChangeListener.class, l);
    }



}
