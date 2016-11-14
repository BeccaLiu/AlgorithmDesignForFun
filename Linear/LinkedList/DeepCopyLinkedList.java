package Linear.LinkedList;

/**
 * Created by rliu on 11/13/16.
 * TODO:
 */
public class DeepCopyLinkedList {
    public static void simpleCopy(Node head){
        if(head==null)
            return;
        Node dummy=new Node(0);
        Node pre=dummy;
        while(head!=null){
            Node curr=new Node(head.val);
            pre.next=curr;
            head=head.next;
            pre=pre.next;
        }
        System.out.println(dummy.next);
    }

    public static void deepCopy(Node head){

    }

    public static void main(String[] args) {
        int[] arr={1,2,3,4,5};
        Node head=new Node(arr);
        simpleCopy(head);


    }
}
