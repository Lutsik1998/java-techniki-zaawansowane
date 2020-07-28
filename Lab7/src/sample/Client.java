package sample;

import javax.xml.namespace.QName;
import javax.xml.soap.*;

public class Client {
    public int port;


    public Client(int port) {
        this.port = port;
    }

    public SOAPMessage createSOAPMessage() throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        SOAPHeader soapHeader = soapEnvelope.getHeader();
        SOAPBody soapBody = soapEnvelope.getBody();

        SOAPHeaderElement soapHeaderElement = soapHeader.addHeaderElement(new QName("http://localhost:", "actors"));
        soapHeaderElement.setAttribute("from", Integer.toString(port));
        soapHeaderElement.setActor(Server.controller.fieldTargetServer.getText());
        SOAPElement soapBodyElement = soapBody.addChildElement("text").addTextNode(Server.controller.areaMessageToSend.getText());
        soapBodyElement.setAttribute("from", Integer.toString(port));
        soapMessage.saveChanges();
        return soapMessage;
    }
}
