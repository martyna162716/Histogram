       import java.awt.Color;
       import java.awt.Graphics;
       import javax.media.jai.JAI;
       import javax.media.jai.PlanarImage;
       import java.awt.image.BufferedImage;
       import java.awt.image.RescaleOp;
       import java.io.IOException;
       import javax.swing.*;

    public class histogram extends JPanel {

        int[] bins = new int[256];

  histogram(int[] pbins) {
    bins = pbins;
    repaint();
   }

 @Override
 protected void paintComponent(Graphics g) {


    for (int i = 0; i < 256; i++) {

        System.out.println("bin[" + i + "]===" + bins[i]);
        g.drawLine(200 + i, 300, 200 + i, 300 - (bins[i])/1000);
   
    }
   }

  public static void main(String[] args) throws IOException {
    JFrame frame = new JFrame();
    frame.setSize(299, 168);
    int[] pbins = new int[256];
    int[] sbins = new int[256];
    PlanarImage image = JAI.create("zaladuj", "widok.jpg");
    BufferedImage bi = image.getAsBufferedImage();    
    System.out.println("typ" + bi.getType());
    int[] pixel = new int[3];

    int p = 0;
    Color c = new Color(k);
    Double d = 0.0;
    Double d1;
    
    for (int x = 0; x < bi.getWidth(); x++) {
        for (int y = 0; y < bi.getHeight(); y++) {
            pixel = bi.getRaster().getPixel(x, y, new int[3]);
          d=(0.212*pixel[0])+(0.715*pixel[1])+(0.072*pixel[2]);
              p=(int) (d/256);

            sbins[k]++;
        }
     }
     System.out.println("zakoncz" + d + "--" + p);
     JTabbedPane jtp=new JTabbedPane();
     ImageIcon im= new ImageIcon(bi);

     jtp.addTab("Histogram",new histogram(sbins));
     frame.add(jtp);
     frame.setVisible(true);
     frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }
 }
