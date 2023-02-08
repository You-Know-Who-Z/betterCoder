package dataStructure;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhao
 */
public class BinaryTree {
    TreeNode root;
    int size;
    private BinaryTree() throws Exception {
        throw new Exception("必须初始化根节点");
    }

    public BinaryTree(int val) {
        this.root = new TreeNode(val);
    }

    class TreeNode {
        int val;

        TreeNode lNode;
        TreeNode rNode;
        TreeNode parent;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(TreeNode parent, TreeNode l, TreeNode r, int val) {
            this.parent = parent;
            this.lNode = l;
            this.rNode = r;
            this.val = val;
        }
    }

    /**
     * 添加节点
     */
    public void add(int val) throws Exception {
        add(root, val);
    }

    private void add(TreeNode parent, int val) throws Exception {
        if (parent.val == val) {
            throw new Exception("节点已存在");
        }
        if (parent.val > val) {
            if (parent.lNode != null) {
                add(parent.lNode, val);
            } else {
                parent.lNode = new TreeNode(parent, null, null, val);
            }
        } else {
            if (parent.rNode != null) {
                add(parent.rNode, val);
            } else {
                parent.rNode = new TreeNode(parent, null, null, val);
            }
        }
        size++;
    }

    /**
     * 删除节点
     */
    public void del(int val) throws Exception {
        del(root, val);
    }

    public void del(TreeNode cur, int val) throws Exception {
        TreeNode tree = find(cur, val);
        if (tree.parent == null) {
            // 根节点
            if (tree.lNode == null && tree.rNode == null) {
                tree = null;
            }
        } else {
            // 非根节点
            if (tree.lNode == null || tree.rNode == null) {
                // 单个儿子
                if (tree.lNode != null) {
                    if (tree.parent.val > val) {
                        tree.parent.lNode = tree.lNode;
                    } else {
                        tree.parent.rNode = tree.lNode;
                    }
                }
                if (tree.rNode != null) {
                    // 单个儿子
                    if (tree.parent.val > val) {
                        tree.parent.lNode = tree.rNode;
                    } else {
                        tree.parent.rNode = tree.rNode;
                    }
                }
            }

            if (tree.lNode != null && tree.rNode != null) {
                // 两个儿子 找左侧最大或右侧最小
                List<TreeNode> list = new ArrayList<>();
                traverse(tree.rNode, list);
                TreeNode min = list.stream().min(Comparator.comparing(i -> i.val)).orElseThrow(() -> new Exception("运行出错"));
                min.lNode = tree.lNode;
                // 加父节点
                if (tree.parent.val > min.val) {
                    tree.parent.lNode = min;
                } else {
                    tree.parent.rNode = min;
                }
                // 删除右侧最小节点
                del(tree.rNode, min.val);
            }
        }

    }

    /**
     * 搜索节点
     */
    public TreeNode find(int val) throws Exception {
        return find(root, val);
    }

    private TreeNode find(TreeNode cur, int val) throws Exception {
        if (cur != null) {
            if (cur.val == val) {
                return cur;
            }
            if (cur.val > val) {
                return find(cur.lNode, val);
            } else {
                return find(cur.rNode, val);
            }
        }
        throw new Exception("未找到节点");
    }

    /**
     * 遍历二叉树
     *
     * @param list
     */
    public void traverse(List<TreeNode> list) {
        traverse(root, list);
    }

    private void traverse(TreeNode cru, List<TreeNode> list) {
        if (cru != null) {
            list.add(cru);
            traverse(cru.lNode, list);
            traverse(cru.rNode, list);
        }
    }

    public static void main(String[] args) throws Exception {
        BinaryTree binaryTree = new BinaryTree(5);
        binaryTree.add(binaryTree.root, 1);
        binaryTree.add(binaryTree.root, 3);
        binaryTree.add(binaryTree.root, 6);
        binaryTree.add(binaryTree.root, 10);
        List<TreeNode> list = new ArrayList<>();
        binaryTree.traverse(list);
        System.out.println(list);
        TreeNode find = binaryTree.find(1);
        System.out.println(find.val);
        binaryTree.del(1);
        TreeNode find1 = binaryTree.find(1);
        System.out.println(find1.val);

    }
}
