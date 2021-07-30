package com.ncu.avltree;

public class AVLTreeDemo {

	public static void main(String[] args) {
		int[] arr = { 6, 5, 9, 7, 8, 10 };
		AVLTree tree = new AVLTree();
		for (int i = 0; i < arr.length; i++) {
			tree.add(new Node(arr[i]));
		}

		tree.midOrder();
		Node root = tree.getRoot();
		System.out.println("root height => " + root.height());
		System.out.println("left height => " + root.leftHeight());
		System.out.println("right height => " + root.rightHeight());

	}
}

class AVLTree {
	private Node root;

	public void add(Node node) {
		if (root == null) {
			root = node;
			return;
		}
		root.add(node);
	}

	public void midOrder() {
		if (root == null) {
			System.out.println("the tree is empty");
			return;
		}
		root.midOrder();
	}

	public Node getRoot() {
		return this.root;
	}

}

class Node {
	private int value;
	private Node left;
	private Node right;

	public Node(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	/**
	 * tree的高度
	 * 
	 * @return
	 */
	public int height() {
		return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
	}

	/**
	 * 左子树高度
	 * 
	 * @return
	 */
	public int leftHeight() {
		if (left == null) {
			return 0;
		}
		return left.height();
	}

	/**
	 * 右子树高度
	 * 
	 * @return
	 */
	public int rightHeight() {
		if (right == null) {
			return 0;
		}
		return right.height();
	}

	/**
	 * 前序遍历
	 */
	public void midOrder() {
		if (this.left != null) {
			this.left.midOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.midOrder();
		}
	}

	/**
	 * 向树中添加节点
	 * 
	 * @param node
	 */
	public void add(Node node) {
		if (node == null) {
			return;
		}

		if (node.value < this.value) {
			if (this.left == null) {
				this.left = node;
			} else {
				this.left.add(node);
			}
		} else {
			if (this.right == null) {
				this.right = node;
			} else {
				this.right.add(node);
			}
		}

		if (this.rightHeight() - this.leftHeight() > 1) {

			if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
				this.rightSpin();
			}
			this.leftSpin();
			return;
		}

		// 右旋
		if (this.leftHeight() - this.rightHeight() > 1) {
			// 根节点左子树的右子树高度大于其左子树
			if (this.left != null && this.left.rightHeight() > left.leftHeight()) {
				// 对根节点左子树进行左旋
				this.left.leftSpin();
			} 
			// 在对根节点进行右旋
			this.rightSpin();
		}
	}

	/**
	 * 左旋转
	 */
	private void leftSpin() {

		// 以当前节点的值，创建新的节点
		Node newNode = new Node(this.value);
		// 把新的结点的左子树设置成当前节点的左子树
		newNode.setLeft(this.left);
		// 把新节点的右子树设置成当前节点的右子树的左子树
		newNode.setRight(this.right.left);
		// 把当前节点的值换成右子节点的值
		this.value = this.right.value;
		// 把当前节点的右子树设置成当前节点右子树的右子树
		this.right = this.right.right;
		// 把当前节点的左子节点设置成新的节点
		this.left = newNode;
	}

	/**
	 * 右旋转
	 */
	private void rightSpin() {
		Node newNode = new Node(this.value);
		newNode.setRight(this.right);
		newNode.setLeft(this.left.right);
		this.value = this.left.value;
		this.left = this.left.left;
		this.right = newNode;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

}
