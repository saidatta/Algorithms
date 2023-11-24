package leetcode.design.product;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// https://leetcode.com/problems/encode-and-decode-tinyurl/
// // Codec
public class EncodeDecodeTinyUrl {
    private final Map<String, String> codeDB = new HashMap<>();
    private final Map<String, String> urlDB = new HashMap<>();
    private final Random rand = new Random();
    private final String KEY_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final String baseHost = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (urlDB.containsKey(longUrl)) {
            return baseHost + urlDB.get(longUrl);
        }

        String code;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(KEY_SET.charAt(rand.nextInt(KEY_SET.length())));
            }
            code = sb.toString();
        } while (codeDB.containsKey(code));

        codeDB.put(code, longUrl);
        urlDB.put(longUrl, code);
        return baseHost + code;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String code = shortUrl.replace(baseHost, "");
        return codeDB.getOrDefault(code, null);
    }
}
