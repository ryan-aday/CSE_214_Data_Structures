public class test{
  public static void main(String[] args){

    Email email1 = new Email("1.@.com", "1.@.com", "1.@.com", "1.@.com", "1.@.com");
    Folder folder = new Folder("test");

    folder.addEmail(email1);
    System.out.println(folder.getEmail(0).getTo());

    Mailbox mailbox = new Mailbox();
    mailbox.composeEmail();
    mailbox.composeEmail();
    System.out.println(mailbox.getFolder("Inbox").getEmail(0).getTo());
    System.out.println(mailbox.getFolder("Inbox").getEmail(1).getTo());

    mailbox.getFolder("Inbox").printAllEmails();
    Folder targetFolder =  mailbox.getFolder("Inbox");
    targetFolder.printAllEmails();



  }
}
