package ma.enset.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import ma.enset.services.ChatServices;

import java.io.IOException;

public class ChatServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server= ServerBuilder.forPort(6666)
                .addService(new ChatServices())
                .build();
        server.start();
        server.awaitTermination();
    }
}
