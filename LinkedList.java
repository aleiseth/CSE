
// A simple Java program for traversal of a linked list
public class LinkedList {
	Node head; // head of list
	Node tail; 

	/* Linked list Node. This inner class is made static so that
	main() can access it */
	static class Node {
		int data;
		Node next;
		
		// Constructor 
		Node(int d)
		{
			this.data = d;
			next = null;
		} 
	}
// FUNCTIONS:
	
	// FIND NODE
	public Node findNode (int key) {
		Node lookingFor = null;
		Node n = head;
		if (head.data == key) {
			lookingFor = head;
		} else {
			while (n.data != key) {
				n = n.next;
				if (n.data == key) {
					lookingFor = n;
				}
			}
		}
		return lookingFor;
	}
	// FIND PREVIOUS NODE
	public Node findPreviousNode (int key) {
		Node lookingFor = null;
		Node n = head;
		if (head.data == key) {
			lookingFor = head;
		} else {
			while (n.next.data != key) {
				n = n.next;
				if (n.next.data == key) {
					lookingFor = n;
				}
			}
		}
		// System.out.print(lookingFor.data);
		return lookingFor;
	}
	// FIND NEXT NODE
	public Node findNextNode (int key) {
		Node lookingFor = null;
		Node n = head;
		if (head.data == key) {
			lookingFor = head;
		} else {
			while (n.data != key) {
				n = n.next;
				if (n.data == key) {
					lookingFor = n.next;
				}
			}
		}
		System.out.print(lookingFor.data);
		return lookingFor;
	}
	// FIND LAST NODE
	public Node findLast() {
		Node n = head;
		Node last = null;
		while (n.next != null) {
			n = n.next;
			if (n.next == null) {
			last = n;	
			}
		}
		return last;
	}
	// ADD TO HEAD
	public void addtoHead (Node x) {
		x.next = head;
		head = x;
	}
	// DELETE HEAD
	public void deleteHead() {
		llist.head = llist.head.next;
	}
	// INSERT NODE AFTER A GIVEN VALUE
	public void insertAfter(int key, Node toAdd) {
		Node previous = findNode(key);
		toAdd.next = previous.next;
		previous.next = toAdd;
		
	}
	// REMOVE NODE
	public void removeNode (int key) {
		Node temp = head, prev = null;
		if (temp != null && temp.data == key) {
				head = temp.next;
				return;
			}
			while (temp != null && temp.data != key) {
				prev = temp;
				temp = temp.next;
			}
			if (temp == null)
				return;
		
			prev.next = temp.next;
	}
	// ADD TO TAIL
	public void addtoTail (Node toAdd) {
		Node previous = findLast();
		previous.next = toAdd;
		llist.tail = toAdd;
	}
	// DELETE AFTER
	public void deleteAfter(int key) {
	Node after = findNextNode(key);
	Node current = findNode(key);
	current.next = after.next;
	}
	// LIST LENGTH
	public static Integer listLength(LinkedList llist) {
		Node n = llist.head;
		int count = 0;
		while (n.next != null) {
			n = n.next;
			count++;
		}
		return count + 1;
	}
	// MERGE LISTS
	public static LinkedList combined = new LinkedList();
	public static void mergeLists(LinkedList a, LinkedList b) {
		Node nodeA = a.head;
		Node nodeB = b.head;
		Node start = combined.head;
		while (nodeA != null || nodeB != null) {
			start = nodeA;
			nodeA = nodeA.next;
			start.next = nodeB;
			nodeB = nodeB.next;
			start = start.next.next;
		}
	}

	// PRINT LIST
	public void printList()
	{
		Node n = head;
		while (n != null) {
			System.out.print(n.data + " ");
			n = n.next;
		}
		System.out.println();

	}
	
	public static LinkedList llist = new LinkedList();
	public static LinkedList llist2 = new LinkedList();
	
	public static void main(String[] args)
	{
		llist.head = new Node(1);
		Node second = new Node(2);
		Node third = new Node(4);
		llist.tail = new Node(5);

		llist.head.next = second; 
		second.next = third; 
		third.next = llist.tail;
		
		llist2.head = new Node(7);
		Node two = new Node(11);
		Node three = new Node(3);
		llist2.tail = new Node(6);
		
		llist2.head.next = two;
		two.next = three;
		three.next = llist2.tail;
		
		// print list
		System.out.println("Print lists: ");
		System.out.print("List 1: ");
		llist.printList();
		System.out.print("List 2: ");
		llist2.printList();		
		
		// merge lists
		System.out.print ("Merged lists: ");
		// mergeLists(llist, llist2);
		// combined.printList();
		
		Node x = new Node(7);
		// add a node to the head
		 System.out.println("Add node x to the head: ");
		llist.addtoHead(x);
		llist.printList();
		
		Node y = new Node(9);
		// add a node to the tail
		System.out.println("Add node x to the tail: ");
		llist.addtoTail(y);
		llist.printList();
		
		// find a node by value
		System.out.println("Find a node by value: ");
		System.out.println(llist.findNode(1).data);
		
		// delete head
		System.out.println("Delete the head of the list: ");
		llist.deleteHead();
		llist.printList();
		
		// remove node with given value
		System.out.print("Remove node with given value: ");
		llist.removeNode(2);
		llist.printList();
		
		Node z = new Node(8);
		// insert a node after a node with a certain value
		System.out.print("Insert after a given value: ");
		llist.insertAfter(1, z);
		llist.printList();
		
		// delete node after a node with a certain value
		System.out.print("Delete after a given value: ");
		llist.deleteAfter(8);
		llist.printList();
	}
}
