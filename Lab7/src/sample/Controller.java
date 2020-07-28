package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public int port;
    public Server server;
    public Client client;
    public Translator translator;



    String log = "";
    //client
    @FXML
    public TextField fieldClientPort;
    @FXML
    public TextField fieldTargetServer;
    @FXML
    public TextArea areaMessageClient;
    @FXML
    public TextArea areaMessageToSend;
    @FXML
    public TextArea areaClientLog;


    //server
    @FXML
    public TextField fieldServerPort;
    @FXML
    public TextArea areaMessageServer;

    //translator
    @FXML
    public TextField fieldTranslatorPort;
    @FXML
    public TextArea areaMessageTranslator;



    public void send(){
        SOAPConnection soapConnection = null;
        Socket socket = null;
        PrintStream out = null;
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapMessage = client.createSOAPMessage();

            areaMessageClient.appendText("Wyslano: " + areaMessageToSend.getText() + "\n");
            areaMessageToSend.setText("");


            socket = new Socket("localhost", Integer.parseInt(fieldClientPort.getText()));
            out = new PrintStream(socket.getOutputStream(), true);
            soapMessage.writeTo(out);

            saveToLog(soapMessage);

            soapMessage.writeTo(System.out);
            areaClientLog.setText(log);
        } catch (SOAPException | IOException e) {
            log = log + e.getMessage() + "\n";
        } finally {
            try {
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
            } catch (SOAPException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        server = new Server(port);
        client = new Client(port);
        translator = new Translator(port);
        //server.start();
        Server.controller = this;
        areaMessageClient.setEditable(false);
        areaMessageServer.setEditable(false);
        areaMessageTranslator.setEditable(false);
    }


    private void saveToLog(SOAPMessage soapMessage) throws IOException, SOAPException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        soapMessage.writeTo(stream);
        log += new String(stream.toByteArray(), "utf-8") + "\n";
    }

    public void inputMessage(SOAPMessage soapMessage) throws SOAPException, IOException {
        saveToLog(soapMessage);

        Iterator bodyAttributes = soapMessage.getSOAPBody().getChildElements();
        while (bodyAttributes.hasNext()) {
            SOAPBodyElement soapBody = (SOAPBodyElement) bodyAttributes.next();
            if (soapBody.getLocalName().equals("text")) {
                areaMessageClient.appendText("Otzymano od " + soapBody.getAttribute("from") + ": " + soapBody.getValue() + "\n");
            }
        }
    }


}
