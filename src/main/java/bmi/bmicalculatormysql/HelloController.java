package bmi.bmicalculatormysql;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

public class HelloController {
    @FXML private Label lblWeight;
    @FXML private TextField tfWeight;
    @FXML private Label lblHeight;
    @FXML private TextField tfHeight;
    @FXML private Button btnCalculate;
    @FXML private Label lblResult;
    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    @FXML private Button button4;

    private Locale locale; //

    private Map<String, String> localizedStrings;

    @FXML
    private void initialize() {
        setLanguage(Locale.getDefault());
    }

    private void setLanguage(Locale locale) {
        localizedStrings = LocalizationService.getLocalizedStrings(locale);
        lblWeight.setText(localizedStrings.getOrDefault("weight", "Weight"));
        lblHeight.setText(localizedStrings.getOrDefault("height", "Height"));
        btnCalculate.setText(localizedStrings.getOrDefault("calculate", "Calculate"));
        button1.setText(localizedStrings.getOrDefault("button1", "EN!"));
        button2.setText(localizedStrings.getOrDefault("button2", "FR!"));
        button3.setText(localizedStrings.getOrDefault("button3", "UR!"));
        button4.setText(localizedStrings.getOrDefault("button4", "VI!"));
    }

    @FXML
    public void onENClick() {
        locale = new Locale("en", "US"); // English
        setLanguage(locale);
    }

    @FXML
    public void onFRClick() {
        locale = new Locale("fr", "FR"); // French
        setLanguage(locale);
    }

    @FXML
    public void onURClick() {
        locale = new Locale("ur", "PA"); // Urdu
        setLanguage(locale);
    }

    @FXML
    public void onVIClick() {
        locale = new Locale("vi", "VI"); // Vietnamese
        setLanguage(locale);
    }

    @FXML
    public void onCalculateClick() {
        try {
            localizedStrings = LocalizationService.getLocalizedStrings(locale);
            // Get numbers from textfield
            double weight = Double.parseDouble(tfWeight.getText());
            double height = Double.parseDouble(tfHeight.getText());

            double bmi = calculateBMI(weight, height);

            DecimalFormat df = new DecimalFormat("#0.00");

            String msg = localizedStrings.getOrDefault("result", "Your BMI is");
            lblResult.setText(msg + " " + df.format(bmi));

            // Save to database
            String language = locale.getLanguage();
            BMIResultService.saveResult(weight, height, bmi, language);

        } catch (NumberFormatException e) {
            lblResult.setText(localizedStrings.getOrDefault("invalid", "Invalid input!"));
        }
    }

    public double calculateBMI(double w, double h) {
        double height = h / 100.0;
        double result = w / (height * height);
        return Math.round(result * 100.0) / 100.0;
    }
}
