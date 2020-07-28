package sample;

import com.sun.istack.internal.localization.NullLocalizable;
import com.sun.org.apache.bcel.internal.generic.TargetLostException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.xml.bind.*;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Random;

public class Controller {

    @FXML
    private TextField fieldFirstName;

    @FXML
    private TextField fieldLastName;
    @FXML
    private TextField fieldDepartment;
    @FXML
    private TextField fieldNameXMLDepartment;

    @FXML
    private ListView<Empl> listView;

    @FXML
    private ListView<Departments> listViewXML;


    /**CONNECTION**/
    private static String connectionURL = "jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC";
    private static String userName = "root";
    private static String password = "admin";
    Connection connection;



    @FXML
    void initialize() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);
            System.out.println("Connected to db");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onSelectClick(ActionEvent event) throws ClassNotFoundException {
        if(connection == null) {
            initialize();
        }

        try {
            System.out.println("click Select");
            PreparedStatement preparedStatement = connection.prepareStatement( "SELECT first_name, last_name, d.name from mydb.employees join mydb.departments d on employees.department_id = d.id;");
            ResultSet rs = preparedStatement.executeQuery();
            listView.getItems().clear();
            while(rs.next()) {
                listView.getItems().add(new Empl(rs.getString(1), rs.getString(2),rs.getString(3)));
            }
            connection.commit();

        } catch (SQLException e) {
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException e1) { }
        }
    }

    @FXML
    void onSelectXML(ActionEvent event) throws ClassNotFoundException {
        if(connection == null) {
            initialize();
        }

        try {
            long idDepartment = Long.parseLong(fieldDepartment.getText());
                System.out.println("click SelectXML");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name from mydb.departments where  id >= ? ");
                preparedStatement.setLong(1, idDepartment);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                Departments department = new Departments((Long) rs.getLong(1), rs.getString(2));

                JAXBContext jaxbContext = JAXBContext.newInstance(Departments.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                StringWriter stringWriter = new StringWriter();
                marshaller.marshal(department, stringWriter);
                listViewXML.getItems().clear();
                while (rs.next()) {
                    listViewXML.getItems().add(new Departments(Long.parseLong(rs.getString(1)), rs.getString(2)));
                }
                connection.commit();

        } catch (NumberFormatException | SQLException | PropertyException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) { }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onInsertClick(ActionEvent event) throws ClassNotFoundException {
        if(connection == null) {
            initialize();
        }
        String firstName = fieldFirstName.getText();
        String lastName = fieldLastName.getText();
        long departmentId = Long.parseLong(fieldDepartment.getText());

        try {
            if(departmentId > 0  && departmentId < 5 && firstName != null && !firstName.equals("") && lastName != null && !lastName.equals("") ){

                System.out.println("click Insert");
                PreparedStatement preparedStatement = connection.prepareStatement( "INSERT into mydb.employees (first_name, last_name, department_id) VALUES (?,?,?);");
                preparedStatement.setString(1,firstName);
                preparedStatement.setString(2,lastName);
                preparedStatement.setLong(3,departmentId);
                System.out.println("dodano do bd");
                preparedStatement.executeUpdate();
            }else
            {
                Alert alert = new Alert((Alert.AlertType.INFORMATION));
                alert.setTitle("Błąd");
                alert.setHeaderText("Wypelnij wszystkie pola!\nDepartment ID musi być z zakresu [1-4] ");
                alert.showAndWait();
            }
            connection.commit();
            onSelectClick(null);

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }

        }
    }

    @FXML
    void onInsertXML(ActionEvent event) throws ClassNotFoundException {
        if(connection == null) {
            initialize();
        }

        String name = fieldNameXMLDepartment.getText();
        Long depId = Long.parseLong(fieldDepartment.getText());
      //  long id = Long.parseLong(fieldDepartment.getText());
        try {
            if(name != null && !name.equals("")){
                System.out.println("click InsertXML");
                Departments department = new Departments(depId,name);
                JAXBContext jaxbContext = JAXBContext.newInstance(Departments.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                StringWriter stringWriter = new StringWriter();
                marshaller.marshal(department,stringWriter);
                StringReader stringReader = new StringReader(stringWriter.toString());
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                department = (Departments) unmarshaller.unmarshal(stringReader);
                PreparedStatement preparedStatement = connection.prepareStatement( "INSERT into mydb.departments (id,name) VALUES (?,?);");
                preparedStatement.setLong(1,department.getId());
                preparedStatement.setString(2,department.getName());
                System.out.println("dodano do bd");
                preparedStatement.executeUpdate();

            }else
            {
                Alert alert = new Alert((Alert.AlertType.INFORMATION));
                alert.setTitle("Błąd");
                alert.setHeaderText("Wypelnij ostatnie pole!");
                alert.showAndWait();
            }
            connection.commit();


        } catch (SQLException | JAXBException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {}
            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Błąd");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            e.printStackTrace();

        }
    }

    @FXML
    void onDeleteClick(ActionEvent event) throws ClassNotFoundException {
        if(connection == null) {
            initialize();
        }
        //String firstName = fieldFirstName.getText();
        Empl selected = listView.getSelectionModel().getSelectedItem();
        try {
            /*if(firstName != null && !firstName.equals("")){
                System.out.println("click delete");
                PreparedStatement preparedStatement = connection.prepareStatement( "DELETE from employees where first_name = '"+firstName+"';");
                System.out.println("usunieto z bd");
                preparedStatement.executeUpdate();
            }else {
                Alert alert = new Alert((Alert.AlertType.INFORMATION));
                alert.setTitle("Błąd");
                alert.setHeaderText("Pole 'first name' puste! ");
                alert.showAndWait();
            }*/
            try {
                if(selected==null){
                    Alert alert = new Alert((Alert.AlertType.INFORMATION));
                    alert.setTitle("Błąd");
                    alert.setHeaderText("Nie wybrano z listy!\nKlilnij Select, żeby zobaczyć listę");
                    alert.showAndWait();
                }
            }catch (Exception e){
            }
            System.out.println("click delete");
            PreparedStatement preparedStatement = connection.prepareStatement( "DELETE from mydb.employees where first_name = '"+selected.getFirstName()+"';");
            System.out.println("usunieto z bd");
            preparedStatement.executeUpdate();



            connection.commit();
            onSelectClick(null);
        } catch (NullPointerException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                Alert alert = new Alert((Alert.AlertType.INFORMATION));
                alert.setTitle("Błąd");
                alert.setHeaderText("Nie udało się usunąć");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void onUpdateClick(ActionEvent event) throws ClassNotFoundException {
        if(connection == null) {
            initialize();
        }
        Empl selected = listView.getSelectionModel().getSelectedItem();
        try {
            try {
                if(selected==null){
                    Alert alert = new Alert((Alert.AlertType.INFORMATION));
                    alert.setTitle("Błąd");
                    alert.setHeaderText("Nie wybrano z listy!");
                    alert.showAndWait();
                }
                }catch (Exception e){
                }
            int departmentId = Integer.parseInt(fieldDepartment.getText());
            if(departmentId > 0  && departmentId < 5){
                PreparedStatement preparedStatement = connection.prepareStatement( "UPDATE mydb.employees  SET department_id = '"+departmentId+"' where first_name = '"+selected.getFirstName()+"'");
                System.out.println("updated");
                preparedStatement.executeUpdate();

            }else {
                Alert alert = new Alert((Alert.AlertType.INFORMATION));
                alert.setTitle("Błąd");
                alert.setHeaderText("Department ID musi być z zakresu [1-4]");
                alert.showAndWait();
            }
            connection.commit();
            onSelectClick(null);
        } catch ( NullPointerException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) { }
            Alert alert2 = new Alert((Alert.AlertType.INFORMATION));
            alert2.setTitle("Błąd");
            alert2.setHeaderText("Nie udało się zmodyfikować dane");
            alert2.showAndWait();
        }
    }

}
