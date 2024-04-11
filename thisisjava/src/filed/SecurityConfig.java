package thisisjava.src.filed;

public final class SecurityConfig{
    private static final String ENCRYPTIOM_KEY = "Complexkey123!";

    static String getEncryptiomKey(){
        return ENCRYPTIOM_KEY;
    }
}
