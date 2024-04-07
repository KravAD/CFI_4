package CreacionAlmacen;
import Analizar.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TextEditor extends JFrame {
    private JTextArea textArea;
    private JButton saveButton;
    private JList<String> fileList1;
    private JList<String> fileList2;
    private DefaultListModel<String> listModel1;
    private DefaultListModel<String> listModel2;
    private JButton compareButton;
    private JButton analyzeButton;
    private FileAnalyzer FileAnalyzer;

    private JTextField searchField;
    private JButton searchButton;

    public TextEditor() {
        setLayout(new BorderLayout());


        textArea = new JTextArea();
        saveButton = new JButton("Save");
        compareButton = new JButton("Compare");
        listModel1 = new DefaultListModel<>();
        listModel2 = new DefaultListModel<>();
        fileList1 = new JList<>(listModel1);
        fileList2 = new JList<>(listModel2);
        FileAnalyzer = new FileAnalyzer();
        analyzeButton = new JButton("Analyze");
        JPanel buttonPanel = new JPanel(new FlowLayout());

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        JPanel searchPanel = new JPanel(new FlowLayout());





        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(new JScrollPane(fileList1), BorderLayout.WEST);
        add(new JScrollPane(fileList2), BorderLayout.EAST);
        buttonPanel.add(compareButton);
        buttonPanel.add(analyzeButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.NORTH);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);




        saveButton.addActionListener(new SaveButtonListener());
        compareButton.addActionListener(new CompareButtonListener());
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFile = fileList1.getSelectedValue();
                FileAnalyzer.analyze(selectedFile);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFile = fileList1.getSelectedValue();
                String word = searchField.getText();
                FileAnalyzer.searchWord(selectedFile, word);
            }
        });

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
                    listModel1.addElement(fileName);
                    listModel2.addElement(fileName);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    private void loadFile(String fileName) {
        if (fileName != null) {
            try {
                String content = Files.lines(Paths.get(fileName)).collect(Collectors.joining("\n"));
                textArea.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private class CompareButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String file1 = fileList1.getSelectedValue();
            String file2 = fileList2.getSelectedValue();
            if (file1 != null && file2 != null) {
                try {
                    java.util.List<String> lines1 = Files.readAllLines(Paths.get(file1));
                    List<String> lines2 = Files.readAllLines(Paths.get(file2));
                    if (lines1.equals(lines2)) {
                        JOptionPane.showMessageDialog(null, "The files are identical.");
                    } else {
                        JOptionPane.showMessageDialog(null, "The files are different.");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

}

