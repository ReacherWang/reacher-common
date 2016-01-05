/**
 * 
 */
package org.reacher.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author reacher
 * 
 */
public final class ImageUtil {

	/**
	 * Default water mark text font
	 */
	private static final Font DEFAULT_WATERMARK_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 25);

	/**
	 * Default water mark text color
	 */
	private static final Color DEFAULT_WATERMARK_COLOR = Color.WHITE;

	/**
	 * Default written image format
	 */
	private static final String DEFAULT_IMAGE_FORMAT = "png";

	/**
	 * Degrees of the circumference
	 */
	private static final int CIRCUMFERENCE_DEGREE = 360;

	private ImageUtil() {
	}

	public static BufferedImage readImage(String imageFileLocation) {
		if (StringUtil.isEmpty(imageFileLocation)) {
			return null;
		}
		return readImage(new File(imageFileLocation));
	}

	public static BufferedImage readImage(File imageFile) {
		if (imageFile == null) {
			return null;
		}
		BufferedImage image = null;
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
		}
		return image;
	}

	public static boolean writeImage(BufferedImage image, String destFileLocation) {
		if (StringUtil.isEmpty(destFileLocation)) {
			return false;
		}
		return writeImage(image, DEFAULT_IMAGE_FORMAT, new File(destFileLocation));
	}

	public static boolean writeImage(BufferedImage image, File imageFile) {
		return writeImage(image, DEFAULT_IMAGE_FORMAT, imageFile);
	}

	public static boolean writeImage(BufferedImage image, String imageFormat, String destFileLocation) {
		if (StringUtil.isEmpty(destFileLocation)) {
			return false;
		}
		return writeImage(image, imageFormat, new File(destFileLocation));
	}

	public static boolean writeImage(BufferedImage image, String imageFormat, File imageFile) {
		if (image == null) {
			return false;
		}
		if (imageFile == null) {
			return false;
		}
		if (StringUtil.isEmpty(imageFormat)) {
			return false;
		}
		try {
			return ImageIO.write(image, imageFormat, imageFile);
		} catch (IOException e) {
		}
		return false;
	}

	public static BufferedImage resizeImage(File imageFile, int width, int height) {
		return resizeImage(readImage(imageFile), width, height);
	}

	public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
		if (originalImage == null) {
			return null;
		}
		if (width <= 0 || height <= 0) {
			return null;
		}
		BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
		Graphics2D graphics = resizedImage.createGraphics();
		graphics.drawImage(originalImage, 0, 0, width, height, null);
		graphics.dispose();
		return resizedImage;
	}

	public static BufferedImage rotateImage(File originalImageFile, double degree) {
		if (originalImageFile == null) {
			return null;
		}
		return rotateImage(readImage(originalImageFile), degree);
	}

	public static BufferedImage rotateImage(BufferedImage originalImage, double degree) {
		if (originalImage == null) {
			return null;
		}
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();
		degree = degree % CIRCUMFERENCE_DEGREE;
		BufferedImage rotatedImage = null;
		AffineTransform affineTransform = new AffineTransform();
		if (degree == 90) {
			affineTransform.translate(height, 0);
			rotatedImage = new BufferedImage(height, width, originalImage.getType());
			affineTransform.rotate(Math.toRadians(degree));
		} else if (degree == 270) {
			affineTransform.translate(0, width);
			rotatedImage = new BufferedImage(height, width, originalImage.getType());
			affineTransform.rotate(Math.toRadians(degree));
		} else {
			rotatedImage = new BufferedImage(width, height, originalImage.getType());
			affineTransform.rotate(Math.toRadians(degree), width / 2, height / 2);
		}
		AffineTransformOp op = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		op.filter(originalImage, rotatedImage);
		return rotatedImage;
	}

	public static void addTextWatermark(File sourceImageFile, String text, File destImageFile) {
		addTextWatermark(sourceImageFile, text, DEFAULT_WATERMARK_FONT, DEFAULT_WATERMARK_COLOR, destImageFile);
	}

	public static void addTextWatermark(File sourceImageFile, String text, Font font, Color color, File destImageFile) {
		if (sourceImageFile == null) {
			return;
		}
		if (destImageFile == null) {
			return;
		}
		BufferedImage sourceImage = readImage(sourceImageFile);
		if (sourceImage == null) {
			return;
		}
		BufferedImage drawingboard = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = drawingboard.createGraphics();
		graphics.drawImage(sourceImage, 0, 0, null);
		graphics.setColor(color);
		graphics.setFont(font);
		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle2D rectangle = fontMetrics.getStringBounds(text, graphics);
		int x = (int) (sourceImage.getWidth() - rectangle.getWidth()) / 2;
		int y = sourceImage.getHeight() / 2;
		graphics.drawString(text, x, y);
		writeImage(drawingboard, destImageFile);
		graphics.dispose();
	}

	public static void addImageWatermark(File sourceImageFile, File watermarkImageFile, File destImageFile) {
		if (sourceImageFile == null) {
			return;
		}
		if (watermarkImageFile == null) {
			return;
		}
		if (destImageFile == null) {
			return;
		}
		BufferedImage sourceImage = readImage(sourceImageFile);
		if (sourceImage == null) {
			return;
		}
		BufferedImage watermarkImage = readImage(watermarkImageFile);
		if (watermarkImage == null) {
			return;
		}
		// New a buffered image instance to draw the final image
		BufferedImage drawingboard = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = drawingboard.createGraphics();
		graphics.drawImage(sourceImage, 0, 0, null);
		int width = sourceImage.getWidth() / 4;
		int height = sourceImage.getHeight() / 4;
		int x = (sourceImage.getWidth() - width) / 2;
		int y = (sourceImage.getHeight() - height) / 2;
		graphics.drawImage(resizeImage(watermarkImage, width, height), x, y, null);
		writeImage(drawingboard, destImageFile);
		graphics.dispose();
	}
}
