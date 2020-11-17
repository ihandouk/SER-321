import java.net.*;
import java.util.*; 
import java.util.List;
import java.io.*;

class Performer {

    StringList state;
    Socket sock;

    public Performer(Socket sock, StringList strings) {
        this.sock = sock;
        this.state = strings;
    }

    public void doPerform() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {

            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);
            out.println("Enter text (. to disconnect):");

            boolean done = false;
            while (!done) {

                out.println("From the list, enter the number of an action you would like to perform: ");
                out.println("1: To Add.");
                out.println("2: To Remove.");
                out.println("3: To Display a List.");
                out.println("4: To Display Count.");
                out.println("5: To Reverse a String.");
                out.println("End: Client Logout.");
                String string = in.readLine();

                switch (string) {
                    case "1":
                        out.println("\nEnter an Index: ");
                        String str = in.readLine();
                        if (str == null || str.equals("."))
                            done = true;
                        else {
                            state.add(str);
                            out.println("\nServer state is now: " + state.toString());
                        }
                        break;

                    case "2":
                        out.println("\nEnter an Index to Remove: ");
                        String remov = in.readLine();
                        if(remov == null || remov.equals(".")){
                            done = true;
                        }
                        else{
                            state.remove(remov);
                            out.println("\nServer state is now: " + state.toString());
                        }
                        break;
                    case "3":
                        out.println("\nDisplay List: " + state.toString());
                        break;
                    case "4":
                        String displayCount = state.Count();
                        out.println(displayCount);
                        break;
                    case "5":
                        out.println("\nEnter an Index to Reverse a String: ");
                        String reverse = in.readLine();
                            state.Reverse(reverse);
                            out.println("\nServer state is now: " + state.toString());
                        break;
                    case ".":
                        out.println("Client Discounting...");
                        done = true;
                        break;
                    default:
                    String end = in.readLine();
                    if(end == null || end.equals(".")){
                        done = true;
                    }
                        out.println("\nInvalid entry, Please enter a Number between 1 and 5");
                        out.println("\nServer state is now: " + state.toString());
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            try {
                in.close();
            } catch (IOException e) {e.printStackTrace();}
            try {
                sock.close();
            } catch (IOException e) {e.printStackTrace();}
        }
    }
}

