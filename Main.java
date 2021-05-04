import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

class Main {

    String sc = "widok.jpg";
    private JPanel

            panel1 = new JPanel(new GridLayout(1,1)),
            panel2 = new JPanel(new GridLayout(1,2));

    private Button b1 = new Button("Filtr Gaussa");
    private Button b2 = new Button("Rozmycie");


    class Picture extends Canvas {
        public BufferedImage loadImage(String sciezka) throws IOException {

            try
            {
                return ImageIO.read(new File(sciezka));
            }
            catch (Exception e)
            {
                System.out.println(e);
                System.exit(0);
                return null;
            }
        }
        public void paint(Graphics g) {

            BufferedImage obrazek = null;

            try {
                obrazek = loadImage(sc);
            }
            catch (IOException e) {

                e.printStackTrace();
            }

            g.drawImage(obrazek, obrazek.getMinX(), obrazek.getMinY(), this);
        }
    }

    private Canvas canvas = new Picture();

    public Main() {

        JFrame frame = new JFrame("Rysunek");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        frame.setResizable(false);
        frame.getContentPane().setLayout(new FlowLayout());


        panel1.add(canvas);

        panel2.add(b1);
        panel2.add(b2);

        panel1.setPreferredSize(new Dimension(600, 400));
        panel2.setPreferredSize(new Dimension(600,50));


        frame.getContentPane().add(panel1);
        frame.getContentPane().add(panel2);

        ;

        b1.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {

                                     new Gauss();
                                     sc = "widok.jpg";
                                     canvas.repaint();
                                 }
                             }
        );

        b2.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {

                                     new Rozmycie();
                                     sc = "rozmycie.jpg";
                                     canvas.repaint();
                                 }
                             }
        );

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
