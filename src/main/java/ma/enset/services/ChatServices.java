package ma.enset.services;

import io.grpc.stub.StreamObserver;
import ma.enset.stub.AppChatServiceGrpc;
import ma.enset.stub.Chat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ChatServices extends AppChatServiceGrpc.AppChatServiceImplBase {
    private Scanner scanner=new Scanner(System.in);
    private int conter=0;
    HashMap<String, StreamObserver<Chat.MessageRespense>> clientsMap = new HashMap<String, StreamObserver<Chat.MessageRespense>>();


    @Override
    public StreamObserver<Chat.MessageRequest> sendMessage(StreamObserver<Chat.MessageRespense> responseObserver) {
        Boolean client=false;
        conter++;
        ///System.out.println("Boucle while && couter = "+conter);
        //System.out.println("sream = "+responseObserver);
        clientsMap.put("client "+conter, responseObserver);
        Iterator iterator = clientsMap.entrySet().iterator();
        /*while (iterator.hasNext()) {
            Map.Entry mapentry = (Map.Entry) iterator.next();
            System.out.println("clé: "+mapentry.getKey() + " | valeur: " + mapentry.getValue());
        }*/
        return new StreamObserver<Chat.MessageRequest>() {
            @Override
            public void onNext(Chat.MessageRequest messageRequest) {
                String reponseMsg;
                //System.out.println("Response to "+messageRequest.getCurrentFrom()+" : ");
                ///reponseMsg= scanner.nextLine();
                //System.out.println("reponseMsg= scanner.nextLine(); "+reponseMsg);
                System.out.println(messageRequest.getCurrentFrom()+" said : "+messageRequest.getCurrentMsg());
                System.out.println("**************************************************");
                Iterator iterat = clientsMap.entrySet().iterator();
                while (iterat.hasNext()) {
                    Map.Entry mapentry = (Map.Entry) iterat.next();
                    System.out.println("clé: "+mapentry.getKey() + " | valeur: " + mapentry.getValue());
                    StreamObserver<Chat.MessageRespense> stream= (StreamObserver<Chat.MessageRespense>) mapentry.getValue();
                    if(stream!=responseObserver){
                        System.out.println("inside if ! for stram =  "+stream);
                        Chat.MessageRespense respense=Chat.MessageRespense.newBuilder()
                                .setCurrentFrom(messageRequest.getCurrentFrom())
                                .setCurrentTo(messageRequest.getCurrentTo())
                                .setCurrentMsg(messageRequest.getCurrentMsg())
                                .build();
                        stream.onNext(respense);
                    }
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
