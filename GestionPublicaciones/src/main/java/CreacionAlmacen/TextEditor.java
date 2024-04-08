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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

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

    //AQUI
    private JDesktopPane desktopPane;


    public TextEditor() {
        setLayout(new BorderLayout());

        //otra pantalla para el desktop
        desktopPane = new JDesktopPane();

        JFrame desktopFrame = new JFrame();
        desktopFrame.add(desktopPane, BorderLayout.CENTER);
        desktopFrame.setSize(800, 600);
        desktopFrame.setVisible(true);
        remove(desktopPane);


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
        searchField = new JTextField(5);
        searchButton = new JButton("Search");
        JPanel searchPanel = new JPanel(new FlowLayout());

        JPanel panelWest = new JPanel();
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS)); // Organizar verticalmente
        panelWest.add(new JScrollPane(fileList1));
        panelWest.add(new JScrollPane(fileList2));
        add(panelWest, BorderLayout.WEST);


        add(new JScrollPane(textArea), BorderLayout.CENTER);
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

        JButton openContactManagerButton = new JButton("Open Contact Manager");
        openContactManagerButton.addActionListener(e -> {
            Gestor contactManager = new Gestor(this);
            contactManager.setVisible(true);
        });
        buttonPanel.add(openContactManagerButton);


        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createNewDocument() {
        JInternalFrame internalFrame = new JInternalFrame("Document", true, true, true, true);
        JTextArea textArea = new JTextArea();
        internalFrame.add(new JScrollPane(textArea));
        internalFrame.setSize(200, 200);
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }


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
                    String content1 = new String(Files.readAllBytes(Paths.get(file1)));
                    String content2 = new String(Files.readAllBytes(Paths.get(file2)));
                    if (content1.equals(content2)) {
                        JOptionPane.showMessageDialog(null, "The files are identical.");
                    } else {
                        JOptionPane.showMessageDialog(null, "The files are different.");
                    }
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "Error reading files.");
                }
            }
        }
    }
}

