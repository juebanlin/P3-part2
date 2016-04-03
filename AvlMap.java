//AVLMap.java
//Yu-chi Chang, Allan Wang
//ychang64, awang53
//EN.600.226.01/02
//P3

/**
 *  AVL Map class.
 *  @param <K> key
 *  @param <V> value
 */
public class AvlMap<K extends Comparable<? super K>, V> extends BSTMap<K, V>  {

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
    /** AvlTree variable. */
    private BSTMap<K, V> avlTree;
    /** Root node. */
    private BNode root;  

    /** AvlTree constructor. */
    public AvlMap() {
        this.avlTree = new BSTMap<K, V>();

    }

    /** 
    * Get the height of the tree.
    * @return the height of the tree
    */
    public int height() {
        return this.height(this.root);
    }

    /** 
    * Get the height of the tree.
    * @param n the root of the subtree
    * @return the height of the tree
    */
    private int height(BNode n) {
        if (n == null) {
            throw new java.lang.NullPointerException(); 
        }
        return n.height;
    }

    /**
    * Get maximum of of two integers. 
    * @param a first integer argument
    * @param b second integer argument
    * @return maximum of two integers
    */
    public int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    /**
    * Get balance factor of node n.
    * @param n given node
    * @return balance of node
    */
    public int getBalance(BNode n) {
        if (n == null || n.key == null) {
            throw new java.lang.NullPointerException();
        } 
        return this.height(n.left) - this.height(n.right);
    }

    /**
    * Perform right rotation.
    * @param r the root of the subtree
    * @return rotated node
    */ 
    // y <--> r;   x <--> n;
    public BNode rightRotate(BNode r) {
        // Save children
        BNode n = r.left;
        BNode temp = n.right;
 
        // Perform rotation
        n.right = r;
        r.left = temp;
 
        // Update heights
        r.height = this.max(this.height(r.left), this.height(r.right)) + 1;
        n.height = this.max(this.height(n.left), this.height(n.right)) + 1;
 
        // Return new root
        return n;
    }

    /**
    * Perform left rotation.
    * @param r the root of the subtree
    * @return rotated node
    */ 
    // x <--> r;   y <--> n;
    public BNode leftRotate(BNode r) {
        //Save children
        BNode n = r.right;
        BNode temp = n.left;
 
        // Perform rotation
        n.left = r;
        r.right = temp;
 
        //  Update heights
        n.height = this.max(this.height(r.left), this.height(r.right)) + 1;
        n.height = this.max(this.height(n.left), this.height(n.right)) + 1;
 
        // Return new root
        return n;
    }

    /**
    * Insert into the tree; duplicates are ignored.
    * @param n the node to insert.
    * @return value of inserted node
    */
    public V insert(BNode n) {
        return this.insert(n.key, n.value, this.root);
    }
      
    /**
    * Insert into the subtree.
    * @param key the key to insert.
    * @param val the value to insert
    * @param curr the root of the subtree
    * @return value of inserted node
    */
    public V insert(K key, V val, BNode curr) {
        //call BST put
        V tempVal = this.put(key, val, curr); 
        
        //update height of this ancestor node 
        curr.height = this.max(this.height(curr.left),
            this.height(curr.right)) + 1;

        // Get balance
        int balance = this.getBalance(curr);
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
            curr.left = this.leftRotate(curr.left);
            return this.rightRotate(curr).value;
        }
 
        // Right Left Case
        if (balance < -1 && key.compareTo(curr.right.key) < 0) {
            curr.right = this.rightRotate(curr.right);
            return this.leftRotate(curr).value;
        }
        return tempVal;
    }

    /**
    * Delete node from the subtree.
    * @param key the key to delete
    * @param curr the root of the subtree
    * @return value of deleted node
    */
    public V delete(K key, BNode curr) {
        // Remove node and store value
        V tempVal = this.remove(key, curr);

        // Get new height of node
        curr.height = this.max(this.height(curr.left), this.height(curr.right));

        // Get balance factor of node
        int balance = this.getBalance(curr);

        //Left Left Case
        if (balance > 1 && this.getBalance(curr.left) >= 0) {
            return this.rightRotate(curr).value;
        }

        // Left Right Case
        if (balance > 1 && this.getBalance(curr.left) < 0) {
            curr.left = this.leftRotate(curr.left);
            return this.rightRotate(curr).value;
        }
 
        // Right Right Case
        if (balance < -1 && this.getBalance(curr.right) <= 0) {
            return this.leftRotate(curr).value;
        }
 
        // Right Left Case
        if (balance < -1 && this.getBalance(curr.right) > 0) {
            curr.right = rthis.ightRotate(curr.right);
            return this.leftRotate(curr).value;
        }

        // If tree is still balanced
        return curr.value;
    }

}