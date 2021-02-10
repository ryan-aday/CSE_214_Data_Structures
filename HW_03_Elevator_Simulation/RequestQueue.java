/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>RequestQueue</code> class creates a RequestQueue object that is
* an extension of the Vector class to act as a queue and can enqueue/dequeue
* as well as declare whether such a queue is empty.
*
**/

import java.util.*;  //Used for implementing Vector class

public class RequestQueue extends Vector<Request>{

  private static Vector<Request> data;
  private static int front, rear;

  // Invariants:
  // data represents the actual queue.  front and rear are ints that represent
  // the front and rear index of the queue.
  // None of these vars should change.

  /**
  * Constructs the RequestQueue with a capacity of 10 and increments when
  * adding space using the Vector .addElement() method to save memory.
  * O(1).
  *
  **/

  public RequestQueue (){
    front = -1;
    rear = -1;
    data = new Vector<Request>(10, 1);

  }

  /**
  * Returns whether RequestQueue is empty.  O(1).
  *
  * <dt>Precondition:
  *   <dd>front is defined.
  *
  * @return
  *   Returns true if the front -1, meaning empty.  Vice versa if not.
  *
  **/

  public boolean isEmpty(){
    return front == -1;
  }

  /**
  * Adds a new value to the back of RequestQueue.  O(1) assuming all
  * Vector methods are O(1), but .addElement() increases the size if needed and
  * appends the element so O(n) if it's unoptimized.
  *
  * @param newRequest
  *    The new to be added to thhe back of RequestQueue.
  *
  **/

  public void enqueue(Request newRequest){

    //For full queue
    if (rear + 1 == front && !isEmpty()){
      //Uses vector to increase size of the queue by 1, moves up rear by 1.
      data.addElement(newRequest);
      rear++;

    }
    //For empty queue
    else if (isEmpty()){
      //System.out.println("Empty");

      front = 0;
      rear = 0;
      data.addElement(newRequest);
    }

    else {
      //System.out.println("Not Empty");

      rear++;
      data.addElement(newRequest);
    }
  }

  /**
  * Removes a new value from the front of RequestQueue.  O(1) assuming all
  * Vector methods are O(1), but .removeElement() decreases the size after
  * removing an element so O(n) if it's unoptimized.
  *
  * @return
  *   Returns front Request if present in queue, throws exception if not present.
  *
  * @throws EmptyQueueException
  *   Indicates there are no Requests in RequestQueue.
  *
  **/

  public Request dequeue() throws EmptyQueueException{

    //For empty queue
    if (isEmpty()) throw new EmptyQueueException("There is no Request to remove.");

    Request answer = data.get(front);


    //For only one element in queue
    if(front == rear){
      data.removeElementAt(0);
      front = -1;
      rear = -1;
    }

    else{
      data.removeElementAt(0);
      rear--;
    }

    return answer;

  }

  /**
  * Returns the size of the RequestQueue using a Vector method.  O(1).
  *
  *
  * @return
  *   Returns the int format of the size of the RequestQueue.
  *
  **/

  public int size(){
    return data.size();
  }

  /**
  * Prints a formatted line for all variables of all Requests in RequestQueue.
  * O(n).
  *
  *
  *
  **/

  public void printQueue(){
    if (!isEmpty()){
      for (int i = getFront(); i < getRear() + 1; i++){
        System.out.print("(" + data.get(i).getSource() + ", " + data.get(i).getDest() + ", " + data.get(i).getTime() + ") ");
      }
    }
  }

  /**
  * Returns the front index of the RequestQueue as an int.  O(1).
  *
  * <dt>Precondition:
  *   <dd>front is defined.
  *
  * @return
  *   Returns the int format of front.
  *
  **/

  public int getFront(){
    return front;
  }

  /**
  * Returns the rear index of the RequestQueue as an int.  O(1).
  *
  * <dt>Precondition:
  *   <dd>rear is defined.
  *
  * @return
  *   Returns the int format of rear.
  *
  **/

  public int getRear(){
    return rear;
  }

}
