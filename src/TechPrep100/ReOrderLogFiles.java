package TechPrep100;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ReOrderLogFiles {

    public static void main(String[] args) {

        String[][] logs = {
                {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"},
                {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"},
                {"z1 abc","y1 abc","x1 abc"}
        };

        for(String[] log : logs) {
            System.out.println("After Re-ordering the logs: " + Arrays.toString(reorderLogFiles(log)));
        }
    }

    public static String[] reorderLogFiles(String[] logs) {
        String[] reorderedLogFiles = new String[logs.length];

        PriorityQueue<String> minHeapForLetterLogs = new PriorityQueue<>(
                (a, b) -> {

                    StringBuilder contentStringForA = new StringBuilder();
                    StringBuilder contentStringForB = new StringBuilder();

                    String[] logStringForA = a.split(" ");

                    for(int i = 1 ; i < logStringForA.length; i++){
                        contentStringForA.append(logStringForA[i] + " ");
                    }

                    String[] logStringForB = b.split(" ");

                    for(int i = 1 ; i < logStringForB.length; i++){
                        contentStringForB.append(logStringForB[i] + " ");
                    }

                    int priorityCompare = contentStringForA.compareTo(contentStringForB);

                    if (priorityCompare == 0) {
                        return logStringForA[0].compareTo(logStringForB[0]);
                    }
                    return priorityCompare;

                });

        PriorityQueue<String> minHeapForDigitLogs = new PriorityQueue<>(
                (a, b) -> {

                    String identifierForA = a.split(" ")[0];
                    String identifierForB = b.split(" ")[0];

                    int priorityCompare = identifierForA.compareTo(identifierForB);

                    if (priorityCompare == 0) {
                        String[] digitsFromA = a.split(" ");
                        String[] digitsFromB = b.split(" ");

                        return digitsFromA[1].compareTo(digitsFromB[1]);

                    }
                    return priorityCompare;
                });

        int index = 0;

        for (String log : logs) {
            String word = log.split(" ")[1];
            if (Character.isDigit(word.charAt(0))) {
                minHeapForDigitLogs.offer(log);
            } else {
                minHeapForLetterLogs.offer(log);
            }
        }

        while (!minHeapForLetterLogs.isEmpty()) {
            reorderedLogFiles[index] = minHeapForLetterLogs.poll();
            index++;
        }

        while (!minHeapForDigitLogs.isEmpty()) {
            reorderedLogFiles[index] = minHeapForDigitLogs.poll();
            index++;
        }
        return reorderedLogFiles;
    }
}
