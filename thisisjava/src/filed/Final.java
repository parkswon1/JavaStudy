package thisisjava.src.filed;

public class Final {
    public static void main(String[] args){
        String  encryptionKey = SecurityConfig.getEncryptiomKey();
        System.out.println("암호화 키: " + encryptionKey);
    }
}