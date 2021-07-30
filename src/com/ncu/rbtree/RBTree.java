package com.ncu.rbtree;

/**
 * 插入后修复红黑树平衡的方法
 * 		|---情景1：红黑树为空，将根节点染色为黑
 * 		|---前景2：插入节点的key已经存在，不需要处理，只需要替换value
 * 		|---情景3：插入节点的父节点为黑色，初始插入的节点颜色为红色，
 * 				  所以路径上的黑色节点数量并没有变化，二叉树依然平衡，不需要处理
 * 		|---情景4：插入节点的父节点为红色
 * 				|---情景4.1：叔叔结点存在，并且为红色（父-叔 双红），将父节点和叔叔节点染为黑色
 * 							将爷爷节点染为红色，并且再以父节点为当前节点，进行下一轮处理
 * 				|---情景4.2：叔叔节点不存在，或者为黑色，父节点为爷爷节点的左子节点
 * 							|---情景4.2.1：插入节点为其父节点的左子节点（LL）
 * 								将父节点染黑，将爷爷节点染红，然后以爷爷节点右旋
 * 							|---情景4.2.2：插入节点为其父节点的右子节点（LR）
 * 								以父节点进行一次左旋，得到LL双红的情景（4.2.1），
 * 								然后指定父节点为当前节点进行下一轮处理
 * 				|---情景4.3：叔叔节点不存在，或者为黑色，父节点为爷爷节点的右子节点
 * 							|---情景4.3.1：插入节点为其父节点的右子节点（RR）
 * 								将父节点染为黑色，将爷爷节点染红，在以爷爷节点进行左旋
 * 							|---情景4.3.2：插入节点为其父节点的左子节点（RL）
 *								以父节点进行一次右旋，得到LL双红的情景（4.3.1），
 * 	 * 							然后指定父节点为当前节点进行下一轮处理
 */

public class RBTree<K extends Comparable<K>, V> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private RBNode<K, V> root;
	
	
	public RBTree() {
		
	}
	
	/**
	 *    p             p
	 *    |             | 
	 *    x             y
	 *   / \     =>    / \ 
	 *  lx  y         x  lx
	 *     / \       / \ 
	 *    ly lx     lx ly
	 * @param x
	 */
	private void leftRotate(RBNode<K, V> x) {
		RBNode<K, V> y = x.right;
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}
		if (x.parent != null) {
			y.parent = x.parent;
			if (x == x.parent.left) {
				x.parent.left = y;
			} else {
				x.parent.right = y;
			}
		} else {
			this.root = y;
		}
		x.parent = y;
		y.left = x;
	}

	/**
	 *     p             p
	 *     |             |
	 *     y             x
	 *    / \     =>    / \
	 *   x  ry         lx  y
	 *  / \               / \
	 * lx  rx            rx ry
	 * @param y
	 */
	private void rightRotate(RBNode<K, V> y) {
		RBNode<K, V> x = y.left;
		y.left = x.right;
		if (x.right != null) {
			x.right.parent = y;
		}

		if (y.parent != null) {
			x.parent = y.parent;
			if (y.parent.left == y) {
				y.parent.left = x;
			} else {
				y.parent.right = x;
			}
		} else {
			this.root = y;
		}

		y.parent = x;
		x.right = y;
	}

	public void insert(K key, V value) {
		RBNode<K, V> node = new RBNode<>();
		node.setKey(key);
		node.setValue(value);
		node.setColor(RED);
		insert(node);
	}

	private void insert(RBNode<K, V> node) {
		RBNode<K, V> parent = null;
		RBNode<K, V> x = this.root;
		while (x != null) {
			parent = x;
			int cmp = node.key.compareTo(x.key);
			if (cmp > 0) {
				x = x.right;
			} else if (cmp == 0) {
				x.setValue(node.value);
				return;
			} else {
				x = x.right;
			}
		}

		node.parent = parent;
		if (parent != null) {
			int cmp = node.key.compareTo(parent.key);
			if (cmp > 0) {
				parent.right = node;
			} else {
				parent.left = node;
			}
		} else {
			this.root = node;
		}
		insertFixUp(node);
	}

	private void insertFixUp(RBNode<K, V> node) {
		this.root.setColor(BLACK);
		RBNode<K, V> parent = parentOf(node);
		RBNode<K, V> gParent = parentOf(parent);
		if (isRed(parent)) {
			RBNode<K, V> uncle = null;
			if (parent == gParent.left) {
				uncle = gParent.right;
				if (uncle == null || isBlack(uncle)) {
					if (node == parent.left) { // 处理 4.2.1
						setBlack(parent);
						setRed(gParent);
						insertFixUp(gParent);
					} else { // 处理 4.2.2
						leftRotate(parent);
						insertFixUp(parent);
					}
					return;
				}
			} else {
				uncle = gParent.left;
				if (uncle == null || isBlack(uncle)) {
					if (node == parent.right) { // 处理 4.3.1
						setBlack(parent);
						setRed(gParent);
						insertFixUp(gParent);
					} else { // 处理 4.3.2
						rightRotate(parent);
						insertFixUp(parent);
					}
					return;
				}
			}
			if (isRed(uncle)) { // 处理 4.1
				setBlack(parent);
				setBlack(uncle);
				setRed(gParent);
				insertFixUp(parent);
			}
		}
	}




	private RBNode<K, V> parentOf(RBNode<K, V> node) {
		if (node != null) {
			return node.parent;
		}
		return null;
	}
	
	private boolean isRed(RBNode<K, V> node) {
		if (node != null) {
			return node.color == RED;
		}
		return false;
	}
	
	private void setRed(RBNode<K, V> node) {
		if (node != null) {
			node.color = RED;
		}
	}
	
	private boolean isBlack(RBNode<K, V> node) {
		if (node != null) {
			return node.color == BLACK;
		}
		return false;
	}
	
	private void setBlack(RBNode<K, V> node) {
		if (node != null) {
			node.color = BLACK;
		}
	}
	
	public void inOrderPrint() {
		inOrderPrint(root);
	}
	
	private void inOrderPrint(RBNode<K, V> root) {
		if (root == null)
			return;
		inOrderPrint(root.left);
		System.out.println(root.key + " -> " + root.value + " -> " + (root.color ? "r" : "b"));
		inOrderPrint(root.right);
	}
	
	
	
	static class RBNode<K extends Comparable<K>, V> {
		private RBNode<K, V> parent;
		private RBNode<K, V> left;
		private RBNode<K, V> right;
		private K key;
		private V value;
		private boolean color;

		public RBNode() {

		}

		public RBNode(RBNode<K, V> parent, RBNode<K, V> left, RBNode<K, V> right, K key, V value, boolean color) {
			super();
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.key = key;
			this.value = value;
			this.color = color;
		}
		public RBNode<K, V> getParent() {
			return parent;
		}
		public void setParent(RBNode<K, V> parent) {
			this.parent = parent;
		}
		public RBNode<K, V> getLeft() {
			return left;
		}
		public void setLeft(RBNode<K, V> left) {
			this.left = left;
		}
		public RBNode<K, V> getRight() {
			return right;
		}
		public void setRight(RBNode<K, V> right) {
			this.right = right;
		}
		public K getKey() {
			return key;
		}
		public void setKey(K key) {
			this.key = key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public boolean isColor() {
			return color;
		}
		public void setColor(boolean color) {
			this.color = color;
		}
		
		
	}
}
