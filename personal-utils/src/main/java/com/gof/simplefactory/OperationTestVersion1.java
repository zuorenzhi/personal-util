package com.gof.simplefactory;

import java.util.Scanner;

public class OperationTestVersion1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String firstNum = scanner.nextLine();
		String secondNum = scanner.nextLine();
		String act = scanner.nextLine();
		System.out.println(firstNum);
		System.out.println(secondNum);
		System.out.println(act);
		if("+".equals(act)){
			System.out.println(Integer.valueOf(firstNum) +Integer.valueOf(secondNum));
		}
		if("-".equals(act)){
			System.out.println(Integer.valueOf(firstNum) -Integer.valueOf(secondNum));
		}
		if("*".equals(act)){
			System.out.println(Integer.valueOf(firstNum) *Integer.valueOf(secondNum));
		}
		if("/".equals(act)){
			System.out.println(Integer.valueOf(firstNum) /Integer.valueOf(secondNum));
		}
			
	}
}
