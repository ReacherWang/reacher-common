package org.reacher.common.permission;

public class Test {

	public static void main(String[] args) {
		String resource = "";
		StringBuffer buffer = new StringBuffer(resource);
		buffer.replace(2, 5, "4433");
		System.out.println(buffer.toString());
	}

}
