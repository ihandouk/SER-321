package examples.grpcClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import com.google.protobuf.Empty;

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
import service.ReadResponse;
import service.RegistryGrpc;
import service.ServerListRes;
import service.ServerResponse;
import service.ServicesListRes;
import service.SingleServerRes;
import service.StoryGrpc;
import service.WriteRequest;
import service.WriteResponse;

/**
 * Client that requests `parrot` method from the `EchoServer`.
 */
public class EchoClient {
  private final EchoGrpc.EchoBlockingStub blockingStub;
  private final JokeGrpc.JokeBlockingStub blockingStub2;
  private final RegistryGrpc.RegistryBlockingStub blockingStub3;
  private final CalcGrpc.CalcBlockingStub blockingStub4;
  private final StoryGrpc.StoryBlockingStub blockingStub5;

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
    blockingStub5 = StoryGrpc.newBlockingStub(channel);
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


  public void askForStory() {

    Empty empty = Empty.newBuilder().build();
    ReadResponse response;

    try {
      response = blockingStub5.read(empty);
    } catch (Exception e) {
      System.err.println("RPC failed: " + e);
      return;
    }
    if(response.getIsSuccess()) {
      System.out.println("The Story\n");
      System.out.println(response.getSentence());
    }else{
      System.out.println(response.getError());
    }
  }

  public void writeStory(String lString){
    WriteRequest writeRequest = WriteRequest.newBuilder().setNewSentence(lString).build();
    WriteResponse response;

    try{
      response = blockingStub5.write(writeRequest);
    }catch(Exception e){
      System.err.println("RPC Failed: " + e);
      return;
    }
    if(response.getIsSuccess()){
      System.out.println("Line added: " + lString);
      System.out.println("The Story\n");
      System.out.println(response.getError());
    }
  }

  public void add(String list){
    CalcRequest request = establishCalcRequest(list);
    CalcResponse response;

    try{
      response = blockingStub4.add(request);
    }catch(Exception e){
      System.err.println("RPC failed: " + e);
      return;
    }

    if(response.getIsSuccess()){
      System.out.println("Add Answer: " + response.getSolution());
    }
    else{
      System.out.println(response.getError());
    }
  }

  public void subtract(String list){
    CalcRequest request = establishCalcRequest(list);
    CalcResponse response;

    try{
      response = blockingStub4.subtract(request);
    }catch(Exception e){
      System.err.println("RPC failed: " + e);
      return;
    }

    if(response.getIsSuccess()){
      System.out.println("Subtract Answer: " + response.getSolution());
    }
    else{
      System.out.println(response.getError());
    }
  }

  public void multiply(String list){
    CalcRequest request = establishCalcRequest(list);
    CalcResponse response;

    try{
      response = blockingStub4.multiply(request);
    }catch(Exception e){
      System.err.println("RPC failed: " + e);
      return;
    }

    if(response.getIsSuccess()){
      System.out.println("multiply Answer: " + response.getSolution());
    }
    else{
      System.out.println(response.getError());
    }
  }

