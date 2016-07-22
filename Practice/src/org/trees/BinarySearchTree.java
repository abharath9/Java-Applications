package org.trees;

class BinarySearchTreeNode {
	int data;
	BinarySearchTreeNode left;
	BinarySearchTreeNode right;

	BinarySearchTreeNode(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}

public class BinarySearchTree {
	BinarySearchTreeNode root;

	/////////////////////////////////////////
	// Insert data///////////////////////////

	public void add(int data) {
		BinarySearchTreeNode nodeToAdd = new BinarySearchTreeNode(data);

		if (root == null) {
			root = nodeToAdd;
			return;
		}
		traverseAndAddNode(root, nodeToAdd);

	}

	public void traverseAndAddNode(BinarySearchTreeNode parentNode, BinarySearchTreeNode nodeToAdd) {

		if (nodeToAdd.data < parentNode.data) {
			if (parentNode.left == null) {
				parentNode.left = nodeToAdd;
				return;
			} else {
				traverseAndAddNode(parentNode.left, nodeToAdd);
			}

		} else {
			if (parentNode.right == null) {
				parentNode.right = nodeToAdd;
				return;
			} else {
				traverseAndAddNode(parentNode.right, nodeToAdd);
			}
		}
	}

	/////////////////////////////////////////
	// print data///////////////////////////
	void print() {
		if (root == null) {
			System.out.println("BST is empty");
			return;
		}

		BinarySearchTreeNode nodeToTraverse = root;
		if (root.left == null && root.right == null) {
			System.out.print(root.data + " ");
			return;
		}
		if (nodeToTraverse.left != null) {
			inorderTraversal(nodeToTraverse.left);
		}
		System.out.print(nodeToTraverse.data + " ");
		if (nodeToTraverse.right != null) {
			inorderTraversal(nodeToTraverse.right);
		}

	}

	void inorderTraversal(BinarySearchTreeNode node) {

		if (node.left != null) {
			inorderTraversal(node.left);
		}
		System.out.print(node.data + " ");

		if (node.right != null) {
			inorderTraversal(node.right);
		}

	}

}
