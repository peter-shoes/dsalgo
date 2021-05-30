import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

// import SimpleTreeNode;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
public class JGraphX2 extends JFrame
{

    private static final long serialVersionUID = -2707712944901661771L;
    SimpleTreeNode<String> root = createSimpleTreeNode();

    public void fillGraphFromModel( mxGraph graph)  {
        graphUpdate();

        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try
        {
        Object vRoot = graph.insertVertex(parent, null, root.getData(), 80, 0, 80, 30);
        Object vChild1 = graph.insertVertex(parent, null,root.getChildren().get(0).getData() , 0, 0, 80, 30);
        Object vChild2 = graph.insertVertex(parent, null, root.getChildren().get(1).getData(), 0, 0, 80, 30);
        Object vChild3 = graph.insertVertex(parent, null, root.getChildren().get(2).getData(), 0, 0, 80, 30);
        Object vChild4 = graph.insertVertex(parent, null, root.getChildren().get(3).getData(), 0, 0, 80, 30);
        Object vChild5 = graph.insertVertex(parent, null, root.getChildren().get(4).getData(), 0, 0, 80, 30);
      //  Object v7 = graph.insertVertex(parent, null, root.getChildren().get(5).getData(), 0, 0, 80, 30);
        Object vGrandChild1 = graph.insertVertex(null, null, "GrandChild1", 0,0,90,30);
        Object vGrandChild2 = graph.insertVertex(null, null, "GrandChild2", 0,0,90,30);
        Object vGrandChild3 = graph.insertVertex(null, null, "GrandChild3", 0,0,90,30);
        Object vGrandChild4 = graph.insertVertex(null, null, "GrandChild4", 0,0,90,30);
        graph.insertEdge(parent, null, "", vRoot, vChild1);
        graph.insertEdge(parent, null, "", vRoot, vChild2);
        graph.insertEdge(parent, null, "", vRoot, vChild3);
        graph.insertEdge(parent, null, "", vRoot, vChild4);
        graph.insertEdge(parent, null, "", vRoot, vChild5);
     //   graph.insertEdge(parent, null, "", vRoot, v7);
        graph.insertEdge(vChild1, null, "", vChild1, vGrandChild1);
        graph.insertEdge(vChild1, null, "", vChild1, vGrandChild2);
        graph.insertEdge(vChild3, null, "",vChild3, vGrandChild3);
        graph.insertEdge(vChild5, null, "", vChild5, vGrandChild4);
            mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
            layout.setUseBoundingBox(false);

            layout.execute(parent);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
    }

    public JGraphX2()
    {
        super("Simple Tree Node");
        mxGraph graph = new mxGraph();
        fillGraphFromModel(graph);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);


    }

    public SimpleTreeNode<String> createSimpleTreeNode() {
        root = new SimpleTreeNode<>("Root");

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
        return root;
    }


    // Backup not used
    public void graphUpdate() {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try
        {
            //Notice that the parent is the default parent...
            //The hierarchical structure looks great but I cannot collapse/expand the tree.
            Object vDogsRoot = graph.insertVertex(parent, null, "Whale", 80, 0, 80, 30);
            Object v2 = graph.insertVertex(parent, null, "Shar Pei", 0, 0, 80, 30);
            Object v3 = graph.insertVertex(parent, null, "Pug", 0, 0, 80, 30);
            Object v4 = graph.insertVertex(parent, null, "Cocker Spaniel", 0, 0, 80, 30);
            Object v5 = graph.insertVertex(parent, null, "Pit Bull", 0, 0, 80, 30);
            Object v6 = graph.insertVertex(parent, null, "Chihuahua", 0, 0, 80, 30);
            Object v7 = graph.insertVertex(null, null, "GrandPuppy", 0,0,90,30);
            Object v8 = graph.insertVertex(null, null, "GrandPuppy2", 0,0,90,30);
            Object v9 = graph.insertVertex(null, null, "GrandPuppy3", 0,0,90,30);
            graph.insertEdge(parent, null, "", vDogsRoot, v2);
            graph.insertEdge(parent, null, "", vDogsRoot, v3);
            graph.insertEdge(parent, null, "", vDogsRoot, v4);
            graph.insertEdge(parent, null, "", vDogsRoot, v5);
            graph.insertEdge(parent, null, "", vDogsRoot, v6);
            graph.insertEdge(v6, null, "", v6, v7);
            graph.insertEdge(v6, null, "", v6, v8);
            graph.insertEdge(v6, null, "", v6, v9);

            mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
            layout.setUseBoundingBox(false);

            layout.execute(parent);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    public static void main(String[] args)
    {
        JGraphX2 frame = new JGraphX2();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 420);
        frame.setVisible(true);
    }

}
