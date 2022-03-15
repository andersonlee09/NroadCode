import java.util.ArrayDeque;
import java.util.Stack;

class MyQueue {
    private Stack<Integer> stackOne = new Stack<>();
    private Stack<Integer> stackTwo = new Stack<>();
    public MyQueue() {

    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());
    }
    public void push(int x) {
        stackOne.push(x);
    }

    public int pop() {
        judge();
        return stackTwo.pop();
    }
    ArrayDeque
    public void judge() {
        if (stackTwo.isEmpty()) {
            while (!stackOne.isEmpty()) {
                stackTwo.push(stackOne.pop());
            }
        }
    }
    public int peek() {
        judge();
        return stackTwo.peek();
    }

    public boolean empty() {
        return stackOne.isEmpty() && stackTwo.isEmpty();
    }
}