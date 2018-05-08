import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * @author Sanford Johnston
 * @version 2.0 Invariant of the MetadataNode<M> class: 1. Each node has one
 *          reference to an M Object, stored in the instance variable element.
 *          2. The instance variables child and sibling are references to the
 *          node's first child and next sibling.
 */
public class MetadataNode<M> extends Object implements MutableTreeNode
{

	private String element;
	private String elementName;
	private MetadataNode<?> child, parent;
	private MetadataNode<?> sibling;
	private String answer;
	private String question;
	private boolean verified;
	private boolean nodeHasChild = false;

	/**
	 * Initialize a <CODE>BTNode</CODE> with a specified initial element and
	 * links children. Note that a child link may be the null reference, which
	 * indicates that the new node does not have that child.
	 * 
	 * @param <CODE>initialData</CODE>
	 *            the initial element of this new node
	 * @param <CODE>initialLeft</CODE>
	 *            a reference to the left child of this new node--this reference
	 *            may be null to indicate that there is no node after this new
	 *            node.
	 * @param <CODE>initialRight</CODE>
	 *            a reference to the right child of this new node--this
	 *            reference may be null to indicate that there is no node after
	 *            this new node.
	 *            <dt><b>Postcondition:</b>
	 *            <dd>This node contains the specified element and links to its
	 *            children.
	 **/
	public MetadataNode(String initialElement, MetadataNode<?> childCopy, MetadataNode<?> siblingCopy)
	{
		element = initialElement;
		child = childCopy;
		sibling = siblingCopy;
		question = "";
		answer = "";
		verified = false;
		parent = null;
		if (child == null)
			nodeHasChild = false;
		else
			nodeHasChild = true;
	}

	/**
	 * Initialize a <CODE>BTNode</CODE> with a specified initial element and
	 * links children. Note that a child link may be the null reference, which
	 * indicates that the new node does not have that child.
	 * 
	 * @param <CODE>initialData</CODE>
	 *            the initial element of this new node
	 * @param <CODE>quest</CODE>
	 *            the question of this new node
	 * @param <CODE>initialLeft</CODE>
	 *            a reference to the left child of this new node--this reference
	 *            may be null to indicate that there is no node after this new
	 *            node.
	 * @param <CODE>initialRight</CODE>
	 *            a reference to the right child of this new node--this
	 *            reference may be null to indicate that there is no node after
	 *            this new node.
	 *            <dt><b>Postcondition:</b>
	 *            <dd>This node contains the specified element and links to its
	 *            children.
	 **/
	public MetadataNode(String initialElement, String quest, MetadataNode<?> initialChild,
			MetadataNode<?> initialSibling)
	{
		element = initialElement;
		child = initialChild;
		sibling = initialSibling;
		question = quest;
		answer = "";
		verified = false;
		parent = null;
		if (child == null)
			nodeHasChild = false;
		else
			nodeHasChild = true;
	}

	/**
	 * Initialize a <CODE>BTNode</CODE> with a specified initial element and
	 * links children. Note that a child link may be the null reference, which
	 * indicates that the new node does not have that child.
	 * 
	 * @param <CODE>initialData</CODE>
	 *            the initial element of this new node
	 * @param <CODE>quest</CODE>
	 *            the question of this new node
	 * @param <CODE>answ</CODE>
	 *            the answer of this new node
	 * @param <CODE>initialLeft</CODE>
	 *            a reference to the left child of this new node--this reference
	 *            may be null to indicate that there is no node after this new
	 *            node.
	 * @param <CODE>initialRight</CODE>
	 *            a reference to the right child of this new node--this
	 *            reference may be null to indicate that there is no node after
	 *            this new node.
	 *            <dt><b>Postcondition:</b>
	 *            <dd>This node contains the specified element and links to its
	 *            children.
	 **/
	public MetadataNode(String initialElement, String quest, String answ, MetadataNode<?> initialChild,
			MetadataNode<?> initialSibling)
	{
		element = initialElement;
		child = initialChild;
		sibling = initialSibling;
		question = quest;
		answer = answ;
		verified = false;
		parent = null;
		if (child == null)
			nodeHasChild = false;
		else
			nodeHasChild = true;
	}

