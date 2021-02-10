/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>Email</code> class creates Tree
* object that has a to, cc, bcc, subject, body, and timestamp.
**/

//For serializable and for future possible functions
import java.io.Serializable;
import java.text.StringCharacterIterator;
import java.util.*;
import java.io.*;

import java.util.GregorianCalendar;  //For the calendar

public class Email implements Serializable{

  private String to, cc, bcc, subject, body;
  private GregorianCalendar timestamp;

  /**
  * A constructor that sets the to, cc, bcc, subjetc, and body from the parameters
  * and generates a timestamp for when the email is created.
  * O(1) since all methods are O(1).
  *
  * @param newTo
  *    The to of the Email.
  * @param newCC
  *    The CC of the Email.
  * @param newBCC
  *    The BCC of the Email.
  * @param newSubject
  *    The subject of the Email.
  * @param newBody
  *    The body of the Email.
  *
  **/


  public Email(String newTo, String newCC, String newBCC, String newSubject,
  String newBody){
    try{
      //Initializes the new GregorianCalendar
      GregorianCalendar newCalendar = new GregorianCalendar();

      setTo(newTo);
      setCC(newCC);
      setBCC(newBCC);
      setSubject(newSubject);
      setBody(newBody);
      timestamp = newCalendar;
    }
    catch (IllegalEmailException e){
      //e.printStackTrace();
      System.out.println("\nThe email must have \"@\" or \".com\", \".org\", and \".edu\". ");
    }

  }

  /**
  * Returns the to of the Email as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>to is defined.
  *
  * @return
  *   Returns the String format of to.
  *
  **/

  public String getTo(){
    return to;
  }

  /**
  * Returns the CC of the Email as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>cc is defined.
  *
  * @return
  *   Returns the String format of cc.
  *
  **/

  public String getCC(){
    return cc;
  }

  /**
  * Returns the BCC of the Email as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>bcc is defined.
  *
  * @return
  *   Returns the String format of bcc.
  *
  **/

  public String getBCC(){
    return bcc;
  }

  /**
  * Returns the subject of the Email as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>subject is defined.
  *
  * @return
  *   Returns the String format of subject.
  *
  **/

  public String getSubject(){
    return subject;
  }

  /**
  * Returns the body of the Email as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>body is defined.
  *
  * @return
  *   Returns the String format of body.
  *
  **/

  public String getBody(){
    return body;
  }

  /**
  * Returns the timestamp of the Email as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>timestamp is defined.
  *
  * @return
  *   Returns the GregorianCalendar format of timestamp.
  *
  **/

  public GregorianCalendar getTimestamp(){
    return timestamp;
  }

  /**
  * Sets a value for to.  O(1).
  *
  * @param newTo
  *    The new to of the email.
  *
  * @throws IllegalEmailException
  *    Thrown if the email isn't formatted correctly.
  *
  **/

  public void setTo(String newTo) throws IllegalEmailException{
    String[] data = newTo.split(",");
    for (String emailAddress : data){
      if (emailAddress.matches(".*?@.*?\\.com") || emailAddress.matches(".*?@.*?\\.gov") || emailAddress.matches(".*?@.*?\\.edu")){}
      else throw new IllegalEmailException("The email was not formatted correctly.");
    }
    to = newTo;
  }

  /**
  * Sets a value for cc.  O(1).
  *
  * @param newCC
  *    The new cc of the email.
  *
  * @throws IllegalEmailException
  *    Thrown if the email isn't formatted correctly.
  *
  **/

  public void setCC(String newCC) throws IllegalEmailException{
    String[] data = newCC.split(",");
    for (String emailAddress : data){
      if (emailAddress.matches(".*?@.*?\\.com") || emailAddress.matches(".*?@.*?\\.gov") || emailAddress.matches(".*?@.*?\\.edu")){}
      else throw new IllegalEmailException("The email was not formatted correctly.");
    }
    cc = newCC;
  }

  /**
  * Sets a value for bcc.  O(1).
  *
  * @param newBCC
  *    The new BCC of the email.
  *
  * @throws IllegalEmailException
  *    Thrown if the email isn't formatted correctly.
  *
  **/

  public void setBCC(String newBCC) throws IllegalEmailException{
    String[] data = newBCC.split(",");
    for (String emailAddress : data){
      /*
      if (emailAddress.contains("@") && (emailAddress.contains(".com") || emailAddress.contains(".org") ||
      emailAddress.contains(".edu") ) ){}
      */
      if (emailAddress.matches(".*?@.*?\\.com") || emailAddress.matches(".*?@.*?\\.gov") || emailAddress.matches(".*?@.*?\\.edu")){}
      else throw new IllegalEmailException("The email was not formatted correctly.");
    }
    bcc = newBCC;
  }

  /**
  * Sets a value for subject.  O(1).
  *
  * @param newSubject
  *    The new subject of the email.
  *
  **/

  public void setSubject(String newSubject){
    subject = newSubject;
  }

  /**
  * Sets a value for body.  O(1).
  *
  * @param newBody
  *    The new body of the email.
  *
  **/

  public void setBody(String newBody){
    body = newBody;
  }

  /**
  * Sets a value for timestamp.  O(1).
  *
  * @param newTimestamp
  *    The new timestamp of the email.
  *
  **/

  public void setTimestamp(GregorianCalendar newTimestamp){
    timestamp = newTimestamp;
  }

}
