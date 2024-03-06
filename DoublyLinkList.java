// A class that defines a DLL.
public class DoublyLinkList {

    // Declare private variables to store head, tail, and length of the list
    private Node head;
    private Node tail;
    private int length;

    // Constructor to initialize the doubly linked list with a single node
    public DoublyLinkList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    // Inner class representing a node in the doubly linked list
    class Node {
        int value;
        Node next;
        Node prev;

        // Constructor to initialize a node with a given value.
        Node(int value) {
            this.value = value;
        }
    }

    // Method to print the elements of the list.
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    // Method to print all details of the list including head, tail, and length.
    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nDoubly Linked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    // Method to make the list empty by resetting head, tail, and length.
    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    // Getter method to access the head node of the list.
    public void getHead() {
        System.out.println("Head: " + head.value);
    }

    // Getter method to access the tail node of the list.
    public void getTail() {
        System.out.println("Tail: " + tail.value);
    }

    // Getter method to retrieve the length of the list.
    public void getLength() {
        System.out.println("Length: " + length);
    }

    // Method to append/add a new node with the given value at the end of the list.
    public void append(int value) {

        // we first create a node with a given value.
        Node newNode = new Node(value);

        // we check to see if the list is empty, if it is, make head and tail point to
        // the new node.
        if (head == null) {
            head = newNode;
            tail = newNode;

            // and if there is an item in the list.
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        // finally we have to increase the length by 1 with the node we added.
        length++;
    }

    // this method allows us to remove the last node in the list.
    public Node removeLast() {
        // edge case 1: if the list is empty.
        if (length == 0)
            return null;
        // after we check, we create a temp node and make it point to tail.
        Node temp = tail;

        // edge case 2: if we have 1 item in the list.
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            // if we have more then 1 item in the list.
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }
        // finally we decrement and just return the removed node.
        length--;
        return temp;
    }

    // this method allows us to prepend/add a node at the start of the link list.
    public void prepend(int value) {

        // we create a node with a given value.
        Node newNode = new Node(value);

        // we check if the list is empty.
        if (length == 0) {
            head = newNode;
            tail = newNode;
            // if theres more than 1 item in the list.
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        // after adding the node we just increase the length by 1.
        length++;
    }

    // this method allows us to remove a node at the start of the link list.
    public Node removeFirst() {
        // edge case 1: we check to see if the list has no items.
        if (length == 0)
            return null;
        // if its not empty, we create a temporary node pointing at head.
        Node temp = head;

        // edge case 2: we check if theres 1 item in the list.
        if (length == 1) {
            head = null;
            tail = null;
            // if theres more then one item in the list.
        } else {
            head = head.next;
            head.prev = null;
            temp.next = null;
        }
        // once we finsih with the else statement we just decrement the length by 1 with
        // the node we removed and just return temp.
        length--;
        return temp;
    }

    // this method is to obtain a node at a particular index.
    public Node get(int index) {
        // edge case 1: check if the index in withen bounds.
        if (index < 0 || index >= length)
            return null;
        // after we check, we want to create a temporary node at head.
        Node temp = head;

        // Because we have a link list that can back track, we can create an efficient
        // way to find indexes withen a list by checking the first half of a list.
        if (index < length / 2) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            // if its not in the first half of the list, we make the temp node point to tail
            // and check backwards.
        } else {
            temp = tail;
            for (int i = length - 1; i > index; i--) {
                temp = temp.prev;
            }
        }
        // once we find the node of our choice we just return temp.
        return temp;
    }

    // this method allows us to check if we can change a value at a particular
    // index.
    public boolean set(int index, int value) {
        // we create a temp node that gets us the index we want to change.
        Node temp = get(index);
        // iterate through the list until we reach null.
        if (temp != null) {
            // while we are iterating, we'll change the value with the index we want to
            // change.
            temp.value = value;
            // and once we change we just return true and exit the if statement.
            return true;
        }
        // if we did not find the index, we'll leave the if statement and just return
        // false.
        return false;
    }

    // this method allows us to insert a node with a particular value at a
    // particular index.
    public boolean insert(int index, int value) {
        // edge case 1: check if index is withen bounds.
        if (index < 0 || index > length)
            return false;

        // if the index is at start, we created a prepend method and just return true.
        if (index == 0) {
            prepend(value);
            return true;
        }

        // if the index is at the end, we create a append method and just return true.
        if (index == length) {
            append(value);
            return true;
        }

        // if we wanna insert in the middle, we create a new node.
        Node newNode = new Node(value);

        // we create a before node at the index before the new node we create.
        Node before = get(index - 1);

        // and we create a after node that is in front of the before node.
        Node after = before.next;

        // we make the newNode point backwards to before.
        newNode.prev = before;
        // we'll make the newNode point forward to after.
        newNode.next = after;
        // then we'll make the before node point to our newNode.
        before.next = newNode;
        // and finally, our after node well point backwards to our newNode.
        after.prev = newNode;

        // after all that, we just increase the length by 1 and return true.
        length++;
        return true;
    }

    // this method allows us to remove a node at a particular index.
    public Node remove(int index) {
        // we'll check if the index is withen bounds.
        if (index < 0 || index >= length)
            return null;

        // if the index is at the start, we'll just call the removeFirst method.
        if (index == 0)
            return removeFirst();

        // if the index is at the end, we'll call the removeLast method.
        if (index == length - 1)
            return removeLast();

        // we create a temp node and use the get method to where we're trying to remove
        // a node.
        Node temp = get(index);

        // we trying to remove the temp node while having the node behind it and in
        // front of it point to eachother.
        temp.next.prev = temp.prev;
        temp.prev.next = temp.next;

        // once have have the nodes point to eachother after removing the temp node,
        // we'll just set temp to equal null of both sides.
        temp.next = null;
        temp.prev = null;

        // the removed temp node decreeses the length by 1 and we just return temp.
        length--;
        return temp;
    }
}
