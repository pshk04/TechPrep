package TechPrep100;

import java.util.Stack;

public class ValidParentheses {

    public static void main(String[] args) {
        String[] parentheses = {
                "({})",
                "()[{}]",
                "{)",
                "[{()}]",
                "{[}] "
        };
        for(String expression : parentheses) {
            System.out.println("Is expression valid?: " +((isValid(expression)) ? "YES" : "NO"));
        }
    }

    public static boolean isValid(String expression) {

        if(expression.length() < 2){
            return false;
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                stack.push(")");
            } else if (expression.charAt(i) == '[') {
                stack.push("]");
            } else if (expression.charAt(i) == '{') {
                stack.push("}");
            } else if (expression.charAt(i) == '}') {
                if (!stack.isEmpty() && stack.peek().equals("}")) {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (expression.charAt(i) == ')') {
                if (!stack.isEmpty() && stack.peek().equals(")")) {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (expression.charAt(i) == ']') {
                if (!stack.isEmpty() && stack.peek().equals("]")) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValidWithThreestacks(String expression){
        Stack<String> smallBraceStack = new Stack<>();
        Stack<String> flowerBraceStack = new Stack<>();
        Stack<String> bigBraceStack = new Stack<>();

        for(int i = 0 ; i < expression.length(); i++){
            if(expression.charAt(i) == '('){
                smallBraceStack.push("(");
            }else if(expression.charAt(i) == '['){
                bigBraceStack.push("[");
            }else if(expression.charAt(i) == '{'){
                flowerBraceStack.push("{");
            }else if(expression.charAt(i) == '}'){
                if(!flowerBraceStack.isEmpty()){
                    flowerBraceStack.pop();
                }else{
                    return false;
                }
            }else if(expression.charAt(i) == ')'){
                if(!smallBraceStack.isEmpty()){
                    smallBraceStack.pop();
                }else{
                    return false;
                }
            }else if(expression.charAt(i) == ']'){
                if(!bigBraceStack.isEmpty()){
                    bigBraceStack.pop();
                }else{
                    return false;
                }
            }
        }
        return (flowerBraceStack.isEmpty() && bigBraceStack.isEmpty() && smallBraceStack.isEmpty());
    }
}
