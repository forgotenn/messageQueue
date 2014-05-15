package calculator;

public class Task {
    public Task(char type, int first, int second, String resultQName) {
        this.type = type;
        this.first = first;
        this.second = second;
        this.resultQName = resultQName;
    }

    public char type;
    public int first, second;
    public String resultQName;
}
