package sample;




import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;


import javax.swing.*;


import javax.swing.border.EtchedBorder;


public class Test {
static String s;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Laboratorium 4");
        JFrame panel = new JFrame("Bean");
        JTextField textField = new JTextField();
        JButton submit = new JButton("Zmien tekst");
        JButton choose = new JButton("Wybierz kolor");
        Controller control = new Controller();
        Bean bean = new Bean();
        try {
            bean.setBeanString("Zmien ten tekst");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        try {
            bean.setBeanColor(new Color(0,0,1));
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }


        bean.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        bean.addVetoableChangeListener(control);
        bean.addPropertyChangeListener(control);
        JColorChooser jColorChooser =new JColorChooser();

        ActionListener okActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    bean.setBeanColor(jColorChooser.getColor());
                    bean.setBeanString(s);
                } catch (PropertyVetoException e) {
                    e.printStackTrace();
                }
                SwingUtilities.updateComponentTreeUI(frame);
                SwingUtilities.updateComponentTreeUI(panel);
            }
        };
        ActionListener cancelActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("cancled");
            }
        };
        choose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                JColorChooser.createDialog(new JDialog(), "Color chooser", false, jColorChooser,okActionListener,cancelActionListener).show();
                SwingUtilities.updateComponentTreeUI(frame);
                SwingUtilities.updateComponentTreeUI(panel);
            }
        });


        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                    try {
                        s = textField.getText();
                        bean.setBeanString(s);
                    } catch (PropertyVetoException e) {
                        e.printStackTrace();
                    }
                SwingUtilities.updateComponentTreeUI(frame);
                SwingUtilities.updateComponentTreeUI(panel);

            }
        });

        panel.setSize(500,200);
        panel.getContentPane().setLayout(new BorderLayout());
        panel.getContentPane().add(bean, BorderLayout.CENTER);

        frame.setSize(600, 250);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(textField, BorderLayout.BEFORE_FIRST_LINE);
        frame.getContentPane().add(choose, BorderLayout.WEST);
        frame.getContentPane().add(submit, BorderLayout.AFTER_LAST_LINE);
        frame.setVisible(true);
        panel.setVisible(true);
    }
}