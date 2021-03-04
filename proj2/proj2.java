import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

class proj2 {
  public static void main(String[] args) {

  }
  void messageTest(){
    
  }
}

class Message {
  String recipient;
  String sender;
  String subject;
  String messageText;
  Date date;
  ArrayList<String> messagelines;

  public Message(String recipient, String sender, String subject){
    this.recipient = recipient;
    this.sender = sender;
    this.subject = subject;
  }
  public void append(String line) {
    messageLines.add(line);
  }
  public String getMessageHeader() {
    messageLines.get(0);
  }

  @java.lang.Override
  public String toString() {
    return  "To: " + recipient + '\n' +
    "From: " + sender + '\n' +
    "Subject: " + subject + '\t'+ date + '\n' +
    messageText;
  }
  public void print(){
    System.out.println(Message.toString());
  }
  public void send(Mailbox sender, Mailbox recipient, String subject){}
}

class MailboxList {
  public static ArrayList<Mailbox> mailboxList;
  public static Mailbox getUserMailbox(String user){} // getter for mailbox
}

class Mailbox {
  String user;
  ArrayList<Message> messages;
  ArrayList<Message> sentMessages;

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
    messages.get(i); // getter for individual message given index ?
  }
  public Message getSentMessage(int i){
    sentMessages.get(i);// getter for individual message given index?
  }
  public void removeMessage(int i){
    messages.remove(i);// popper for individual message given index
  }
  public void removeSentMessage(int i){
    messages.remove(i);//popper for individual message given index
  }
}
