package ma.enset.services;

import io.grpc.stub.StreamObserver;
import ma.enset.stub.NbrMagique;
import ma.enset.stub.NombreMagiqueGrpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NbrMagiqueServices extends NombreMagiqueGrpc.NombreMagiqueImplBase {
    private Scanner scanner=new Scanner(System.in);
    String joueurGan=null;
    @Override
    public StreamObserver<NbrMagique.BroadMsgRequest> broadcastMsg(StreamObserver<NbrMagique.BroadMsgResponse> responseObserver) {
        return new StreamObserver<NbrMagique.BroadMsgRequest>() {
            @Override
            public void onNext(NbrMagique.BroadMsgRequest broadMsgRequest) {
                int nbrMaqique=500;
                int nbr= (int) broadMsgRequest.getMsg();
                NbrMagique.BroadMsgResponse response = null;

                if (nbrMaqique==nbr){
                    response=NbrMagique.BroadMsgResponse.newBuilder()
                            .setFrom(broadMsgRequest.getFrom())
                            .setMsg("BRAVOO VOUS AVEZ TROUVER LE NOMBRE ^-^")
                            .build();
                    System.out.println(broadMsgRequest.getFrom()+" said : "+broadMsgRequest.getMsg());
                    System.out.println("Response to "+broadMsgRequest.getFrom()+" : "+response.getMsg());
                    joueurGan=broadMsgRequest.getFrom();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                } else if (nbrMaqique < nbr) {
                    if (joueurGan!=null){
                        response=NbrMagique.BroadMsgResponse.newBuilder()
                                .setFrom(broadMsgRequest.getFrom())
                                .setMsg(joueurGan+ " a deja trouver le nombre")
                                .build();
                        System.out.println(broadMsgRequest.getFrom()+" said : "+broadMsgRequest.getMsg());
                        System.out.println("Response to "+broadMsgRequest.getFrom()+" : "+response.getMsg());
                        responseObserver.onNext(response);
                        responseObserver.onCompleted();
                    }else {
                        response=NbrMagique.BroadMsgResponse.newBuilder()
                                .setFrom(broadMsgRequest.getFrom())
                                .setMsg("retester :: nbrMaqique < nbr")
                                .build();
                        System.out.println(broadMsgRequest.getFrom()+" said : "+broadMsgRequest.getMsg());
                        System.out.println("Response to "+broadMsgRequest.getFrom()+" : "+response.getMsg());
                        responseObserver.onNext(response);
                    }


                } else if (nbrMaqique > nbr) {
                    if (joueurGan!=null){
                        response=NbrMagique.BroadMsgResponse.newBuilder()
                                .setFrom(broadMsgRequest.getFrom())
                                .setMsg(joueurGan+ " a deja trouver le nombre")
                                .build();
                        System.out.println(broadMsgRequest.getFrom()+" said : "+broadMsgRequest.getMsg());
                        System.out.println("Response to "+broadMsgRequest.getFrom()+" : "+response.getMsg());
                        responseObserver.onNext(response);
                        responseObserver.onCompleted();
                    }else {
                        response=NbrMagique.BroadMsgResponse.newBuilder()
                                .setFrom(broadMsgRequest.getFrom())
                                .setMsg("retester :: nbrMaqique > nbr")
                                .build();
                        System.out.println(broadMsgRequest.getFrom()+" said : "+broadMsgRequest.getMsg());
                        System.out.println("Response to "+broadMsgRequest.getFrom()+" : "+response.getMsg());
                        responseObserver.onNext(response);
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
