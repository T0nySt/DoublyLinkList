// this is where the main method starts to test of DLL.
public class Main {
    public static void main(String[] args) {

        // this is where we can test out our methods and see how it operates.
        DoublyLinkList myDLL = new DoublyLinkList(0);
        myDLL.append(1);
        myDLL.append(2);

        // myDLL.remove(1);

        myDLL.printList();
    }
}