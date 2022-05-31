package task;

import util.SimpleLinkedListStack;

import java.util.Stack;

public class Program {
    public static SimpleLinkedListStack<String> execute1(String[] line){
        SimpleLinkedListStack<String> answerLinkedListStack = new SimpleLinkedListStack<>();
        String str;
        for (String s : line) {
            try {
                str = s;
                answerLinkedListStack.push(str);
            } catch (NumberFormatException e) {

            }
        }
        return answerLinkedListStack;
    }

    public static Stack<String> execute2(String[] line){
        final Stack<String> answerStack = new Stack<>();
        String  str;
        for (String s : line) {
            try {
                str = s;
                answerStack.push(str);
            } catch (NumberFormatException e) {
            }
        }
        return answerStack;
    }
}