	/**
	 * Initialize a <CODE>BTNode</CODE> with a specified initial element and
	 * links children. Note that a child link may be the null reference, which
	 * indicates that the new node does not have that child.
	 * 
	 * @param <CODE>initialData</CODE>
	 *            the initial element of this new node
	 * @param <CODE>quest</CODE>
	 *            the question of this new node
	 * @param <CODE>answ</CODE>
	 *            the answer of this new node
	 * @param <CODE>ver</CODE>
	 *            the verified flag of this new node
	 * @param <CODE>initialLeft</CODE>
	 *            a reference to the left child of this new node--this reference
	 *            may be null to indicate that there is no node after this new
	 *            node.
	 * @param <CODE>initialRight</CODE>
	 *            a reference to the right child of this new node--this
	 *            reference may be null to indicate that there is no node after
	 *            this new node.
	 *            <dt><b>Postcondition:</b>
	 *            <dd>This node contains the specified element and links to its
	 *            children.
	 **/
	public MetadataNode(String initialElement, String initialQuestion, String initialAnswer, boolean initialVerified,
			MetadataNode<?> initialChild, MetadataNode<?> initialSibling)
	{
		element = initialElement;
		child = initialChild;
		sibling = initialSibling;
		question = initialQuestion;
		answer = initialAnswer;
		verified = initialVerified;
		parent = null;
		if (child == null)
			nodeHasChild = false;
		else
			nodeHasChild = true;
	}

	public MetadataNode(String initialElement, String initialElementName, String initialQuestion, String initialAnswer)
	{
		element = initialElement;
		elementName = initialElementName;
		question = initialQuestion;
		sibling = null;
		child = null;
		answer = null;
		verified = false;
		parent = null;
		if (child == null)
			nodeHasChild = false;
		else
			nodeHasChild = true;
	}

	/**
	 * Accessor method to get the element from this node.
	 * 
	 * @param -
	 *            none
	 * @return the element from this node
	 **/
	public String getElement()
	{
		return element;
	}

	/**
	 * Accessor method to get the element from this node.
	 * 
	 * @param -
	 *            none
	 * @return the element from this node
	 **/
	public String getElementName()
	{
		return elementName;
	}

	/**
	 * Accessor method to get the question from this node.
	 * 
	 * @param -
	 *            none
	 * @return the question from this node as a String object
	 **/
	public String getQuestion()
	{
		return question;
	}

	/**
	 * Accessor method to get the answer from this node.
	 * 
	 * @param -
	 *            none
	 * @return the answer from this node as a String object
	 **/
	public String getAnswer()
	{
		return answer;
	}

	/**
	 * Accessor method to get the verified flag from this node.
	 * 
	 * @param -
	 *            none
	 * @return the verified flag from this node as a boolean primative
	 **/
	public boolean getVerified()
	{
		return verified;
	}

	/**
	 * Accessor method to get a reference to the first child of this node.
	 * 
	 * @param -
	 *            none
	 * @return a reference to the first child of this node (or the null
	 *         reference if there is no child)
	 **/
	public MetadataNode<?> getChild()
	{
		return child;
	}

	/**
	 * Accessor method to get the element from the youngest-child node of the
	 * tree below this node.
	 * 
	 * @param -
	 *            none
	 * @return the element from the deepest node that can be reached from this
	 *         node by following child links.
	 **/
	public MetadataNode<?> getLastChild()
	{
		MetadataNode<?> thisNode = this;
		if (child == null)
			return thisNode;
		else
			return child.getLastChild();
	}

	/**
	 * Accessor method to get a reference to the next sibling of this node.
	 * 
	 * @param -
	 *            none
	 * @return a reference to the next sibling of this node (or the null
	 *         reference if there is no child)
	 **/
	public MetadataNode<?> getSibling()
	{
		return sibling;
	}

	public MetadataNode<?> getParent()
	{
		return parent;
	}

	/**
	 * Accessor method to determine whether a node is a leaf.
	 * 
	 * @param -
	 *            none
	 * @return <CODE>true</CODE> (if this node is a leaf) or <CODE>false</CODE>
	 *         (if this node is not a leaf.
	 **/
	public boolean isLeaf()
	{
		return (child == null) && (child == null);
	}

	/**
	 * Remove the youngest child node of the tree with this node as its root.
	 * 
	 * @param -
	 *            none
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The tree starting at this node has had its youngest child
	 *            node removed (i.e., the deepest node that can be reached by
	 *            following child links). The return value is a reference to the
	 *            root of the new (smaller) tree. This return value could be
	 *            null if the original tree had only one node (since that one
	 *            node has now been removed).
	 **/
	public MetadataNode<?> removeLastChild()
	{
		if (child == null)
			return sibling;
		else
		{
			child = child.removeLastChild();
			return this;
		}
	}

