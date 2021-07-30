package com.ncu.tree;

public class BinaryTree {
	public static void main(String[] args) {
		TreeDemo tree = new TreeDemo();
		Node node1 = new Node(7,11);
		Node node2 = new Node(5,"Tom");
		Node node3 = new Node(6,"jack");
		Node node4 = new Node(1,"hello");
		Node node5 = new Node(8,15);
		node1.left = node2;
		node1.right = node5;
		node2.right = node3;
		node2.left = node4;
		tree.setRoot(node1);
		tree.delete(9);
		System.out.println("前序");
		tree.perOrder();
		System.out.println("中序");
		tree.midOrder();
		System.out.println("后序");
		tree.postOrder();
		
		System.out.println("查找");
		Node result = tree.perSearch(5);
		System.out.println(result);
	}

}

class TreeDemo{
	private Node root;
	//设置根节点
	public void setRoot(Node root) {
		this.root = root;
	}
	
	//删除方法
	public void delete(int key) {
		if(root != null) {
			if(root.getKey() == key) {
				root = null;
				return;
			} else {
				root.delete(key);
			}
		} else {
			System.out.println("树为空");
		}
	}
	//前序查找
	public Node perSearch(int key) {
		if(this.root != null) {
			return this.root.perSearch(key);
		} else {
			return null;
		}
	}
	//中序查找
	public Node midSearch(int key) {
		if(this.root != null) {
			return this.root.midSearch(key);
		} else {
			return null;
		}
	}
	//后序查找
	public Node postSearch(int key) {
		if(this.root != null) {
			return this.root.postSearch(key);
		} else {
			return null;
		}
	}
	//前序遍历
	public void perOrder() {
		if(this.root != null) {
			this.root.perOrder();
		} else {
			System.out.println("二叉树为空");
		}
	}
	//中序遍历
	public void midOrder() {
		if(this.root != null) {
			this.root.midOrder();
		} else {
			System.out.println("二叉树为空");
		}
	}
	// 后序遍历
	public void postOrder() {
		if (this.root != null) {
			this.root.postOrder();
		} else {
			System.out.println("二叉树为空");
		}
	}
}

class Node{
	
	private int key;
	private Object value;
	Node left;
	Node right;
	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Node(int key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}
	//删除节点
	public void delete(int key) {
		
		if(this.left != null && this.left.key == key) {
			this.left = null;
			return;
		}
		if(this.right != null && this.right.key == key) {
			this.right = null;
			return;
		}
		//开始向左子树遍历
		if(this.left != null) {
			this.left.delete(key);
		}
		//向有递归删除
		if(this.right != null) {
			this.right.delete(key);
		}
		//
	}

	//前序遍历的方法
	public void perOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.perOrder();
		}
		if(this.right != null) {
			this.right.perOrder();
		}
	}
	
	// 中序遍历的方法
	public void midOrder() {
		if (this.left != null) {
			this.left.midOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.midOrder();
		}
	}
	

	// 后序遍历的方法
	public void postOrder() {
		if (this.left != null) {
			this.left.postOrder();
		}
		if (this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}

	//前序查找
	public Node perSearch(int key) {
		//比较当前节点是不是
		System.out.println("前序查找");
		if(this.key == key) {
			return this;
		}
		
		Node node = null;
		if(this.left != null) {
			node = this.left.perSearch(key);
		}
		if(node != null) {
			return node;
		}
		if(this.right != null) {
			node = this.right.perSearch(key);
		}
		//不管找没找到都返回
		return node;
	}
	//中序查找
	public Node midSearch(int key) {
		
		
		Node node = null;
		if(this.left != null) {
			node = this.left.perSearch(key);
		}
		if(node != null) {
			return node;
		}
		//比较当前节点是不是
		if(this.key == key) {
			return this;
		}
		if(this.right != null) {
			node = this.right.perSearch(key);
		}
		if(node != null) {
			return node;
		}
		return node;
	}
	//后序查找
	public Node postSearch(int key) {
		Node node = null;
		if(this.left != null) {
			node = this.left.perSearch(key);
		}
		if(node != null) {
			return node;
		}
	
		if(this.right != null) {
			node = this.right.perSearch(key);
		}
		if(node != null) {
			return node;
		}
		//比较当前节点是不是
		if(this.key == key) {
			return this;
		}
		return node;
		
	}
	
	@Override
	public String toString() {
		return "Node [key=" + key + ", value=" + value + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}