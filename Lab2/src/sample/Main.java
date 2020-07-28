package sample;

import com.labyjava.lab1.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//export reports;
//opens reports to javafx.graphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application {

    ObservableList<String> listaNazw = FXCollections.observableArrayList();
    ObservableList<Float> listaLiczb = FXCollections.observableArrayList();
    List<IElement> listaElementow = new ArrayList<>();


    /**ZMIENNE/OBIEKTY**/
    ResourceBundle resourceBundle;
    Locale locale = Locale.getDefault();
    GridPane panel;

    /***MENU**/
    MenuBar menuBar;
    VBox vBox;
    Menu languageMenu;
    Menu exit;
    Menu dataMenu;
    MenuItem exitButton;
    RadioMenuItem polish;
    RadioMenuItem english;
    RadioMenuItem englishUS;

    /***Tekst***/
    Label nazwaLabel;
    Label wartoscLabel;
    Label wyborLabel;
    Label nazwaListaLabel ;
    Label wartoscListaLabel;
    Label countElements;

    /**Wprowadzienie tekstu**/
    TextField nazwaText;
    TextField wartoscText;


    /**Przyciski**/
    Button dodajLiczbe;
    Button sortuj;



    /** Listy **/
    ListView<String> nazwaList;
    ListView wartoscList;
    /**Combox box **/
    ComboBox wybierzCombo;
    /**Obrazek**/
    Image image;
    ImageView imageView;


    public void inicjalizacja(Locale locale) throws FileNotFoundException {
        locale = Locale.getDefault();

        panel = new GridPane();

        /***MENU**/
        menuBar = new MenuBar();
        vBox = new VBox(menuBar);
        languageMenu = new Menu("Języki");
        dataMenu = new Menu();
        dataMenu.setDisable(true);

        exit = new Menu("Zamknij");
        exitButton = new MenuItem("Zamknij");
        polish = new RadioMenuItem("Polski");
        english = new RadioMenuItem("Angielski GB");
        englishUS = new RadioMenuItem("Angielski US");


        /***Tekst***/
        nazwaLabel = new Label("Podaj nazwę liczby");
        wartoscLabel = new Label("Podaj wartość liczby");
        wyborLabel = new Label("Wybierz algorytm");
        nazwaListaLabel = new Label("Nazwa");
        wartoscListaLabel = new Label("Wartość");
        countElements = new Label("Elementow");

        /**Wprowadzienie tekstu**/
        nazwaText = new TextField();
        wartoscText = new TextField() {
            @Override public void replaceText(int start, int end, String text) {
                String wpisanyText = this.getText();
                if (!text.matches("[a-z]")&&!(".".equals(text) && (wpisanyText.contains(".") || "".equals(wpisanyText) ))) {
                    super.replaceText(start, end, text);
                }
            }

        };


        /**Przyciski**/
        dodajLiczbe = new Button("Dodaj");
        dodajLiczbe.setTooltip(new Tooltip(""));
        sortuj = new Button("Sortuj");
        sortuj.setTooltip(new Tooltip(""));


        /** Listy **/
        nazwaList = new ListView(listaNazw);
        wartoscList = new ListView(listaLiczb);

        /**Combox box **/
        wybierzCombo = new ComboBox();

        FileInputStream input = new FileInputStream("src/resources/images/test.png");
        image = new Image(input);
        imageView = new ImageView(image);

        initLanguage(locale);
    }

    public void initLanguage(Locale locale){
        resourceBundle = ResourceBundle.getBundle("resources.resource", locale);
        nazwaLabel.setText(resourceBundle.getString("Podaj nazwe liczby"));
        wartoscLabel.setText(resourceBundle.getString("Podaj wartosc liczby"));
        wyborLabel.setText(resourceBundle.getString("Wybierz algorytm"));
        dodajLiczbe.setText(resourceBundle.getString("Dodaj"));
        dodajLiczbe.setTooltip(new Tooltip(resourceBundle.getString("Kliknij zeby dodac liczbe")));
        nazwaListaLabel.setText(resourceBundle.getString("Nazwa"));
        wartoscListaLabel.setText(resourceBundle.getString("Wartosc"));
        dataMenu.setText(resourceBundle.getString("date"));
        sortuj.setText(resourceBundle.getString("Sortuj"));
        sortuj.setTooltip(new Tooltip(resourceBundle.getString("Kliknij zeby odsortowac")));
        languageMenu.setText(resourceBundle.getString("Jezyk"));
        exit.setText(resourceBundle.getString("Zamknij"));
        exitButton.setText(resourceBundle.getString("Zamknij"));
        wybierzCombo.setValue(resourceBundle.getString("Wybierz algorytm"));
        setCountElements(locale);


    }





    @Override
    public void start(Stage primaryStage) throws Exception{
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.resource", Locale.getDefault());
        inicjalizacja(locale);
       // english2.setSelected(true);

        primaryStage.setTitle("Cwiczenie 2");
        /**INTERNACJONALIZACJA***/

        polish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                english.setSelected(false);
                englishUS.setSelected(false);
                locale = new Locale("pl", "PL");
                initLanguage(locale);
            }
        });
        englishUS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                english.setSelected(false);
                polish.setSelected(false);
                englishUS.setSelected(true);
                locale = new Locale("en", "US");
                initLanguage(new Locale("en", "US"));
            }
        });
        english.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                polish.setSelected(false);
                englishUS.setSelected(false);
                english.setSelected(true);
                locale = new Locale("en", "GB");
                initLanguage(new Locale("en", "GB"));
            }
        });


        panel.setVgap(5);
        panel.setHgap(10);

        /*********ROZMIESZCZENIE*********/

        //KOLUMNY
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        panel.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        panel.getColumnConstraints().add(column2);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(10);
        panel.getColumnConstraints().add(column3);

        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(20);
        panel.getColumnConstraints().add(column4);

        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(20);
        panel.getColumnConstraints().add(column5);

        ColumnConstraints column6 = new ColumnConstraints();
        column6.setPercentWidth(20);
        panel.getColumnConstraints().add(column6);


        //WIERSZY
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(10);
        panel.getRowConstraints().add(row1);

        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(10);
        panel.getRowConstraints().add(row2);

        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(10);
        panel.getRowConstraints().add(row3);

        RowConstraints row4 = new RowConstraints();
        row4.setPercentHeight(10);
        panel.getRowConstraints().add(row4);

        RowConstraints row5 = new RowConstraints();
        row5.setPercentHeight(20);
        panel.getRowConstraints().add(row5);




        /** Menu **/

        vBox.setMaxWidth(Double.MAX_VALUE);


        menuBar.getMenus().add(languageMenu);
        menuBar.getMenus().add(exit);
        menuBar.getMenus().add(dataMenu);





        exit.getItems().add(exitButton);

        languageMenu.getItems().add(polish);
        languageMenu.getItems().add(english);
        languageMenu.getItems().add(englishUS);



        /** pozycje Pol wyswietlajace text **/

        GridPane.setHalignment(nazwaLabel,HPos.CENTER);
        GridPane.setHalignment(wartoscLabel,HPos.CENTER);
        GridPane.setHalignment(wyborLabel,HPos.CENTER);
        GridPane.setHalignment(nazwaListaLabel,HPos.CENTER);
        GridPane.setHalignment(wartoscListaLabel,HPos.CENTER);







        /** Przyciski rozmiar **/
        dodajLiczbe.setMaxWidth(Double.MAX_VALUE);
        dodajLiczbe.setMaxHeight(70);
        sortuj.setMaxWidth(Double.MAX_VALUE);



        /**Combox box **/
        wybierzCombo.getItems().add(0,"Quick Sort");
        wybierzCombo.getItems().add(1,"Selection Sort");
        wybierzCombo.getItems().add(2,"Counting Sort");


        /** Dodanie na panel **/
        panel.add(nazwaLabel,0,1);
        panel.add(wartoscLabel,0,2);
        panel.add(nazwaText, 1, 1);
        panel.add(wartoscText, 1, 2);
        panel.add(dodajLiczbe,2,1, 1,2);

        panel.add(wyborLabel,0,3);
        panel.add(wybierzCombo,1,3);
        panel.add(sortuj,2,3);

        panel.add(nazwaListaLabel,3,1);
        panel.add(wartoscListaLabel,4,1);
        panel.add(nazwaList,3,2,1,3);
        panel.add(wartoscList,4,2,1,3);
        panel.add(countElements,5,1);

        panel.add(vBox,0,0,6,1);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        panel.add(imageView,1,4);




        dodajLiczbe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String str = nazwaText.getText();
                if(wartoscText.getText().contains(".")){
                    if(wybierzCombo.getItems().contains("Counting Sort")){
                        wybierzCombo.getItems().remove(2);
                    }
                    IElement tmp = new FloatElement(nazwaText.getText(),Float.parseFloat(wartoscText.getText()));
                    listaElementow.add(tmp);
                    listaNazw.add(tmp.getName());
                    listaLiczb.add(tmp.getValue());
                }else {
                    IElement tmp = new IntElement(nazwaText.getText(),Integer.parseInt(wartoscText.getText()));
                    listaElementow.add(tmp);
                    listaNazw.add(tmp.getName());
                    listaLiczb.add(tmp.getValue());
                }
                setCountElements(locale);
                initLanguage(locale);
            }
        });

        sortuj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(wybierzCombo.getSelectionModel().isSelected(0)){
                    QuickSort tmp = new QuickSort();
                    listaElementow = tmp.solveFloatAndInt(listaElementow);
                }else if (wybierzCombo.getSelectionModel().isSelected(1)){
                    SelectionSort tmp = new SelectionSort();
                    listaElementow = tmp.solveFloatAndInt(listaElementow);
                }else if(wybierzCombo.getSelectionModel().isSelected(2)){
                    CountingSort tmp = new CountingSort();
                    List<IntElement> listaIntow = new ArrayList<>();
                    for(int i = 0; i < listaElementow.size(); i++){
                        listaIntow.add((IntElement)listaElementow.get(i));
                    }
                    listaIntow = tmp.solve(listaIntow);
                    listaElementow.clear();
                    for(int i = 0; i < listaIntow.size(); i++){
                        listaElementow.add((IElement)listaIntow.get(i));
                    }
                }


                listaNazw.clear();
                listaLiczb.clear();
                for(int i = 0; i < listaElementow.size(); i++){
                    listaNazw.add(listaElementow.get(i).getName());
                    listaLiczb.add(listaElementow.get(i).getValue());
                }
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //System.exit(0);
                primaryStage.close();
            }
        });

        Scene scene = new Scene(panel,800,400);
        primaryStage.setScene(scene);


        primaryStage.show();

    }
    //"Wariant1", "Element"
    //"Wariant2", "Elementy
    //"Wariant3", "Elementów
    public void setCountElements(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("resources.resource", locale);
        int tmp = listaElementow.size() % 10;
        switch (tmp) {
            case 1: {
                if(listaElementow.size()>10){
                    countElements.setText(Integer.toString(listaElementow.size())+ " " + resourceBundle.getString("Wariant3"));
                }else {
                    countElements.setText(Integer.toString(listaElementow.size())+ " " + resourceBundle.getString("Wariant1"));
                }
                break;
            }
            case 2:
            case 3:
            case 4: {
                if(listaElementow.size()>10 && listaElementow.size()<20){
                    countElements.setText(Integer.toString(listaElementow.size())+ " " + resourceBundle.getString("Wariant3"));
                }else {
                    countElements.setText(Integer.toString(listaElementow.size())+ " " + resourceBundle.getString("Wariant2"));
                }
                break;
            }
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 0: {
                countElements.setText(Integer.toString(listaElementow.size())+ " " + resourceBundle.getString("Wariant3"));
                break;
            }
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
