package com.ncu.binarytree;

public class BinarySortTreeDemo {

	public static void main(String[] args) {
		BinarySortTree tree = new BinarySortTree();
		int[] arr = {1, 2};
		for (int i = 0; i < arr.length; i++) {
			tree.add(new Node(arr[i]));
		}
		tree.midOrder();
		tree.delete(1);
		tree.delete(2);
		System.out.println("======");
		tree.midOrder();


	
	}
}

class BinarySortTree {
	
	private Node root;
	
	public void add(Node node) {
		if (root == null) {
			root = node;
		} else {
			root.add(node);
		}
	
	}
	
	public void midOrder() {
		if (root != null) {
			root.midOrder();
		} else {
			System.out.println("the tree is empty");
		}
	}
	
	public Node getOne(int value) {
		if (root == null)
			return null;
		return root.getOne(value);
	}
	
	public Node getParent(int value) {
		if (root == null)
			return null;
		return root.getParent(value);
	}
	
	public void delete(int value) {
		if (root == null) {
			System.out.println("the tree is empty");
			return;
		} else {
			Node target = getOne(value);
			if (target == null) {
				System.out.println("the node is empty");
				return;
			}
			
			// tree只有一个根节点
			if (root.getLeft() == null && root.getRight() == null) {
				root = null;
				return;
			}
			
			Node parent = getParent(value);
			
			// 删除叶子节点
			if (target.getLeft() == null && target.getRight() == null) {
				
				
				
				// 根据value的大小判断，target为parent的左子节点还是右子节点
				if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
					parent.setLeft(null);
				} else if (parent.getRight() != null && parent.getRight().getValue() == value) {
					parent.setRight(null);
				}
			
			// 此时target有两个子树
			} else if (target.getLeft() != null && target.getRight() != null) {
				// 在target节点的右子树中找到最小的节点
				int deleteMin = deleteMin(target.getRight());
				// 将最小节点的值赋给target
				target.setValue(deleteMin);
				
			} else { // 在target有只有一个子树的情况下
				
				
				if (target.getLeft() != null) { // 左子树
					
					if (parent != null) {
						if (parent.getValue() == value) {
							parent.setLeft(target.getLeft());
						} else {
							parent.setRight(target.getLeft());
						}
					} else {
						root = target.getLeft();
					}
					
				} else { // 右子树
					if (parent != null) {
						// 判断target是parent的左节点还是右节点
						if (parent.getValue() == value) {
							parent.setLeft(target.getRight());
						} else {
							parent.setRight(target.getRight());
						}
					} else {
						root = target.getRight();
					}
					 
				}
				
			}
			
			
			
			
		}
	}
	
	// 找到以node为根节点的右子树中最小的值，并删除该节点
	public int deleteMin(Node node) {
		Node target = node;
		
		// 找到最左的节点（即最小的节点）
		while (target.getLeft() != null) {
			target = target.getLeft();
		}
		delete(target.getValue());
		return target.getValue();
	}
}

/**
 * 一个节点
 * @author 10374
 *
 */
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
	 * 向树中添加节点
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
	 * 找到一个值为value的节点
	 * @param value
	 * @return
	 */
	public Node getOne(int value) {
		
		if (value == this.value) {
			return this;
			
		} else if (value < this.value) {
			
			if (this.left == null) {
				return null;
			} else {
				return this.left.getOne(value);
			}
		} else {
			
			if (this.right == null) {
				return null;
			} else {
				return this.right.getOne(value);
			}
		}
	}
	
	/**
	 * 获取值为value节点的父节点
	 * @param value
	 * @return
	 */
	public Node getParent(int value) {
		if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		} else {
			if (this.left != null && this.value > value) {
				return this.left.getParent(value);
			} else if (this.right != null && this.value <= value) {
				return this.right.getParent(value);
			} else {
				return null;
			}
		}
	}
	
	
	@Override
	public String toString() {
		return String.valueOf(this.value);
	}
}
