//AVLMap.java
//Yu-chi Chang, Allan Wang
//ychang64, awang53
//EN.600.226.01/02
//P3

import java.util.Map;

public class AvlMap<K extends Comparable<? super K>, V> extends BSTMap<K,V> 
    implements MapJHU<K, V>, Iterable<Map.Entry<K, V>> {

        private class AvlNode extends BNode { //Bnode is private cannot be accessed. 
        }

    private BSTMap<K, V> avlTree;
    private BNode root;  //cannot access private inner class

    public AvlMap() {
        avlTree = new BSTMap<K,V> ();

    }

    /**
    * Insert into the tree; duplicates are ignored.
    * @param x the item to insert.
    */
    public void insert(BNode n) {
        this.root = insert(n,this.root);
    }

/*
    private AvlNode insert (AvlNode n, AvlNode r) {
        if (n == null) {
            throw new java.lang.NullPointerException(); 
        } 
    }

   */ 

        /**
         * Internal method to insert into a subtree.
         * @param x the item to insert.
         * @param t the node that roots the tree.
         * @return the new root.
         */
    /*
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
    */
}