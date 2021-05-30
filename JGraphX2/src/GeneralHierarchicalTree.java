import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class GeneralHierarchicalTree {

    static class Node {
        int key;
        ArrayList<Node> child = new ArrayList<>();
        Node root = null;



        // Utility function to create a new tree node
       public Node newNode(int key) {
            Node temp = new Node();
            temp.key = key;
            return temp;
        }

        public void initialize() {
            /* Let us create below tree
             *             10
             *     / / \ \
             *     2 34 56 100
             *     / \         | / | \
             *     77 88     1 7 8 9
             */
            root = newNode(10);
            (root.child).add(newNode(2));
            (root.child).add(newNode(34));
            (root.child).add(newNode(56));
            (root.child).add(newNode(100));
            (root.child.get(0).child).add(newNode(77));
            (root.child.get(0).child).add(newNode(88));
            (root.child.get(2).child).add(newNode(1));
            (root.child.get(3).child).add(newNode(7));
            (root.child.get(3).child).add(newNode(8));
            (root.child.get(3).child).add(newNode(9));

            System.out.println("Level order traversal " +
                    "Before Mirroring ");
            root.LevelOrderTraversal(root);
        }
        // Prints the n-ary tree level wise
        public void LevelOrderTraversal(Node root) {
            if (root == null)
                return;

            // Standard level order traversal code
            // using queue
            Queue<Node> q = new LinkedList<>(); // Create a queue
            q.add(root); // Enqueue root
            while (!q.isEmpty()) {
                int n = q.size();

                // If this node has children
                while (n > 0) {
                    // Dequeue an item from queue
                    // and print it
                    Node p = q.peek();
                    q.remove();
                    System.out.print(p.key + " ");

                    // Enqueue all children of
                    // the dequeued item
                    for (int i = 0; i < p.child.size(); i++)
                        q.add(p.child.get(i));
                    n--;
                }

                // Print new line between two levels
                System.out.println();
            }
        }

    }


    // Driver Code
    public  static void main(String[] args)
    {
        GeneralHierarchicalTree tree = new GeneralHierarchicalTree();
        Node node = new Node();
        node.initialize();
    }
}

