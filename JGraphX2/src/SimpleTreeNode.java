import java.util.*;

public class SimpleTreeNode<T>{
    private T data = null;  // name or other data about node
    private List<SimpleTreeNode> children = new ArrayList<>();
    private SimpleTreeNode parent = null;

    public SimpleTreeNode(T data) {
        this.data = data;
    }

    public void addChild(SimpleTreeNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data) {
        SimpleTreeNode<T> newChild = new SimpleTreeNode<>(data);
        this.addChild(newChild);
    }

    public void addChildren(List<SimpleTreeNode> children) {
        for(SimpleTreeNode t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public List<SimpleTreeNode> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(SimpleTreeNode parent) {
        this.parent = parent;
    }

    public SimpleTreeNode getParent() {
        return parent;
    }

    public  String getParents(SimpleTreeNode node)
    {
        Deque stack = new LinkedList<SimpleTreeNode>();
        String path="";
        SimpleTreeNode parentNode = node.getParent();
        while (parentNode != null)
        {
            stack.push(parentNode);
            parentNode = parentNode.getParent();
        }
        Iterator<SimpleTreeNode> itr = stack.iterator();
        while (itr.hasNext()) {
            path = path + ((SimpleTreeNode) itr.next()).data + ":";
        }
        return path;
    }

    //  Recursive methods to print multi-level tree
    public void printChildren(SimpleTreeNode node) {
        if (node.parent != null) {
            if (node.children.size() == 0) {
                System.out.println(node.getParents((SimpleTreeNode) node) + ((SimpleTreeNode) node).data);
            } else {
                for (Object child : node.getChildren()) {
                    System.out.println(node.getParents((SimpleTreeNode) child) + ((SimpleTreeNode) child).data);
                    for (Object childOfChild : ((SimpleTreeNode) child).getChildren())
                        printChildren((SimpleTreeNode) childOfChild);
                }
            }
        }
        //       System.out.println(node.data);
    }

    public static void main(String[] args) {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("Root");

        SimpleTreeNode<String> child1 = new SimpleTreeNode<>("Child1");
        child1.addChild("Grandchild1");
        child1.addChild("Grandchild2");

        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("Child2");
        child2.addChild("Grandchild3");

        root.addChild(child1);
        root.addChild(child2);
        SimpleTreeNode<String> child3 = new SimpleTreeNode<>("Child3");
        root.addChild(child3);
        child3.addChild(new SimpleTreeNode<>("GrandChild4"));

        root.addChildren(Arrays.asList(
                new SimpleTreeNode<>("Child4"),
                new SimpleTreeNode<>("Child5"),
                new SimpleTreeNode<>("Child6")
        ));
        for (SimpleTreeNode child: root.getChildren()) {
            root.printChildren(child);
        }
    }
}
