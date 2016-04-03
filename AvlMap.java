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

    /*
    private class AvlNode extends BNode {
        private int height = 0; 

        AvlNode(K k, V v) {
            super(k, v);
            this.height = 1;
        }
        public void setHeight(int h) {
            this.height = h;
        }
        public int getHeight() {
            return this.height;
        }        

    } //end inner class 

*/

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
    * Get Balance factor of node n
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

    public BNode insert(K key, V val, BNode curr) {
        //call BST insert
        //V tempVal = super.put(key, val, curr); 

        BNode temp = null; 
        if (curr == null || curr.key == null) {
            curr = new BNode(key, val);
            temp = curr;
        }

        int diff = curr.key.compareTo(key);
        if (diff > 0 ) {
            curr.left = insert(key, val, curr.left);

        } else if (diff < 0) {
            curr.right = insert(key, val, curr.right);
        } else {

            curr.setValue(val);
            temp = curr;
        }

        curr.height = max(height(curr.left), height(curr.right)) +1 ;

        // Get balance
        int balance = getBalance(curr);
        System.out.println("balance" + balance);
        // balance > 1 (left heavy)
        // balance < -1 (right heavy)

        // If unbalanced, check 4 cases
        // Left Left Case
        if (balance > 1 && key.compareTo(curr.left.key) < 0) {
            return this.rightRotate(curr);
        }
 
        // Right Right Case
        if (balance < -1 && key.compareTo(curr.right.key) > 0) {
            return this.leftRotate(curr);
        }
 
        // Left Right Case
        if (balance > 1 && key.compareTo(curr.left.key) > 0) {
            curr.left = leftRotate(curr.left);
            return this.rightRotate(curr);
        }
 
        // Right Left Case
        if (balance < -1 && key.compareTo(curr.right.key) < 0 ) {
            curr.right = rightRotate(curr.right);
            return this.leftRotate(curr);
        }
        return temp;
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
            set.addLast(curr.key);
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





}