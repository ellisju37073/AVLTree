public class AVLT {

    Node root;

    int height(Node n)
    {
        if(n==null)
            return 0;
        else
            return n.height;
    }

    // Get the balance Factor
    int balanceFactor(Node n)
    {
        if(n== null)
            return 0;
        else
            return height(n.left) - height(n.right);
    }

    //Get the max values out of two data values
    int max(int a , int b)
    {
        if(a>b)
            return a;
        else
            return b;
    }

    // Left rotation
    //this will happen around the 3 nodes
    Node leftR(Node a)
    {
        Node b = a.right;
        Node c = b.left;

        //rotation
        b.left = a;
        a.right = c;

        // Update the height
        a.height = max(height(a.left), height(a.right)) + 1;
        b.height = max(height(b.left), height(b.right)) + 1;
        return b;
    }

    // Right rotation
    Node rightR(Node a)
    {
        Node b = a.left;
        Node c = b.right;

        //rotation
        b.right = a;
        a.left= c;

        // Update the height
        a.height = max(height(a.left), height(a.right)) + 1;
        b.height = max(height(b.left), height(b.right)) + 1;
        return b;
    }

    Node insertAVL(Node node, int d){
        if(node == null)
            return new Node(d);
        if(d < node.data)
        {
            node.left = insertAVL(node.left, d);
        }
        else if(d > node.data)
        {
            node.right = insertAVL(node.right, d);
        }
        else
            return node;
        node.height = max(height(node.left), height(node.right)) + 1;
        int bf = balanceFactor(node);
        if(bf > 1)
        {
// RR
            if(d < node.left.data)
                return rightR(node);
// RL
            else if(d > node.left.data)
            {
                node.left = leftR(node.left);
                return rightR(node);
            }
        }
        if(bf < -1)
        {
// LL
            if(d > node.right.data)
                return leftR(node);
// LR
            else if(d < node.right.data)
            {
                node.right = rightR(node.right);
                return leftR(node);
            }
        }
        return node;

    }
    void inOrder(Node node)
    {
        if(node !=null)
        {
            inOrder(node.left);
            System.out.println(node.data + "\t");
            inOrder(node.right);
        }
    }
    public static void main(String[] args)
    {
        AVLT tree = new AVLT();
        tree.root = tree.insertAVL(tree.root, 10);
        tree.root = tree.insertAVL(tree.root, 20);
        tree.root = tree.insertAVL(tree.root, 30);
        tree.root = tree.insertAVL(tree.root, 40);
        tree.root = tree.insertAVL(tree.root, 50);
        tree.root = tree.insertAVL(tree.root, 25);
        tree.inOrder(tree.root);
    }
}
