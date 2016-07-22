class Node {
	Node next;
	int num;

	public Node(int val) {
		num = val;
		next = null;
	}
}

class LinkedList {

	private Node head = null;

	public void append(int val) {
		Node lastNode = getLastNode();
		if (lastNode == null) {
			head = new Node(val);
		} else {
			lastNode.next = new Node(val);
		}
	}

	public void delete(int val) {
		if (head == null) {
			return;
		}

		Node prevNode = null;
		Node currNode = head;
		while (currNode != null && currNode.num != val) {
			prevNode = currNode;
			currNode = currNode.next;
		}

		if (prevNode == null) {
			head = head.next;
			return;
		}

		if (currNode == null) {
			System.out.println("A node with that value does not exist.");
			return;
		}
		prevNode.next = currNode.next;
	}

	private int cnt = 1;

	public void deleteAll(int val) {
		if (head == null) {
			return;
		}

		Node prevNode = null;
		Node currNode = head;
		while (currNode != null) {
			if (currNode.num == val) {
				prevNode.next = currNode.next;
				currNode = currNode.next;
			} else {
				prevNode = currNode;
				currNode = currNode.next;
			}
		}

		if (prevNode == null) {
			head = head.next;
			return;
		}

		if (currNode == null) {

			System.out.println("Value not found");
			return;
		}

	}

	public void print() {
		System.out.println("");
		if (head == null) {
			System.out.print("EMPTY");
			return;
		}
		Node tmpNode = head;
		while (tmpNode != null) {
			System.out.print(tmpNode.num + " -> ");
			tmpNode = tmpNode.next;
		}
	}

	public void midElement() {
		System.out.println("\n");
		int cnt = 0;
		if (head == null) {
			System.out.println("list is empty");
			return;
		}
		if (head.next == null) {
			System.out.println("list has only one element");
			return;
		}
		Node tempFast = head;
		Node tempSlow = head;
		while (tempFast.next != null) {
			cnt++;
			if (tempFast.next.next != null) {

				tempFast = tempFast.next.next;
				tempSlow = tempSlow.next;
			} else {
				tempFast = tempFast.next;
			}
		}

		if (tempFast.next == null) {
			System.out.println(cnt);
			System.out.println(tempSlow.num);
		} else {
			System.out.println(cnt);
			System.out.println(tempSlow.next.num);
		}
	}

	private Node getLastNode() {
		if (head == null) {
			return null;
		}
		Node tmpNode = head;
		while (tmpNode.next != null) {
			tmpNode = tmpNode.next;
		}
		return tmpNode;
	}

	public static void main(String[] args) {
		LinkedList myList = new LinkedList();
		myList.print();
		myList.append(35);
		myList.append(33);
		myList.append(39);
		myList.append(34);
		myList.append(37);
		myList.append(40);
		// myList.print();

		// myList.deleteAll(33);
		// myList.delete(35);
		// myList.delete(35);
		myList.print();
		myList.midElement();
	}
}