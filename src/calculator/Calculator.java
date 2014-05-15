package calculator;

import client.Client;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {
    public void evaluate(Task task) {
        Client client = new Client(addr, port);
        if (task.type == '+') {
            client.put(task.resultQName, task.first + task.second);
        } else if (task.type == '-') {
            client.put(task.resultQName, task.first - task.second);
        } else if (task.type == '*') {
            client.put(task.resultQName, task.first * task.second);
        } else if (task.type == '/') {
            client.put(task.resultQName, task.first / task.second);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rnd = new Random();
        Client client = new Client("127.0.0.1", 1234);

        while (true) {
            String task = in.nextLine();
            StringTokenizer tokens = new StringTokenizer(task);
            Stack<Integer> st = new Stack<Integer>();
            while (tokens.hasMoreTokens()) {
                String cur = tokens.nextToken();
                try {
                    st.add(Integer.parseInt(cur));
                } catch (NumberFormatException e) {
                    assert cur.length() == 1: "Incorrect expression";
                    String qName = "" + rnd.nextInt();
                    new Calculator("127.0.0.1", 1234).evaluate(new Task(cur.charAt(0), st.pop(), st.pop(), qName));
                    st.add((Integer) client.get(qName));
                }
            }
            System.out.println(st.pop());
            assert st.empty(): "Incorrect expression";
        }
    }

    public Calculator(String addr, int port) {
        this.addr = addr;
        this.port = port;
    }

    final String addr;
    final int port;
}
