public class test{
  public static void main(String[] args){

    //For ItemInfo

    try{
      ItemInfo item = new ItemInfo();
      item.setRFID("123456789");
      item.setOriginalLocation("s12345");
      item.setCurrentLocation("s12354");
      System.out.println(item.getRFID());
      System.out.println(item.getOriginalLocation());
      System.out.println(item.getCurrentLocation());

      System.out.println("_______________________________________________");


      //For ItemInfoNode
      ItemInfo item2 = new ItemInfo();
      item2.setRFID("222222222");
      item2.setOriginalLocation("s22222");
      item2.setCurrentLocation("c222");

      ItemInfo item3 = new ItemInfo();
      item3.setRFID("333333333");
      item3.setOriginalLocation("s33333");
      item3.setCurrentLocation("c333");

      ItemInfoNode node1 = new ItemInfoNode();
      node1.setInfo(item);
      ItemInfoNode node2 = new ItemInfoNode();
      node2.setInfo(item2);
      ItemInfoNode node3 = new ItemInfoNode();
      node3.setInfo(item3);

      node2.setPrev(node1);
      node2.setNext(node3);

      System.out.println(node2.getPrev().getInfo().getRFID());
      System.out.println(node2.getInfo().getRFID());
      System.out.println(node2.getNext().getInfo().getRFID());

      //To see if null
      //System.out.println(node3.getNext().getInfo().getRFID());


      System.out.println("_______________________________________________");



      //For ItemList

      //For insertInfo

      System.out.println("First Inst\n");

      ItemList list = new ItemList();

      list.insertInfo("One", "010A00001", 11.11, "s11111");
      System.out.println("Head: " + list.getHead().getInfo().getName() );
      System.out.println("Tail: " + list.getTail().getInfo().getName() );
      System.out.println("Cursor: " + list.getCursor().getInfo().getName() + "\n");


      //System.out.println("Test");
      //System.out.println(Long.parseLong("100B00011", 16));

      System.out.println("Second Inst\n");

      list.insertInfo("Two", "B00000011", 22.22, "s22222");
      System.out.println("Head: " + list.getHead().getInfo().getName() );
      System.out.println("Tail: " + list.getTail().getInfo().getName() );
      System.out.println("Cursor: " + list.getCursor().getInfo().getName() + "\n");

      System.out.println("Third Inst\n");

      list.insertInfo("Three", "B00000014", 33.33, "s33333");
      System.out.println("Head: " + list.getHead().getInfo().getName() );
      System.out.println("Tail: " + list.getTail().getInfo().getName() );
      System.out.println("Cursor: " + list.getCursor().getInfo().getName() + "\n");

      System.out.println("Fourth Inst\n");

      list.insertInfo("Four", "444444444", 44.44, "s44444");
      System.out.println("Head: " + list.getHead().getInfo().getName() );
      System.out.println("Tail: " + list.getTail().getInfo().getName() );
      System.out.println("Cursor: " + list.getCursor().getInfo().getName() + "\n");


      //Works!!!

      System.out.println("Current order is 1423");

      System.out.println("_______________________________________________");

      //For Print all
      list.printAll();

      //Works!!!

      //For moveItem

      //Remember Two is at the tail
      //Format:  rfid, firstLocation, destLocation
      System.out.println(list.moveItem("010A00001", "s11111", "c123"));
      System.out.println(list.moveItem("444444444", "s22222", "c444"));
      System.out.println(list.moveItem("B00000011", "s22222", "c222"));
      System.out.println(list.moveItem("B00000014", "s33333", "c333"));


      list.printAll();

      //Works!!!

      //For removeAllPurchased

      //list.removeAllPurchased();
      //list.printAll();

      //Works!!!

      //For printByLocation

      //list.printByLocation("out");

      //Works!!

      //For printByRFID

      //list.printByRFID("B00000014");

      //For cleanStore
      //list.cleanStore();
      list.printAll();

      //Works!!!

      //For checkOut()
      System.out.println(list.checkOut("c123"));
      list.printAll();
    }catch (RFIDCharsException e){
      System.out.println("The RFID must contain A-F or 0-9 only.");
    }catch (RFIDLengthException e){
      System.out.println("The RFID must be 9 characters long.");
    }catch (LocationStartException e){
      System.out.println("The original location must start with 's'.");
    }catch (LocationLengthException e){
      System.out.println("The current location must be 6 characters long and the original equaltion 9 characters long.");
    }catch (LocationCharsException e){
      System.out.println("The characters after the 's' or 'c' must be numerical digits.");
    }catch (IllegalArgumentException e){
      System.out.println("Initial position can't be \"out\" and must start with either a 'c' or an 's'.");
    }
  }
}
