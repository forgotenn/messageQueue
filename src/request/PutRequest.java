package request;

import java.io.Serializable;

public class PutRequest extends Request {
    public PutRequest(String qName, Serializable task) {
        super(qName);
        this.task = task;
    }

    public Serializable getTask() {
        return task;
    }
    protected final Serializable task;
}
