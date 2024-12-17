import java.awt.Color;

/**
 * Demonstrates the scaling operation featured by Runigram.java.
 * The program receives three command-line arguments: a string representing the name
 * of the PPM file of a source image, and two integers that specify the width and
 * the height of the scaled, output image. For example, to scale/resize ironman.ppm
 * to a width of 800 pixels and a height of 500 pixels, use:
 * java Editor2 ironman.ppm 800 500
 */
public class Editor2 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Editor2 <imageFile.ppm> <newWidth> <newHeight>");
            System.exit(1);
        }

        String filename = args[0];
        int newWidth = Integer.parseInt(args[1]);
        int newHeight = Integer.parseInt(args[2]);

        Color[][] originalImage = Runigram.read(filename);
        Color[][] scaledImage = Runigram.scaled(originalImage, newWidth, newHeight);

        Runigram.setCanvas(originalImage);
        Runigram.display(originalImage);
        StdDraw.pause(3000);

        Runigram.setCanvas(scaledImage);
        Runigram.display(scaledImage);
    }
}
