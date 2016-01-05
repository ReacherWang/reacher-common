/**
 * 
 */
package org.reacher.common.wechat;

import java.io.File;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * @author reacher
 *
 */
public final class ZXingUtil {
	
	private static Logger logger = Logger.getLogger(ZXingUtil.class);
	
	private ZXingUtil() {
		
	}
	
	public static File createTwoDimensionalDode(String data) {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>(); 
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = null;
		try {
			bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 300, 300, hints);
		} catch (WriterException e) {
			logger.error(e);
			return null;
		}
        File file = new File("zxing-temp.png");
        return MatrixToImageWriterUtil.writeToFile(bitMatrix, "png", file) ? file : null;
	}
	
	public static File createTwoDimensionalDode(String data, String logoPath) {
		File file = new File(logoPath);
		return ZXingUtil.createTwoDimensionalDode(data, file);
	}
	
	public static File createTwoDimensionalDode(String data, File logo) {
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>(); 
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = null;
		try {
			bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 300, 300, hints);
		} catch (WriterException e) {
			logger.error(e);
			return null;
		}
        File file = new File("zxing-temp.png");
        return MatrixToImageWriterUtil.writeToFile(bitMatrix, "png", file, logo) ? file : null;
	}
	
}
