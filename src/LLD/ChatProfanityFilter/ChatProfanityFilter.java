package LLD.ChatProfanityFilter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatProfanityFilter {
    private Set<String> bannedWordsSet;
    private Map<String, Integer> censoredWordCountMap;

    public ChatProfanityFilter() {
        this.bannedWordsSet = new HashSet<>();
        this.censoredWordCountMap = new HashMap<>();
    }

    public void addWord(String word) {
        this.bannedWordsSet.add(word.toLowerCase());
    }

    public boolean removeWord(String word) {
        if(!this.bannedWordsSet.contains(word.toLowerCase())){
            return false;
        }else{
            this.bannedWordsSet.remove(word.toLowerCase());
            return true;
        }
    }

    public String filter(String message) {

        StringBuilder delimeterWord = new StringBuilder();
        int badWordLength = 0, censoredCount = 0;
        String regex = "";

        for(String badWord : this.bannedWordsSet){

            badWordLength = badWord.length();
            while(badWordLength > 0){
                delimeterWord.append("*");
                badWordLength--;
            }

            regex = "(?i)(?<=^|[\\s-_!?.,;])"+ badWord + "(?=$|[\\s-_!?.,;])";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(message.toLowerCase());

            while (matcher.find()) {
                censoredCount++;
            }

            message = message.replaceAll(regex, delimeterWord.toString());

            if(!this.censoredWordCountMap.containsKey(badWord)){
                this.censoredWordCountMap.put(badWord, censoredCount);
            }else{
                this.censoredWordCountMap.put(badWord, this.censoredWordCountMap.get(badWord) + censoredCount);
            }
            censoredCount = 0;
            delimeterWord = new StringBuilder();
        }
        return message;
    }

    public int getCensorCount(String word) {
        return this.censoredWordCountMap.get(word);
    }

    public static void main(String[] args) {
        ChatProfanityFilter chatProfanityFilter = new ChatProfanityFilter();

        chatProfanityFilter.addWord("badword");
        System.out.println(chatProfanityFilter.filter("This is a badword in chat"));
        System.out.println(chatProfanityFilter.filter("BadWord again, badword!"));
        System.out.println(chatProfanityFilter.getCensorCount("badword"));
        System.out.println(chatProfanityFilter.removeWord("badword"));
        System.out.println(chatProfanityFilter.filter("badword is fine now"));
        System.out.println(chatProfanityFilter.getCensorCount("badword"));

        System.out.println();

        chatProfanityFilter = new ChatProfanityFilter();

        chatProfanityFilter.addWord("foo");
        chatProfanityFilter.addWord("bar");
        System.out.println(chatProfanityFilter.filter("foobar contains foo and bar"));
        System.out.println(chatProfanityFilter.filter("FOO! bar? foo-bar."));
        System.out.println(chatProfanityFilter.getCensorCount("foo"));
        System.out.println(chatProfanityFilter.getCensorCount("bar"));
        System.out.println(chatProfanityFilter.removeWord("baz"));

        System.out.println();

        chatProfanityFilter = new ChatProfanityFilter();

        chatProfanityFilter.addWord("abc");
        chatProfanityFilter.addWord("xyz");
        System.out.println(chatProfanityFilter.filter("abc-xyz_abc xyz"));
        System.out.println(chatProfanityFilter.getCensorCount("abc"));
        System.out.println(chatProfanityFilter.getCensorCount("xyz"));
        System.out.println();

        chatProfanityFilter = new ChatProfanityFilter();

        chatProfanityFilter.addWord("cat");

        System.out.println(chatProfanityFilter.filter("cat cats category caterpillar cat."));
        System.out.println(chatProfanityFilter.filter("a cat, the cat; CAT!"));

        System.out.println(chatProfanityFilter.getCensorCount("cat"));
    }
}
