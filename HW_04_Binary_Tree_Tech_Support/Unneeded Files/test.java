import java.io.FileNotFoundException;

public class test{
  public static void main(String[] args){

    //For ScannerExample
    try{
      Tree tree = new Tree("sample.txt");
      //tree.preOrder();
      tree.tempRoot = tree.mainRoot;
      System.out.println(tree.getTreeNodeReference("3-2").getLabel());
      tree.tempRoot = tree.getTreeNodeReference("3-2");
      System.out.println(tree.tempRoot.getLabel());

    }catch (FileNotFoundException e){
      System.out.println("File not found");
    }

  }
}
