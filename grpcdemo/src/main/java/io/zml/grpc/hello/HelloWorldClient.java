package io.zml.grpc.hello;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorldClient {
    private static final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());

    private final ManagedChannel channel;

    //阻塞模型
    private final GreeterGrpc.GreeterBlockingStub blockingStub;


    public HelloWorldClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build());
    }


    HelloWorldClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public static void main(String[] args) throws Exception {

        HelloWorldClient client = new HelloWorldClient("localhost", 50051);
        try {
            /* Access a service running on the local machine on port 50051 */
            String user = "world";
            if (args.length > 0) {
                user = args[0]; /* Use the arg as the name to greet if provided */
            }
            client.greet(user);
        } finally {
            client.shutdown();
        }
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void greet(String name) {
        logger.info("Will try to greet " + name + " ...");
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("Greeting: " + response.getMessage());
    }
}
