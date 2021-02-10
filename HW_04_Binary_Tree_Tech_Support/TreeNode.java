/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>TreeNode</code> class creates a TreeNode
* object that has a label, message, and prompt with
* accessor and mutator methods.
**/

public class TreeNode{

  //For normal and extra credit assignment; capacity should be 9 nodes
  //since this is a telephone service


  public TreeNode[] allNodes;

  private String label, message, prompt;
  private int size;

  // Invariants:
  // allNodes is an array that should only contain up to 9 nodes at a time.
  // label is the "name" of this TreeNode. It will be used when constructing
  // the tree.
  // message is a question or just a normal message. If this TreeNode has
  // children TreeNodes, it should then traverse one level downwards to each
  // child node to get the prompts as answers. If this TreeNode is a leaf node,
  // then it should just display the message which will act as a solution.
  // prompt is a possible answer to a question.
  // size is how many nodes are in the allNodes of this node.
  // None of these are static due to issues with traversing if they are; only
  // the last node would be displayed otherwise.


  /**
  * Constructs the TreeNode.
  * O(1) since all methods are O(1).
  *
  * @param newLabel
  *    The new label for TreeNode.
  * @param newMessage
  *    The new message for TreeNode.
  * @param newPrompt
  *    The new prompt for TreeNode.
  *
  **/

  public TreeNode(String newLabel, String newMessage, String newPrompt){
    //Creates new node array with capacity of 9
    allNodes = new TreeNode[9];
    label = newLabel;
    message = newMessage;
    prompt = newPrompt;
  }

  /**
  * Returns the label of the TreeNode as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The label is defined.
  *
  * @return
  *   Returns the String format of label.
  *
  **/

  public String getLabel(){
    return label;
  }


  /**
  * Returns the message of the TreeNode as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The message is defined.
  *
  * @return
  *   Returns the String format of message.
  *
  **/

  public String getMessage(){
    return message;
  }

  /**
  * Returns the prompt of the TreeNode as a String.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The prompt is defined.
  *
  * @return
  *   Returns the String format of prompt.
  *
  **/

  public String getPrompt(){
    return prompt;
  }

  /**
  * Returns the size of the TreeNode as an int.  O(1).
  *
  * <dt>Precondition:
  *   <dd>The size is defined.
  *
  * @return
  *   Returns the int format of size.
  *
  **/

  public int getSize(){
    return size;
  }

  /**
  * Returns whether the TreeNode has any elements at all in its
  * allNodes array.  O(n).
  *
  * @return
  *   Returns true if there is no node in allNodes, false if there is one.
  *
  **/

  public boolean isLeaf(){
    //returns true if there are no nodes in allNodes
    for (int i = 0; i < this.allNodes.length; i++){
      if (this.allNodes[i] != null){
        return false;
      }
    }
    //Returns true if all null
    return true;
  }

  /**
  * Increments size by 1.  O(1).
  *
  **/

  public void addSize(){
    size++;
  }

  /**
  * Returns true if can add node to allNodes.  O(1).
  *
  * @param nodes
  *    The new node to add to allNodes.
  *
  * @return
  *   Returns true if node successfully added to allNodes, false if allNodes
  *   is full and wasn't added.
  *
  **/

  public boolean addNode(TreeNode node){
    if (getSize() < 9){
      allNodes[getSize()] = node;
      addSize();
      return true;
    }
    return false;
  }

  /**
  * Prints out the label, prompt, and message formatted for all of the
  * nodes in preorder.  O(nlogn) since it's parsing through a tree.
  *
  **/

  public void preOrder(){
    System.out.println("Label: " + getLabel());
    System.out.println("Prompt: " + getPrompt());
    System.out.println("Message: " + getMessage() + "\n");

    //Must do this for every single cell of the 9-array
    if (allNodes[0] != null){
      allNodes[0].preOrder();
    }
    if (allNodes[1] != null){
      allNodes[1].preOrder();
    }
    if (allNodes[2] != null){
      allNodes[2].preOrder();
    }
    if (allNodes[3] != null){
      allNodes[3].preOrder();
    }
    if (allNodes[4] != null){
      allNodes[4].preOrder();
    }
    if (allNodes[5] != null){
      allNodes[5].preOrder();
    }
    if (allNodes[6] != null){
      allNodes[6].preOrder();
    }
    if (allNodes[7] != null){
      allNodes[7].preOrder();
    }
    if (allNodes[8] != null){
      allNodes[8].preOrder();
    }

  }

  //NOTE:  Unneeded but potentially useful
  /*
  public boolean isFull(){
    return this.getSize() == 9;
  }

  public void removeSize(){
    size--;
  }

  public TreeNode getNode(int index){
    return this.allNodes[index];
  }
  */

}
