package examples.grpcClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import service.CalcGrpc;
import service.CalcRequest;
import service.CalcResponse;
import service.ClientRequest;
import service.EchoGrpc;
import service.FindServerReq;
import service.FindServersReq;
import service.GetServicesReq;
import service.JokeGrpc;
import service.JokeReq;
import service.JokeRes;
import service.JokeSetReq;
import service.JokeSetRes;
import service.RegistryGrpc;
import service.ServerListRes;
import service.ServerResponse;
import service.ServicesListRes;
import service.SingleServerRes;
import service.Tip;
import service.TipsGrpc;
import service.TipsReadResponse;
import service.TipsWriteRequest;
import service.TipsWriteResponse;

/**
 * Client that requests `parrot` method from the `EchoServer`.
 */
public class EchoClient {
  private final EchoGrpc.EchoBlockingStub blockingStub;
  private final JokeGrpc.JokeBlockingStub blockingStub2;
  private final RegistryGrpc.RegistryBlockingStub blockingStub3;
  private final CalcGrpc.CalcBlockingStub blockingStub4;
  private final TipsGrpc.TipsBlockingStub blockingStub5;

  /** Construct client for accessing server using the existing channel. */
  public EchoClient(Channel channel, Channel regChannel) {
    // 'channel' here is a Channel, not a ManagedChannel, so it is not this code's
    // responsibility to
    // shut it down.

    // Passing Channels to code makes code easier to test and makes it easier to
    // reuse Channels.
    blockingStub = EchoGrpc.newBlockingStub(channel);
    blockingStub2 = JokeGrpc.newBlockingStub(channel);
    blockingStub3 = RegistryGrpc.newBlockingStub(regChannel);
    blockingStub4 = CalcGrpc.newBlockingStub(channel);
    blockingStub5 = TipsGrpc.newBlockingStub(channel);
  }

  public void askServerToParrot(String message) {
    ClientRequest request = ClientRequest.newBuilder().setMessage(message).build();
    ServerResponse response;
    try {
      response = blockingStub.parrot(request);
    } catch (Exception e) {
      System.err.println("RPC failed: " + e.getMessage());
      return;
    }
    System.out.println("Received from server: " + response.getMessage());
  }

  public void askForFunction(String funString, String[] nStrings){
    ArrayList<Double> adub = new ArrayList<Double>();
    CalcRequest rCalcRequest;
    CalcResponse rBuilder;
    double solution;

    try{
      for(int i = 0; i < nStrings.length; i++){
        adub.add(Double.parseDouble(nStrings[i]));
      }
      rCalcRequest = CalcRequest.newBuilder().addAllNum(adub).build();
    }
    catch(NumberFormatException exception){
      System.out.println("Incorrect input of numbers");
      return;
    }

    try{
      if(funString.equalsIgnoreCase("ADD")){
        rBuilder = blockingStub4.add(rCalcRequest);
      }else if(funString.equalsIgnoreCase("SUBTRACT")){
        rBuilder = blockingStub4.subtract(rCalcRequest);
      }else if(funString.equalsIgnoreCase("MULTIPLY")){
        rBuilder = blockingStub4.multiply(rCalcRequest);
      }else if(funString.equalsIgnoreCase("DIVIDE")){
        rBuilder = blockingStub4.divide(rCalcRequest);
      }else{
        System.out.println("Invalid Operator Input");
        return;
      }
      solution = rBuilder.getSolution();
      System.out.println("Solution: "+solution);
    }catch(Exception exception){
      System.err.println("RPC failed: "+exception);
      return;
    }
  }

  public void askForJokes(int num) {
    JokeReq request = JokeReq.newBuilder().setNumber(num).build();
    JokeRes response;

    try {
      response = blockingStub2.getJoke(request);
    } catch (Exception e) {
      System.err.println("RPC failed: " + e);
      return;
    }
    System.out.println("Your jokes: ");
    for (String joke : response.getJokeList()) {
      System.out.println("--- " + joke);
    }
  }

