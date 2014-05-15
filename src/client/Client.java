package client;

import request.GetRequest;
import request.PutRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public void put(String qName, Serializable task) {
        Socket socket = new Socket();
        try {
            socket.connect(address);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(new PutRequest(qName, task));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object get(String qName) {
        Socket socket = new Socket();
        Object obj = null;
        try {
            socket.connect(address);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(new GetRequest(qName));
            obj = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Client(String addr, int port) {
        this.address = new InetSocketAddress(addr, port);
    }
    private final InetSocketAddress address;
}
