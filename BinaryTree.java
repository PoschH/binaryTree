/*Hannah Posch
* HW 5, Summer 2018
* The code will utilize a binary tree and print the tree InOrder and PostOrder. 
* The code will also compute the size and depth of the tree.
* The textbook, Data Structures and Algorithm Analysis in Java, was consulted for this project.
*/
import java.util.*; // Needed for level-order print routine
/**
 * BinaryTree class
 * 
 * Implements an unbalanced binary tree - this is not a search tree.
 * @author mccauleyr
 *
 * @param <AnyType>
 */
public class BinaryTree<AnyType> extends HW5_AbstractClass<AnyType>{

    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }

    /** The tree root - the tree class instance variable. */
    private BinaryNode<AnyType> root;

    // Create an empty binary tree
    public BinaryTree() {
    	root = null;
    }
    
    public BinaryTree(AnyType rootElement) {
    	root = new BinaryTree.BinaryNode<AnyType>(rootElement);
    }
    
    // Build a binary tree from existing binary trees, adding a new root value
    // Either or both subtrees could be null
    public BinaryTree(AnyType rootElement, BinaryTree<AnyType> leftTree, BinaryTree<AnyType> rightTree) {
    	 root = new BinaryTree.BinaryNode<AnyType>(rootElement, leftTree.root, rightTree.root);
    }
    
    /* 
     * Public interface to preorder traversal - uses private
     * recursive helper method to accomplish job.
     */
    public void printTreePreOrder () {
    	printTreePreorder(root);
    }
    
    /**
     * private recursive method to print tree contents in preorder
     * @param t
     */
    private void printTreePreorder(BinaryTree.BinaryNode<AnyType> t) {
    	if (t != null) {
    		System.out.println(t.element); // assumes element printable
    		printTreePreorder(t.left);
    		printTreePreorder(t.right);
    	}
    }

    /**
     * Internal method to compute height of a subtree.
     * Outsiders currently cannot access this method.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    /**
     * Does a level-order traversal, printing out a tree that looks
     * something like a tree
     */
    public void printTreeLevelOrder() {
    	printTreeLevelOrder(root);
    }

    /** 
     * Output all nodes, level by level
     * @ param node - the node that roots the current subtree
     */
    private void printTreeLevelOrder(BinaryNode<AnyType> node) {
    	System.out.println("\n============ Start of tree ============");
        if (node == null) {
           System.out.print(" - ");
        }
        else {
          //Queue that holds the nodes on the current level
          Queue<BinaryNode<AnyType>> thisLevel = new LinkedList<>();
          //Queue that holds the nodes on the next level
          Queue<BinaryNode<AnyType>> nextLevel = new LinkedList<>(); 
          //put the root on the current level's queue
          thisLevel.add(node);
          int h = height(root);
          int levelTotal = 0;  //keeps track of the total levels printed so we don't  pass the height and print a billion "null"s
          while(!thisLevel.isEmpty()&& (levelTotal<= h))
          {

              while (!thisLevel.isEmpty()) // print nodes at current level
              {
                 if (thisLevel.peek() != null)
                 {
                    System.out.print(thisLevel.peek().element + " ");
                    // if there is a left pointer , put on next level; if none, put a null
                    node = thisLevel.peek().left; // look at the left pointer of node
                    nextLevel.add(node); 
                    node = thisLevel.remove().right; // get right pointer and remove node
                    nextLevel.add(node); 
                 }
                 else
                 {
                    System.out.print(" - ");
                    nextLevel.add(null); 
                    nextLevel.add(null);
                    thisLevel.remove();
                 }
              }  // end while (!thisLevel.isEmpty())
              // Moving to the next level, copy nodes to thisLevel so we know to 
              // advance level count and line in output
              while (!nextLevel.isEmpty())
              {
                    thisLevel.add(nextLevel.remove());
              } // end while (!nextLevel.isEmpty()
              System.out.println(); 
              levelTotal++;
          } // end while(!thisLevel.isEmpty()&& (levelTotal<= h))
        System.out.println("=========== end of tree ============="); // an extra line feed to separate this prinout of from next
      }

    }

    /* 
     * printTreeInOrder (public) - uses private recursive helper
     * method to accomplish job and sends the root node to the private method.
     */
	public void printTreeInOrder() {
		printTreeInOrder(root);
		
	}
	/* 
     * printTreeInOrder (private) - The method visits the root after visiting left subtree
     * and after the root, then visits the right subtree. The method prints the nodes in this 
     * order. The method also checks to see if the node is null.
     * @param: Binary tree node
     */
	private void printTreeInOrder(BinaryNode<AnyType> node) {
		if (node != null) {
		
	    		printTreeInOrder(node.left);
	    		System.out.println(node.element);
	    		printTreeInOrder(node.right);	
		}
	}
	/* 
     * printTreePostOrder (public) - uses private recursive helper
     * method to accomplish job and sends the root node to the private method.
     */
	public void printTreePostOrder() {
		printTreePostOrder(root);
		
	}
	/* 
     * printTreePostOrder (private) - The method visits the root after visiting 
     * all of the children. The children are visited from left to right. 
     * The method prints the nodes in this order. The method also checks to see if 
     * the node is null.
     * @param: Binary tree node
     */
	private void printTreePostOrder(BinaryNode<AnyType> node) {
		if (node != null) {
	    		
	    		printTreePostOrder(node.left);
	    		printTreePostOrder(node.right);
	    		System.out.println(node.element);
		}
	}
	/* 
     * size (public) - uses private recursive helper method to 
     * accomplish job and sends the root node to the private method.
     * @return: size of tree
     */
	public int size() {
		return size(root);
	}
	/* 
     * size (private) - The method computes the size of the tree by checking if
     * the tree is empty, then it will return 0, otherwise it continues it's recursive
     * call by adding 1 to the size of the left and right subtree.
     * @param: Binary tree node
     * @return: size of tree
     */
	private int size(BinaryNode<AnyType> node) {
		if (node == null) {
			return 0;
		}
		else {
			return 1 + size(node.left) + size(node.right);
		}
	}
	/* 
     * depth (public) - uses private recursive helper method to 
     * accomplish job and sends the root node, Anytype value and
     * an initial depth (just initialized to 0) to the private method to calculate.
     * @param: Anytype value
     * @return: depth
     */
	public int depth(AnyType x) {
		int initialDepth = 0;
		return depth(x, root,initialDepth);
	}
	/* 
     * depth (private) - The method computes the depth of a certain AnyType value. If
     * there are multiple values, then the depth is returned of the first node and will
     * return -1 if the node is not found.
     * @param: Anytype value, binary tree node, and a depth variable
     * @return: depth
     */
	private int depth(AnyType value, BinaryNode<AnyType> node, int depthVal) {
		
		if(node == null) {
			return -1;
		}
		if(node.element == value) {
			return depthVal;
		}
		int lowerDepthLeft = depth(value, node.left, depthVal +1);
		if(lowerDepthLeft != -1 ) {
			return lowerDepthLeft;
		}
		int lowerDepthRight = depth(value, node.right, depthVal +1);
		return lowerDepthRight;
			
		
	}


	// Test program
    public static void main( String [ ] args )
    {
    	/*BinaryTree<String> tree0 = new BinaryTree<>();
    	BinaryTree<String> tree1 = new BinaryTree<>("f",new BinaryTree<>("g"),tree0);
    //	tree1.printTreeLevelOrder();
     	BinaryTree<String> tree2 = new BinaryTree<>();
    //	tree2.printTreeLevelOrder();
     	BinaryTree<String> tree3 = new BinaryTree<>("c",tree2, tree1);
     	//tree3.printTreeLevelOrder();
     	tree3 = new BinaryTree<>("a", new BinaryTree<>("b",new BinaryTree<>("d"),new BinaryTree<>("e")), tree3);
       // tree3.printTreeLevelOrder();
    	System.out.println("=======PreOrder=========");
    	tree3.printTreePreOrder(); // Can't tell the structure of the tree
    	System.out.println("========LevelOrder===========");
    	tree3.printTreeLevelOrder(); 
    	System.out.println("=======PostOrder=========");
    	tree3.printTreePostOrder(); // Can't tell the structure of the tree
    	System.out.println("=======InOrder=========");
    	tree3.printTreeInOrder(); // Can't tell the structure of the tree
    	System.out.println("Size: " + tree3.size());
    	System.out.println("Depth of Node b: " + tree3.depth("b"));
    	System.out.println("Depth of Node f: " + tree3.depth("f"));
    	System.out.println("Depth of Node a: " + tree3.depth("a"));
    	System.out.println("Depth of Node z: " + tree3.depth("z"));
    	System.out.println("Depth of Node g: " + tree3.depth("g"));*/
    	
    }

	
}
