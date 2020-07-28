package resources;

import java.text.DateFormat;
import java.util.Date;
import java.util.ListResourceBundle;
import java.util.Locale;

public class resource_pl extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"Jezyk", "Język"},
                    {"Zamknij", "Zamknij"},
                    {"Polski", "Polski"},
                    {"Angielski GB", "Angielski GB"},
                    {"Angielski US", "Angielski US"},
                    {"Rosyjski", "Rosyjski"},
                    {"Podaj nazwe liczby", "Podaj nazwę liczby"},
                    {"Podaj wartosc liczby", "Podaj wartość liczby"},
                    {"Wybierz algorytm", "Wybierz algorytm"},
                    {"Nazwa", "Nazwa"},
                    {"Wartosc", "Wartość"},
                    {"Dodaj", "Dodaj"},
                    {"Wariant1", "Element"},
                    {"Wariant2", "Elementy"},
                    {"Wariant3", "Elementów"},
                    {"Kliknij zeby odsortowac", "Kliknij żeby odsortować"},
                    {"Kliknij zeby dodac liczbe", "Kliknij żeby dodać liczbę"},
                    {"date", DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("pl", "PL")).format(new Date())},
                    {"Sortuj", "Sortuj"}

            };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
