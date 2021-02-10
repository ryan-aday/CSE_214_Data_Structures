/*****************************************************************/
/* Copyright 2013 Code Strategies                                */
/* This code may be freely used and distributed in any project.  */
/* However, please do not remove this credit if you publish this */
/* code in paper or electronic form, such as on a web site.      */
/*****************************************************************/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSerialization {

	public static void main(String[] args) {

		try {
			MyBean mb = new MyBean("first value", "second value");

			// write object to file
      /*
			FileOutputStream fos = new FileOutputStream("mybean.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(mb);
			oos.close();
      */

			// read object from file
			FileInputStream fis = new FileInputStream("mybean.ser");
      /*
			ObjectInputStream ois = new ObjectInputStream(fis);
			MyBean result = (MyBean) ois.readObject();
			ois.close();

			System.out.println("One:" + result.getOne() + ", Two:" + result.getTwo());

      */

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    /* catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
	}

}
