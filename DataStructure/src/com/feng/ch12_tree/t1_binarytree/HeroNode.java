package com.feng.ch12_tree.t1_binarytree;

/*
 * 创建 HeroNode 结点
 *
 * 1、数据域 包含 编号 和 姓名
 * 2、指针域包括 左子结点 和 右子结点
 * */
public class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    // 编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);// 先输出父结点
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父结点
        System.out.println(this);
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        // 递归向遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        // 输出父结点
        System.out.println(this);
    }


    /*
     *前序遍历查找
     * @param no 查找 no
     * @return 如果找到就返回该 Node，如果没有找到就返回 null
     * */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历查找~~");
        // 比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        /*
         * 1、则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
         * 2、如果左递归前序查找，找到节点，则返回
         * */
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        // 1、左递归前序查找，找到节点，则返回，否则继续判断
        // 2、当前节点的右子节点是否为空，如果不为空，则继续向右递归前序查找。
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /*
     * 中序遍历查找
     * */
    public HeroNode infixOrderSearch(int no) {
        //1、判断当前节点的左子结点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序遍历查找~~");
        // 2、如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点，
        if (this.no == no) {
            return this;
        }

        // 3、否则继续进行右递归中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /*
     * 后序遍历查找
     * */
    public HeroNode postOrderSearch(int no) {
        //1、判断当前节点的左子结点是否为空，如果不为空，则递归后序查找,如果找到，则返回，
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        // 2、如果没有找到，就判断当前节点的右子节点是否为空，如果不为空，则有递归进行后续查找，如果找到，就返回
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序遍历查找");
        // 3、如果左右节点都没有找到，就和当前节点进行比较，如果是则返回，否则返回 null
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    /*
     * 递归删除结点
     * 1、如果删除的结点是叶子节点，则删除该叶子节点
     * 2、如果删除的结点是非叶子节点，则删除该子树
     * 首先先处理：考虑如果树是空树root，如果只有一个root结点，则等价将二叉树置空。 这一部分放在上一层处理
     * */
    public void deleteNode(int no) {
        // 第二第三步 为递归的 结束条件
        /*
         * 1、因为我们的二叉树是单向的，所以我们是判断当前节点的子节点是否需要删除节点，而不能取判断当前这个结点是不是需要删除结点。
         * 2、如果当前节点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null 即可；并且就返回(结束递归删除)
         * 3、如果当前结点的右子节点不为空，并且左子结点就是要删除结点，就将 this.right = null 即可；并且就返回(结束递归删除)
         * 4、如果第2步和第3步没有删除结点，那么我们就需要向左子树进行递归删除
         * 5、如果第4步也没有删除结点，则应当向右子树进行递归删除。
         * */
        // 2、如果当前节点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null 即可；并且就返回(结束递归删除)
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        //3、如果当前结点的右子节点不为空，并且左子结点就是要删除结点，就将 this.right = null 即可；并且就返回(结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 4、如果第2步和第3步没有删除结点，那么我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.deleteNode(no);
        }
        //5、如果第4步也没有删除结点，则应当向右子树进行递归删除。
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }
}
