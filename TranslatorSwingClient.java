package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslatorSwingClient {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Translator Client");
        frame.setSize(500, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField wordField = new JTextField(20);
        JComboBox<String> srcLang = new JComboBox<>(new String[]{"english", "italian", "romanian", "spanish", "german", "french"});
        JComboBox<String> tgtLang = new JComboBox<>(new String[]{"english", "italian", "romanian", "spanish", "german", "french"});
        JButton translateButton = new JButton("Translate");
        JTextArea resultArea = new JTextArea(5, 40);
        resultArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Word:"));
        panel.add(wordField);
        panel.add(new JLabel("From:"));
        panel.add(srcLang);
        panel.add(new JLabel("To:"));
        panel.add(tgtLang);
        panel.add(translateButton);
        panel.add(new JScrollPane(resultArea));

        frame.add(panel);
        frame.setVisible(true);

        translateButton.addActionListener((ActionEvent e) -> {
            String word = wordField.getText().trim();
            String src = srcLang.getSelectedItem().toString();
            String tgt = tgtLang.getSelectedItem().toString();

            if (word.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a word!");
                return;
            }

            try {
                String urlString = "http://localhost:8080/CiornaAppTranslator-1.0-SNAPSHOT/api/translate?word="
                        + URLEncoder.encode(word, "UTF-8")
                        + "&src=" + src + "&tgt=" + tgt;

                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                conn.disconnect();

                String response = content.toString();
                if (response.contains("translation")) {
                    String translated = response.replaceAll(".*\"translation\"\\s*:\\s*\"(.*?)\".*", "$1");
                    resultArea.setText(translated + " (" + src + " → " + tgt + ")");
                } else if (response.contains("error")) {
                    String error = response.replaceAll(".*\"error\"\\s*:\\s*\"(.*?)\".*", "$1");
                    resultArea.setText("Eroare: " + error);
                } else {
                    resultArea.setText("Răspuns necunoscut: " + response);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                resultArea.setText("Eroare la conectare cu serverul.");
            }
        });
    }
}
