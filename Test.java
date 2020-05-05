两个单链表生成相加链表
public class AddLists {
    public Node addLists(Node head1,Node head2){
        Stack<Node> stack1=new Stack<>();
        Stack<Node> stack2=new Stack<>();
        while(head1!=null){
            stack1.push(head1);
            head1=head1.next;
        }
        while(head2!=null){
            stack2.push(head2);
            head2=head2.next;
        }
        Node head=null;
        int carry=0;
        while(!stack1.isEmpty()||!stack2.isEmpty()||carry!=0){
            int a=stack1.isEmpty()?0:stack1.pop().val;
            int b=stack2.isEmpty()?0:stack2.pop().val;
            int sum=a+b+carry;
            Node node=new Node(sum%10);
            node.next=head;
            head=node;
            carry=sum/10;
        }
        return head;
    }

    private static class Node{
        private int val;
        private Node next;
        public Node(int val){
            this.val=val;
        }
    }
}


相交链表的一系列问题
public class IntersectNode {

    public Node getIntersectNode(Node l1,Node l2){
        Node entranceL1=entranceOfCircle(l1);
        Node entranceL2=entranceOfCircle(l2);
        if(entranceL1==null&&entranceL2==null){
            return getFirstNoCircle(l1,l2);
        }else if(entranceL1!=null&&entranceL2!=null){
            return getFirstHasCircle(l1,l2,entranceL1,entranceL2);
        }
        return null;
    }

    private Node getFirstHasCircle(Node l1,Node l2,Node loop1,Node loop2){
        if(loop1==loop2){
            Node fast=l1;
            int m=0;
            while(fast!=loop1){
                fast=fast.next;
                m++;
            }
            Node slow=l2;
            int n=0;
            while(slow!=loop2){
                slow=slow.next;
                n++;
            }
            int len=m-n;
            fast=l1;
            slow=l2;
            if(len<0){
                len=-len;
                fast=l2;
                slow=l1;
            }
            while(len!=0){
                fast=fast.next;
                len--;
            }
            while(fast!=slow){
                fast=fast.next;
                slow=slow.next;
            }
            return fast;
        }else{
            Node cur=loop1.next;
            while(cur!=loop1){
                if(cur==loop2){
                    return loop1;
                }
                cur=cur.next;
            }
            return null;
        }
    }
    private Node getFirstNoCircle(Node l1,Node l2){
        int m=0;
        Node left=l1;
        while(left.next!=null){
            m++;
            left=left.next;
        }
        int n=0;
        Node right=l2;
        while(right.next!=null){
            n++;
            right=right.next;
        }
        if(left!=right){
            return null;
        }
        int len=m-n;
        left=l1;
        right=l2;
        if(len<0){
            len=-len;
            left=l2;
            right=l1;
        }
        while(len!=0){
            left=left.next;
            len--;
        }
        while(left!=right){
            left=left.next;
            right=right.next;
        }
        return left;
    }
    private Node entranceOfCircle(Node head){
        if(head==null||head.next==null||head.next.next==null){
            return null;
        }
        Node fast=head.next.next;
        Node slow=head.next;
        while(fast!=slow){
            if(fast.next==null||fast.next.next==null){
                return null;
            }
            fast=fast.next.next;
            slow=slow.next;
        }
        return fast;
    }

    private static class Node{
        private int val;
        private Node next;
        public Node(int val){
            this.val=val;
        }
    }
}


删除无序单链表中重复出现的节点
public class RemoveRep {

    //时间复杂度O(n)
    private void removeRep1(Node head){
        if(head==null) return ;
        Set<Integer> set=new HashSet<>();
        set.add(head.val);
        Node pre=head;
        Node cur=head.next;
        while(cur!=null){
            if(set.contains(cur.val)){
                pre.next=cur.next;
            }else{
                set.add(cur.val);
                pre=cur;
            }
            cur=cur.next;
        }
    }

    //空间复杂度O(1)
    private void removeRep2(Node head){
        if(head==null) return;
        Node cur=head;
        while(cur!=null){
            Node pre=cur;
            Node next=cur.next;
            while(next!=null){
                if(next.val==cur.val){
                    pre.next=cur.next;
                }else{
                    pre=next;
                }
                next=next.next;
            }
            cur=cur.next;
        }
    }
    private static class Node{
        private int val;
        private Node next;
        public Node(int val){
            this.val=val;
        }
    }
}