  public void divide(String list){
    CalcRequest request = establishCalcRequest(list);
    CalcResponse response;

    try{
      response = blockingStub4.divide(request);
    }catch(Exception e){
      System.err.println("RPC failed: " + e);
      return;
    }

    if(response.getIsSuccess()){
      System.out.println("divide Answer: " + response.getSolution());
    }
    else{
      System.out.println(response.getError());
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

  private CalcRequest establishCalcRequest(String eString){
    CalcRequest.Builder rBuilder = CalcRequest.newBuilder();

    for(String strin: eString.split(" ")){
      try{
        rBuilder.addNum(Double.parseDouble(strin));
      }catch(NumberFormatException nfe){
        System.out.println("Invalid input!");
      }
    }
    return rBuilder.build();
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
    if (args.length != 6) {
      System.out
          .println("Expected arguments: <host(String)> <port(int)> <regHost(string)> <regPort(int)> <message(String)>");
      System.exit(1);
    }
    int port = 9099;
    int regPort = 9003;
    String host = args[0];
    String regHost = args[2];
    String message = args[4];
    int gene = 0;
    try{
      gene = Integer.valueOf(args[5]);
    }catch(NumberFormatException nfe){
      System.out.println("Input Must be an Integer!");
      System.exit(2);
    }
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

      if(gene == 1){
        String nums = "10 9 7 15 6";
        System.out.println("\n\n");
        System.out.println("------ Testing Parrot ------");
        client.askServerToParrot("This is a test parrot");
        System.out.println("\n\n");
        System.out.println("------ Testing Add ------");
        System.out.println("Numbers as input:" + nums);
        client.add(nums);
        System.out.println("\n\n");
        System.out.println("------ Testing Subtract ------");
        System.out.println("Numbers as input:" + nums);
        client.subtract(nums);
        System.out.println("\n\n");
        System.out.println("------ Testing Divide ------");
        System.out.println("Numbers as input:" + nums);
        client.divide(nums);
        System.out.println("\n\n");
        System.out.println("------ Testing Multiply ------");
        System.out.println("Numbers as input:" + nums);
        client.multiply(nums);
        System.out.println("\n\n");
        System.out.println("------ Testing Read Story ------");
        client.askForStory();
        System.out.println("\n\n");
        System.out.println("------ Testing Add to Story ------");
        String addNewLine = "He was the most popular muffin.";
        System.out.println("Adding line to story: " + addNewLine);
        client.writeStory(addNewLine);
        System.out.println("\n\n");
        System.out.println("------ Testing Get Joke ------");
        System.out.println("Asking for 2 jokes");
        client.askForJokes(2);
        System.out.println("\n\n");
        System.out.println("------ Testing Add Joke ------");
        String newJoke = "What do you call a bird with a hat??... A bird with a hat";
        System.out.println("Adding joke: " + newJoke);
        client.setJoke(newJoke);
        System.out.println("\n\n");
        System.out.println("COMPLETED ALL TESTS");
        System.exit(0);
      }

      // call the parrot service on the server
      client.askServerToParrot(message);

      // ask the user for input how many jokes the user wants
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

      System.out.println("Available Operators: ADD, SUBTRACT, MULTIPLY, DIVIDE, READSTORY, ADDSTORY, JOKE, ADDJOKE, PARROT");
      while (true){
        System.out.print("Choose an Option: ");
        String cString = reader.readLine();
        if(cString.equalsIgnoreCase("exit")){
          System.out.println("Exiting...");
          break;
        }
        switch(cString.toLowerCase()){
          case "ADD":
            System.out.println("Must have two operands to perform an addition");
            System.out.print("Numbers: ");
            String addNumbers = reader.readLine();
            client.add(addNumbers);
            break;
          case "SUBTRACT":
            System.out.println("Must have two operands to perform a subtraction");
            System.out.print("Numbers: ");
            String subNumbers = reader.readLine();
            client.subtract(subNumbers);
            break;
          case "MULTIPLY":
            System.out.println("Must have two operands to perform a multiplication");
            System.out.print("Numbers: ");
            String multNumbers = reader.readLine();
            client.multiply(multNumbers);
            break;
          case "DIVIDE":
            System.out.println("Must have two operands to perform a division");
            System.out.print("Numbers: ");
            String divNumbers = reader.readLine();
            client.divide(divNumbers);
            break;
          case "READSTORY":
            client.askForStory();
            break;
          case "ADDSTORY":
            System.out.println("Input a sentance to add to the story");
            System.out.print("Sentence: ");
            String newLine = reader.readLine();
            client.writeStory(newLine);
            break;
          case "JOKE":
            System.out.println("How many joke would you like to hear?");
            System.out.print("Number: ");
            try {
              int num = Integer.valueOf(reader.readLine());
              client.askForJokes(num);
            }catch(NumberFormatException e){
              System.out.println("Incorrect Number given!");
            }
            break;
          case "ADDJOKE":
            System.out.println("Type a joke to add!");
            System.out.print("Joke: ");
            String newJoke = reader.readLine();
            client.setJoke(newJoke);
            break;
          case "PARROT":
            System.out.println("Type a Sentence to have printed back to you!");
            System.out.print("Sentence: ");
            String parrot = reader.readLine();
            client.askServerToParrot(parrot);
            break;
          default:
            System.out.println("Unknown command!");
        }
      }
      /* Reading data using readLine
      System.out.println("How many jokes would you like?"); // NO ERROR handling of wrong input here.
      String num = reader.readLine();

      calling the joked service from the server with num from user input
      client.askForJokes(Integer.valueOf(num));

      adding a joke to the server
      client.setJoke("I made a pencil with two erasers. It was pointless.");

      showing 6 joked
      client.askForJokes(Integer.valueOf(6));

      ############### Contacting the registry just so you see how it can be done

      Comment these last Service calls while in Activity 1 Task 1, they are not needed and wil throw issues without the Registry running
      get thread's services
      client.getServices();

      get parrot
      client.findServer("services.Echo/parrot");
      
      get all setJoke
      client.findServers("services.Joke/setJoke");

      get getJoke
      client.findServer("services.Joke/getJoke");

      does not exist
      client.findServer("random");*/


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
