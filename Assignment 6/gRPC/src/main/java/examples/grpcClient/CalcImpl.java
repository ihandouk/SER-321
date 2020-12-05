package examples.grpcClient;

import java.util.ArrayList;
import java.util.Stack;

import io.grpc.stub.StreamObserver;
import service.CalcGrpc;
import service.CalcRequest;
import service.CalcResponse;

public class CalcImpl extends CalcGrpc.CalcImplBase {
    
    Stack<String> number = new Stack<String>();

    public CalcImpl(){
        super();
    }

    ArrayList<String> arraylist = new ArrayList<>();

    @Override
    public void add(CalcRequest request, StreamObserver<CalcResponse> responseObserver){
        try{
            System.out.println("Input numbers from a client: "+ request.getNumList().toString());
            double sum = 0;
            for(int i = 0; i < request.getNumList().size(); i++){
                arraylist.add(request.getNumList().get(i).toString());
            }
            for(int i = 0; i < arraylist.size(); i++){
                sum = sum + Double.parseDouble(arraylist.get(i));
            }
            System.out.println(sum);

            CalcResponse.Builder rBuilder = CalcResponse.newBuilder();
            rBuilder.setIsSuccess(true);
            rBuilder.setSolution(sum);
            CalcResponse resCal = rBuilder.build();

            responseObserver.onNext(resCal);
            responseObserver.onCompleted();
            arraylist.clear();
        }catch(Exception e){
            CalcResponse.Builder rBuilder = CalcResponse.newBuilder();
            rBuilder.setIsSuccess(false);
            rBuilder.setError(e.toString());
            CalcResponse resCal = rBuilder.build();
            responseObserver.onNext(resCal);
            responseObserver.onCompleted();
            arraylist.clear();
        }
    }

    @Override
    public void subtract(CalcRequest request, StreamObserver<CalcResponse> responseObserver){
        try{
            System.out.println("Input numbers from a client: " +request.getNumList().toString());
            for(int i = 0; i < request.getNumList().size(); i++){
                arraylist.add(request.getNumList().get(i).toString());
            }
            double sum = Double.parseDouble(arraylist.get(0));
            for(int i = 1; i<arraylist.size(); i++){
                System.out.println(sum);
                sum = sum - Double.parseDouble(arraylist.get(i));
            }
            System.out.println(sum);

            CalcResponse.Builder rBuilder = CalcResponse.newBuilder();
            rBuilder.setIsSuccess(true);
            rBuilder.setSolution(sum);
            CalcResponse resCal = rBuilder.build();

            responseObserver.onNext(resCal);
            responseObserver.onCompleted();
            arraylist.clear();
        }catch(Exception exception){
            CalcResponse.Builder rBuilder = CalcResponse.newBuilder();
            rBuilder.setIsSuccess(false);
            rBuilder.setError(exception.toString());
            CalcResponse resCal = rBuilder.build();
            responseObserver.onNext(resCal);
            responseObserver.onCompleted();
            arraylist.clear();
        }
    }

    @Override
    public void multiply(CalcRequest request, StreamObserver<CalcResponse> responseObserver){
        try{
            System.out.println("Input numbers from a client: " +request.getNumList().toString());
            for(int i = 0; i < request.getNumList().size(); i++){
                arraylist.add(request.getNumList().get(i).toString());
            }
            double prod = Double.parseDouble(arraylist.get(0));
            for(int i = 1; i<arraylist.size(); i++){
                System.out.println(prod);
                prod = prod * Double.parseDouble(arraylist.get(i));
            }
            System.out.println(prod);

            CalcResponse.Builder rBuilder = CalcResponse.newBuilder();
            rBuilder.setIsSuccess(true);
            rBuilder.setSolution(prod);
            CalcResponse resCal = rBuilder.build();

            responseObserver.onNext(resCal);
            responseObserver.onCompleted();
            arraylist.clear();
        }catch(Exception exception){
            CalcResponse.Builder rBuilder = CalcResponse.newBuilder();
            rBuilder.setIsSuccess(false);
            rBuilder.setError(exception.toString());
            CalcResponse resCal = rBuilder.build();
            responseObserver.onNext(resCal);
            responseObserver.onCompleted();
            arraylist.clear();
        }
    }
    
    @Override
    public void divide(CalcRequest request, StreamObserver<CalcResponse> responseObserver){
        try{
            System.out.println("Input numbers from a client: " +request.getNumList().toString());
            for(int i = 0; i < request.getNumList().size(); i++){
                arraylist.add(request.getNumList().get(i).toString());
            }
            double numb = Double.parseDouble(arraylist.get(0));
            for(int i = 1; i<arraylist.size(); i++){
                System.out.println(numb);
                numb = numb / Double.parseDouble(arraylist.get(i));
            }
            System.out.println(numb);

            CalcResponse.Builder rBuilder = CalcResponse.newBuilder();
            rBuilder.setIsSuccess(true);
            rBuilder.setSolution(numb);
            CalcResponse resCal = rBuilder.build();

            responseObserver.onNext(resCal);
            responseObserver.onCompleted();
            arraylist.clear();
        }catch(Exception exception){
            CalcResponse.Builder rBuilder = CalcResponse.newBuilder();
            rBuilder.setIsSuccess(false);
            rBuilder.setError(exception.toString());
            CalcResponse resCal = rBuilder.build();
            responseObserver.onNext(resCal);
            responseObserver.onCompleted();
            arraylist.clear();
        }
    }
}
