package Day5;

import java.security.MessageDigest;

public class HashCode {
    public static void main(String[] args) {
        String input = "reyedfim";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            System.out.println(digest);

        }catch (Exception e){e.printStackTrace();}
    }
}
