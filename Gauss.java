import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


public class Gauss {

    BufferedImage image;
    int width;
    int height;

    public Gauss() {

        try {

            //odczyt obrazu z pliku
            File input = new File("widok.jpg");

            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            int[][] M = new int[][] { {1,2,1}, {2,2*2,2}, {1,2,1}};

            double red_pnym = 0, green_pnym = 0, blue_pnym = 0;

            //odczyt pixeli obrazu w dwóch pętlach po kolumnach i wierszach
            for(int i=1; i<height-1; i++){

                for(int j=1; j<width-1; j++) {

                    int red = 0, green = 0, blue = 0;

                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {

                            //odczyt składowych koloru RGB
                            Color c = new Color(image.getRGB(j + k, i + l));

                            red = (c.getRed());
                            green = (c.getGreen());
                            blue = (c.getBlue());

                            red_pnym += red * M[k + 1][l + 1];
                            green_pnym += green * M[k + 1][l + 1];
                            blue_pnym += blue * M[k + 1][l + 1];

                        }
                    }

                    red_pnym = 100 * Math.exp(-(i * i + j * j) / (2 * red_pnym * red_pnym));
                    green_pnym = 100 * Math.exp(-(i * i + j * j) / (2 * green_pnym * green_pnym));
                    blue_pnym = 100 * Math.exp(-(i * i + j * j) / (2 * blue_pnym * blue_pnym));


                    if (red >= 0 && red <= 255)
                        red = (int) red_pnym;

                    if (green >= 0 && green <= 255)
                        green = (int) green_pnym;

                    if (blue >= 0 && blue <= 255)
                        blue = (int) blue_pnym;


                    Color newColor = new Color(red, green, blue);
                    image.setRGB(j, i, newColor.getRGB());


                } 
            }

            //zapis do pliku zmodyfikowanego obrazu
            File ouptut = new File("widok1.jpg");
            ImageIO.write(image, "jpg", ouptut);

        } catch (Exception e) {}
    }

    static public void main(String args[]) throws Exception
    {
        Gauss obj = new Gauss();
    }
}
