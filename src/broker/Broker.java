package broker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Broker implements Runnable{
    public Broker(int port) {
        this.queue = new TaskQueue();
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();
                new Thread(new RequestProcessor(socket, queue)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Broker(1234).run();
    }

    private final TaskQueue queue;
    private final int port;
}
