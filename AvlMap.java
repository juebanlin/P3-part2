//AVLMap.java
//Yu-chi Chang, Allan Wang
//ychang64, awang53
//EN.600.226.01/02
//P3

import java.util.Map;

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
    private BNode root;  

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
            throw new java.lang.NullPointerException(); 
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
    public V insert(BNode n) {
         return this.insert(n.key, n.value, this.root);
    }
      // node <--> curr
    public V insert(K key, V val, BNode curr) {
        //call BST insert
        V tempVal = this.put(key, val, curr); 
        //update height of this ancestor node 
        curr.height = max(height(curr.left), height(curr.right)) + 1;

        // Get balance
        int balance = getBalance(curr);
        // balance > 1 (left heavy)
        // balance < -1 (right heavy)

        // If unbalanced, check 4 cases
        // Left Left Case
        if (balance > 1 && key.compareTo(curr.left.key) < 0) {
            return this.rightRotate(curr).value;
        }
 
        // Right Right Case
        if (balance < -1 && key.compareTo(curr.right.key) > 0) {
            return this.leftRotate(curr).value;
        }
 
        // Left Right Case
        if (balance > 1 && key.compareTo(curr.left.key) > 0) {
            curr.left = leftRotate(curr.left);
            return this.rightRotate(curr).value;
        }
 
        // Right Left Case
        if (balance < -1 && key.compareTo(curr.right.key) < 0 ) {
            curr.right = rightRotate(curr.right);
            return this.leftRotate(curr).value;
        }
        return tempVal;
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

}