package broker;

import request.GetRequest;
import request.PutRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestProcessor implements Runnable{
    public RequestProcessor(Socket socket, TaskQueue q) {
        this.socket = socket;
        this.q = q;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Object obj = in.readObject();
            if (obj instanceof PutRequest) {
                PutRequest req = (PutRequest)obj;
                q.put(req.getQueryName(), req.getTask());
            } else if (obj instanceof GetRequest) {
                GetRequest req = (GetRequest)obj;
                out.writeObject(q.get(req.getQueryName()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private final Socket socket;
    private final TaskQueue q;
}
