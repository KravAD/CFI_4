package Funciones;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Dibujo extends JPanel {
    private BufferedImage image;
    private Graphics2D graphics;
    public Dibujo() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                draw(e.getX(), e.getY());
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                draw(e.getX(), e.getY());
            }
        });
    }


}