  public void setJoke(String joke) {
    JokeSetReq request = JokeSetReq.newBuilder().setJoke(joke).build();
    JokeSetRes response;

    try {
      response = blockingStub2.setJoke(request);
      System.out.println(response.getOk());
    } catch (Exception e) {
      System.err.println("RPC failed: " + e);
      return;
    }
  }

  public void setTip(String uString, String tString){
    Tip tip = Tip.newBuilder().setTip(tString).setName(uString).build();
    TipsWriteRequest tipsWriteRequest = TipsWriteRequest.newBuilder().setTip(tip).build();
    TipsWriteResponse tipsWriteResponse;
    try{
      tipsWriteResponse = blockingStub5.write(tipsWriteRequest);
      System.out.println("Success: "+tipsWriteResponse.getIsSuccess());
    }
    catch(Exception exception){
      System.err.println("RPC failed: "+exception);
      return;
    }
  }

  public void fineTips(){
    ArrayList<String> tList = new ArrayList<>();
    TipsReadResponse tipsReadResponse;

    try{
      tipsReadResponse = blockingStub5.read(null);
    }catch(Exception exception){
      System.err.println("RPC failed: "+exception);
      return;
    }
    System.out.println("Tips: ");
    for (Tip tip : tipsReadResponse.getTipsList()){
      System.out.println(tip.getName() + ": " + tip.getTip());
    }
  }

  public void getServices() {
    GetServicesReq request = GetServicesReq.newBuilder().build();
    ServicesListRes response;
    try {
      response = blockingStub3.getServices(request);
      System.out.println(response.toString());
    } catch (Exception e) {
      System.err.println("RPC failed: " + e);
      return;
    }
  }

  public void findServer(String name) {
    FindServerReq request = FindServerReq.newBuilder().setServiceName(name).build();
    SingleServerRes response;
    try {
      response = blockingStub3.findServer(request);
      System.out.println(response.toString());
    } catch (Exception e) {
      System.err.println("RPC failed: " + e);
      return;
    }
  }

  public void findServers(String name) {
    FindServersReq request = FindServersReq.newBuilder().setServiceName(name).build();
    ServerListRes response;
    try {
      response = blockingStub3.findServers(request);
      System.out.println(response.toString());
    } catch (Exception e) {
      System.err.println("RPC failed: " + e);
      return;
    }
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 5) {
      System.out
          .println("Expected arguments: <host(String)> <port(int)> <regHost(string)> <regPort(int)> <message(String)>");
      System.exit(1);
    }
    int port = 9099;
    int regPort = 9003;
    String host = args[0];
    String regHost = args[2];
    String message = args[4];
    try {
      port = Integer.parseInt(args[1]);
      regPort = Integer.parseInt(args[3]);
    } catch (NumberFormatException nfe) {
      System.out.println("[Port] must be an integer");
      System.exit(2);
    }

    // Create a communication channel to the server, known as a Channel. Channels
    // are thread-safe
    // and reusable. It is common to create channels at the beginning of your
    // application and reuse
    // them until the application shuts down.
    String target = host + ":" + port;
    ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
        // Channels are secure by default (via SSL/TLS). For the example we disable TLS
        // to avoid
        // needing certificates.
        .usePlaintext().build();

