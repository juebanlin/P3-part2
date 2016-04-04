import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.HashSet;
import java.util.ArrayList;


public class AvlMap<K extends Comparable<? super K>, V> extends BSTMap<K,V>  {



    private BSTMap<K, V> avlTree;
    //private BNode root;  

    public AvlMap() {
        avlTree = new BSTMap<K,V> ();
    }

    /** 
    * Get the height of the tree.
    * @param n the root of the subtree
    * @return the height of the tree
    */
    public int height() {
        return height(this.root);
    }

    private int height(BNode n) {
        if (n == null) {
            return 0;
        }
        return n.height;
    }

    /**
    * Get maxium of of two integers. 
    */
    public int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    /**
    * Get Balance factor of node n.
    */
    public int getBalance(BNode n) {
        if (n == null || n.key == null) {
            throw new java.lang.NullPointerException();
        } 
        return height(n.left) - height(n.right);
    }

    /**
    * Perform right rotation.
    * @param r the root of the subtree
    */ 
    // y <--> r;   x <--> n;
    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    public BNode rightRotate(BNode r) {
        //BNode n= r.left;
        //BNode temp = n.right;

        BNode n= r.left;
        BNode temp = n.right;
 
        // Perform rotation
        n.right = r;
        r.left = temp;
 
        // Update heights
        r.height = max(height(r.left), height(r.right)) + 1;
        n.height = max(height(n.left), height(n.right)) + 1;
 
        // Return new root
        return n;
    }

    /**
    * Perform left rotation.
    * @param r the root of the subtree
    */ 
    // x <--> r;   y <--> n;
    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    public BNode leftRotate(BNode r) {
        BNode n = r.right;
        BNode temp = n.left;
 
        // Perform rotation
        n.left = r;
        r.right = temp;
 
        //  Update heights
        n.height = max(height(r.left), height(r.right)) + 1;
        n.height = max(height(n.left), height(n.right)) + 1;
 
        // Return new root
        return n;
    }

    /**
    * Insert into the tree; duplicates are ignored.
    * @param x the item to insert.
    */
    public BNode insert(K key, V val) {
         return this.insert(key, val, this.root);
    }
      // node <--> curr


    /*
how would you insert(key, val, curr)

    */

/*
    public BNode insert(K key, V val, BNode curr) {
        //call BST insert
        //V tempVal = super.put(key, val, curr); 

        BNode temp = null;

        this.put(key, val, curr);
        System.out.println("curr should be 1 " + curr.key);

        curr.height = max(height(curr.left), height(curr.right)) +1 ;
        System.out.println("curr.height is: " + curr.height);

        // Get balance
        int balance = getBalance(curr);
        System.out.println("balance :" + balance);
        // balance > 1 (left heavy)
        // balance < -1 (right heavy)

        // If unbalanced, check 4 cases
        // Left Left Case
        if (balance > 1 && key.compareTo(curr.left.key) < 0) {
            System.out.println("LL Case");
            return this.rightRotate(curr);
        }
 
        // Right Right Case
        if (balance < -1 && key.compareTo(curr.right.key) > 0) {
            System.out.println("RR Case");
            return this.leftRotate(curr);
        }
 
        // Left Right Case
        if (balance > 1 && key.compareTo(curr.left.key) > 0) {
            curr.left = leftRotate(curr.left);
            System.out.println("LR Case");
            return this.rightRotate(curr);
        }
 
        // Right Left Case
        if (balance < -1 && key.compareTo(curr.right.key) < 0 ) {
            curr.right = rightRotate(curr.right);
            System.out.println("RL Case");
            return this.leftRotate(curr);
        }
        return temp;
    }


    */

