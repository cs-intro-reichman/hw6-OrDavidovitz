import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);
		
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for (int i = 0; i < numRows; i++) 
        for (int j = 0; j < numCols; j++) {
            int red = in.readInt();
            int green = in.readInt();
            int blue = in.readInt();
            image[i][j] = new Color(red, green, blue);
        }
    
    return image;
}
		
	

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
		private static void print(Color[][] image) {
			for (int i = 0; i < image.length; i++) {
				for (int j = 0; j < image[i].length; j++) {
					print(image[i][j]);
				}
				System.out.println();
			}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		int numRows = image.length;
		int numCols = image[0].length;
		Color[][] flippedImage = new Color[numRows][numCols];
	
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				flippedImage[i][j] = image[i][numCols - 1 - j];
			}
		}
		return flippedImage;
	}
	
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image) {
		int numRows = image.length;
		int numCols = image[0].length;
		Color[][] flippedImage = new Color[numRows][numCols];
	
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				flippedImage[i][j] = image[numRows - 1 - i][j];
			}
		}
		return flippedImage;
	}
	
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		int red = pixel.getRed();
		int green = pixel.getGreen();
		int blue = pixel.getBlue();
		int lum = (int) Math.round(0.299 * red + 0.587 * green + 0.114 * blue);
		return new Color(lum, lum, lum);
	}
	
	
	
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	/**
 * Step 2: Convert an entire image to greyscale.
 * @param image 2D array of Color objects representing the image.
 * @return Greyscaled 2D array of Color objects.
 */
public static Color[][] grayScaled(Color[][] image) {
    int rows = image.length;
    int cols = image[0].length;
    Color[][] greyscaledImage = new Color[rows][cols];

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            greyscaledImage[i][j] = luminance(image[i][j]); // Use the luminance method
        }
    }

    return greyscaledImage;
}
	
	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		int originalHeight = image.length;
		int originalWidth = image[0].length;
		Color[][] scaledImage = new Color[height][width];
	
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int y = (int) Math.min(originalHeight - 1, Math.floor((double) i * originalHeight / height));
				int x = (int) Math.min(originalWidth - 1, Math.floor((double) j * originalWidth / width));
				scaledImage[i][j] = image[y][x];
			}
		}
		return scaledImage;
	}
	
	
	
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int red = (int) Math.floor(alpha * c1.getRed() + (1.0 - alpha) * c2.getRed() + 0.5);
		int green = (int) Math.floor(alpha * c1.getGreen() + (1.0 - alpha) * c2.getGreen() + 0.5);
		int blue = (int) Math.floor(alpha * c1.getBlue() + (1.0 - alpha) * c2.getBlue() + 0.5);
		return new Color(clamp(red), clamp(green), clamp(blue));
	}
	
    /**
     * Blends two images (2D Color arrays) using a specified alpha.
     * Validates that both images have the same dimensions before blending.
     */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		int rows = image1.length;
		int cols = image1[0].length;
		Color[][] blendedImage = new Color[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				blendedImage[i][j] = blend(image1[i][j], image2[i][j], alpha);
			}
		}
	
		return blendedImage;
	}
	
	

    /**
     * Ensures RGB values remain in the range [0, 255].
     */
	private static int clamp(int value) {
		int clamped = Math.max(0, Math.min(255, value));
		if (value != clamped) {
			System.out.println("Clamping value: " + value + " to " + clamped);
		}
		return clamped;
	}
	





	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		int sourceHeight = source.length;
		int sourceWidth = source[0].length;
		Color[][] scaledTarget = scaled(target, sourceWidth, sourceHeight);
	
		for (int step = 1; step <= n; step++) {
			double alpha = (double) step / n;
			Color[][] morphedImage = blend(source, scaledTarget, alpha);
			display(morphedImage);
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

