package sample;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class BeanInfo extends SimpleBeanInfo  {

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {

            return new PropertyDescriptor[] {
                    new PropertyDescriptor("beanColor", Bean.class, "getBeanColor", "setBeanColor"),
                    new PropertyDescriptor("beanString", Bean.class, "getBeanString", "setBeanString"),
            };
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
