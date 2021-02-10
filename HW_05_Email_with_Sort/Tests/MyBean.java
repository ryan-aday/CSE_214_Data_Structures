/*****************************************************************/
/* Copyright 2013 Code Strategies                                */
/* This code may be freely used and distributed in any project.  */
/* However, please do not remove this credit if you publish this */
/* code in paper or electronic form, such as on a web site.      */
/*****************************************************************/


import java.io.Serializable;

public class MyBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String one;
	private String two;

	public MyBean() {
	}

	public MyBean(String one, String two) {
		this.one = one;
		this.two = two;
	}

	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		this.one = one;
	}

	public String getTwo() {
		return two;
	}

	public void setTwo(String two) {
		this.two = two;
	}

}
