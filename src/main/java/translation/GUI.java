package translation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;


// TODO Task D: Update the GUI for the program to align with UI shown in the README example.
//            Currently, the program only uses the CanadaTranslator and the user has
//            to manually enter the language code they want to use for the translation.
//            See the examples package for some code snippets that may be useful when updating
//            the GUI.
public class GUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // dropdown
            JPanel languagePanel = new JPanel();
            Translator translator = new CanadaTranslator();
            JComboBox<String> languageComboBox = new JComboBox<>();
            for(String countryCode : translator.getLanguageCodes()) {
                languageComboBox.addItem(countryCode);
            }
            languagePanel.add(new JLabel("Language:"));
            languagePanel.add(languageComboBox);

            JPanel countryPanel = new JPanel();
            JTextField countryField = new JTextField(10);
            countryField.setText("can");
            countryField.setEditable(false); // we only support the "can" country code for now
            countryPanel.add(new JLabel("Country:"));
            countryPanel.add(countryField);

//            JPanel buttonPanel = new JPanel();
//            JButton submit = new JButton("Submit");
//            buttonPanel.add(submit);

            JPanel resultPanel = new JPanel();
            resultPanel.add(new JLabel("Translation:"));
            JLabel resultLabel = new JLabel("\t\t\t\t\t\t\t");
            resultPanel.add(resultLabel);


            // adding listener for when the user clicks the submit button
            languageComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    String country = countryField.getText();
                    Translator translator = new CanadaTranslator();

                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String language = Objects.requireNonNull(languageComboBox.getSelectedItem()).toString();
                        String result = translator.translate(country, language);
                        if (result == null) {
                            result = "no translation found!";
                        }
                        resultLabel.setText(result);
                    }

                    // for now, just using our simple translator, but
                    // we'll need to use the real JSON version later.
                }

            });

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(languagePanel);
            mainPanel.add(resultPanel);
            mainPanel.add(countryPanel);

            JFrame frame = new JFrame("Country Name Translator");
            frame.setContentPane(mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);


        });
    }
}
