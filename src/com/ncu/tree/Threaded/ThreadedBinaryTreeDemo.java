package com.ncu.tree.Threaded;



public class ThreadedBinaryTreeDemo {

	public static void main(String[] args) {

		Node node1 = new Node(1, "tom");
		Node node2 = new Node(3, "jack");
		Node node3 = new Node(6, "smith");
		Node node4 = new Node(8, "rose");
		Node node5 = new Node(10, "slash");
		Node node6 = new Node(14, "duff");
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		
		Tree tree = new Tree();
		tree.setRoot(node1);
		tree.threadedNodes();
//		System.out.println(node5.left);
//		tree.perOrder();
		tree.threadedShow();
	}

}

class Tree{
	private Node root;
	
	//为了实现线索化，需要创建一个指向当前节点的钱去节点的指针
	private Node pre = null;
	//设置根节点
	public void setRoot(Node root) {
		this.root = root;
	}
	public void threadedNodes() {
		this.threadedNodes(this.root);
	}
	//中序线索化之后的遍历方法
	public void threadedShow() {
		Node node = root;
		while(node != null) {
			while(node.getLeftType() == 0) {
				node = node.left;
			}
			
			//打印当前节点
			System.out.println(node);
			while(node.getRightType() == 1) {
				node = node.right;
				System.out.println(node);
			}
			node = node.right;
		}
	}
	//对二叉树进行中序线索化的方法
	public void threadedNodes(Node node) {
		
		//如果node==null，不能线索化
		if(node == null) {
			return;
		}
		//向左递归
		threadedNodes(node.left);
		
		if(node.left == null) {
			//当前节点的左指针指向pre
			node.left = pre;
			//修改当前节点的左指针类型，指向前驱动
			node.setLeftType(1);
		}
		
		if(pre != null && pre.right == null) {
			pre.right = node;
			pre.setRightType(1);
		}
		
		pre = node;
		//向右线索化
		threadedNodes(node.right);
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
	//1.如果leftType == 0表示指向的是左子树，如果1则表示指向前驱节点
	private int leftType;
	//rightType同理
	private int rightType;
	
	public int getLeftType() {
		return leftType;
	}

	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}

	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
	}

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