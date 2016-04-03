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
public class RecAvlMap<K extends Comparable<? super K>, V> extends BSTMap<K, V>  {

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
    * Recursively insert into the subtree.
    * @param key the key to insert.
    * @param val the value to insert
    * @param curr the root of the subtree
    * @return inserted node
    */
    private BNode recInsert(K key, V val, BNode curr) {
        BNode n = BNode(key, value);
        if (key == null || val == null || curr == null) {
            throw new NullPointerException;
        } else if (key.compareTo(curr.left.key) < 0) {
            curr.left = this.recInsert(key, val, t.left);
            if (this.getBalance(t) == 2) {
                if (key < curr.left.key) {
                    curr = this.rightRotate(curr);
                } else {
                    curr.left = this.leftRotate(curr.left);
                    curr = this.rightRotate(curr);
                }
                curr.height = this.max(this.height(t.left), this.height(t.right)) + 1;
            }
        } else if (key.compareTo(curr.left.key) > 0) {
            curr.right = this.recInsert(key, val, t.right);
            if (this.getBalance(t) == -2) {
                if (key < curr.right.key) {
                    curr = this.leftRotate(curr);
                } else {
                    curr.right = this.rightRotate(curr.right);
                    curr = this.leftRotate(curr);
                }
                curr.height = this.max(this.height(t.left), this.height(t.right)) + 1;
            }
        }
        return curr;
    }

    /**
    * Insert into the subtree.
    * @param key the key to insert.
    * @param val the value to insert
    * @param curr the root of the subtree
    * @return value of inserted node
    */
    public V insert(K key, V val, BNode curr) {
        return this.recInsert(key, val, curr).value;
    }

    /**
    * Delete node from the subtree.
    * @param key the key to delete
    * @param curr the root of the subtree
    * @return deleted node
    */
    private BNode recDelete(K key, BNode curr) {
        if (root == null) {
            return root;
        }
        if (key < curr.key) {
            root.left = this.recDelete(key, curr.left);
        } else if (key > curr.key) {
            root.right = this.recDelete(key, curr.right);
        } else {
            // node with only one child or no child
            if ((curr.left == null) || (curr.right == null)) {
                BNode temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }
                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else { // One child case
                    curr = temp; // Copy non-empty child
                }
            } else { //Two children case
                BNode temp = curr.right; 
                while (temp.left != null) {
                    temp = temp.left;
                }
                // Copy the inorder successor's data to this node
                curr.key = temp.key;
                curr.value = temp.value
                // Delete the inorder successor
                curr.right = this.recDelete(temp.key, curr.right);
            }
        }
        // If the tree had only one node then return
        if (curr == null) {
            return curr;
        }
        // Update height
        root.height = this.max(this.height(curr.left), this.height(curr.right)) + 1;
        // Get balance factor
        int balance = this.getBalance(curr);
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && this.getBalance(curr.left) >= 0) {
            return this.rightRotate(curr);
        }
        // Left Right Case
        if (balance > 1 && this.getBalance(curr.left) < 0) {
            curr.left = this.leftRotate(curr.left);
            return this.rightRotate(curr);
        }
        // Right Right Case
        if (balance < -1 && this.getBalance(curr.right) <= 0) {
            return this.leftRotate(curr);
        }
        // Right Left Case
        if (balance < -1 && this.getBalance(curr.right) > 0) {
            curr.right = this.rightRotate(curr.right);
            return this.leftRotate(curr);
        }
        return curr;
    }

    /**
    * Delete node from the subtree.
    * @param key the key to delete
    * @param curr the root of the subtree
    * @return value of deleted node
    */
    public V delete(K key, BNode curr) {
        return this.recDelete(key, curr).value;
    }

}