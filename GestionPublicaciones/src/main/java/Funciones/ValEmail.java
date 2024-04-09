package Funciones;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.util.regex.Pattern;

public class ValEmail implements DocumentListener {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
    );

    private JTextArea textArea;
    private JLabel validationLabel;

    public ValEmail(JTextArea textArea, JLabel validationLabel) {
        this.textArea = textArea;
        this.validationLabel = validationLabel;
        this.textArea.getDocument().addDocumentListener(this);
    }

    private void validateEmail() {
        String text = textArea.getText();
        if (EMAIL_PATTERN.matcher(text).matches()) {
            validationLabel.setText("Valid email");
            validationLabel.setForeground(Color.GREEN);
        } else {
            validationLabel.setText("Invalid email");
            validationLabel.setForeground(Color.RED);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        validateEmail();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        validateEmail();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        validateEmail();
    }
}