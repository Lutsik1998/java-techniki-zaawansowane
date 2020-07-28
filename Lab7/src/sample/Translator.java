package sample;



import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Iterator;

public class Translator {
    public int port;


    public Translator(int port) {
        this.port = port;
    }

    private void saveToLog(SOAPMessage soapMessage) throws IOException, SOAPException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        soapMessage.writeTo(stream);
        Server.controller.areaMessageTranslator.appendText(new String(stream.toByteArray(), "utf-8") + "\n");
    }

    public void transfer(SOAPMessage soapMessage) {
        try {
            saveToLog(soapMessage);
            SOAPConnection soapConnection = null;
            Socket socket = null;
            PrintStream out = null;
            try {
                SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
                soapConnection = soapConnectionFactory.createConnection();

                Iterator soapHeaders = soapMessage.getSOAPHeader().examineAllHeaderElements();
                while (soapHeaders.hasNext()) {
                    SOAPHeaderElement soapHeaderElement = (SOAPHeaderElement) soapHeaders.next();
                    if (soapHeaderElement.getTagName().equals("actors")) {
                        if (!soapHeaderElement.getAttribute("from").equals(Server.controller.fieldTranslatorPort.getText())) {
                            socket = new Socket("localhost", Integer.parseInt(Server.controller.fieldTranslatorPort.getText()));
                            out = new PrintStream(socket.getOutputStream(), true);
                            soapMessage.writeTo(out);
                        }
                    }
                }
            } finally {
                if (out != null) {
                    out.flush();
                    out.close();
                }
                if (socket != null) {
                    socket.close();
                }
                if (soapConnection != null) {
                    soapConnection.close();
                }
            }
        } catch (IOException | NullPointerException | SOAPException e) {
            e.printStackTrace();
        }
    }


}
