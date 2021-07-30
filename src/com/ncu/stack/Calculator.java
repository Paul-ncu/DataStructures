package com.ncu.stack;

/*使用栈完成表达式的计算思路
 * 1.通过一个index值（索引），来遍历我们的表达式
 * 2.如果我们发现是一个数字，就直接入栈
 * 3.如果扫描到的是一个符号，就分如下情况
 * 3.1当前的符号栈为空，就直接入栈
 * 3.2符号栈中有操作符，就进行比较，如果当前的操作符的优先级小于或者
 * 等于栈中的操作符，就需要从数栈中pop出两个数，再从符号栈中pop出一个
 * 符号，进行运算，将得到的结果入数栈，然后将当前的操作符入符号栈，如果
 * 当前的操作符的优先级大于栈中的操作符，就直接入符号栈
 * 4.当表达式扫扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
 * 5.最后再数栈只有一个数字，就是表达式的结果
 * 
 * 
 * 这种算法只能对一位数的进行加减乘除运算
 */
public class Calculator {
	public static void main(String[] args) {
		String expression = "2+2*3-2*2-1";
		//创建一个数栈，一个符号栈
		ArrayStack1 numStack = new ArrayStack1(10);
		ArrayStack1 operStack = new ArrayStack1(10);
		//第一运算需要的相关变量
		int index = 0;
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int result = 0;
		char ch = ' ';
		String number= "";
		//循环扫描expression
		while(true) {
			//获取每一个字符
			ch = expression.substring(index,index + 1).charAt(0);
			//判断是不是操作符
			if(operStack.isOper(ch)) {
				if(!operStack.isEmpty()) {
					//如果ch的优先级小于或者等于栈顶符号的优先级
					//要从数栈中pop两个数字运算再将结果放入数栈
					if(operStack.priority(ch) <= operStack.priority(operStack.getTopValue())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						result = numStack.cal(num1, num2, oper);
						//把结果放入数栈
						numStack.push(result);
						//把当前的操作符号放入符号栈
						operStack.push(ch);
					} else {
						operStack.push(ch);
					}
				}else {
					//如果符号栈为空，当前符号直接入符号栈
					operStack.push(ch);
				}
			}else {
				//numStack.push(ch - 48); //根据ASCII码，'1'对应的值为49
				/*
				 * 1.在处理多位数时，不能放发现是一个数就立即入栈，因为他可能是多为数
				 * 2.在处理是数，需要expression的index向后再看一位，如果是数就进行扫描，如果是符号才入栈
				 * 3.因此需要定义一个变量字符串，用于拼接
				 * 
				 */
				number += ch;
				//如果ch就是最后一位数字，那么直接入栈
				if(index == expression.length() - 1) {
					numStack.push(Integer.parseInt(number));
				}else {
					//判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
					//注意是看后一位，不是index++
					if(operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
						//如果后一位是运算符，则入栈
						numStack.push(Integer.parseInt(number));
						number = "";
					}
				}
				
			}
			index++;
			//判断是否遍历完
			if(index >= expression.length()) {
				break;
			}
		}
		
		//表达式扫描完毕，就顺序从数栈和符号栈中pop出相应的数栈和符号，再运算
		while(true) {
			//如果符号栈为空，则表示计算完成，数栈中只有一个数栈，即为结果
			if(operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			result =numStack.cal(num1, num2, oper);
			numStack.push(result);
		}
		System.out.println("计算结果为：" + expression + " = " + numStack.pop());
	}

}

class ArrayStack1{
	private int maxSize;
	private int[] stack;
	private int top = -1;//表示栈底
	
	public ArrayStack1(int maxSize) {
		this.maxSize = maxSize;
		stack =new int[this.maxSize];
	}
	
	public boolean isFull() {
		return top == maxSize - 1;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	//入栈push
	public void push(int value) {
		if(isFull()) {
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}
	//出栈
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("栈空");
		}
		int value = stack[top];
		top--;
		return value;
	}
	
	//遍历栈
	public void show() {
		if(isEmpty()) {
			System.out.println("栈空");
			return;
		}
		for (int i = top; i > -1 ; i--) {
			System.out.println("stack[" + i + "] = " + stack[i]);
		}
	}
	//判断运算符的优先级
	//运算赋的优先级越大，返回值越大
	public int priority(int oper) {
		if(oper == '*' || oper == '/') {
			return 1;
		}else if(oper == '+' || oper == '-') {
			return 0;
		}else {
			return -1;
		}
	}
	//判断是不是一个运算符
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val =='*' || val == '/';
	}
	
	//计算方法
	public int cal(int num1, int num2, int oper) {
		int result = 0;//用于存放运算结果
		switch(oper) {
		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num2 - num1;//后pop出来的数字作为被减数
			break;
		case '*':
			result = num1 * num2;
			break;
		case '/':
			result = num2 / num1;
			break;
		default:
			break;
		}
		return result;
	}
	//返回栈顶的值
	public int getTopValue() {
		return stack[top];
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}