    public BNode insert(K key, V val, BNode curr) {

        BNode temp;

        if (curr == null) {
            curr = new BNode(key, val);
            temp = curr;
            //System.out.println("insert: " + curr);
            return curr;
        } else if (curr.key == null) { 
            //initial root has null key null value, but root is not null
            this.root.setKey(key);
            this.root.setValue(val);
            curr = this.root;
            return curr; 
        }
        int diff = key.compareTo(curr.key);

        //System.out.println("curr.key is : " + curr.key + " curr.height is" + curr.height);
        int balance = getBalance(curr);
        System.out.println("balance is " + balance);


        if (diff < 0 ) { //insert left
            System.out.println("diff is < 0");
            curr.left = insert(key, val, curr.left);
            curr.height = max( height( curr.left ), height( curr.right ) ) + 1;
            if (balance == 2 ) { //left heavy 
                if (key.compareTo(curr.left.key) < 0) { //LLcase
                   System.out.println("enter left rotation");
                    curr = rotateWithLeftChild(curr);
                    this.root = curr;
                } else {
                   System.out.println("enter double left rotation");
                    curr = doubleWithLeftChild(curr);
                    this.root = curr;
                }
            }
        } else if (diff > 0) {
            curr.right = insert(key, val, curr.right);
            curr.height = max( height( curr.left ), height( curr.right ) ) + 1;

            if (balance == -2) {
                if (key.compareTo(curr.right.key) > 0) {
                    System.out.println("enter right rotation");
                    curr = rotateWithRightChild(curr);                 
                    this.root = curr; 
                } else {
                    System.out.println("enter double right rotation");
                    curr = doubleWithRightChild(curr);                    
                    this.root = curr;       
                }
            }
        } else { //update curr's value
            curr.setValue(val);
        }
        return curr;
    }

    public V delete(K key, BNode curr) {
        // Check if given key or root is null
        if (curr == null || key == null) {
            throw new java.lang.NullPointerException();
        }

        // Remove node and store value
        V tempVal = this.remove(key, curr);

        // Return null if key not found
        if (tempVal == null) {
            return null;
        }

        // Get new height of node
        curr.height = max(height(curr.left), height(curr.right));

        // Get balance factor of node
        int balance = getBalance(curr);

        //Left Left Case
        if (balance > 1 && getBalance(curr.left) >= 0) {
            return rightRotate(curr).value;
        }

        // Left Right Case
        if (balance > 1 && getBalance(curr.left) < 0) {
            curr.left = leftRotate(curr.left);
            return rightRotate(curr).value;
        }
 
        // Right Right Case
        if (balance < -1 && getBalance(curr.right) <= 0) {
            return leftRotate(curr).value;
        }
 
        // Right Left Case
        if (balance < -1 && getBalance(curr.right) > 0) {
            curr.right = rightRotate(curr.right);
            return leftRotate(curr).value;
        }

        // If tree is still balanced
        return curr.value;
    }


    public Collection<K> preOrder() {
        return this.preOrder(this.root);
    }

    public Collection<K> preOrder(BNode curr) {
        LinkedList<K> set = new LinkedList<K>(); 
        if (curr == null) {
        } else {
            set.addFirst(curr.key);
            set.addAll(this.preOrder(curr.left));
            set.addAll(this.preOrder(curr.right));
        }
        return set;
    }

    public Collection<K> postOrder() {
        return this.postOrder(this.root);
    }


    public Collection<K> postOrder(BNode curr) {
        LinkedList<K> set = new LinkedList<K>(); 
        if (curr == null) {
        } else {
            set.addAll(this.postOrder(curr.left));
            set.addAll(this.postOrder(curr.right));
            set.addLast(curr.key);
        }
        return set;
    }

        /**
         * Rotate binary tree node with left child.
         * For AVL trees, this is a single rotation for case 1.
         * Update heights, then return new root.
         */
        private BNode rotateWithLeftChild( BNode k2 )
        {
            BNode k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
            k1.height = max( height( k1.left ), k2.height ) + 1;
            return k1;
        }

        /**
         * Rotate binary tree node with right child.
         * For AVL trees, this is a single rotation for case 4.
         * Update heights, then return new root.
         */
        private BNode rotateWithRightChild( BNode k1 ){
            if (k1 == null) {
                return null;
            }
            BNode k2 = k1.right;
            if (k2 != null) {
            k1.right = k2.left;
            k2.left = k1;
            k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
            k2.height = max( height( k2.right ), k1.height ) + 1;
            }
            return k2;
        }

        /**
         * Double rotate binary tree node: first left child
         * with its right child; then node k3 with new left child.
         * For AVL trees, this is a double rotation for case 2.
         * Update heights, then return new root.
         */
        private BNode doubleWithLeftChild( BNode k3 ){
            if (k3 != null) {
                k3.left = rotateWithRightChild( k3.left );
                return this.rotateWithLeftChild( k3 );
            }
            return k3;
        }

        /**
         * Double rotate binary tree node: first right child
         * with its left child; then node k1 with new right child.
         * For AVL trees, this is a double rotation for case 3.
         * Update heights, then return new root.
         */
        private BNode doubleWithRightChild( BNode k1 ){
            if (k1 != null) {
            k1.right = rotateWithLeftChild( k1.right );
            return this.rotateWithRightChild( k1 );
            }
            return k1;
        }




}