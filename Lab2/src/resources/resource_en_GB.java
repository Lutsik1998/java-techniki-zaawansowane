package resources;

import java.text.DateFormat;
import java.util.Date;
import java.util.ListResourceBundle;
import java.util.Locale;

public class resource_en_GB extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"Jezyk", "Language"},
                    {"Zamknij", "Exit"},
                    {"Polski", "Polish"},
                    {"Angielski GB", "English GB"},
                    {"Angielski US", "English US"},
                    {"Rosyjski", "Russian"},
                    {"Podaj nazwe liczby", "Enter the name of the number"},
                    {"Podaj wartosc liczby", "Enter the value of the number"},
                    {"Wybierz algorytm", "Choose an algorithm"},
                    {"Nazwa", "Name"},
                    {"Wartosc", "Value"},
                    {"Dodaj", "Add"},
                    {"Wariant1", "Element"},
                    {"Wariant2", "Elements"},
                    {"Wariant3", "Elements"},
                    {"Kliknij zeby odsortowac", "Click to sort"},
                    {"Kliknij zeby dodac liczbe", "Click to add"},
                    {"date", DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("en", "GB")).format(new Date())},
                    {"Sortuj", "Sort"}

            };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
