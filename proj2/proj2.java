import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

class proj2 {
  public static void main(String[] args) {
    proj2 p2 = new proj2();
    p2.messageTest();
  }
  void messageTest(){
    Mailbox me = new Mailbox("me");
    Mailbox you = new Mailbox("you");

    MailboxList mbl = new MailboxList();
    mbl.addTo(me);
    mbl.addTo(you);

    Mailbox youMailbox = mbl.getUserMailbox("you");
    Mailbox meMailbox = mbl.getUserMailbox("me");

    Message test1 = new Message("you", "me", "this is the first test");
    test1.append("this is the header line for message 1.");
    test1.append("this is the second line for message 1.");
    test1.append("Best,");
    test1.append("Me");
    test1.compileMessage();
    test1.send(youMailbox, meMailbox);

    Message test2 = new Message("me", "you", "this is the second test");
    test2.append("Thank you for the previous message.");
    test2.append("It was very formal.");
    test2.append("Best,");
    test2.append("You");
    test2.compileMessage();
    test2.send(meMailbox, youMailbox);

    Message yourMessage = meMailbox.getMessage(0);
    Message myMessage = youMailbox.getMessage(0);

    myMessage.print();
    System.out.println("");
    yourMessage.print();
  }
}

class Message {
  String recipient;
  String sender;
  String subject;
  String messageText = "";
  LocalDateTime date = LocalDateTime.now();
  ArrayList<String> messageLines = new ArrayList<>();

  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  public Message(String recipient, String sender, String subject){
    this.recipient = recipient;
    this.sender = sender;
    this.subject = subject;
  }
  public void append(String line) {
    messageLines.add(line);
  }
  public String getMessageHeader() {
    return messageLines.get(0);
  }
  public void compileMessage() {
    for (int i=1; i<this.messageLines.size();i++){
      this.messageText += messageLines.get(i);
      this.messageText += "\n";
    }
  }

  @java.lang.Override
  public String toString() {
    return  "To: " + recipient + '\n' +
    "From: " + sender + '\n' +
    "Subject: " + subject + '\t'+ dtf.format(date) + '\n'+'\n' +
    this.getMessageHeader() + '\n' +
    messageText;
  }
  public void print(){
    System.out.println(this.toString());
  }
  public void send(Mailbox reciepientMailbox, Mailbox senderMailbox){
    senderMailbox.addSentMessage(this);
    reciepientMailbox.addMessage(this);
  }
}

class MailboxList {
  public static ArrayList<Mailbox> mailboxList = new ArrayList<>();
  public static void addTo(Mailbox mb){
    mailboxList.add(mb);
  }
  public static Mailbox getUserMailbox(String user){
    for (int i=0;i<mailboxList.size();i++){
      if (mailboxList.get(i).user.equals(user)) return mailboxList.get(i);
    }
    return null;
  }
}

class Mailbox {
  String user;
  ArrayList<Message> messages = new ArrayList<>();
  ArrayList<Message> sentMessages = new ArrayList<>();

  public Mailbox(String user){
    this.user = user;//class constructor ?
  }
  public ArrayList<Message> getSentMessages(){
    return sentMessages;// getter for sent messages arraylist
  }
  public ArrayList<Message> getMessages(){
    return messages;// getter for inbox
  }
  public void addMessage(Message message){
    messages.add(message);// arraylist message adder
  }
  public void addSentMessage(Message message){
    sentMessages.add(message);// arraylist message adder
  }
  public Message getMessage(int i){
    return messages.get(i); // getter for individual message given index ?
  }
  public Message getSentMessage(int i){
    return sentMessages.get(i);// getter for individual message given index?
  }
  public void removeMessage(int i){
    messages.remove(i);// popper for individual message given index
  }
  public void removeSentMessage(int i){
    messages.remove(i);//popper for individual message given index
  }
}
