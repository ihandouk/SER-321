import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;

import org.json.JSONObject;


public class Remote {
    private TopicSession jSession = null;
    private TopicConnection jConnection = null;
    private TopicPublisher jPublisher = null;

    public Remote(){
        establishJMSConnection();
    }

    private void publishJMSConnection(String string){
        try{
           TextMessage message = jSession.createTextMessage();
           JSONObject json = new JSONObject();
           json.put("action", string);
           message.setText(json.toString());
           jPublisher.publish(message);
        }catch(JMSException jmsEx){
  
        }
     }

    private void establishJMSConnection(){
        try{
            InitialContext jndi = new InitialContext();
            TopicConnectionFactory cFactory = (TopicConnectionFactory)jndi.lookup("TopicConnectionFactory");
            jConnection = cFactory.createTopicConnection("default", "password");
            jSession = jConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic chatTopic = (Topic) jndi.lookup("Remote");
            jPublisher = jSession.createPublisher(chatTopic);
            jPublisher.setDeliveryMode(DeliveryMode.PERSISTENT);
        }catch(Exception e){
            System.out.println("Invalid JMS Connection!");
        }
    }

    private void closeJMSConnection(){
        try{
           jSession.close();
           jConnection.close();
        }catch(JMSException e){
           System.out.println("No Esteblished Connection Available.");
        }
     }

     public static void main(String[] args){
        Remote remote = null;
         try{
             remote = new Remote();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             String iString = "";
             while(true){
                 iString = bufferedReader.readLine();
                 if(iString.equalsIgnoreCase("Exit")){
                     System.out.println("Exitig Now!");
                     break;
                 }else if(iString.equalsIgnoreCase("stop")){
                     System.out.println("Stoping all Music Threads Now!");
                     remote.publishJMSConnection("stop");
                 }else if(iString.equalsIgnoreCase("play")){
                     System.out.println("Playing Must on All Threads");
                     remote.publishJMSConnection("play");
                 }else{
                     System.out.println("Invalid Input!");
                 }
             }
         }catch(IOException ioe){
             System.out.println("Invalid Input");
         }finally{
             if(remote!=null){
                 remote.closeJMSConnection();
             }
         }
     }
}
