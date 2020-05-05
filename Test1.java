给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/validate-binary-search-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    private boolean isValidBST(TreeNode root,long min,long max){
        if(root==null) return true;
        if(root.val<=min||root.val>=max){
            return false;
        }
        return isValidBST(root.left,min,root.val)&&isValidBST(root.right,root.val,max);
    }
}

将二叉搜索树转为双向链表
public class TreeToDoubleList {
    private ListNode pre;
    public ListNode treeToDouble(ListNode root){
        ListNode first=root;
        while(first.left!=null){
            first=first.left;
        }
        ListNode last=root;
        while(last.right!=null){
            last=last.right;
        }
        transform(root);
        first.left=last;
        last.right=first;
        return first;
    }
    private void transform(ListNode root){
        if(root==null) return;
        transform(root.left);
        root.left=pre;
        if(pre!=null){
            pre.right=root;
        }
        pre=root;
        transform(root.right);
    }
    private static class ListNode {
        int val;
        ListNode left;
        ListNode right;
        ListNode(int x) { val = x; }
    }
}


对链表进行插入排序。
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head==null) return null;
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode pre=head;
        ListNode cur=head.next;
        while(cur!=null){
            if(pre.val<=cur.val){
                pre=cur;
                cur=cur.next;
            }else{
                ListNode insert=dummy;
                while(insert.next!=null&&insert.next.val<cur.val){
                    insert=insert.next;
                }
                pre.next=cur.next;
                cur.next=insert.next;
                insert.next=cur;

                cur=pre.next;
            }
        }
        return dummy.next;
    }
}

按照左右半区的方式重新组合链表
public class MergerTwo {

    public void mergeTwo(Node head){
        if(head==null||head.next==null) return;
        Node fast=head.next;
        Node slow=head;
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        fast=slow.next;
        slow.next=null;
        merge(head,fast);
    }
    private void merge(Node fast,Node slow){
        while(fast.next!=null){
            Node next=slow.next;
            slow.next=fast.next;
            fast.next=slow;
            fast=slow.next;
            slow=next;
        }
        fast.next=slow;
    }
    private static class Node{
        private int val;
        private Node next;
        public Node(int val){
            this.val=val;
        }
    }
}


向有序的环形单链表中插入元素
public class InsertInto {

    public Node insertNum(Node head,int num){
        Node node=new Node(num);
        if(head==null){
            node.next=node;
            return node;
        }
        Node pre=head;
        Node cur=head.next;
        while(cur!=head){
            if(pre.val<=num&&cur.val>=num){
                break;
            }
            pre=cur;
            cur=cur.next;
        }
        node.next=pre.next;
        pre.next=node;
        return num<head.val?node:head;
    }
    private static class Node{
        private int val;
        private Node next;
        public Node(int val){
            this.val=val;
        }
    }
}


