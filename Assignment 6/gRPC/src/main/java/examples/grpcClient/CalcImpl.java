package examples.grpcClient;

import java.util.ArrayList;
import java.util.Stack;

import io.grpc.stub.StreamObserver;
import service.CalcGrpc;
import service.CalcRequest;
import service.CalcResponse;

public class CalcImpl extends CalcGrpc.CalcImplBase {

    public CalcImpl(){
        super();
    }

    @Override
    public void add(CalcRequest request, StreamObserver<CalcResponse> responseObserver){

        CalcResponse.Builder response = CalcResponse.newBuilder();
        double sum = 0;
        for(int i = 0; i<request.getNumCount(); i++){
            sum = sum + request.getNum(i);
        }

        response.setIsSuccess(true);
        response.setError("Empty");
        response.setSolution(sum);

        CalcResponse calcResponse = response.build();
        responseObserver.onNext(calcResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void subtract(CalcRequest request, StreamObserver<CalcResponse> responseObserver){
        
        CalcResponse.Builder response = CalcResponse.newBuilder();

        if(request.getNumCount() > 1){
            double subs = request.getNum(0);
            for(int i = 1; i<request.getNumCount(); i++){
                subs = subs - request.getNum(i);
            }

            response.setIsSuccess(true);
            response.setError("Empty");
            response.setSolution(subs);
        }else{
            response.setIsSuccess(false);
            response.setError("Invalid Input value!");
            response.setSolution(0);
        }
        CalcResponse calcResponse = response.build();
        responseObserver.onNext(calcResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void multiply(CalcRequest request, StreamObserver<CalcResponse> responseObserver){

        CalcResponse.Builder response = CalcResponse.newBuilder();
        double mult = 0;
        for(int i = 0; i<request.getNumCount(); i++){
            mult = mult * request.getNum(i);
        }

        response.setIsSuccess(true);
        response.setError("Empty");
        response.setSolution(mult);

        CalcResponse calcResponse = response.build();
        responseObserver.onNext(calcResponse);
        responseObserver.onCompleted();
    }
    
    @Override
    public void divide(CalcRequest request, StreamObserver<CalcResponse> responseObserver){
        CalcResponse.Builder response = CalcResponse.newBuilder();
        
        if(request.getNumCount() > 1){
            double numer = request.getNum(0);
            double denom = 0;

            for(int i = 1; i<request.getNumCount(); i++){
                denom = denom + request.getNum(i);
            }

            if(denom != 0){
                double divi = numer/denom;
                response.setIsSuccess(true);
                response.setSolution(divi);
                response.setError("Empty");
            }else{
                response.setIsSuccess(false);
                response.setSolution(0);
                response.setError("Unsupported value is in the denominator.");
            }
        }else{
            response.setIsSuccess(false);
            response.setSolution(0);
            response.setError("A calculation must have at least two operands");
        }

        CalcResponse calcResponse = response.build();
        responseObserver.onNext(calcResponse);
        responseObserver.onCompleted();
    }
}
