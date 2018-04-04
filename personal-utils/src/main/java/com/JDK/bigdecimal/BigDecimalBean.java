package com.JDK.bigdecimal;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class BigDecimalBean {

	@Test
	public void setScal(){

		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("amount",new BigDecimal(20));
//		hashMap.put("amount",new BigDecimal(2.9454289348290D));
		//转换为 BigDecimal
		BigDecimal bigDecimal = (BigDecimal) hashMap.get("amount");
		System.out.println(bigDecimal.toString());
		System.out.println(Double.valueOf(bigDecimal.toString()));
		System.out.println(bigDecimal.doubleValue());

		//做保留两位小数的四舍五入
		bigDecimal=bigDecimal.setScale( 2,BigDecimal.ROUND_HALF_UP);
		System.out.println(bigDecimal.doubleValue());
		System.out.println(String.valueOf(bigDecimal.doubleValue()+"0"));
	}

	@Test
	public void stringValueOf(){
		System.out.println(Double.valueOf(new BigDecimal(56.00).toString()));
		System.out.println(Double.valueOf(new BigDecimal(56.01).toString()));
		System.out.println(Double.valueOf(new BigDecimal(56.15).toString()));
		System.out.println(Double.valueOf(new BigDecimal(56.15).toString()));
		System.out.println(String.valueOf(Double.valueOf(new BigDecimal(56.98).toString())));
	}

	/**
	 *	重要的测试 BigDecimal与基本数据类型是一样的，传值而不是传引用,, 值的副本
	 * <p>Discription:[方法功能中文描述]</p>
	 * Created on 2017年2月28日
	 * @author:[左仁智]
	 */
	@Test
	public void passParamValue() {
		System.out.println(BigDecimal.ZERO);
		System.out.println(BigDecimal.ZERO.intValue()==0);
		//初始化借贷发生额
		double doubValue = 0.00;
		BigDecimal totalCR = BigDecimal.ZERO;
		BigDecimal temp = new BigDecimal(doubValue);
		System.out.println(totalCR == temp);
		System.out.println(totalCR.equals(temp));
		
		packageResult(totalCR);
		System.out.println("外面的---"+totalCR);
	}
	
	private void packageResult(BigDecimal totalCR) {
		totalCR = totalCR.add(new BigDecimal(0.25));
		System.out.println("里面的---"+totalCR);
	}

	@Test
	public void equals() {
		BigDecimal bigDecimal = new BigDecimal(0.00);
		System.out.println(new BigDecimal(0.00).equals(new BigDecimal(0.00)));
		System.out.println(new BigDecimal(0.0).equals(bigDecimal));
		
		System.out.println(new BigDecimal(2.00).setScale(3, BigDecimal.ROUND_HALF_UP)
				.equals(new BigDecimal(2.00).setScale(2,BigDecimal.ROUND_HALF_UP)));
		
		System.out.println(new BigDecimal(0.0).compareTo(new BigDecimal(0.00)));
	}
	
	/**
	 * <p>Discription:[加减运算]</p>
	 * Created on 2017年2月3日
	 * @author:[左仁智]
	 */
	@Test
	public void additionAndSubtraction() {
		BigDecimal bigDecimal = new BigDecimal(0.02).setScale(2,BigDecimal.ROUND_HALF_UP);
		bigDecimal = bigDecimal.add(new BigDecimal(0.07).setScale(2,BigDecimal.ROUND_HALF_UP)).subtract(new BigDecimal(0.01).setScale(2,BigDecimal.ROUND_HALF_UP));
		System.out.println(bigDecimal);
		System.out.println(bigDecimal);
		System.out.println(bigDecimal.toString());
	}
	/**
	 * BigDecimal加减乘除--计算操作
	 */
	@Test
	public void accurate() {
		BigDecimal bigDecimal1 = new BigDecimal(0.00);
		System.out.println(round(bigDecimal1));
		System.out.println(bigDecimal1);
		System.out.println(new BigDecimal(0.00));
		BigDecimal bigDecimal2 = round(new BigDecimal(0.01));
		BigDecimal bigDecimal3 = round(new BigDecimal(0.09));
		//加
		System.out.printf("--add--结果是%s%n",bigDecimal1.add(bigDecimal2));
		//减
		System.out.printf("--subtract--结果是%s%n",bigDecimal1.subtract(bigDecimal2));
		//乘
		System.out.printf("--multiply--结果是%s%n",bigDecimal1.multiply(bigDecimal2).doubleValue());
		//除
		System.out.printf("--divide--结果是%s%n",bigDecimal1.divide(round(bigDecimal2)));
		//混合
		System.out.printf("--混合--结果是%s%n",bigDecimal1.add(bigDecimal2).subtract(bigDecimal3));
	}
	/**
	 * <p>Discription:[保留2位小数]</p>
	 * Created on 2017年2月4日
	 * @param bigDecimal
	 * @return
	 * @author:[左仁智]
	 */
	private BigDecimal round(BigDecimal bigDecimal){
		return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * <p>Discription:[BigDecimal比较操作]</p>
	 * Created on 2017年2月3日
	 * @author:[左仁智]
	 */
	@Test
	public void compareTo() {
		System.out.println(Math.abs(-100));
		System.out.println(Math.abs(-1200));
		System.out.println(Math.abs(1200.23));

		BigDecimal bigDecimal = new BigDecimal(1);
		System.out.println(bigDecimal.compareTo(new BigDecimal(1.00)));
		
		System.out.println(BigDecimal.ZERO.compareTo(round(new BigDecimal(0.00))));
	}

	@Test
	public void round() {
		// up >= 0.5 down > 0.5
		double num1 = 1.355, num2 = 1;
		System.out.println(new BigDecimal(num1).divide(new BigDecimal(num2), 2));

		double num3 = new BigDecimal(1.15).divide(new BigDecimal(1D), 1,BigDecimal.ROUND_HALF_EVEN).doubleValue();
		System.out.println("num3=" + num3);
		BigDecimal a = new BigDecimal(0.145);
		System.out.println("EVEN=" + a.setScale(0, BigDecimal.ROUND_HALF_EVEN));
		System.out.println("EVEN=" + a.setScale(1, BigDecimal.ROUND_HALF_EVEN));
		System.out.println("EVEN=" + a.setScale(2, BigDecimal.ROUND_HALF_EVEN));
		System.out.println("down=" + a.setScale(0, BigDecimal.ROUND_HALF_DOWN)	+ "/tup=" + a.setScale(0, BigDecimal.ROUND_HALF_UP));
		System.out.println("down=" + a.setScale(2, BigDecimal.ROUND_HALF_DOWN)	+ "/tup=" + a.setScale(2, BigDecimal.ROUND_HALF_UP));

		System.out.println(a.setScale(2, BigDecimal.ROUND_HALF_EVEN));
	}

	@Test
	public void decimalFormat() {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		Object price = 2.368;
		String str = df.format(price);
		System.out.println("-->" + str); // 结果为 str = 2.30
		//todo 这种稳妥 2018-1-5
		String format = new DecimalFormat("0.00").format(2.369999D);// 保留几位小数
		System.out.println(format);
	}

	/**
	 * <p>Discription:[保留*位小数]</p>
	 * Created on 2017年2月3日
	 * @author:[左仁智]
	 */
	@Test
	public void setScale() {
		double f = 111231.529585;
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(f1);
	}

	@Test
	public void getShouldAmount(){
		BigDecimal bigDecimal = BigDecimal.ZERO;
		bigDecimal = bigDecimal
				.add(new BigDecimal(Double.valueOf("1.11")))
				.add(new BigDecimal(Double.valueOf("2.22009")))
				.add(new BigDecimal(Double.valueOf("3.33")));
		bigDecimal =bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println(bigDecimal.doubleValue());
	}
}
