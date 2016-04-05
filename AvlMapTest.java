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
    public void testPut() {
               AvlMap<Integer, String> um = new AvlMap<Integer, String>();
          
          System.out.println("heeeee");
        um.put(1,"a");
        um.put(2,"b");
        um.put(6,"c");
        um.put(4,"d");
        um.put(3,"e");

        System.out.println(um.preOrder());

        System.out.println(um.inOrder());

        System.out.println(um.postOrder());

        System.out.println("now change 4's value");

        um.put(4, "f");

        System.out.println(um.preOrder());

        System.out.println(um.inOrder());

        System.out.println(um.postOrder());

        System.out.println("4's new value should be f " + um.get(4));

        System.out.println("size should be 5 " + um.size());


        um.remove(2);
        um.remove(1);
 
        System.out.println(um.preOrder());
        System.out.println(um.inOrder());
        System.out.println(um.postOrder());
        System.out.println("size should be 3 " + um.size());


        System.out.println("hesdfadrg");
    }


/*

	@Test
	public void testadd() {
	   
	   AvlMap<Integer, String> um = new AvlMap<Integer, String>();
          
        um.add(1,"a");
        um.add(2,"b");
        um.add(6,"c");
        um.add(4,"d");
        um.add(3,"e");

        System.out.println(um.preOrder());

        System.out.println(um.inOrder());

        System.out.println(um.postOrder());

        System.out.println("now change 4's value");

        um.add(4, "f");

        System.out.println(um.preOrder());

        System.out.println(um.inOrder());

        System.out.println(um.postOrder());

        System.out.println("4's new value should be f " + um.get(4));

        System.out.println("size should be 5 " + um.size());


        um.delete(2);
        um.delete(4);
        um.delete(1);
 
        System.out.println(um.preOrder());
        System.out.println(um.inOrder());
        System.out.println(um.postOrder());
        System.out.println("size should be 2 " + um.size());


    }

*/
/*

    @Test
        public void testIterator() {
            AvlMap<Integer, String> um = new AvlMap<Integer, String>();            
            um.add(1,"a");
            um.add(2,"b");
            um.add(3,"c");
            um.add(4,"d");
            um.add(5,"e");
            System.out.println("um map size is: " + um.size());
            Iterator it = um.iterator();
            String str = "[";
            while (it.hasNext()) {
                String s = it.next().toString();
                //System.out.print(s + " ");
                str += s;
              //  str += "]";
                if (it.hasNext()) {
                    str += ", ";
                } 
        }
         str += "]";
            String inOrder = um.inOrder().toString();
            System.out.println("String inOrder " + inOrder);
            assertEquals(inOrder, str);
         
        }

*/



/*
	@Test
	public void testadd() {
	   
	   ReclAvlMap<Integer, String> um = new RecAvlMap<Integer, String>();
        
        um.recadd(1,"a");
        um.recadd(2,"b");
        um.recadd(6,"c");
        um.recadd(4,"d");
        um.recadd(3,"e");

  
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