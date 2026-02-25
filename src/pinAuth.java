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


    public String hashPin(String pin) throws Exception {

        // Generating random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        //Creating a Key specification
        KeySpec key_Spec = new PBEKeySpec(
                pin.toCharArray(),
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
        return String.format("%s:%s:%d:%d", saltBase64, hashBase64, Iteration, KEY_LENGTH);
    }

    public boolean verifyPin(String inputPin, String storedHashPin) throws Exception {

        String[] parts = storedHashPin.split(":"); // splitting the passed hashed pin value
        String saltBase = parts[0];
        String storedHashedBase64 = parts[1];
        int iteration = Integer.parseInt(parts[2]);
        int keyLength = Integer.parseInt(parts[3]);

        byte[] salt = Base64.getDecoder().decode(saltBase);
        byte[] storedHash = Base64.getDecoder().decode(storedHashedBase64);

        KeySpec key_Spec = new PBEKeySpec(
                inputPin.toCharArray(),
                salt,
                iteration,
                keyLength
        );

        SecretKeyFactory key_Factory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] computeHash = key_Factory.generateSecret(key_Spec).getEncoded();

        return Arrays.equals(computeHash,storedHash);
    }


}
