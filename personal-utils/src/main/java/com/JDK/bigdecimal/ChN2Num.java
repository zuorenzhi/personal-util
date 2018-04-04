package com.JDK.bigdecimal;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;


public class ChN2Num {

	
	@Test
	public void Ch2Int(){
		int num = chineseNumber2Int("一千万八百万五十万一万九千八百九十六");
		String str = "两千零一十五九".replace("两", "二");
		System.out.println(chineseNumber2Int("十"));
		System.out.println(chineseNumber2Int("一千零八"));
		System.out.println(chineseNumber2Int("一百零八"));
		System.out.println(chineseNumber2Int("第我九"));
		System.out.println(chineseNumber2Int("五卷百第六十哈七"));
		System.out.println(isAllNum("一千万八百万五十万"));
		System.out.println(isAllNum("一千万我八百"));
		System.out.println(isAllNum("一千零八"));
		/*String s = "第二集除灵事务所第二十四章卓思曼的心腹";
		String string = "第二十四章卓思曼的心腹";
		System.out.println(getChapterOrder(string));*/
	}
	
	public Integer getChapterOrder(String subChapName){
		String srcStr = subChapName;
		Integer thisOrder =  0;
		List<Integer> listD = getIndexOfStr(subChapName, "第");
		List<Integer> listZ = getIndexOfStr(subChapName, "章");
		for (int i = 0; i < listD.size(); i++) {
			for (int j = 0; j < listZ.size(); j++) {
				if(listZ.get(j) > listD.get(i)){
					System.out.println(listZ.get(j)+"==="+(listD.get(i)+1));
					subChapName = srcStr.substring(listD.get(i)+1, listZ.get(j));
					subChapName = subChapName.replace("两", "二");
					if(StringUtils.isNumeric(subChapName)){
						thisOrder = Integer.parseInt(subChapName);
						return thisOrder;
					}else if(chineseNumber2Int(subChapName) > 0 && isAllNum(subChapName)){
						if(subChapName.contains("百十"))
							subChapName = subChapName.replace("百十", "百一十");
						thisOrder = chineseNumber2Int(subChapName);
						return thisOrder;
					}
				}
			}
		}
		return thisOrder;
	}
	
	public List<Integer> getIndexOfStr(String paramStr, String subStr) {

		List<Integer> list = new ArrayList<Integer>();
		Integer first = paramStr.indexOf(subStr);
		list.add(first);
		if (paramStr.indexOf(subStr, first + 1) > 0) {
			Integer second = paramStr.indexOf(subStr, first + 1);
			list.add(second);
			if (paramStr.indexOf(subStr, second + 1) > 0) {
				Integer third = paramStr.indexOf(subStr, second + 1);
				list.add(third);
				if (paramStr.indexOf(subStr, third + 1) > 0) {
					Integer fourth = paramStr.indexOf(subStr, third + 1);
					list.add(fourth);
					if (paramStr.indexOf(subStr, fourth + 1) > 0) {
						Integer fiveth = paramStr.indexOf(subStr, fourth + 1);
						list.add(fiveth);
					}
				}
			}
		}
		return list;
	}
	
	private boolean isAllNum(String subChapName) {
		String checkStr = "零一二三四五六七八九十百千万亿";
		for (int i = 0; i < subChapName.length(); i++) {
			char c = subChapName.charAt(i);
			if(!checkStr.contains(String.valueOf(c))){
				return false;
			}
		}
		return true;
	}

	 /**
	 * 中文數字转阿拉伯数组【十万九千零六十  --> 109060】
	 * @author 雪见烟寒
	 * @param chineseNumber
	 * @return
	 */
	public static int chineseNumber2Int(String chineseNumber){
	  int result = 0;
	  int temp = 1;//存放一个单位的数字如：十万
	  int count = 0;//判断是否有unitArr 单位
	  char[] numArr = new char[]{'一','二','三','四','五','六','七','八','九'};
	  char[] unitArr = new char[]{'十','百','千','万','亿'};
	  for (int i = 0; i < chineseNumber.length(); i++) {
	    boolean b = true;//判断是否是unitArr 单位数组
	    char c = chineseNumber.charAt(i);
	    for (int j = 0; j < numArr.length; j++) {//非单位，即数字
	      if (c == numArr[j]) {
	        if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
	          result += temp;
	          temp = 1;
	          count = 0;
	        }
	        // 下标+1，就是对应的值
	        temp = j + 1;
	        b = false;
	        break;
	      }
	    }
	    if(b){//单位{'十','百','千','万','亿'}
	      for (int j = 0; j < unitArr.length; j++) {
	        if (c == unitArr[j]) {
	          switch (j) {
	          case 0:
	            temp *= 10;
	            break;
	          case 1:
	            temp *= 100;
	            break;
	          case 2:
	            temp *= 1000;
	            break;
	          case 3:
	            temp *= 10000;
	            break;
	          case 4:
	            temp *= 100000000;
	            break;
	          default:
	            break;
	          }
	          count++;
	        }
	      }
	    }
	    if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
	      result += temp;
	    }
	  }
	  return result;
	}
}
