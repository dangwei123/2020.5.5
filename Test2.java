public class Traversal {
    //先序遍历
    public List<Integer> PreOrder(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node=stack.pop();
            res.add(node.val);
            if(node.right!=null) stack.push(node.right);
            if(node.left!=null) stack.push(node.left);
        }
        return res;
    }

    //中序遍历
    public List<Integer> inorder(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode cur=root;
        while(!stack.isEmpty()||cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            TreeNode node=stack.pop();
            res.add(node.val);
            if(node.right!=null) cur=node.right;
        }
        return res;
    }

    //后序遍历
    public List<Integer> postorder(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode cur=root;
        TreeNode pre=null;
        while(!stack.isEmpty()||cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            TreeNode node=stack.peek();
            if(node.right==null||pre==node.right){
                TreeNode tmp=stack.pop();
                pre=tmp;
                res.add(tmp.val);
            }else{
                cur=node.right;
            }
        }
        return res;
    }

    //层序遍历
    public List<List<Integer>> leverel(TreeNode root){
        List<List<Integer>> res=new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer> row=new ArrayList<>();
            while(size--!=0){
                TreeNode node=queue.poll();
                row.add(node.val);
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            res.add(row);
        }
        return res;
    }
}


打印边界节点
public class PrintEdg {
    //先打印左边界，再打印叶子节点，最后打印右边界
    public void printEdg1(TreeNode root){
        if(root==null) return;
        int high=getHeight(root);
        TreeNode[][] edgMap=new TreeNode[high][2];
        setEdgMap(root,0,edgMap);
        for(int i=0;i<high;i++){
            System.out.println(edgMap[i][0].val+" ");
        }
        getLeaf(root,0,edgMap);
        for(int i=high-1;i>=0;i--){
            if(edgMap[i][1]!=edgMap[i][0]){
                System.out.println(edgMap[i][1].val+" ");
            }
        }
        System.out.println();
    }
    private int getHeight(TreeNode root){
        if(root==null) return 1;
        return 1+Math.max(getHeight(root.left),getHeight(root.right));
    }

    private void setEdgMap(TreeNode root,int h,TreeNode[][] edgMap){
        if(root==null) return;
        edgMap[h][0]=edgMap[h][0]==null?root:edgMap[h][0];
        edgMap[h][1]=root;
        setEdgMap(root.left,h+1,edgMap);
        setEdgMap(root.right,h+1,edgMap);
    }

    private void getLeaf(TreeNode root, int h, TreeNode[][] edgMap){
        if(root==null) return;
        if(root.left==null&&root.right==null&&root!=edgMap[h][0]&&root!=edgMap[h][1]){
            System.out.println(root.val+" ");
        }
        getLeaf(root.left,h+1,edgMap);
        getLeaf(root.right,h+1,edgMap);
    }
}


较为直观的打印二叉树
public class PrintTree {

    public static void printTree(TreeNode root){
        printTree(root,0,"H",17);
    }
    public static void printTree(TreeNode root,int h,String to,int len){
         if(root==null) return;
         printTree(root.right,h+1,"v",len);
         String val=to+root.val+to;
         int left=(len-val.length())/2;
         int right=len-val.length()-left;
         String res=getSpace(left)+val+getSpace(right);
         System.out.println(getSpace(len*h)+res);
         printTree(root.left,h+1,"^",len);
    }
    private static String getSpace(int num){
        StringBuilder sb=new StringBuilder();
        while(num--!=0){
            sb.append(" ");
        }
        return new String(sb);
    }
}

二叉树的序列化和反序列化
public class MySerializable {
    public void serial(TreeNode root){
        String s="";
        serial(root,s);
    }
    private void serial(TreeNode root,String s){
        if(root==null) s+="#!";
        s+=root.val;
        s+="!";
        serial(root.left,s);
        serial(root.right,s);
    }

    public void deserial(String s){
        String[] arr=s.split("!");
        List<String> list=new ArrayList<>();
        for(String str:arr){
            list.add(str);
        }
    }
    private TreeNode deserial(LinkedList<String> list){
        String s=list.removeFirst();
        if(list.isEmpty()||s.equals("#")){
            return null;
        }
        TreeNode root=new TreeNode(Integer.parseInt(s));
        root.left=deserial(list);
        root.right=deserial(list);
        return root;
    }
	
	
	
	//按照层序遍历
    public String serLevel(TreeNode root){
        if(root==null){
            return "#!";
        }
        StringBuilder sb=new StringBuilder();
        sb.append(root.val).append("!");
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode cur=queue.poll();
            if(cur.left!=null){
                queue.offer(root.left);
                sb.append(root.left.val).append("!");
            }else{
                sb.append("#!");
            }

            if(cur.right!=null){
                queue.offer(root.right);
                sb.append(root.right.val).append("!");
            }else{
                sb.append("#!");
            }
        }
        return new String(sb);
    }

    public TreeNode deserialLeveral(String s){
        String[] arr=s.split("!");
        int i=0;
        TreeNode root=getNode(arr[i++]);
        if(root==null) return null;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode cur=queue.poll();
            cur.left=getNode(arr[i++]);
            cur.right=getNode(arr[i++]);
            if(cur.left!=null) queue.offer(cur.left);
            if(cur.right!=null) queue.offer(cur.right);
        }
        return root;
    }
    private TreeNode getNode(String s){
        if(s.equals("#")){
            return null;
        }
        return new TreeNode(Integer.parseInt(s));
    }
}

