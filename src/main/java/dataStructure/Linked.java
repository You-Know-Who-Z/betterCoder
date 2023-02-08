package dataStructure;

/**
 * @author zhao
 */
public class Linked {
    Node head;
    Node tail;
    int size;
    class Node {
        Node parent;
        Node next;
        int val;
        public Node(int val) {
            this.val = val;
        }
    }
    private Linked() throws Exception {
        throw new Exception("未初始化");
    }

    public Linked(Node head) throws Exception {
        this.head = head;
    }
}