	/**
	 * Remove the youngest sibling node of the tree with this node as its root.
	 * 
	 * @param -
	 *            none
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The tree starting at this node has had its youngest
	 *            sibling node removed (i.e., the deepest node that can be
	 *            reached by following sibling links). The return value is a
	 *            reference to the root of the new (smaller) tree. This return
	 *            value could be null if the original tree had only one node
	 *            (since that one node has now been removed).
	 **/
	public MetadataNode<?> removeLastSibling()
	{
		if (sibling == null)
			return child;
		else
		{
			sibling = sibling.removeLastSibling();
			return this;
		}
	}

	/**
	 * Modification method to set the element in this node.
	 * 
	 * @param <CODE>newElement</CODE>
	 *            the new element to place in this node
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The element of this node has been set to
	 *            <CODE>newElement</CODE>.
	 **/
	public void setElement(String newElement)
	{
		element = newElement;
	}

	/**
	 * Modification method to set the element in this node.
	 * 
	 * @param <CODE>newElement</CODE>
	 *            the new element to place in this node
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The element of this node has been set to
	 *            <CODE>newElement</CODE>.
	 **/
	public void setElementName(String newElement)
	{
		elementName = newElement;
	}

	/**
	 * Modification method to set the question in this node.
	 * 
	 * @param <CODE>newQuestion</CODE>
	 *            the new question to place in this node
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The question of this node has been set to
	 *            <CODE>newQuestion</CODE>.
	 **/
	public void setQuestion(String newQuestion)
	{
		question = newQuestion;
	}

	/**
	 * Modification method to set the answer in this node.
	 * 
	 * @param <CODE>newAnswer</CODE>
	 *            the new answer to place in this node
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The answer of this node has been set to
	 *            <CODE>newAnswer</CODE>.
	 **/
	public void setAnswer(String newAnswer)
	{
		answer = newAnswer;
	}

	/**
	 * Modification method to set the verified flag in this node.
	 * 
	 * @param <CODE>newVerified</CODE>
	 *            the new verified flag to place in this node
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The verified flag of this node has been set to
	 *            <CODE>newVerified</CODE>.
	 **/
	public void setVerified(boolean newVerified)
	{
		verified = newVerified;
	}

	/**
	 * Modification method to set the link to the first child of this node.
	 * 
	 * @param <CODE>newLeft</CODE>
	 *            a reference to the node that should appear as the first child
	 *            of this node (or the null reference if there is no child for
	 *            this node)
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The link to the first child of this node has been set to
	 *            <CODE>newChild</CODE>. Any other node (that used to be the
	 *            first child) is no longer connected to this node.
	 **/
	@SuppressWarnings("unchecked")
	public void addChild(MetadataNode<?> metadataNode)
	{
		child = (MetadataNode<M>) metadataNode;

	}

	/**
	 * Modification method to set the link to the sibling of this node.
	 * 
	 * @param <CODE>newLeft</CODE>
	 *            a reference to the node that should appear as the sibling of
	 *            this node (or the null reference if there is no sibling for
	 *            this node)
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The link to the sibling of this node has been set to
	 *            <CODE>newSibling</CODE>. Any other node (that used to be the
	 *            sibling) is no longer connected to this node.
	 **/
	public void addSibling(MetadataNode<?> metadataNode)
	{
		sibling = metadataNode;
	}

	public void setParent(MetadataNode<M> newParent)
	{
		parent = newParent;

	}

	public boolean hasChild()
	{
		return nodeHasChild;
	}

	/**
	 * edit from here down...
	 */

	/**
	 * Copy a binary tree.
	 * 
	 * @param <CODE>source</CODE>
	 *            a reference to the root of a binary tree that will be copied
	 *            (which may be an empty tree where <CODE>source</CODE> is null)
	 * @return The method has made a copy of the binary tree starting at
	 *         <CODE>source</CODE>. The return value is a reference to the root
	 *         of the copy.
	 * @exception OutOfMemoryError
	 *                Indicates that there is insufficient memory for the new
	 *                tree.
	 **/
	public static <M> MetadataNode<?> treeCopy(MetadataNode<?> source)
	{
		MetadataNode<?> childCopy, siblingCopy;

		if (source == null)
			return null;
		else
		{
			childCopy = treeCopy(source.child);
			siblingCopy = treeCopy(source.sibling);
			return new MetadataNode<M>(source.element, childCopy, siblingCopy);
		}
	}

	/**
	 * Count the number of nodes in a binary tree.
	 * 
	 * @param <CODE>root</CODE>
	 *            a reference to the root of a binary tree (which may be an
	 *            empty tree where <CODE>source</CODE> is null)
	 * @return the number of nodes in the binary tree
	 *         <dt><b>Note:</b>
	 *         <dd>A wrong answer occurs for trees larger than
	 *         <CODE>INT.MAX_VALUE</CODE>.
	 **/
	public static long treeSize(MetadataNode<?> node)
	{
		if (node == null)
			return 0;
		else
			return 1 + treeSize(node.child) + treeSize(node.sibling);
	}

