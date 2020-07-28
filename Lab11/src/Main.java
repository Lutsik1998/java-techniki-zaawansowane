import javax.management.*;
import java.lang.management.ManagementFactory;

public class Main {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        Sort sortMBean = new Sort();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("lab11JMX:type=JMX");
        mbs.registerMBean(sortMBean, objectName);
        RandomLista.setRange(1000);
        sortMBean.sort();
    }
}
