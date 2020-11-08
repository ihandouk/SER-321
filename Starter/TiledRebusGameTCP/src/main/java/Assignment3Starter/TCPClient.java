package Assignment3Starter;
import java.net.*;
import java.io.*;
import org.json.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import Assignment3Starter.lib.LoseGameException;
import Assignment3Starter.lib.ServerIsFullException;

class TCPClient {
    Socket sock = null;
    PrintWriter output = null;
    BufferedReader input = null;
    MessageDigest messd;
    ClientGui gui;
    int port = 8000;
    String host = "localhost";
    String steam;


    public TCPClient (String args[], ClientGui clientgui) throws Exception {
        gui = clientgui;

        try {
            if (args.length == 2) {
                for (int i = 0; i < args.length; i++) {
                    String[] p = args[i].split("=");
                    if (p[0].equals("-Pport")) {
                        port = Integer.parseInt(p[1]);
                    } else if (p[0].equals("-Phost")) {
                        host = p[1];
                    } else {
                        System.out.println("invalid flag " + p[0]);
                    }
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Port must be integer");
            System.exit(2);
        }
    }
    public void start() {
        try {
            sock = new Socket(host, port);
            output = new PrintWriter(sock.getOutputStream(), true);
	        input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void end() {
        try {
            if (output != null)  output.close();
            if (input != null)   input.close(); 
            if (sock != null) sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createGame(int boards) throws IOException, ServerIsFullException {
        JSONObject headpiece = new JSONObject();
        JSONObject obj = new JSONObject();
        obj.put("headpiece", headpiece);
        try {
            start();
            messd = MessageDigest.getInstance("SHA-256");
            String t = java.time.Clock.systemUTC().instant().toString();  
            byte[] chash = messd.digest(t.getBytes(StandardCharsets.UTF_8));
            this.steam = intHex(chash);
            headpiece.put("sObject", "newgame");
            headpiece.put("steam", this.steam);
            headpiece.put("boards", boards);
            obj.put("headpiece", headpiece);
            output.println(obj);
            String line = input.readLine();
            JSONObject object = new JSONObject(line);
            JSONObject gamein = object.getJSONObject("headpiece");
            int ServerStatusCode = gamein.getInt("ServerStatusCode");
            end();
			// HTTP status code 226 = undefined = IM
            if (ServerStatusCode == 226) {
                throw new ServerIsFullException();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    private String intHex(byte[] hash) {
	    StringBuilder sHex = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            sHex.append('0');
	        }
	        sHex.append(hex);
	    }
	    return sHex.toString();
    }
    public JSONObject getQuestion() throws IOException, JSONException {
        start();
        JSONObject obj = new JSONObject();
        JSONObject headpiece = new JSONObject();
        headpiece.put("sObject", "getquestion");
        obj.put("headpiece", headpiece);
        output.println(obj);
        String ln = input.readLine();
        JSONObject object = new JSONObject(ln);
        JSONObject head = object.getJSONObject("head");
        JSONObject question = head.getJSONObject("question");
        end();
        return question;
    }
    public JSONObject CheckAnswer(String val) throws IOException, JSONException, LoseGameException {
        start();
        JSONObject obj = new JSONObject();
        JSONObject headpiece = new JSONObject();
        JSONObject head = new JSONObject();
        Integer valn = null;
        try {
            valn = Integer.parseInt(val);
        } catch (NumberFormatException e) {}
        headpiece.put("sObject", "CheckAnswer");
        if (valn != null)
            head.put("value", val);
        else 
            head.put("Text", val.toLowerCase());
            obj.put("headpiece", headpiece);
            obj.put("head", head);
            output.println(obj);
        String ln = input.readLine();
        JSONObject object = new JSONObject(ln);
        JSONObject b = object.getJSONObject("head");
        Boolean correct = b.getBoolean("corVal");
        if (!correct) {
            if (b.getBoolean("lose")) {
                throw new LoseGameException();
            }
        }
        end();
        return b;
    }
    
}