	/**
	 * Uses an in-order traversal to print the element from each node at or
	 * below this node of the binary tree.
	 * 
	 * @param -
	 *            none
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The element of this node and all its descendants have been
	 *            written by <CODE>System.out.println( )</CODE> using an
	 *            in-order traversal.
	 **/
	public void inorderPrint()
	{
		if (child != null)
			child.inorderPrint();
		System.out.println(element + " : " + elementName + " : " + question + " : " + answer + " : "
				+ ((verified) ? "verified" : "not verified"));
		if (sibling != null)
			sibling.inorderPrint();
	}

	/**
	 * Uses a pre-order traversal to print the element from each node at or
	 * below this node of the binary tree.
	 * 
	 * @param -
	 *            none
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The element of this node and all its descendants have been
	 *            written by <CODE>System.out.println( )</CODE> using a
	 *            pre-order traversal.
	 **/
	public void preorderPrint()
	{
		System.out.println(element + " : " + elementName + " : " + question + " : " + answer + " : "
				+ ((verified) ? "verified" : "not verified"));
		if (child != null)
			child.preorderPrint();
		if (sibling != null)
			sibling.preorderPrint();
	}

	/**
	 * Uses a post-order traversal to print the element from each node at or
	 * below this node of the binary tree.
	 * 
	 * @param -
	 *            none
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The element of this node and all its descendants have been
	 *            written by <CODE>System.out.println( )</CODE> using a
	 *            post-order traversal.
	 **/
	public void postorderPrint()
	{
		if (child != null)
			child.postorderPrint();
		if (sibling != null)
			sibling.postorderPrint();
		System.out.println(element + " : " + elementName + " : " + question + " : " + answer + " : "
				+ ((verified) ? "verified" : "not verified"));
	}

	/**
	 * Uses an inorder traversal to print the element from each node at or below
	 * this node of the binary tree, with indentations to indicate the depth of
	 * each node.
	 * 
	 * @param <CODE>depth</CODE>
	 *            the depth of this node (with 0 for root, 1 for the root's
	 *            children, and so on)(
	 *            <dt><b>Precondition:</b>
	 *            <dd><CODE>depth</CODE> is the depth of this node.
	 *            <dt><b>Postcondition:</b>
	 *            <dd>The element of this node and all its descendants have been
	 *            writen by <CODE>System.out.println( )</CODE> using an inorder
	 *            traversal. The indentation of each line of element is four
	 *            times its depth in the tree. A dash "--" is printed at any
	 *            place where a child has no sibling.
	 **/
	public void print(int depth)
	{
		int i;
		// Print the indentation and the contents from the current node:
		for (i = 1; i <= depth; i++)
			System.out.print("    ");
		System.out.println(element + " : " + elementName + " : " + question + " : " + answer + " : "
				+ ((verified) ? "verified" : "not verified"));

		// Print the children first
		if (child != null)
			child.print(depth + 1);
		/*
		 * else if (sibling != null) { for (i = 1; i <= depth+1; i++)
		 * System.out.print("    "); System.out.println("--"); }
		 */
		// Print the siblings second
		if (sibling != null)
			sibling.print(depth);
		/*
		 * else if (child != null) { for (i = 1; i <= depth + 1; i++)
		 * System.out.print("    "); System.out.println("--"); }
		 */
	}

	/**
	 * print only the leaves of a binary tree.
	 * <dt><b>Postcondition:</b>
	 * <dd>All leaves of the tree are printed.
	 * <dt><b>Note:</b>
	 * <dd>Leaves are found left-right-root (post-order traversal)
	 */
	public void printLeaves()
	{
		if (isLeaf()) // Base Case it is a leaf
		{
			System.out.println(element); // print leaf element
			System.out.println(question); // print leaf question
			System.out.println(answer); // print leaf answer
		}
		else // Recursive Case it is not a leaf
		{
			child.printLeaves(); // call left node first
			sibling.printLeaves(); // call right node second
		}
		return;
	}

	@Override
	public Enumeration<?> children()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAllowsChildren()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TreeNode getChildAt(int arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIndex(TreeNode arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insert(MutableTreeNode arg0, int arg1)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(int arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(MutableTreeNode arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFromParent()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setParent(MutableTreeNode arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserObject(Object arg0)
	{
		// TODO Auto-generated method stub

	}

}
