package SystemDesign;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class OTPGenerator {

    // Assuming you have these constants somewhere
    private static final int OTP_LENGTH = 8;  // Example value, adjust accordingly
    private static final String OTP_SECRET = "YourSecretHere"; // Replace with your secret

    public static int generateOTP() {
        int length = OTP_LENGTH;
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length) - 1;

        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public static String hashOTP(String phone, int otp) {
        // Valid for 5 minutes
        long ttl = 5 * 60 * 1000;
        long expires = System.currentTimeMillis() + ttl;
        // combine the data to be hashed
        String data = phone + "." + otp + "." + expires;
        String hash = hashUsingHmacSha256(data, OTP_SECRET);
        // return the hash and expiry time
        return hash + "." + expires;
    }

    public static boolean verifyOTP(String phone, String hash, String otp) {
        String[] parts = hash.split("\\.");

        if (parts.length != 2) {
            return false;
        }

        String hashValue = parts[0];
        long expires;
        try {
            expires = Long.parseLong(parts[1]);
        } catch (NumberFormatException e) {
            return false;
        }

        if (System.currentTimeMillis() > expires) {
            return false;
        }

        String data = phone + "." + otp + "." + expires;
        String newHash = hashUsingHmacSha256(data, OTP_SECRET);

        return newHash.equals(hashValue);
    }

    private static String hashUsingHmacSha256(String data, String secret) {
        try {
            Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            hmacSHA256.init(secretKey);
            byte[] byteData = data.getBytes();
            byte[] byteHash = hmacSHA256.doFinal(byteData);
            StringBuilder sb = new StringBuilder();
            for (byte b : byteHash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateOTP());

        int otp = 123456; // Replace with your otp generation logic
        String phone = "15103628150"; // Example phone number
        String hashedOtp = hashOTP(phone, otp);
        System.out.println("Hashed OTP: " + hashedOtp);
        boolean isValid = verifyOTP(phone, hashedOtp, String.valueOf(otp));
        System.out.println("Is Valid OTP: " + isValid);
    }
}
