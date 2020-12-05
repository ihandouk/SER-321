package examples.grpcClient;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerMethodDefinition;
import io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class NodeService {
    static private Server server;
    int port;

    net.Network network = null;
    ServerSocket serverSocket = null;
    InputStream input = null;
    OutputStream output = null;
    Socket cSocket = null;

    NodeService(int port){
        this.port = port;
        this.network = new net.proto.Network();
    }
    
    private void stop() throws InterruptedException{
        if(server != null){
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void start() throws IOException{
        ArrayList<String> arrayList = new ArrayList<>();
        server = ServerBuilder.forPort(port).addService(new EchoImpl())
        .addService(new JokeImpl()).addService(new StoryImpl())
        .addService(new CalcImpl())
        .addService(new RegistryAnswerImpl(arrayList)).build().start();

        for(var variable : server.getServices()){
            for(ServerMethodDefinition<?, ?> method: variable.getMethods()){
                arrayList.add(method.getMethodDescriptor().getFullMethodName());
                System.out.println(method.getMethodDescriptor().getFullMethodName());
            }
        }

        System.out.println("Server is running.....>>");
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                System.err.println("Shutting down gRPC server.....>>");
                try{
                    NodeService.this.stop();
                }catch(InterruptedException exception){
                    exception.printStackTrace(System.err);
                }
                System.err.println("Shutting down server!");
            }
        });
    }

    private void blockUtilShutdown() throws InterruptedException{
        if(server != null){
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException{
        if(args.length != 5){
            System.out.println("Expected: <regAddr(string)> <regPort(int)> <nodeAddr> <nodePort<int>");
            System.exit(1);
        }
        int regPort = 9003;
        int nodePort = 9099;

        try{
            regPort = Integer.parseInt(args[1]);
            nodePort = Integer.parseInt(args[3]);
        }catch(NumberFormatException nfe){
            System.exit(2);
        }

        final NodeService server = new NodeService(nodePort);
        System.out.println(args[0]);
        System.out.println(args[1]);
        System.out.println(args[2]);
        System.out.println(args[3]);

        System.out.println(args[4]);

        Register register = new Register(args[0], regPort, args[2], nodePort, args[4]);
        register.start();

        server.start();
        server.blockUtilShutdown();
    }
}