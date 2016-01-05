/**
 * 
 */
package org.reacher.common.file;

/**
 * @author reacher
 *
 */
public class RCompress {

	private int width;
	private int height;
	private String format;
	private boolean proportion;
	
	public RCompress(int width, int height, String format) {
		this.width = width;
		this.height = height;
		this.format = format;
		this.proportion = false;
	}
	
	public RCompress(int width, int height, String format, boolean proportion) {
		this.width = width;
		this.height = height;
		this.format = format;
		this.proportion = proportion;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isProportion() {
		return proportion;
	}

	public void setProportion(boolean proportion) {
		this.proportion = proportion;
	}

}
