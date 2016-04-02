//AVLMap.java
//Yu-chi Chang, Allan Wang
//ychang64, awang53
//EN.600.226.01/02
//P3

import java.util.Map;

public class AvlMap<K extends Comparable<? super K>, V> extends BSTMap<K,V> 
    implements MapJHU<K, V>, Iterable<Map.Entry<K, V>> {

    private class AvlNode extends BNode {
        private int height = 0; 

        AvlNode(K k, V v) {
            BNode(k, v);
            this.height = 0;
        }
        public void setHeight(int h) {
            this.height = h;
        }
        public int getHeight() {
            return this.height;
        }        

    } //end inner class 

    private BSTMap<K, V> avlTree;
    private BNode root;  

    public AvlMap() {
        avlTree = new BSTMap<K,V> ();

    }

    // A utility function to get height of the tree
    public int height(BNode n) {
        if (n == null) {
            return 0;
        }
        return n.height;
    }
    
    /**
    * Insert into the tree; duplicates are ignored.
    * @param x the item to insert.
    */
    public void insert(BNode n) {
        this.root = insert(n,this.root);
    }

    public void insert(K key, V val, BNode curr) {
        BSTMap.put(key, val, curr);  //

    }

    private BNode insert (BNode n, BNode t) {
        if (n == null) {
            throw new java.lang.NullPointerException(); 
        } 
        int diff = t.key.compareTo(n.key);

        if (diff > 0) { //look left
            t.left = insert(n, t.left); 
            if (height (t.left) - height(t.right) == 2) {
                if (t.key.compareTo(t.left.key) < 0) {

                }
            }
        }

            if (diff > 0) {  // look left
                //key not exists in current tree, add new node
                if (this.left == null) { 
                    this.left = new BNode(k, val); 
                    return null;
                } else {
                    this.left.insert(k, val);
                }
            }



    }





        /**
         * Internal method to insert into a subtree.
         * @param x the item to insert.
         * @param t the node that roots the tree.
         * @return the new root.
         */

        private AvlNode insert( Comparable x, AvlNode t )
        {
            if( t == null )
                t = new AvlNode( x, null, null );
            else if( x.compareTo( t.element ) < 0 )
            {
                t.left = insert( x, t.left );
                if( height( t.left ) - height( t.right ) == 2 )
                    if( x.compareTo( t.left.element ) < 0 )
                        t = rotateWithLeftChild( t );
                    else
                        t = doubleWithLeftChild( t );
            }
            else if( x.compareTo( t.element ) > 0 )
            {
                t.right = insert( x, t.right );
                if( height( t.right ) - height( t.left ) == 2 )
                    if( x.compareTo( t.right.element ) > 0 )
                        t = rotateWithRightChild( t );
                    else
                        t = doubleWithRightChild( t );
            }
            else
                ;  // Duplicate; do nothing
            t.height = max( height( t.left ), height( t.right ) ) + 1;
            return t;
        }
}