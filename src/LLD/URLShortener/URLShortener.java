package LLD.URLShortener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class URLShortener {
    private long uniqueURLId;
    private Map<String, String> shortUrlToLongUrlMap;
    private Map<String, String> longUrlToShortUrlMap;
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int BASE_LEN = ALPHABET.length();
    private String SHORT_URL_BASE = "http://short.url/";

    public URLShortener() {
        this.uniqueURLId = 1L;
        this.shortUrlToLongUrlMap = new HashMap<>();
        this.longUrlToShortUrlMap = new HashMap<>();
    }

    public String encode(long num){

        if(num == 0){
            return String.valueOf(this.ALPHABET.charAt(0));
        }
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int remainder = (int) (num % this.BASE_LEN);
            sb.append(this.ALPHABET.charAt(remainder));
            num /= this.BASE_LEN;
        }
        return sb.reverse().toString();

    }
    public String shortenUrl(String longUrl) {

        if(this.longUrlToShortUrlMap.containsKey(longUrl)){
            return this.SHORT_URL_BASE + this.longUrlToShortUrlMap.get(longUrl);
        }

        String shortCode = this.encode(this.uniqueURLId);
        this.uniqueURLId++;

        this.longUrlToShortUrlMap.put(longUrl, shortCode);
        this.shortUrlToLongUrlMap.put(shortCode, longUrl);

        return this.SHORT_URL_BASE + shortCode;
    }

    public String getOriginalUrl(String shortUrl) {
        String shortCode = shortUrl.replace(this.SHORT_URL_BASE, "");

        if(this.shortUrlToLongUrlMap.containsKey(shortCode)){
            return this.shortUrlToLongUrlMap.get(shortCode);
        }

        return "";
    }

    public static void main(String[] args) {

        URLShortener shortener = new URLShortener();

        System.out.println(shortener.shortenUrl("https://www.example.com/very/long/path")); // b
        System.out.println(shortener.shortenUrl("https://www.google.com/search?q=hello")); // c
        System.out.println(shortener.getOriginalUrl("http://short.url/b")); // ""
        System.out.println(shortener.getOriginalUrl("http://short.url/a")); // ""
        System.out.println(shortener.shortenUrl("https://www.example.com/very/long/path")); // b
        System.out.println(shortener.getOriginalUrl("http://short.url/c")); // "https://www.google.com/search?q=hello"

        System.out.println();

        shortener = new URLShortener();

        System.out.println(shortener.getOriginalUrl("http://short.url/a"));
        System.out.println(shortener.shortenUrl("https://www.test.com"));
        System.out.println(shortener.getOriginalUrl("http://short.url/b"));
        System.out.println(shortener.getOriginalUrl("http://short.url/xyz"));

        System.out.println();
    }
}
