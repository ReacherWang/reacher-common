/**
 * 
 */
package org.reacher.common.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reacher.common.utils.StringUtil;

/**
 * @author reacher
 *
 */
public final class RFileManager {
	
	private static final Log logger = LogFactory.getLog(RFileManager.class);
	
	private RFileManager() {
	}
	
	private static class FileManagerHolder {
		private static RFileManager fileManager = new RFileManager();
	}
	
	public static RFileManager getInstance() {
		return FileManagerHolder.fileManager;
	}
	
	public String store(File sourceFile, String fileUploadDirectory) {
		return this.store(sourceFile, fileUploadDirectory, RSecurityAlgorithmType.MD5, null);
	}
	
	public String store(File sourceFile, String fileUploadDirectory, RCompress compress) {
		return this.store(sourceFile, fileUploadDirectory, RSecurityAlgorithmType.MD5, compress);
	}
	
	public String store(File sourceFile, String fileUploadDirectory, RSecurityAlgorithmType securityAlgorithmType, RCompress compress) {
		String checksum = RSecurityAlgorithm.getChecksum32(sourceFile, securityAlgorithmType);
		if (null == checksum || 0 == checksum.length()) {
			logger.error("计算文件MD5返回为空！");
			return null;
		}
		File fileDirectory = getFileDirectory(checksum, fileUploadDirectory);
		if(null == fileDirectory) {
			logger.error("获取文件存储目录失败！");
			return null;
		}
		if(null != compress && RImageCompressed.getInstance().isImage(sourceFile)) {
			this.compressed(sourceFile, compress, fileDirectory, checksum + "-compressed");
		}
		if (exist(checksum, fileDirectory)) {
			return checksum;
		}
		File destinationFile = new File(fileDirectory, checksum);
		try {
			FileUtils.moveFile(sourceFile, destinationFile);
		} catch (IOException e) {
			logger.error("将原始文件移动到制定目录出错！");
			return null;
		}
		return checksum;
	}
	
	public File seek(String checksum, String fileUploadDirectory) {
		return seek(checksum, fileUploadDirectory, false);
	}
	
	public File seek(String checksum, String fileUploadDirectory, boolean compressed) {
		File fileDirectory = getFileDirectory(checksum, fileUploadDirectory);
		if (fileDirectory == null) {
			logger.error("获取文件存储目录失败！");
			return null;
		}
		if(compressed) {
			return new File(fileDirectory.getPath() + File.separator + checksum + "-compressed");
		}
		return new File(fileDirectory.getPath() + File.separator + checksum);
	}
	
	private boolean exist(String checksum, File fileDirectory) {
		if(null == checksum || 0 == checksum.length()) {
			logger.error("传递过来的文件MD5码为空！");
			return false;
		}
		if (fileDirectory == null) {
			logger.error("传递过来的文件目录为空！");
			return false;
		}
		File file = new File(fileDirectory.getPath() + File.separator + checksum);
		return file.exists();
	}
	
	private File getFileDirectory(String checksum, String fileUploadDirectory) {
		if (StringUtil.isEmpty(checksum)) {
			logger.warn("文件MD5码为空！");
			return null;
		}
		StringBuilder filePath = new StringBuilder(fileUploadDirectory);
		for (int i = 1; i <= 3; i++) {
			filePath.append(checksum.substring(3 * (i - 1), 3 * i));
			filePath.append(File.separator);
		}
		File fileDirectory = new File(filePath.toString());
		if (!fileDirectory.exists()) {
			fileDirectory.mkdirs();
		}
		return fileDirectory;
	}
	
	private boolean compressed(File file, RCompress compress, File directory, String name) {
		if (exist(name, directory)) {
			return true;
		}
		File compressedFile = null;
		try {
			compressedFile = RImageCompressed.getInstance().compressed(file, compress, name);
		} catch(Exception e) {
			logger.error("压缩文件API接口出错！");
			return false;
		}
		if(null == compressedFile) {
			logger.error("压缩文件API返回的压缩文件为空！");
			return false;
		}
		File temp = new File(directory, name);
		try {
			FileUtils.moveFile(compressedFile, temp);
		} catch (IOException e) {
			logger.error("将压缩后的图片移动到指定路径出错！");
			return false;
		}
		return true;
	}
	
	public boolean removeCompressed(File root) {
		if(null == root || !root.exists()) {
			logger.error("根目录文件夹不存在！");
			return false;
		}
		File[] files = root.listFiles();
		if(null == files) {
			logger.warn("根目录文件夹下不存在其他文件！");
			return false;
		}
		for(int i = 0; i < files.length; ++i) {
			if(files[i].isDirectory()) {
				removeCompressed(files[i]);
				continue;
			}
			String fileName = files[i].getName();
			if(fileName.endsWith("-compressed")) {
				continue;
			}
			File file = new File(files[i].getPath() + "-compressed");
			if(file.exists()) {
				file.delete();
			}
		}
		return true;
	}
	
}
