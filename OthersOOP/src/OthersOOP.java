import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
public class OthersOOP {
    private static final Logger logger = LoggerFactory.getLogger(OthersOOP.class);
    public static void main(String[] args) {
        // class : System, Math, FileWriter
        // instance : f1, f2

        logger.println(Math.PI);
        logger.println(Math.floor(1.8));
        logger.println(Math.ceil(1.8));

        try (FileWriter f1 = new FileWriter("data.txt")) {
            f1.write("Hello");
            f1.write(" Java");
            // try-with-resources를 사용하면 명시적으로 close()를 호출하지 않아도 됩니다.
        } catch (IOException e) {
            e.printStackTrace(); // IOException 처리
        }

        try (FileWriter f2 = new FileWriter("data2.txt")) {
            f2.write("Hello");
            f2.write(" Java2");
            // try-with-resources를 사용하면 명시적으로 close()를 호출하지 않아도 됩니다.
        } catch (IOException e) {
            e.printStackTrace(); // IOException 처리
        }

        try (FileWriter f1 = new FileWriter("data.txt", true)) {
            // "!!!"를 기존 파일에 이어서 쓰기
            f1.write("!!!");
        } catch (IOException e) {
            e.printStackTrace(); // IOException 처리
        }
    }
}
