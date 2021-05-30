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
        SimpleTreeNode<String> root = new SimpleTreeNode<>("Lexeme");

        SimpleTreeNode<String> variable = new SimpleTreeNode<>("Variable");
        variable.addChild("V");
        variable.addChild("Z");
        variable.addChild("R");

        SimpleTreeNode<String> symbol = new SimpleTreeNode<>("Symbol");

        root.addChild(variable);
        root.addChild(symbol);

        SimpleTreeNode<String> grouping = new SimpleTreeNode<>("Grouping");
        grouping.addChild("(");
        grouping.addChild(")");
        grouping.addChild("[");
        grouping.addChild("]");
        SimpleTreeNode<String> booleanOps = new SimpleTreeNode<>("BooleanOps");
        booleanOps.addChild("!");
        booleanOps.addChild("&");
        booleanOps.addChild("|");
        booleanOps.addChild("/~");
        booleanOps.addChild(">");
        booleanOps.addChild("<");
        booleanOps.addChild("=");
        SimpleTreeNode<String> arithmatic = new SimpleTreeNode<>("Arithmatic");
        arithmatic.addChild("+");
        arithmatic.addChild("-");
        arithmatic.addChild("*");
        arithmatic.addChild("/");
        SimpleTreeNode<String> text = new SimpleTreeNode<>("Text");
        text.addChild(".");
        text.addChild(",");
        text.addChild("\\r");
        text.addChild("\\t");
        text.addChild("\\n");
        SimpleTreeNode<String> special = new SimpleTreeNode<>("Special");
        special.addChild("->");
        special.addChild("=>");
        special.addChild("i");
        special.addChild("w");

        symbol.addChildren(Arrays.asList(grouping, booleanOps, arithmatic, text, special));
        for (SimpleTreeNode child: root.getChildren()) {
            root.printChildren(child);
        }
    }
}
