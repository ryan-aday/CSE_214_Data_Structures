/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>IitemInfoNode</code> class creates an ItemInfoNode
* object that has a ItemInfo and access to node "in front" and "behind" it with
* accessor and mutator methods.
**/

public class ItemInfoNode{

  ItemInfoNode prevNode, nextNode;
  ItemInfo currentInfo;

  /**
  * Constructs the ItemInfo.
  *
  * O(1) since all methods are O(1).
  *
  **/

  public ItemInfoNode(){
    currentInfo = new ItemInfo();
  }

  /**
  * Sets a value for currentInfo.  O(1).
  *
  * @param info
  *    The new ItemInfo for currentInfo.
  *
  **/

  public void setInfo(ItemInfo info){
    currentInfo = info;
  }

  /**
  * Returns the currentInfo of the ItemInfo as an ItemInfo.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The currentInfo is defined.
  *
  * @return
  *   Returns the ItemInfo format of currentInfo.
  *
  **/

  public ItemInfo getInfo(){
    return currentInfo;
  }

  /**
  * Sets a value for nextNode.  O(1).
  *
  * @param info
  *    The new ItemInfoNode for nextNode.
  *
  **/

  public void setNext(ItemInfoNode next){
    nextNode = next;
  }

  /**
  * Sets a value for prevNode.  O(1).
  *
  * @param info
  *    The new ItemInfoNode for prevNode.
  *
  **/

  public void setPrev(ItemInfoNode prev){
    prevNode = prev;
  }

  /**
  * Returns the nextNode of the ItemInfoNode as an ItemInfoNode.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The nextNode is defined.
  *
  * @return
  *   Returns the ItemInfoNode format of nextNode.
  *
  **/

  public ItemInfoNode getNext(){
    return nextNode;
  }

  /**
  * Returns the prevNode of the ItemInfoNode as an ItemInfoNode.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The prevNode is defined.
  *
  * @return
  *   Returns the ItemInfoNode format of prevNode.
  *
  **/

  public ItemInfoNode getPrev(){
    return prevNode;
  }

}
