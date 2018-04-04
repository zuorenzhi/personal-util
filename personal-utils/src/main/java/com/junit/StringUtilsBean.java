package com.junit;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringUtilsBean {

	@Test
	public void test(){

		System.out.println(StringUtils.isAnyBlank(null));
		System.out.println(StringUtils.isAnyBlank(""));
		System.out.println(StringUtils.isAnyBlank("null"));
		System.out.println(StringUtils.isAnyBlank("abc","mn"));
		System.out.println(String.valueOf(null));
	}

	@Test
	public void join(){
		String[] arr = new String []{"22","33","77","abc"};
		String joinStr = StringUtils.join(arr, "$$");
		System.out.println(joinStr);
		String joinStrOnly = StringUtils.join(arr,"---");
		System.out.println(joinStrOnly);
	}
	@Test
	public void deleteWhiteSpace(){
		String str = "你 好 q  1 1 1 1 1  哈 A #% ^$ ^^$  ^";
		System.out.println(StringUtils.deleteWhitespace(str));
		System.out.println(StringUtils.deleteWhitespace("  qsd sdfdsf 23142 234234"));
		System.out.println(StringUtils.deleteWhitespace("  q317855   888@ sina .c o m"));

	}
	@Test
	public void trimToEmpty(){
		System.out.println(StringUtils.trim("  q317855   888@ sina .c o m"));;
		System.out.println(StringUtils.trimToEmpty("  q317855   888@ sina .c o m"));;
		System.out.println(StringUtils.trimToNull("  q317855   888@ sina .c o m"));;
		System.out.println("  q317855   888@ sina .c o m".trim());
	}
}
