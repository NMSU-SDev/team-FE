import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;

public class MyTree extends JPanel implements TreeSelectionListener {
   
   private JTree tree;
   private static boolean DEBUG = false;
   
   //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = true;
    
    public MyTree() {
    
        super(new GridLayout(1,0));

        //Create the nodes.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("MyTree root");
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);

        /* Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        initHelp();
        JScrollPane htmlView = new JScrollPane(htmlPane); */

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        
        //adds the bottom of the split pane - removed by Lucas for now
        //splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100); 
        splitPane.setPreferredSize(new Dimension(500, 300));

        //Add the split pane to this panel.
        add(splitPane);
        
    }//end MyTree constructor
   
   /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
    
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();

        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        
        if (node.isLeaf()) {
            //NodeInfo node = (NodeInfo)nodeInfo;
            //displayURL(book.bookURL); 
        }
        
    }//end valueChanged
    
    private class NodeInfo {
    
        public String nodeName;

        public NodeInfo(String node) {
        
            nodeName = node;
            
        }//end NodeInfo
        
         public String toString() {
            
            return nodeName;
            
         }//end toString
            
    }//end NodeInfo
        
     private void createNodes(DefaultMutableTreeNode top) {
     
     DefaultMutableTreeNode category = null;
     DefaultMutableTreeNode node = null;
     
     //Category 1 
     category = new DefaultMutableTreeNode("Category 1");
     top.add(category);

     //Category 1 Node 1
     node = new DefaultMutableTreeNode(new NodeInfo
         ("Category 1 Node 1"));
        category.add(node);

     //Category 1 Node 2
     node = new DefaultMutableTreeNode(new NodeInfo
            ("Category 1 Node 2"));
     category.add(node);

     //Category 1 Node 3
     node = new DefaultMutableTreeNode(new NodeInfo
            ("Category 1 Node 3"));
     category.add(node);

     //Category 1 Node 4
     node = new DefaultMutableTreeNode(new NodeInfo
            ("Category 1 Node 4"));
     category.add(node);

     //Category 1 Node 5
     node = new DefaultMutableTreeNode(new NodeInfo
            ("Category 1 Node 5"));
     category.add(node);

     //Category 1 Node 6
     node = new DefaultMutableTreeNode(new NodeInfo
            ("Category 1 Node 6"));
     category.add(node);
     
     //Category 2
     category = new DefaultMutableTreeNode("Category 2");
        top.add(category);

     //Category 2 Node 1
     node = new DefaultMutableTreeNode(new NodeInfo
            ("Category 2 Node 1"));
     category.add(node);

     //Category 2 Node 2
     node = new DefaultMutableTreeNode(new NodeInfo
            ("Category 2 Node 2"));
     category.add(node);
     
    }//end createNodes
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
    
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        //Create and set up the window.
        JFrame frame = new JFrame("MyTree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new MyTree());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
    }//end createAndShowGUI();
    
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }//end main
}//end class