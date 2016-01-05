/**
 * 
 */
package org.reacher.common.wechat;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.google.zxing.common.BitMatrix;

/**
 * @author reacher
 *
 */
public final class MatrixToImageWriterUtil {

	private static Logger logger = Logger.getLogger(MatrixToImageWriterUtil.class);

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private MatrixToImageWriterUtil() {
		
	}
	
	private static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	public static boolean writeToFile(BitMatrix matrix, String format, File file) {
		BufferedImage image = toBufferedImage(matrix);
		try {
			return ImageIO.write(image, format, file);
		} catch (IOException e) {
			logger.error("Could not write an image of format " + format + " to " + file);
			return false;
		}
	}
	
	public static boolean writeToFile(BitMatrix matrix, String format, File file, File logoPic) {
		BufferedImage image = toBufferedImage(matrix);
		Graphics2D gs = image.createGraphics();
		BufferedImage logo = null;
		try {
			logo = ImageIO.read(logoPic);
		} catch (IOException e) {
			logger.error("Could not write an image of format " + format + " to " + logoPic);
			return false;
		}
		int x = (image.getWidth() - logo.getWidth()) / 2;
		int y = (image.getHeight() - logo.getHeight()) / 2;
		gs.drawImage(logo, x, y, null);
		gs.dispose();
		logo.flush();
		try {
			return ImageIO.write(image, format, file);
		} catch (IOException e) {
			logger.error("Could not write an image of format " + format + " to " + file);
			return false;
		}
	}
}
