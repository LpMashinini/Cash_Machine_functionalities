import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class pinAuth {

    private static String ALGORITHM = "PBKDF2withHmacSHA512";
    private static final int Iteration = 65536;
    private static final int KEY_LENGTH = 512;
    private static final int SALT_LENGTH = 16;


    public String hashPassword(String password) throws Exception{

        // Generating random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        //Creating a Key specification
        KeySpec key_Spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                Iteration,
                KEY_LENGTH
        );

        //Hashing the password
        SecretKeyFactory key_factor = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash = key_factor.generateSecret(key_Spec).getEncoded();

        //Encoding the salt and hash to Base64 string for easy storage eg Database
        String saltBase64 = Base64.getEncoder().encodeToString(salt);
        String hashBase64 = Base64.getEncoder().encodeToString(hash);

        // formating the string for database purposes
        return String.format("%s:%s:%d:%d", saltBase64, hashBase64, Iteration,KEY_LENGTH);
    }


    public boolean verifyPassword(String inputPasword, String storedHashData) throws Exception{

        String[] parts = storedHashData.split(":");
        String saltBase64 = parts[0];//stores salt
        String storedHashBase64 = parts[1];// stores the hashed code
        int iteration = Integer.parseInt(parts[2]);// stores Iteration counts
        int key_lenght  = Integer.parseInt(parts[3]);// Stores key lenght


        byte[] salt = Base64.getDecoder().decode(saltBase64);
        byte[] storedHash = Base64.getDecoder().decode(storedHashBase64);

        KeySpec key_spec = new PBEKeySpec(
          inputPasword.toCharArray(),
          salt,
          iteration,
          key_lenght
        );

        SecretKeyFactory key_factory  =  SecretKeyFactory.getInstance(ALGORITHM);

        byte[] compute  = key_factory.generateSecret(key_spec).getEncoded();

        return Arrays.equals(compute,storedHash);
    }


}
