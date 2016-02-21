/*
 * Author : Gopinatha Mishra (gxm141630)
 * References : SinglyLinkedList (Unzip) by Professor Balaji Raghavachari 
 */

import java.io.Console;
import java.util.*;


public class SinglyLinkedList_MultiZip<T> {
    public class Entry<T> {
        T element;
        Entry<T> next; //object of the current class

        //super method
        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
    }

    Entry<T> header, tail;
    int size;

    SinglyLinkedList_MultiZip() {
        header = new Entry<>(null, null);
        tail = null;
        size = 0;
    }

    void add(T x) {
        if(tail == null) {
            header.next = new Entry<>(x, header.next);
            tail = header.next;
        } else {
            tail.next = new Entry<>(x, null);
            tail = tail.next;
        }
	size++;
    }

    void printList() {
        Entry<T> x = header.next;
        while(x != null) {
            System.out.print(x.element + " ");
            x = x.next;
        }
        System.out.println();
    }

    void multiZip(int zip) {
	if(size < 3) {  // Too few elements.  No change.
	    return;
	}

	Entry<T> head = header.next;
	Entry<T> c = head.next;
	int state = 0;
	int n = zip;
	
	Entry<T>[] HeadOfStates = new Entry[n]; 
	Entry<T>[] TailOfStates = new Entry[n];

	for(int count = 0; count <= n-1; count ++)
	{
		HeadOfStates[count] = head;
		TailOfStates[count] = HeadOfStates[count];
		head = head.next;
		c=head;
	}
	
	//Invariant: HeadOfStates[], points the starting node(head) of  each state 
	//Invariant: TailOfStates[], used for adding new nodes to the states and points to the last node(tail) of each state.
	// c is current element to be processed.
	// state indicates the state of the finite state machine
	// state value indicates that the current element is added after taili (i=0,1 ... n). Where n is the multiZip value
	state  = 0;
	while(c != null) {
		TailOfStates[state].next = c;
		TailOfStates[state] = c;
	    c = c.next;
	    state++;
	    if(state == n)
	    	state = 0;   
		}
	
	
		//After forming all the States, combining the nodes of all the states correspondingly:
		// The last node of state 1 points to the first node of state 2 and so on...
		// Finally the last node of the last State(n) is set to null.
		int count = 0;
		while(count < n-1)
		{	 
			TailOfStates[count].next = HeadOfStates[count+1];
			count++;
		}
		TailOfStates[n-1].next = null;
	
    }

    public static void main(String[] args) {
        int n = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Please Enter the size of the list : value should be greater than 3");     
        
        int listSize = Integer.parseInt(input.nextLine());
        
        if(listSize <= 3)
        {
        	System.out.println("Invalid size of the list : value should be greater than 3, Please re-enter the value");     
            listSize = Integer.parseInt(input.nextLine());
        }
        
        System.out.println("Please Enter the multiZip Value : ");     

        int mzip = Integer.parseInt(input.nextLine());   
        
        
     
        
        if(mzip >= listSize)
        {
        	System.out.println("Please re-Enter the multiZip Value : value should be less than listSize");     
        	mzip = Integer.parseInt(input.nextLine());
        }
        
        
        SinglyLinkedList_MultiZip<Integer> lst = new SinglyLinkedList_MultiZip<>();
        for(int i=1; i<=listSize; i++) {
            lst.add(new Integer(i));
        }
        
        System.out.println("\nList of Values \"Before MultiZip\" :\n");
        lst.printList();
        System.out.println("\nList of Values \"After MultiZip\" :\n");
        lst.multiZip(mzip);
        lst.printList();
    }
}

/* Sample output:
 * 
 * Please Enter the size of the list : value should be greater than 3
 * 17
 * Please Enter the multiZip Value : 
 * 4
 * List of Values "Before MultiZip" :
 * 
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 
 * 
 * List of Values "After MultiZip" :
 * 
 * 1 5 9 13 17 2 6 10 14 3 7 11 15 4 8 12 16 
*/