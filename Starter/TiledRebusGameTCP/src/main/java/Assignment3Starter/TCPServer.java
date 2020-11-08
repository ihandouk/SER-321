package Assignment3Starter;

import java.io.*;
import java.net.*;
import org.json.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.util.Random;
import java.util.Base64;

class TCPServer {
    int boards=0;
    Random random;
    String steam = null;
    String[] imge = null;
    String value = null;
    int isWrong=0;
    int num=0;
    JSONObject ObSon;
    ServerSocket ss = null;
    Socket sock = null;
    BufferedReader input = null;
    PrintWriter output = null;

    public static void main (String args[]) throws Exception {
        try {
            new TCPServer().clientRun(args);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    private String readObj() throws IOException, JSONException {
        StringBuilder builder = new StringBuilder();
        String ln = input.readLine();
        builder.append(ln);
        String content = builder.toString();
        return content;
    }
    public void clientRun(String args[]) throws IOException {
        int port = 8000; 
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
          ss = new ServerSocket(port);
          System.out.println("Server Port Number: " + port);
        } catch(Exception e) {
          e.printStackTrace();
          System.exit(2);
        }
        while (ss.isBound() && !ss.isClosed()) {
          System.out.println("Server is ON!");
          try {
              sock = ss.accept();
              output = new PrintWriter(sock.getOutputStream(), true);
              input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
              String obj = readObj();
              parStri(obj);
          } catch (JSONException e) {
              e.printStackTrace();
              JSONObject object = new JSONObject();
              JSONObject headpiece = new JSONObject();
              headpiece.put("ServerStatusCode", 400);
              object.put("headpiece", headpiece);
              output.println(object);
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              if (output != null)  output.close();
              if (input != null)   input.close();
              if (sock != null) sock.close();
          }
        }
    }

    public TCPServer() {
        random = new Random(java.time.Clock.systemUTC().millis());
    }
    
    private void parStri(String obj) throws JSONException, IOException {
        JSONObject newObj = new JSONObject(obj);
        JSONObject headpiece = newObj.getJSONObject("headpiece");
        String ObjType = headpiece.getString("sObject");
        switch (ObjType) {
            case "newgame":
                newGame(headpiece);
                break;
            case "getquestion":
                getQuestion();
                break;
            case "CheckAnswer":
                CheckAnswer(newObj);
                break;
            default:
                break;
        }
    }
    private void newGame(JSONObject headpiece) {
        String str = headpiece.getString("steam");
        JSONObject obj = new JSONObject();
        JSONObject json = new JSONObject();
        if (steam == null || str.equals(steam)) {
            steam = headpiece.getString("steam");
            boards = headpiece.getInt("boards");

            json.put("ServerStatusCode", 200);
        } else {

            json.put("ServerStatusCode", 226);
        }
        obj.put("headpiece", json);
        output.println(obj);
    }
    private void getQuestion() {
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
        JSONObject headpiece = new JSONObject();
        JSONObject head = new JSONObject();
        headpiece.put("ServerStatusCode", 200);
        head.put("question", ObSon);
        object.put("headpiece", headpiece);
        object.put("head", head);
        output.println(object);
    }
    private void CheckAnswer(JSONObject obj) throws JSONException, IOException {
        JSONObject head = obj.getJSONObject("head");
        if (head.has("value")) {
            int val = head.getInt("value");
            int question1 = ObSon.getInt("quest1");
            int question2 = ObSon.getInt("quest2");
            int question3 = ObSon.getInt("quest3");
            int question4 = ObSon.getInt("quest4");
            int key = ObSon.getInt("quest1") + ObSon.getInt("quest2") * ObSon.getInt("quest3") + ObSon.getInt("quest4");;
            if (key == val) {
                corVal(false);
            } else {
                incorVal();
            }
        } else if (head.has("Text")) {
            String text = head.getString("Text");
            if (text.equals(value)) corVal(true);
            else incorVal();
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
    private void corVal(boolean win) throws IOException {
        JSONObject object = new JSONObject();
        JSONObject headpiece = new JSONObject();
        JSONObject head = new JSONObject();
        headpiece.put("ServerStatusCode", 200);
        head.put("corVal", true);
        object.put("headpiece", headpiece);
        object.put("head", head);
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
        head.put("image", imge[num]);
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
        output.println(object);
    }
    private void incorVal() {
        isWrong++;
        JSONObject object = new JSONObject();
        JSONObject headpiece = new JSONObject();
        JSONObject head = new JSONObject();
        headpiece.put("ServerStatusCode", 200);
        head.put("corVal", false);
        if (isWrong <= 3) {
            head.put("lose", false);
        } else {
            endSimulation();
            head.put("lose", true);
        }
        object.put("headpiece", headpiece);
        object.put("head", head);
        output.println(object);
    }
}
