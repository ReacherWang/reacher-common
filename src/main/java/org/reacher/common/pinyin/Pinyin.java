/**
 * 
 */
package org.reacher.common.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author reacher
 *
 */
public class Pinyin {
	
	private HanyuPinyinOutputFormat spellFormat;
	
	private Pinyin() {
		this.spellFormat = new HanyuPinyinOutputFormat();
		this.spellFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		this.spellFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		this.spellFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	}
	
	private static class PinyinHolder {
		private static Pinyin pinyin = new Pinyin();
	}
	
	public static Pinyin getInstance() {
		return PinyinHolder.pinyin;
	}
	
	public void init(HanyuPinyinCaseType caseType, HanyuPinyinVCharType charType, HanyuPinyinToneType toneType) {
		/*
		 * HanyuPinyinCaseType
		 * LOWERCASE        <------->	min2
		 * UPPERCASE		<------->	MIN2
		 */
		this.spellFormat.setCaseType(caseType);
		/*
		 * HanyuPinyinVCharType
		 * WITH_U_AND_COLON	<------->	u:
		 * WITH_V           <------->	v
		 * WITH_U_UNICODE   <------->	ü
		 */
		this.spellFormat.setVCharType(charType);
		/*
		 * HanyuPinyinToneType
		 * WITH_TONE_NUMBER	<------->	da3
		 * WITHOUT_TONE	    <------->	da
		 * WITH_TONE_MARK   <------->	dǎ
		 * 
		 */
		this.spellFormat.setToneType(toneType);
	}
	
	public String turnToPinyin(String src) {
		return turnToPinyin(src, true);
	}
	
	
	public String turnToPinyin(String src, boolean isUppercase) {
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < src.length(); ++i) {
			String pinyin = this.turnToPinyin(src.charAt(i), isUppercase);
			if(null == pinyin) {
				buffer.append(src.charAt(i));
			} else {
				buffer.append(pinyin);
			}
		}
		return buffer.toString();
	}
	
	public String turnToPinyin(char ch) {
		return this.turnToPinyin(ch, true);
	}
	
	public String turnToPinyin(char ch, boolean isUppercase) {
		if(!RegexUtil.regexChineseCharacter(String.valueOf(ch))) {
			return null;
		}
		String[] pinyins = null;
		try {
			pinyins = PinyinHelper.toHanyuPinyinStringArray(ch, this.spellFormat);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		if(null == pinyins) {
			return null;
		}
		if(!isUppercase) {
			return pinyins[0];
		}
		return PinyinUtil.firstToUpperCase(pinyins[0]);
	}
	
	public String firstLetter(String src) {
		return this.firstLetter(src, true);
	}

	public String firstLetter(String src, boolean isUppercase) {
		String pinyin = this.turnToPinyin(src, isUppercase);
		if(null == pinyin || 0 == pinyin.length()) {
			return null;
		}
		return String.valueOf(pinyin.charAt(0));
	}
	
	public static void main(String[] args) {
		String temp = Pinyin.getInstance().firstLetter("中国");
		System.out.println(temp);
	}
	
}
