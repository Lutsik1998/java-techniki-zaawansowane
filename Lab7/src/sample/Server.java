package sample;


import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Iterator;

public class Server  extends Thread{
    public static Controller controller;
    public int port;
    boolean running = true;

    public Server(int port) {
        this.port = port;
    }

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;


    public void serverStart() {
        try {
            serverSocket = new ServerSocket(this.port);
            controller.fieldServerPort.setText(Integer.toString(this.port));
            System.out.println("server started on port:" + this.port);
            while (running) {
                try {
                    clientSocket = serverSocket.accept();
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String inputLine = in.readLine();
                    InputStream inputStream = new ByteArrayInputStream(inputLine.getBytes());
                    SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(null, inputStream);
                    saveToLog(soapMessage);
                    Iterator soapHeaders = soapMessage.getSOAPHeader().examineAllHeaderElements();
                    while (soapHeaders.hasNext()) {
                        SOAPHeaderElement soapHeaderElement = (SOAPHeaderElement) soapHeaders.next();
                        if (soapHeaderElement.getTagName().equals("actors")) {
                            if (soapHeaderElement.getActor().equals(Integer.toString(this.port))) {
                                controller.inputMessage(soapMessage);
                            } else {
                                if (soapHeaderElement.getActor().equals("BROADCAST")) {
                                    controller.inputMessage(soapMessage);
                                }
                                controller.translator.transfer(soapMessage);
                            }
                        }

                    }
                } catch (SOAPException e) {
                    e.printStackTrace();
                } catch (SocketTimeoutException ignored) {

                } finally {
                    try {
                        if (clientSocket != null) {
                            clientSocket.close();
                        }
                        if (in != null) {
                            in.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void saveToLog(SOAPMessage soapMessage) throws IOException, SOAPException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        soapMessage.writeTo(stream);
        controller.areaMessageServer.appendText(new String(stream.toByteArray(), "utf-8") + "\n");
    }

    @Override
    public void run() {
        this.serverStart();
    }
}
