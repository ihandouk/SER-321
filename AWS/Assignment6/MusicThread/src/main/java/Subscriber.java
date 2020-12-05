import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Subscriber implements javax.jms.MessageListener {
    private TopicSession jSession;
    private TopicConnection jConnection;
    private TopicSubscriber jSub;
    private JSONArray pCount;
    private final File sFile;

    public Subscriber (){
        pCount = new JSONArray();
        sFile = new File("sFile.save");
        try{
            if(sFile.createNewFile()){
                System.out.println("New record file has been created!");
            }
            else{
                Scanner scan = new Scanner(sFile);
                String jtem = "";
                while(scan.hasNextLine()){
                    jtem = jtem + scan.nextLine();
                }
                try{
                    JSONTokener jtokener = new JSONTokener(jtem);
                    pCount = new JSONArray(jtokener);
                }catch(JSONException je){
                    System.out.println("File is Corrupted");
                    System.out.println("Record file path: "+sFile.getCanonicalPath());
                }
            }
        }
        catch(IOException ioe){
            System.out.println("Error Creating Record file!");
            System.exit(0);
        }
        updateTrackRanking();
        establishJMSConnection();
    }

    private void establishJMSConnection(){
        try{
            InitialContext jndi = new InitialContext();
            TopicConnectionFactory cFactory = (TopicConnectionFactory)jndi.lookup("TopicConnectionFactory");
            jConnection = cFactory.createTopicConnection();
            jConnection.setClientID("subscriber");
            jSession = jConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic chatTopic = (Topic) jndi.lookup("MyTopic");
            jSub = jSession.createDurableSubscriber(chatTopic, "Subscriber");
            jSub.setMessageListener(this);
            jConnection.start();
        }catch(Exception e){
            System.out.println("Invalid JMS Connection!");
        }
    }

    private void closeJMSConnection(){
        try{
            jSession.close();
            jConnection.close();
        }catch(JMSException jmse){
            System.out.println("No Established Connection Available!");
        }
    }

    private void updateRecordFile(){
        FileWriter rite = null;
        try{
            rite = new FileWriter(sFile);
            rite.write(pCount.toString());
        }catch(IOException ioe){
            System.out.println("Record File is Corrupted");
        }finally{
            try{
                rite.close();
            }catch(IOException ioe){
                System.out.println("Invalid Action!");
            }
        }
    }

    private void updatePlayCount(String sName){
        for(int i = 0; i<pCount.length(); i++){
            JSONObject jobj = pCount.getJSONObject(i);
            if(jobj.getString("song").equalsIgnoreCase(sName)){
                int inCount = jobj.getInt("count");
                inCount++;
                jobj.put("count", inCount);

                return;
            }
        }

        JSONObject nSong = new JSONObject();
        nSong.put("song", sName);
        nSong.put("count", 1);
        pCount.put(nSong);
    }

    private void updateTrackRanking(){
        System.out.println("\n\nLIST OF MUSIC RANKINGS:");
        JSONArray jArray = new JSONArray(new JSONTokener(pCount.toString()));
        while(jArray.length()>0){
            int ind = 0;
            int large = 0;

            for(int i = 0; i < jArray.length(); i++){
                JSONObject jsonObject = jArray.getJSONObject(i);
                int sCount = jsonObject.getInt("count");
                if(sCount > large){
                    large = sCount;
                    ind = i;
                }
            }
            System.out.println("Count: "+ large +" | Music: " + jArray.getJSONObject(ind).get("song"));
            jArray.remove(ind);
        }
    }

    public void onMessage(Message message){
        try{
           if(message instanceof TextMessage){
              TextMessage textMessage = (TextMessage) message;
              JSONTokener jtokener = new JSONTokener(textMessage.getText());
              JSONObject obj = new JSONObject(jtokener);
              String sName = obj.getString("song");
              updatePlayCount(sName);
              updateRecordFile();
              updateTrackRanking();
           }
        }catch(JMSException e1){
           e1.printStackTrace();
        }
    }

    public static void main(String[] args){
        Subscriber subscriber = null;
        try{
            subscriber = new Subscriber();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String iString = "";
            while(true){
                iString = bufferedReader.readLine();
                if(iString.equalsIgnoreCase("Exit")){
                    break;
                }
            }
        }catch(IOException ioe){
            System.out.println("Invalid input!");
        }finally{
            if(subscriber != null){
                subscriber.closeJMSConnection();
            }
        }
    }
}