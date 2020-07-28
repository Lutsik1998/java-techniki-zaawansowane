import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

public class EncrypterDecrepter {


    public static void encrypt(String key, String file){
        File f = new File(file);
        init(Cipher.ENCRYPT_MODE, f, key);
    }

    public static void decrypt(String key, String file){
        File f = new File(file);
        init(Cipher.DECRYPT_MODE, f, key);
    }


    public static void init(int szyfrMode, File file, String key){
        try {
            if(key.length() != 16) {
                key = "standardowyklucz";
            }
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(szyfrMode, secretKey);

            FileInputStream inputStream = new FileInputStream(file);
            byte[] input = new byte[(int) file.length()];
            inputStream.read(input);
            inputStream.close();

            byte[] output = cipher.doFinal(input);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(output);
            outputStream.close();

        } catch (Exception ex) {
        }
    }

}




