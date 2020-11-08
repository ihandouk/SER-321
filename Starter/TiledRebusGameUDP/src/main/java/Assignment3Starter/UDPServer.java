package Assignment3Starter;

import java.io.*;
import java.net.*;
import org.json.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.util.Random;
import java.util.Base64;

class UDPServer {
    int boards=0;
    Random random;
    String steam = null;
    String[] imge = null;
    String[] pieces = null;
    String value = null;
    int isWrong=0;
    int num=0;
    JSONObject ObSon;
    int port = 9000 ;
    String host = "224.0.0.0";
    InetAddress mcastaddr = null;
    MulticastSocket sock = null;

    public static void main (String args[]) throws Exception {
        try {
            new UDPServer().clientRun(args);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void clientRun(String args[]){

        try {
          if (args.length == 1) {
              String nport[] = args[0].split("=");
              if (nport[0].equals("Pport")) {
                  port = Integer.parseInt(nport[1]);
              } else {
                  System.out.println("invalid argument " + nport[0]);
              }
          }
        } catch (NumberFormatException nfe) {
          System.out.println("Port must be an integer");
          System.exit(2);
        }
        try {
            mcastaddr = InetAddress.getByName(host);
            sock = new MulticastSocket(port);
            sock.joinGroup(mcastaddr);
            System.out.println("Server Port Number:Address " + port + ":" + host);
        } catch(Exception e) {
          e.printStackTrace();
          System.exit(2);
        }
        while (true) {
          System.out.println("Server is ON!");
          try {
            for(int i = 0; i < 3;i++){		// get messages from others in group
                byte[] buffer = new byte[10*1024];
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                sock.receive(messageIn);
                System.out.println("Received:" + new String(messageIn.getData()));
                String str = new String(messageIn.getData());
                parStri(str);
            }
            sock.leaveGroup(mcastaddr);
          } catch (JSONException e) {
              e.printStackTrace();
              JSONObject object = new JSONObject();
              movePacket(object, 400, "");
          } catch (Exception e) {
              e.printStackTrace();
          } 
        }
    }

    public UDPServer() {
        random = new Random(java.time.Clock.systemUTC().millis());
    }
    
    private void parStri(String obj) throws JSONException {
        JSONObject newObj = new JSONObject(obj);
        JSONObject headpiece = newObj.getJSONObject("headpiece");
        String string = headpiece.getString("string");
        String ObjType = headpiece.getString("sObject");
        switch (ObjType) {
            case "newgame":
                newGame(newObj, string);
                break;
            case "getquestion":
                getQuestion(string);
                break;
            case "CheckAnswer":
                CheckAnswer(newObj, string);
                break;
            case "transferData":
                transferData(newObj, string);
                break;
            default:
                break;
        }
    }
    private void movePacket(JSONObject JSObj, int ServerStatusCode, String string){
        try{JSONObject headpiece = new JSONObject();
            headpiece.put("ServerStatusCode", ServerStatusCode);
            headpiece.put("sObject", "response");
            headpiece.put("string", string);
            JSObj.put("header", headpiece);
            String pstring = JSObj.toString();
            DatagramPacket packet = new DatagramPacket(pstring.getBytes(), pstring.length(), mcastaddr, port);
            sock.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newGame(JSONObject newObj, String string) {
        JSONObject head = newObj.getJSONObject("head");
        String str = head.getString("steam");
        JSONObject object = new JSONObject();
        if (steam == null || str.equals(steam)) {
            steam = newObj.getString("steam");
            boards = newObj.getInt("boards");

            movePacket(object, 200, string);
        } else {

            movePacket(object, 226, string);
        }
    }
    private void getQuestion(String string) {
        ObSon = new JSONObject();
        int question1 = random.nextInt(10);
        int question2 = random.nextInt(20);
        int question3 = random.nextInt(30);
        int question4 = random.nextInt(40);
        ObSon.put("quest1",question1);
        ObSon.put("quest2", question2);
        ObSon.put("quest3", question3);
        ObSon.put("quest4", question4);
        JSONObject object = new JSONObject();
        JSONObject head = new JSONObject();
        head.put("question", ObSon);
        object.put("head", head);
        movePacket(object, 200, string);
    }
    private void CheckAnswer(JSONObject obj, String string) throws JSONException {
        JSONObject head = obj.getJSONObject("head");
        if (head.has("value")) {
            int val = head.getInt("value");
            int question1 = ObSon.getInt("quest1");
            int question2 = ObSon.getInt("quest2");
            int question3 = ObSon.getInt("quest3");
            int question4 = ObSon.getInt("quest4");
            int key = ObSon.getInt("quest1") + ObSon.getInt("quest2") * ObSon.getInt("quest3") + ObSon.getInt("quest4");;
            if (key == val) {
                corVal(string, false);
            } else {
                incorVal(string);
            }
        } else if (head.has("Text")) {
            String text = head.getString("Text");
            if (text.equals(value)) corVal(string, true);
            else incorVal(string);
        } else {
            throw new JSONException("Invalid Argument");
        }
    }
    private void endSimulation() {
        steam = null;
        boards = 0;
        num = 0;
        isWrong = 0;
        imge = null;
    }

    private void imageLookup(){
        try{
            if (imge == null) {
                File file = new File("img/imge");
                String[] filepath = file.list();
                int ind = random.nextInt(filepath.length);
                value = String.join(" ", filepath[ind].substring(0, filepath[ind].indexOf(".")).toLowerCase().split("-"));
                String[] args = {"img/imge/" + filepath[ind], this.boards + ""};
                BufferedImage[] fpat = new GridMaker().run(args);
                imge = new String[fpat.length];
                for (int i = 0; i < fpat.length; i++) {
                    ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
                    ImageIO.write(fpat[i], "jpg", byteArr);
                    byte[] imgBy = byteArr.toByteArray();
                    imge[i] = Base64.getEncoder().encodeToString(imgBy);
                    byteArr.close();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void corVal(String string,boolean win) {
        JSONObject object = new JSONObject();
        JSONObject head = new JSONObject();
        head.put("corVal", true);
        object.put("head", head);
        imageLookup();
        formImage(imge[num]);
        head.put("number", pieces.length);
        int row = num / boards;
        int col = num % boards;
        head.put("row", row);
        head.put("col", col);
        num++;
        if (win || num == imge.length) {
            head.put("win", true);
            endSimulation();
        } else {
            head.put("win", false);
        }
        movePacket(object, 200, string);;
    }
    private void transferData(JSONObject obj, String string){
        JSONObject objHead = obj.getJSONObject("head");
        int dig = objHead.getInt("dig");
        JSONObject object = new JSONObject();
        for(int i = dig; i<pieces.length; i++){
            JSONObject head = new JSONObject();
            head.put("dig",i);
            head.put("str", pieces[i]);
            object.put("head", head);
            movePacket(object, 200, string);
        }
    }
    private void formImage(String pict){
        int imgPiece = 0;
        imgPiece = (pict.length() + 1023) / 1024;
        pieces = new String[imgPiece];
        int k = 0;
        for(int i = 0; i < imgPiece; i++) {
            int j = k + 1024 > pict.length() ? pict.length() : k+1024;
            pieces[i] = pict.substring(j, k);
            k+= 1024;
        }
    }
    private void incorVal(String string) {
        isWrong++;
        JSONObject object = new JSONObject();
        JSONObject head = new JSONObject();
        head.put("corVal", false);
        if (isWrong <= 3) {
            head.put("lose", false);
        } else {
            endSimulation();
            head.put("lose", true);
        }
        object.put("head", head);
        movePacket(object, 200, string);
    }
}
