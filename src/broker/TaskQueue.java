package broker;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {
    public TaskQueue() {
        this.queue = new HashMap<String, LinkedBlockingQueue<Serializable>>();
    }

    public Serializable get(String qName) throws InterruptedException {
        if (!queue.containsKey(qName)) {
            queue.put(qName, new LinkedBlockingQueue<Serializable>());
        }
        return queue.get(qName).take();
    }

    public void put(String qName, Serializable task) throws InterruptedException {
        if (!queue.containsKey(qName)) {
            queue.put(qName, new LinkedBlockingQueue<Serializable>());
        }
        queue.get(qName).put(task);
    }

    private Map<String, LinkedBlockingQueue<Serializable>> queue;
}