    String regTarget = regHost + ":" + regPort;
    ManagedChannel regChannel = ManagedChannelBuilder.forTarget(regTarget).usePlaintext().build();
    try {

      // ##############################################################################
      // ## Assume we know the port here from the service node it is basically set through Gradle
      // here.
      // In your version you should first contact the registry to check which services
      // are available and what the port
      // etc is.

      /**
       * Your client should start off with 
       * 1. contacting the Registry to check for the available services
       * 2. List the services in the terminal and the client can
       *    choose one (preferably through numbering) 
       * 3. Based on what the client chooses
       *    the terminal should ask for input, eg. a new sentence, a sorting array or
       *    whatever the request needs 
       * 4. The request should be sent to one of the
       *    available services (client should call the registry again and ask for a
       *    Server providing the chosen service) should send the request to this service and
       *    return the response in a good way to the client
       * 
       * You should make sure your client does not crash in case the service node
       * crashes or went offline.
       */

      // Just doing some hard coded calls to the service node without using the
      // registry
      // create client
      EchoClient client = new EchoClient(channel, regChannel);

      // call the parrot service on the server
      client.askServerToParrot(message);

      // ask the user for input how many jokes the user wants
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

      while(true){
        System.out.println("Type function (Calc or Tip)");
        String iString = reader.readLine();
        while(!iString.equalsIgnoreCase("Calc") && !iString.equalsIgnoreCase("Tip")){
          System.out.println("Invalid Input Function! Chose From the Following (Calc, Tip, or Exit");
          iString = reader.readLine();
        }

        if(iString.equalsIgnoreCase("Calc")){
          System.out.println("Chose an Operator (ADD, SUBTRACT, MULTIPLY, DIVIDE, or Exit");
          String funString = reader.readLine();
          while(!funString.equalsIgnoreCase("ADD") && !funString.equalsIgnoreCase("SUBTRACT")
          && !funString.equalsIgnoreCase("MULTIPLY") && !funString.equalsIgnoreCase("DIVIDE") 
          && !funString.equalsIgnoreCase("Exit")){
            System.out.println("Invalid Input Operator");
            funString = reader.readLine();
          }
          if(funString.equalsIgnoreCase("Exit")){
            break;
          }
          System.out.println("Enter an Integer: ");
          iString = reader.readLine();
          String[] nStrings = iString.split(" ");
          client.askForFunction(funString, nStrings);
        }else if(iString.equalsIgnoreCase("Tip")){
          System.out.println("Please choose if you would like to Add a tip, or read a tip (ADD, READ, Exit)");
          iString = reader.readLine();
          while(!iString.equalsIgnoreCase("ADD") && iString.equalsIgnoreCase("READ")
          && !iString.equalsIgnoreCase("Exit")){
            System.out.println("Invalid Input, Please try again! Choose a function (ADD, READ, Exit)");
            iString = reader.readLine();
          }
          if(iString.equalsIgnoreCase("ADD")){
            String nString;
            System.out.println("Enter you Name: ");
            nString = reader.readLine();

            String tip;
            System.out.println("Add a Tip: ");
            tip = reader.readLine();
            client.setTip(nString, tip);
          }else if(iString.equalsIgnoreCase("READ")){
            client.fineTips();
          }
          else if(iString.equalsIgnoreCase("Exit")){
            break;
          }
        }

        else if(iString.equalsIgnoreCase("Exit")){
          System.out.println("Exiting now..... ");
          System.exit(0);
        }
      }
      // Reading data using readLine
      System.out.println("How many jokes would you like?"); // NO ERROR handling of wrong input here.
      String num = reader.readLine();

      // calling the joked service from the server with num from user input
      client.askForJokes(Integer.valueOf(num));

      // adding a joke to the server
      client.setJoke("I made a pencil with two erasers. It was pointless.");

      // showing 6 joked
      client.askForJokes(Integer.valueOf(6));

      // ############### Contacting the registry just so you see how it can be done

      // Comment these last Service calls while in Activity 1 Task 1, they are not needed and wil throw issues without the Registry running
      // get thread's services
      client.getServices();

      // get parrot
      client.findServer("services.Echo/parrot");
      
      // get all setJoke
      client.findServers("services.Joke/setJoke");

      // get getJoke
      client.findServer("services.Joke/getJoke");

      // does not exist
      client.findServer("random");


    } finally {
      // ManagedChannels use resources like threads and TCP connections. To prevent
      // leaking these
      // resources the channel should be shut down when it will no longer be used. If
      // it may be used
      // again leave it running.
      channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
      regChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
  }
}
