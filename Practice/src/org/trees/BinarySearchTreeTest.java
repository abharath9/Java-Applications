package org.trees;

public class BinarySearchTreeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree bst = new BinarySearchTree();
		bst.add(50);
		bst.add(25);
		bst.add(75);

		bst.add(10);
		bst.add(30);
		bst.add(60);
		bst.add(80);
		bst.add(85);
		bst.add(1);
		bst.add(100);

		bst.print();
	}

}
