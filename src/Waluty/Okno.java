package Waluty;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Okno {
    JPanel plane;
    JTextArea textArea1;
    JPanel wybor;
    JTextField datapocz;
    JTextField datakon;
    JButton infoButton;
    JButton pokażCenyButton;
    ButtonGroup button;
    JButton wyczyśćPoleButton;
    JButton zapiszButton;
    JPanel przyciski;
    JRadioButton aRadioButton;
    JRadioButton bRadioButton;
    JRadioButton cRadioButton;
    private JButton infoOProgramieButton;
    private JButton wykresButton;
    private JButton pdfButton;
    String dane = null;

    //  f
    public Okno() {

        button = new ButtonGroup();
        button.add(aRadioButton);
        button.add(bRadioButton);
        button.add(cRadioButton);
        wykresButton.setVisible(false);
        pdfButton.setVisible(false);
        infoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "a - tabela kursów średnich walut obcych \n" +
                        "b - tabela kursów średnich walut niewymienialnych\n" +
                        "c - tabela kursów kupna i sprzedaży", "Informacje", 1);
            }
        });

        pokażCenyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Wyswietl wy = new Wyswietl();
                textArea1.setEditable(false);
                String urlString = createUrlString();
                String spr = ("http://api.nbp.pl/api/exchangerates/tables/null///?format=xml");
                if (urlString.equals(spr)) {

                    JOptionPane.showMessageDialog(null, "BŁĄD! Nie podano żądnych danych do wyszukiwania\n" +
                            "Musisz podać przynajmniej rodzaj", "Błąd", 2);
                } else {
                    if (datakon.getText().isEmpty() && datapocz.getText().isEmpty()) {
                        textArea1.setText(wy.wyswietl(urlString));
                        if(aRadioButton.isSelected())
                        wykresButton.setVisible(true);
                    } else if (sprawdzanie() == true) {
                        textArea1.setText(wy.wyswietl(urlString));
                        if(aRadioButton.isSelected())
                        wykresButton.setVisible(true);
                    }
                }

            }
        });
        wyczyśćPoleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea1.setText("");
                datapocz.setText("");
                datakon.setText("");
                wykresButton.setVisible(false);
                pdfButton.setVisible(false);
            }
        });
        zapiszButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                zapisz();
            }
        });
        infoOProgramieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Program walutowy(beta)\nObowiązkowym polem jest pole rodzaju tabeli.\n" +
                                "W przypadku nie podania daty, wyświetlane są najświeższe dane.\nMaksymalny przedział to 91dni\nDane archiwalne są dostępne od 2 stycznia 2002r. (2002-01-02)",
                        "Program", 1
                );
            }
        });
        wykresButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Wykres wy=new Wykres();
                wy.wykresik(createUrlString());
                pdfButton.setVisible(true);

            }
        });
        pdfButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Wykres wy=new Wykres();
                wy.pdf(createUrlString());
            }
        });
    }
    // funkcja tworząca adres url do naczego xml

    String createUrlString() {

        String rodzaj = null;
        if (aRadioButton.isSelected())
            rodzaj = "a";
        if (bRadioButton.isSelected())
            rodzaj = "b";
        if (cRadioButton.isSelected())
            rodzaj = "c";

        StringBuilder builder = new StringBuilder();
        builder.append("http://api.nbp.pl/api/exchangerates/tables/");
        builder.append(rodzaj);
        builder.append("/");
        builder.append(datapocz.getText());
        builder.append("/");
        builder.append(datakon.getText());
        builder.append("/?format=xml");
        return builder.toString();
    }

    // funkcja sprawdzająca poprawność wpisywanej daty
    boolean sprawdzanie() {
        Pattern data = Pattern.compile("^((?:20)\\d\\d)[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$");
        int a = 0;
        int b = 0;
        String datain = datapocz.getText();
        String dataout = datakon.getText();
        Matcher spr_datapocz = data.matcher(datain);
        Matcher spr_datakon = data.matcher(dataout);
        if (datain.isEmpty()) {
        } else {
            if (spr_datapocz.matches() == false) {
                JOptionPane.showMessageDialog(null, "Podany został zły format daty początkowej.\n" +
                        "Prawidłowy format: YYYY-MM-DD", "Błąd", 2);
                a = 1;
            }
        }

        if (dataout.isEmpty()) {
        } else {
            if (spr_datakon.matches() == false) {
                JOptionPane.showMessageDialog(null, "Podany został zły format daty końcowej.\n" +
                        "Prawidłowy format: YYYY-MM-DD", "Błąd", 2);
                b = 1;
            }
        }
        if (a == 1 || b == 1)
            return false;
        else
            return true;
    }

    // funkcja zapisująca dane do pliku
    void zapisz() {
        JFileChooser fc = new JFileChooser();

        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File plik = fc.getSelectedFile();

            try {
                PrintWriter pw = new PrintWriter(plik);
                Scanner skaner = new Scanner(textArea1.getText());

                while (skaner.hasNextLine()) {
                    pw.println(skaner.nextLine());

                }
                pw.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}

