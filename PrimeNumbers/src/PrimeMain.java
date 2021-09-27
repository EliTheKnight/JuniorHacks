import java.io.*;

public class PrimeMain {

    public static void main(String[] args) {
        int max = 1000000;
        PrimeMethods num = new PrimeMethods();

        long a = System.currentTimeMillis();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("filename.txt"), "utf-8"))) {
            writer.write("" + num.getPrimes(max));
        }catch (Exception e){e.printStackTrace();}

        long b = System.currentTimeMillis();

        System.out.println(b-a);


    }
}
