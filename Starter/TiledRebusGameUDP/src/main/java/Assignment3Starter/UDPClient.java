package Assignment3Starter;
import java.net.*;
import java.io.*;
import org.json.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeoutException;
import java.nio.charset.StandardCharsets;
import Assignment3Starter.lib.LoseGameException;
import Assignment3Starter.lib.ServerIsFullException;
class UDPClient {
    MulticastSocket sock = null;
    PrintWriter output = null;
    BufferedReader input = null;
    MessageDigest messd;
    String inTime = null;
    ClientGui gui;
    InetAddress mcastaddr= null;
    int port = 4999;
    String host = "230.0.0.0";
    String steam;


    public UDPClient (String args[], ClientGui clientgui) throws Exception {
        gui = clientgui;

        try {
            if (args.length == 2) {
                for (int i = 0; i < args.length; i++) {
                    String[] nport = args[i].split("=");
                    if (nport[0].equals("-Pport")) {
                        port = Integer.parseInt(nport[1]);
                    } else if (nport[0].equals("-Phost")) {
                        host = nport[1];
                    } else {
                        System.out.println("invalid flag " + nport[0]);
                    }
                }
            }
            mcastaddr = InetAddress.getByName(host);
            sock = new MulticastSocket(port);
            sock.joinGroup(mcastaddr);
        } catch (NumberFormatException nfe) {
            System.out.println("Port must be integer");
            System.exit(2);
        }
    }

    private String movePacket(JSONObject JSObj, String sObject)throws IOException{
        JSONObject headpiece = new JSONObject();
        headpiece.put("sObject", sObject);
        String string = intHash();
        headpiece.put("string", string);
        JSObj.put("headpiece", headpiece);
        String pstring = JSObj.toString();
        DatagramPacket packet = new DatagramPacket(pstring.getBytes(), pstring.length(), mcastaddr, port);
        sock.send(packet);
        return string;
    }
    private JSONObject acceptPacket(String string)throws TimeoutException{
        long intim = (new Date().getTime());
        return acceptPacket(string, intim, intim);
    }
    private JSONObject acceptPacket(String string, long orgInTim, long intim)throws TimeoutException{
        try{
            byte[] buffer = new byte[1024];
            DatagramPacket message = new DatagramPacket(buffer, buffer.length);
            sock.receive(message);
            String str = new String(message.getData());
            JSONObject ojson = new JSONObject(str);
            JSONObject headpiece = ojson.getJSONObject("headpiece");
            String sObject = headpiece.getString("sObject");
            String strs = headpiece.getString("string");
            if (sObject.equals("response") && strs.equals(string)) {
                return ojson;
            } else {
                if (intim - orgInTim < 100000) {
                    intim = (new Date().getTime());
                    return acceptPacket(string, orgInTim, intim);
                }
                throw new TimeoutException("Connection timed out");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (intim - orgInTim < 100000) {
                intim = (new Date().getTime());
                return acceptPacket(string, orgInTim, intim);
            }
            throw new TimeoutException("Connection timed out");
        } catch (IOException e) {
            e.printStackTrace();
            if (intim - orgInTim < 100000) {
                intim = (new Date().getTime());
                return acceptPacket(string, orgInTim, intim);
            }
            throw new TimeoutException("Connection timed out");
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

    private String intHash() {
        try {
            if (messd == null) {
                messd = MessageDigest.getInstance("SHA-256");
            }
            String intim = java.time.Clock.systemUTC().instant().toString();  
            byte[] chash = messd.digest(intim.getBytes(StandardCharsets.UTF_8));
            return intHex(chash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createGame(int boards) throws IOException, ServerIsFullException, TimeoutException {
        JSONObject head = new JSONObject();
        JSONObject obj = new JSONObject();
        this.steam = intHash();
        head.put("steam", this.steam);
        head.put("boards", boards);
        obj.put("head", head);
        String string = movePacket(obj, "newgame");
        JSONObject object = acceptPacket(string);
        JSONObject headpiece = object.getJSONObject("headpiece");
        int ServerStatusCode = headpiece.getInt("ServerStatusCode");
            if (ServerStatusCode == 226) {
            throw new ServerIsFullException();
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
    public JSONObject getQuestion() throws Exception {
        JSONObject obj = new JSONObject();
        String string = movePacket(obj, "getquestion");
        JSONObject object = acceptPacket(string);
        JSONObject head = object.getJSONObject("head");
        JSONObject question = head.getJSONObject("question");
        return question;
    }
    public JSONObject CheckAnswer(String val) throws Exception, JSONException {
        JSONObject obj = new JSONObject();
        JSONObject head = new JSONObject();
        Integer valn = null;
        try {
            valn = Integer.parseInt(val);
        } catch (NumberFormatException e) {}
        if (valn != null)
            head.put("value", val);
        else 
            head.put("Text", val.toLowerCase());
            obj.put("head", head);
        String string = movePacket(obj, "CheckAnswer");
        JSONObject object = getImage(string);
        JSONObject nobj = object.getJSONObject("head");
        return nobj;
    }
    private JSONObject getImage(String string) throws Exception {
        JSONObject object = acceptPacket(string);
        JSONObject head = object.getJSONObject("head");
        Boolean correct = head.getBoolean("corVal");
        if (!correct) {
            if (head.getBoolean("lose")) {
                throw new LoseGameException();
            }
            return object;
        }
        int number = head.getInt("number");
        JSONObject obj = new JSONObject();
        JSONObject objHead = new JSONObject();
        objHead.put("dig", 0);
        obj.put("head", objHead);
        string = movePacket(obj, "transferData");
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while (i < number) {
            JSONObject cres = acceptPacket(string);
            JSONObject bhead = cres.getJSONObject("head");
            int dig = bhead.getInt("dig");
            if (dig == i) {
                builder.append(bhead.getString("data"));
                i++;
            } else {
                objHead.put("dig", i);
                obj.put("head", objHead);
                string = movePacket(obj, "transferData");
            }
        }
        head.put("pict", builder.toString());
        object.put("head", head);
        return object;
    }
    
}
