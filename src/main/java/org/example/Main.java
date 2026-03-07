package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final CalculatorService calculatorService = new CalculatorService();


    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    }

    @Override
    public void start(Stage stage) {

        Label num1Label = new Label("Enter first number:");
        TextField num1Field = new TextField();

        Label num2Label = new Label("Enter second number:");
        TextField num2Field = new TextField();

        Button addButton      = new Button("Add");
        Button subtractButton = new Button("Subtract");
        Button divideButton   = new Button("Divide");

        Label resultLabel = new Label();

        //  --Ad--
        addButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(num1Field.getText());
                double b = Double.parseDouble(num2Field.getText());
                double result = add(a, b);
                resultLabel.setText("Result (Add): " + result);
                calculatorService.saveResult(a, b, "add", result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input!");
            }
        });

        // --Sub---
        subtractButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(num1Field.getText());
                double b = Double.parseDouble(num2Field.getText());
                double result = subtract(a, b);
                resultLabel.setText("Result (Subtract): " + result);
                calculatorService.saveResult(a, b, "subtract", result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input!");
            }
        });

        // ---Div--
        divideButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(num1Field.getText());
                double b = Double.parseDouble(num2Field.getText());
                if (b == 0) {
                    resultLabel.setText("Cannot divide by zero!");
                    return;
                }
                double result = divide(a, b);
                resultLabel.setText("Result (Divide): " + result);
                calculatorService.saveResult(a, b, "divide", result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input!");
            }
        });

        VBox root = new VBox(10,
                num1Label, num1Field,
                num2Label, num2Field,
                addButton, subtractButton, divideButton,
                resultLabel
        );

        Scene scene = new Scene(root, 350, 320);
        stage.setTitle("Calculator with DB");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}