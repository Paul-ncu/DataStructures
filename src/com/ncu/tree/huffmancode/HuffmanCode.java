package com.ncu.tree.huffmancode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {

	public static void main(String[] args) {
		String content = "i like like like java do you like a java";
		byte[] contentBytes = content.getBytes();
		for (byte b : contentBytes) {
			System.out.print(b + " ");
		}
		//System.out.println(contentBytes[0]);
		List<Node> listNodes = getNodes(contentBytes);
		Node root = createHuffmanTree(listNodes);
		//root.perOrder();
////		
////		getCodes(root, "", stringBuilder);
////		System.out.println(huffmanCodes);
		Map<Byte, String> huffmanCodes = getCodes(root);
//		byte[] zip = zip(contentBytes, huffmanCodes);
////		System.out.println(Arrays.toString(zip));
//		
		byte[] zipfile = getZipfileByHuffman(contentBytes);
//		//System.out.println(Arrays.toString(zipfile));
//		//System.out.println(content.cha);
		System.out.println();
		byte[] decode = decode(huffmanCodes, zipfile);
		for (byte b : decode) {
			System.out.print(b + " ");
		}
//		
//		//String src = "D:\\output.log";
//		String des = "D:\\output.txt";
//		
//		//zipFile(src, des);
//		
//
//		
//		String newDes = "D:\\output1.txt";//3422429
//		
//		unzipFile(des, newDes);
//		
//		System.out.println("success");
	}
	
	// 对文件进行解压
	public static void unzipFile(String zipFile, String desFile) {
		
		// 定义文件输入流
		InputStream is = null;
		// 对象输入流
		ObjectInputStream ois = null;
		// 文件输出流
		OutputStream os = null;
		
		try {
			is = new FileInputStream(zipFile);
			// 创建一个和is关联的对象输入流
			ois = new ObjectInputStream(is);
			os = new FileOutputStream(desFile);
			// 读取byte数组 huffmanBytes
			byte[] huffmanBytes = (byte[]) ois.readObject();
			//System.out.println(huffmanBytes.length);
			// 读取huffman编码表
			Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject(); 
			//System.out.println(huffmanCodes.size());
			// 开始解码
			byte[] decode = decode(huffmanCodes, huffmanBytes);
			
			os.write(decode);
			
			} catch (Exception e) {
			e.printStackTrace();
			} finally {
				
				try {
					os.close();
					ois.close();
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
	}
	
	
	// 对一个文件进行压缩
	public static void zipFile(String srcFile, String dseFile) {
		
		FileInputStream is = null;
		FileOutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			// 创建文件输入流
			is = new FileInputStream(srcFile);
			// 创建一个和元文件大小已有的byte
			byte[] b = new byte[is.available()];
			// 读取文件
			is.read(b);
			// 使用huffman编码压缩文件
			byte[] zipfileByHuffman = getZipfileByHuffman(b);
			// 创建文件的输出流，存放压缩文件
			os = new FileOutputStream(dseFile);
			
			oos = new ObjectOutputStream(os);
			
			oos.writeObject(zipfileByHuffman);
			
			oos.writeObject(huffmanCodes);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				oos.close();
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 
	 * @description 解码过程
	 * @author xiaohao
	 * @date 2020年5月27日下午10:00:12
	 * @param huffMancode huffman编码表
	 * @param huffmanBytes 压缩后的字节
	 * @return
	 */
	private static byte[] decode(Map<Byte, String> huffMancode, byte[] huffmanBytes) {
		StringBuilder builder = new StringBuilder();
		// 将压缩后的数组转换成二进制字符串
		
		for (int i = 0; i < huffmanBytes.length; i++) {
			
			// 最后一个字符不要补高位
			boolean flag = (i == huffmanBytes.length - 1);
			builder.append(byteToBitString(huffmanBytes[i], !flag));
		}
		
		// 把字符串按照指定的huffman编码进行解码
		Map<String, Byte> map = new HashMap<String, Byte>();
		for (Map.Entry<Byte, String> entry : huffMancode.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}

		// 创建一个集合存放byte
		List<Byte> list = new ArrayList<>();
		for (int i = 0;i < builder.length();) {
			int count = 1;
			boolean flag = true;
			Byte b = null;
			
			while (flag) {
				String key = builder.substring(i, i + count);
				b = map.get(key);
				if (b == null) {
					count++;
				} else {
					flag = false;
				}
			}
			list.add(b);
			i += count;
		}
		// for循环结束后list中存放了所有的字符
		// 将list中的字符转换为byte
		byte[] b = new byte[list.size()];
		for (int i = 0; i < b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
	}
	
	/**
	 * 
	 * @description 将字节变成原来的二进制
	 * @author xiaohao
	 * @date 2020年5月27日下午9:58:33
	 * @param b
	 * @param flag
	 * @return
	 */
	private static String byteToBitString(byte b, boolean flag) {
		// 使用变量保存b
		int temp = b;
		if (flag) {
			temp |= 256;
		}
		String str = Integer.toBinaryString(temp);
		if (flag) {
			return str.substring(str.length() - 8);
		} else {
			return str;
		}
	}
	
	
	// 封装一个方法
	private static byte[] getZipfileByHuffman(byte[] file) {
		List<Node> nodes = getNodes(file);
		Node root = createHuffmanTree(nodes);
		Map<Byte, String> huffmanCodes = getCodes(root);
		byte[] zip = zip(file, huffmanCodes);
		return zip;
	}
	
	// 将对应的字符串数组，转换成Huffman编码，
	
	private static byte[] zip(byte[] bytes,Map<Byte, String> huffmanCodes) {
		
		// 利用 huffmanCodes 将 bytes转换成 huffman编码表
		StringBuilder builder = new StringBuilder();
		// 遍历bytes
		for (byte b : bytes) {
			builder.append(huffmanCodes.get(b));
		}
		
		// 将字符串转换成byte
		int len;
		if (builder.length() % 8 == 0) {
			len = builder.length() / 8;
		} else {
			len = builder.length() / 8 + 1;
		}
		// 创建存储压缩后的byte数组
		byte[] huffmanCodeBytes = new byte[len];
		int index = 0;// 用来放入huffmanCodeBytes的指针
		for (int i = 0; i < builder.length(); i += 8) {
			String strByte;
			if (i + 8 > builder.length()) {
				strByte = builder.substring(i);
			} else {
				strByte = builder.substring(i, i + 8);
			}
			
			huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
			index++;
		}
		return huffmanCodeBytes;
		
		
	}
//	
	/**
	 *  
	 * @description 将字符串中的字符和相应的权值放入Node，并且用一个list封装
	 * @author xiaohao
	 * @date 2020年5月27日下午8:45:09
	 * @param bytes
	 * @return
	 */
	
	private static List<Node> getNodes(byte[] bytes) {
		
		// 创建一个ArrayList
		ArrayList<Node> listNodes = new ArrayList<Node>();
		
		// 遍历bytes， 统计每个byte出现的次数
		HashMap<Byte, Integer> counts = new HashMap<>();
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if(count == null) {
				counts.put(b, 1);
			} else {
				counts.put(b, count + 1);
			}
		}
		
		// 把每个键值对转成一个Node对象，并加入到nodes集合
		for (Map.Entry<Byte, Integer> entry: counts.entrySet()) {
			listNodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		
		return listNodes;
	}
	
	/**
	 * 
	 * @description 
	 * @author xiaohao
	 * @date 2020年5月27日下午9:37:40
	 * @param listNodes
	 * @return
	 */
	private static Node createHuffmanTree(List<Node> listNodes) {
		
		while(listNodes.size() > 1) {
			Collections.sort(listNodes);
			
			// 取出第一颗最小的二叉树
			Node leftNode = listNodes.get(0);
			// 取出第二棵最小的二叉树
			Node rightNode = listNodes.get(1);
			// 创建一棵新的二叉树，没有data，只有权值
			Node parent = new Node(null, leftNode.weight + rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;
			
			// 将取出的两个节点从listNodes中去除
			listNodes.remove(leftNode);
			listNodes.remove(rightNode);
			// 将parent节点放入listNodes
			listNodes.add(parent);
		}
		
		// 返回最后的根节点
		return listNodes.get(0);
	}
	
	// 将Huffman编码表放在Map中
	// key中放字符，value中放编码
	static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
	// 在生成Huffman编码表时，需要去拼接路径，定义一个StringBuilger存储某个节点的路径
	static StringBuilder stringBuilder = new StringBuilder();
	
	
	/**
	 * 
	 * @description 创建一个Huffman编码表，封装到一个Map中
	 * @author xiaohao
	 * @date 2020年5月27日下午8:17:46
	 * @param node 传入节点
	 * @param code 左子节点为0，右子节点为1
	 * @param path 用于拼接路径
	 */
	private static void getCodes(Node node, String code, StringBuilder path) {
		StringBuilder path2 = new StringBuilder(path);
		// 将code放入path2
		path2.append(code);
		if (node != null) {
			// 如果节点中没有data
			if (node.data == null) {
				// 向左递归
				getCodes(node.left, "0", path2);
				// 向右递归
				getCodes(node.right, "1", path2);
			} else {
				// 此时为叶子节点
				huffmanCodes.put(node.data, path2.toString());
			}
		}
	}
	
	public static Map<Byte, String> getCodes(Node root) {
		
		getCodes(root, "", stringBuilder);
		
		return huffmanCodes;
		
	}
	
}

// 创建Node，带数据和权值
class Node implements Comparable<Node>{
	Byte data;// 存放字符串的ASCII码
	int weight;// 权值，表示字符出现的个数
	Node left;
	Node right;
	
	public Node(Byte data, int weigth) {
		this.data = data;
		this.weight = weigth;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}
	
	public void perOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.perOrder();
		}
		if(this.right != null) {
			this.right.perOrder();
		}
	}

}