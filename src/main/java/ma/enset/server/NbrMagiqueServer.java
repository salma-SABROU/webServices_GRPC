package ma.enset.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import ma.enset.services.NbrMagiqueServices;

import java.io.IOException;

public class NbrMagiqueServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server= ServerBuilder.forPort(5555)
                .addService(new NbrMagiqueServices())
                .build();
        server.start();
        server.awaitTermination();
    }
}
