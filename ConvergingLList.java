
public class ConvergingLList {
	
	public static LinkedList llist = new LinkedList();
	public static LinkedList llist2 = new LinkedList();
	
	public static void main(String[] args) {
		
		
	Node second = new Node(3);
	Node third = new Node(5);
	Node fourth = new Node(7);
	Node fifth = new Node(9);
	llist.tail = new Node(11);
		
	llist.head = second; // Link first node with the second node
	second.next = third; // Link second node with the third node
	third.next = fourth;
	fourth.next = fifth;
	fifth.next = llist.tail;
	
	System.out.println("List 1: ");
	llist.printList();
	
	llist2.head = new Node(2);
	Node two = new Node(4);
	Node three = new Node(6);
	llist2.tail = new Node(12);
	
	llist2.head.next = two;
	two.next = three;
	three.next = llist2.tail;
	
	System.out.println();
	System.out.println("List 2: ");
	llist2.printList();
	
	int x = 7;
	Node selectedNode = llist.findNode(x);
	
	listCombine(selectedNode);

	System.out.println();
	
	System.out.println("Combined Lists: ");	
	llist.printList();
	System.out.println();
	llist2.printList();

	System.out.println();
	System.out.print("Intersection node: ");
	System.out.println();
	Node intersect = findIntersect(llist.head, llist2.head);
	System.out.println(intersect.data);
	
}
// CREATE DOUBLE HEADED LIST AT SELECTED NODE	
	public static void listCombine (Node selectedNode) {
		llist2.tail.next = selectedNode;
		llist2.tail = llist.tail;
	}
	
// FIND INTERSECTION NODE
	public static Node findIntersect (Node a, Node b)
	{
		while (b != null) {
			Node current = a;
			while (current != null) {
				if (current == b) {
					return b;
				}
				current = current.next;
			}
			b = b.next;
		}
		return null;
	}
	
}

class Node {
	int data;  // data to be stored in node
	Node next; // points to next node of the list
	
	// Constructor 
	Node(int d){
		this.data = d;
		next = null;
	} 
}

// Linked list 
class LinkedList {
	Node head; // head of list
	Node tail;
	
	// Constructor 
    LinkedList(){
        head = null;
			tail = null;
    }
    
    // Prints the entire list starting from the head
    public void printList()
    {
    	Node n = head;
    	while (n != null) {
    		System.out.print(n.data + " ");
    		n = n.next;
    	}
    }
	
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
public void push(Node node)
{
		node.next = head;
		head = node;
}

}
	