import java.util.*;

public class VectorExample {

   public static void main(String args[]) {
      /* Vector of initial capacity(size) of 10 */
      Vector<String> vec = new Vector<String>(10, 1);

      /* Adding elements to a vector*/
      vec.addElement("Apple");
      vec.addElement("Orange");
      vec.addElement("Mango");
      vec.addElement("Fig");
      vec.addElement("Apple");
      vec.addElement("Orange");
      vec.addElement("Mango");
      vec.addElement("Fig");
      vec.addElement("Apple");
      vec.addElement("Orange");
      vec.addElement("Mango");
      vec.addElement("Fig");

      /* check size and capacityIncrement*/
      System.out.println("Size is: "+vec.size());
      System.out.println("Default capacity increment is: "+vec.capacity());


      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));
      System.out.println((int)(Math.random() * (double)5 + 1.0));


   }
}
