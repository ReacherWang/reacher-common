/**
 * 
 */
package org.reacher.common.file;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author reacher
 *
 */
public final class RImageCompressed {
	
	private RImageCompressed() {}
	
	private static class ImageCompressedHolder {
		private static RImageCompressed imageCompressed = new RImageCompressed();
	}
	
	public static RImageCompressed getInstance() {
		return ImageCompressedHolder.imageCompressed;
	}
	
	public boolean isImage(File file) {
		if(null == file) {
			return false;
		}
		try {
			Image image = ImageIO.read(file);
			return image == null ? false : true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public File compressed(File file, RCompress compress, String name) throws IOException {
		if(null == file || null == compress) {
			return null;
		}
		Image image = ImageIO.read(file);
		if(null == image) {
			return null;
		}
		if(-1 == image.getWidth(null)) {
			return null;
		}
		if(compress.isProportion()) {
			double rate1 = ((double)image.getWidth(null)) / (double)compress.getWidth() + 0.1;
			double rate2 = ((double)image.getHeight(null)) / (double)compress.getHeight() + 0.1;
			double rate = rate1 > rate2 ? rate1 : rate2;
			compress.setWidth((int) (((double)image.getWidth(null)) / rate));
			compress.setHeight((int) (((double)image.getHeight(null)) / rate));
		}
		return this.compressed(image, compress, name);
	}
	
	private File compressed(Image image, RCompress compress, String name) throws IOException {
		if(null == image || null == compress) {
			return null;
		}
		BufferedImage buffer = new BufferedImage(compress.getWidth(), compress.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		//Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		if(buffer.getGraphics().drawImage(image.getScaledInstance(compress.getWidth(), compress.getHeight(), Image.SCALE_SMOOTH), 0, 0, null)) {
			return null;
		}
		File file = new File(name);
		return ImageIO.write(buffer, compress.getFormat(), file) ? file : null;
	}
	
}
