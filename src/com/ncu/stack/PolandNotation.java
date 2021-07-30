package com.ncu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

	public static void main(String[] args) {
		//先定义一个逆波兰表达式
		/*
		 * 思路
		 * 1.先将"3 4 + 5 x 6 -" => 放到ArrayList中
		 * 2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
		 */
		//4 * 5 - 8 + 60 + 8 / 2
//		String suffixExpression = "4 5 x 8 - 60 + 8 2 / + ";
//		List<String> list = getListString(suffixExpression);
//		int result = calculate(list);
//		System.out.println("表达式为：" + suffixExpression);
//		System.out.println("结果为：" + result);
	
		
		String infixExpression = "(2+7)x(-3)";
		System.out.println("中缀表达式为 :" + infixExpression);
		List<String> expressionList = toInfixExpressionList(infixExpression);
		System.out.println("中缀表达式的list：" + expressionList);
		List<String> suffix = infixToSuffix(expressionList);
		System.out.println("后缀表达式的list：" + suffix);
		int result = calculate(suffix);
		System.out.println("计算结果为：" + result);
		
	}
	//将一个逆波兰表达式依次将数据和运算符放入到ArrayList中
	public static List<String> getListString(String suffixExpression){
		//将suffixExpression分割
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for(String ele : split) {
			list.add(ele);
		}
		return list;
	}
	
	/*
	 * 完成对逆波兰表达式的运算
	 * 1.从左至右扫描，将3和4压入堆栈
	 * 2.遇到+运算符，因此弹出4和3，计算3+4的值为7，再将7推入栈
	 * 3.将5入栈
	 * 4.接下来就是x运算符，因此弹出5和7，计算出7x5=35，将35推入栈
	 * 5.将6入栈
	 * 6.最后是-运算符，计算出35-6的值，即29，由此得出最终结果
	 */
	
	public static int calculate(List<String> list) {
		//创建一个栈
		Stack<String> stack = new Stack<String>();
		//遍历list
		for(String item:list) {
			if(item.matches("\\d+")) {//匹配的是多位数
				//入栈
				stack.push(item);
			} else {
				//pop出两个数，进行运算，再将结果入栈
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int result = 0;
				if(item.equals("+")) {
					result = num1 + num2;
				}else if (item.equals("-")) {
					result = num1 - num2;//用后pop的数减去先pop的数
				}else if(item.equals("x")) {
					result = num1 * num2;
				}else if(item.equals("/")) {
					result = num1 / num2;
				}else {
					throw new RuntimeException("运算符有误");
				}
				//把result入栈
				stack.push(result+"");
			}
		}
		//最后留在栈中的为结果
		return Integer.parseInt(stack.pop());
	}
	
	//先将中缀表达式的数据和运算符装入一个list中
	public static List<String> toInfixExpressionList(String infixExpre) {
		//new一个list，用来存放中缀表达式对应的内容
		List<String> list = new ArrayList<String>();
		int i = 0;//用来遍历字符串的指针
		String str;//多位数的拼接
		char c;//每遍历一个字符，就放到c中
		do {
			//如果c是一个非数字，就要加入到list
			if((c = infixExpre.charAt(i)) < 48 || (c = infixExpre.charAt(i)) > 57) {
				list.add("" + c);
				i++;
			} else {//如果是一个数，要考虑多位数的问题
				str = "";//先将str置空
				while(i < infixExpre.length() 
					  && (c = infixExpre.charAt(i)) >= 48 
					  && (c = infixExpre.charAt(i)) <= 57) {
					str += c;//拼接
					i++;
				}
				list.add(str);
				
			}
		}while(i < infixExpre.length());
		
		return list;
	}
	//将中缀表达式转换成后缀表达式
	public static List<String> infixToSuffix(List<String> list) {
		//s1作为符号栈
		Stack<String> s1 = new Stack<String>();
		//用s2代替数栈，因为数栈在思路过程中没有出栈操作
		List<String> s2 = new ArrayList<String>();
		
		//遍历list
		for (String item : list) {
			//如果是一个数，加入list2
			if (item.matches("\\d+")) {
				s2.add(item);
			} else if ("(".equals(item)) {
				//如果是左括号，直接入栈
				s1.push(item);
			} else if (")".equals(item)) {
				//依次弹出s1中的运算符，知道遇到左括号位置
				while(!"(".equals(s1.peek())) {
					s2.add(s1.pop());
				}
				s1.pop();//将s1栈中的"("弹出，消除"("
			} else {
				//当item的优先级小于等于s1栈顶的运算符的优先级，将s1栈顶中的运算符，加入的s2中，
				while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
					s2.add(s1.pop());
				}
				//将当前扫描的符号压入栈中
				s1.push(item);
			}
		}
		
		//将s1中剩余的运算符依次弹出加入到s2中
		while(s1.size() != 0) {
			s2.add(s1.pop());
		}
		return s2;
	}
	
}

//编写一个类，可以返回一个运算符对应的优先级
class Operation{
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;
	
	//写一个方法返回对应的优先级
	public static int getValue(String operation) {
		int result = 0;
		switch (operation) {
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "x":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
			break;		
		}
		return result;
	}
	
}




