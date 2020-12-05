package examples.grpcClient;

import java.util.ArrayList;
import java.util.List;

import io.grpc.stub.StreamObserver;
import service.*;

public class StoryImpl extends StoryGrpc.StoryImplBase {

    List<String> list = new ArrayList<String>();

    public StoryImpl(){
        super();
        list.add("Once upon a time, there was a boy who took SER321, and he never slept every since. ");
    }

    private boolean addLine(String lString){
        return list.add(lString);
    }

    private String getStory(){
        String response = "";
        for(String str: list){
            response = response + " " + str;
        }

        return response;
    }

    public synchronized void readStory (Empty empty, StreamObserver<ReadResponse> responseObserver){

        ReadResponse.Builder response = ReadResponse.newBuilder();
        String string = getStory();
        if(string.length() > 0 && string != null){
            response.setIsSuccess(true);
            response.setSentence(string);
            response.setError("Empty");
        }else{
            response.setIsSuccess(false);
            response.setSentence("");
            response.setError("No story have been written!");
        }

        ReadResponse nresponse = response.build();
        responseObserver.onNext(nresponse);
        responseObserver.onCompleted();
    }

    public synchronized void writeStory(WriteRequest req, StreamObserver<WriteResponse> responsObserver){

        WriteResponse.Builder response = WriteResponse.newBuilder();

        String lString = req.getNewSentence();

        if(lString != null && lString.length() > 0){
            boolean bool = addLine(lString);
            if(bool){
                response.setIsSuccess(true);
                response.setStory(getStory());
                response.setError("Empty");
            }else{
                response.setIsSuccess(false);
                response.setStory(getStory());
                response.setError("Unable to add a new line to the story");
            }
        }else{
            response.setIsSuccess(false);
            response.setStory(getStory());
            response.setError("New line was not added to the story, please try again.");
        }
        WriteResponse nresponse = response.build();
        responsObserver.onNext(nresponse);
        responsObserver.onCompleted();
    }
}