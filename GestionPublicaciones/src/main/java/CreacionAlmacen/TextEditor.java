package CreacionAlmacen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class TextEditor extends JFrame {
    private JTextArea textArea;
    private JButton saveButton;
    private JList<String> fileList;
    private DefaultListModel<String> listModel;
    public TextEditor() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        saveButton = new JButton("Save");
        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
        add(new JScrollPane(fileList), BorderLayout.EAST);

        saveButton.addActionListener(new SaveButtonListener());
        fileList.addListSelectionListener(e -> loadFile(fileList.getSelectedValue()));

        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fileName = JOptionPane.showInputDialog("Enter file name");
            if (fileName != null) {
                try (FileWriter writer = new FileWriter(fileName)) {
                    writer.write(textArea.getText());
                    listModel.addElement(fileName);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }




}