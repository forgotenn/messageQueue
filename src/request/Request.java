package request;

import java.io.Serializable;

public abstract class Request implements Serializable {
    public Request(String qName) {
        this.qName = qName;
    }

    public String getQueryName() {
        return qName;
    }

    protected final String qName;

}

