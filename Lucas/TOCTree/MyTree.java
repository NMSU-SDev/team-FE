import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;


public class MyTree extends JPanel implements TreeSelectionListener {
   
   private JTree tree;
   
   //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = true;
    
    //constructor
    public MyTree(MetadataNode<String> root) {
    
        super(new GridLayout(1,0));        
        
        //Create the nodes.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("Table of Contents");
        createNodes(top,root);

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

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);

        Dimension minimumSize = new Dimension(100, 50);
        
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100); 
        splitPane.setPreferredSize(new Dimension(500, 300));

        //Add the split pane to this panel.
        add(splitPane);
        
    }//end MyTree constructor
   
   /** Required by TreeSelectionListener interface.
    *  This is where we can make changes when a tree entry is 
    *  selected */
    public void valueChanged(TreeSelectionEvent e) {
    
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();

        if (node == null) return;
        
        /* Code from tutorial - not sure if useful
         * 
         
        Object nodeInfo = node.getUserObject();
        
        if (node.isLeaf()) {
            //NodeInfo node = (NodeInfo)nodeInfo;
          
        }
        */
        
    }//end valueChanged
    
    /* NodeInfo class. This is where the attributes of the MetadataNode are assigned
     * to the JTree nodes.
     */
    @SuppressWarnings({ "unused"})
    private class NodeInfo {
    
        public String element;
        public String elementName;
        public String question;
        public MetadataNode<String> sibling;
        public MetadataNode<String> child;
        public String answer;
        public boolean isVerified;
        public MetadataNode<String> parent;
        
        public NodeInfo(MetadataNode<String> mNode) {
        	
        	element = mNode.getElement();
        	elementName = mNode.getElementName();
        	question = mNode.getQuestion();
        	sibling = mNode.getSibling();
        	child = mNode.getChild();
        	answer = mNode.getAnswer();
        	isVerified = mNode.getVerified();
        	parent = mNode.getParent();
        	
        }//end constructor
        
        /* this toString is what part of the MetadataNode becomes the
         * name in the table of contents. Set to element for now.
         */
        
         public String toString() {
            
            return element;
            
         }//end toString
            
    }//end NodeInfo
    
     /* createNodes creates the structure of the JTree based on children and siblings
      * of the MetadataNode root sent to it.
      */
     private void createNodes(DefaultMutableTreeNode top, MetadataNode<String> mNode) {
     
    	 DefaultMutableTreeNode node = null;
     
    	 //base case for leaves
    	 if (mNode == null)
    		 return;
    	 
    	 //set the DefaultMutableTreeNode to the MetadataNode and add it
    	 node = new DefaultMutableTreeNode(new NodeInfo(mNode));
    	 top.add(node);
    	 
    	 //recursive call to populate sibling
    	 if (mNode.getSibling() != null)
    		 createNodes(top, mNode.getSibling());
    	 
    	 //recursive call to populate children
    	 if (mNode.getChild() != null)
    		 createNodes(node, mNode.getChild());
     
     /* Old code for reference
     
     DefaultMutableTreeNode grandChildNode = null;
     DefaultMutableTreeNode rootChild = null;
    //Category 1 
    rootChild = new DefaultMutableTreeNode(new NodeInfo(mNode));
    top.add(rootChild);
    
    node = new DefaultMutableTreeNode(new NodeInfo(mNode.getChild()));
    rootChild.add(node);
    
    grandChildNode = new DefaultMutableTreeNode(new NodeInfo(mNode.getChild().getChild()));
    node.add(grandChildNode);
    
    node = new DefaultMutableTreeNode(new NodeInfo(mNode.getChild().getSibling()));
    rootChild.add(node); */
     
    }//end createNodes
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowJTree(MetadataNode<String> root) {
    
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
        frame.add(new MyTree(root));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
    }//end createAndShowJTree();
    
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
    	
    	//test mNodes
        MetadataNode<String> rootGrandChildSibling = new MetadataNode<String>("Root Grandchild Sibling",null,null);
        MetadataNode<String> rootGrandChild = new MetadataNode<String>("Root Grandchild", null, rootGrandChildSibling);
        MetadataNode<String> rootChildSibling = new MetadataNode<String>("Root Child Sibling", null, null);
        MetadataNode<String> rootChild = new MetadataNode<String>("Root child", rootGrandChild, rootChildSibling);
        MetadataNode<String> root = new MetadataNode<String>("Root", rootChild, null);
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowJTree(root);
            }
        });
    }//end main
}//end class