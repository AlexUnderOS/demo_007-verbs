package com.alex.demo_007;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class HelloController {
    @FXML
    private TextField textField;

    @FXML
    private Label mainWord;

    public void onMouseEnteredMainWord(){

    }

    public void onMouseExitedMainWord(){

    }

    String currentTranslation = "";
    Color defaultMainWordColor = Color.BLACK;
    public void onKeyPressedTextField(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            List<String> lines = new ArrayList<>();

            if (textField.getText().equals(currentTranslation)){
                mainWord.setTextFill(defaultMainWordColor);
            }else {
                mainWord.setTextFill(Color.RED);
            }
            loadRandomTranslation(lines);

            textField.clear();
        }
    }

    private void loadRandomTranslation(List<String> lines){
        try (BufferedReader br = new BufferedReader(new FileReader( Objects.requireNonNull(
                getClass().getResource("bank.csv")).getFile() ))) {

            String line;
            while ((line = br.readLine()) != null) {
                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty()) {
                    lines.add(trimmedLine);
                }
            }

            Random random = new Random();
            String randomLine = lines.get(random.nextInt(lines.size()));


            displayTranslation(randomLine);

        } catch (IOException e) {
            System.out.println("IO: " + e);
        }
    }

    private void displayTranslation(String randomLine){
        String[] parts = randomLine.split(",");
        if (parts.length >= 2) {
            String firstPart = parts[0];
            currentTranslation = parts[1];

            mainWord.setText("<" + firstPart.toUpperCase() + ">");
            System.out.println(firstPart+" - "+currentTranslation);
        }
    }
}