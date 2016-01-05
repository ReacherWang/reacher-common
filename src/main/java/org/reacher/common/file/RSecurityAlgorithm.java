/**
 * 
 */
package org.reacher.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reacher.common.utils.StringUtil;

/**
 * @author reacher
 * 
 */
public class RSecurityAlgorithm {

	private static final Log LOG = LogFactory.getLog(RSecurityAlgorithm.class);
	
	private static final int BUFFER_SIZE = 256 * 1024;
	private static int BYTE_CONVERT_INT_AND_FLAG = 0XFF;

	public static String getChecksum32(File sourceFile, RSecurityAlgorithmType securityAlgorithmType) {
		if (sourceFile == null || !sourceFile.exists()) {
			return null;
		}
		FileInputStream fileInputStream = null;
		DigestInputStream digestInputStream = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(securityAlgorithmType.getType());
			fileInputStream = new FileInputStream(sourceFile);
			digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
			byte[] buffer = new byte[BUFFER_SIZE];
			while (digestInputStream.read(buffer) > 0);
			messageDigest = digestInputStream.getMessageDigest();
			byte[] result = messageDigest.digest();
			return byteToHexString(result);
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			try {
				digestInputStream.close();
				fileInputStream.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}
		return null;
	}
	
	public static String getChecksum16(File sourceFile, RSecurityAlgorithmType securityAlgorithmType) {
		String checksum = getChecksum32(sourceFile, securityAlgorithmType);
		if (StringUtil.isEmpty(checksum)) {
			return null;
		}
		return checksum.substring(8, 24);
	}

	public static String getChecksum32(String content, RSecurityAlgorithmType securityAlgorithmType) {
		if (StringUtil.isEmpty(content)) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(securityAlgorithmType.getType());
			byte[] result = messageDigest.digest(content.getBytes());
			return byteToHexString(result);
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e);
		}
		return null;
	}
	
	public static String getChecksum16(String content, RSecurityAlgorithmType securityAlgorithmType) {
		String checksum = getChecksum32(content, securityAlgorithmType);
		if (StringUtil.isEmpty(checksum)) {
			return null;
		}
		return checksum.substring(8, 24);
	}

	private static String byteToHexString(byte[] b) {
		StringBuilder hexString = new StringBuilder();
		String temp;
		for (int i = 0; i < b.length; i++) {
			temp = Integer.toHexString(b[i] & BYTE_CONVERT_INT_AND_FLAG);
			if (temp.length() == 1) {
				hexString.append('0');
			}
			hexString.append(temp);
		}
		return hexString.toString();
	}
}
