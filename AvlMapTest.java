import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.AbstractMap;
import java.util.Set;
import java.util.Collection;

public class AvlMapTest {

	@Test
	public void testinsert() {
	   
	   AvlMap<Integer, String> um = new AvlMap<Integer, String>();
        
        
/*
        um.insert(1,"a");
        um.insert(2,"b");
        um.insert(6,"c");
        um.insert(4,"d");
        um.insert(3,"e");

*/

System.out.println("hello");
        um.insert(1,"a");
        um.insert(2,"b");
        um.insert(6,"c");
        um.insert(4,"d");
        um.insert(3,"e");

               System.out.println(um.preOrder());

        System.out.println(um.inOrder());

        System.out.println(um.postOrder());
     
    }


/*
	@Test
	public void testinsert() {
	   
	   ReclAvlMap<Integer, String> um = new RecAvlMap<Integer, String>();
        
        um.recInsert(1,"a");
        um.recInsert(2,"b");
        um.recInsert(6,"c");
        um.recInsert(4,"d");
        um.recInsert(3,"e");

  
        assertTrue(um.hasValue("a"));
        assertTrue(um.hasValue("b"));
        assertTrue(um.hasValue("c"));
        assertTrue(um.hasValue("d"));
        assertFalse(um.hasValue("WRONG"));
        System.out.println(um.preOrder());

        System.out.println(um.inOrder());

        System.out.println(um.postOrder());
    }

*/


}