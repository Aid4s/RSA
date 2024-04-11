import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.PrivateKey;
import javax.crypto.Cipher;
import java.util.Scanner;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        try {
            KeyPair keyPair = generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            Cipher cipher = Cipher.getInstance("RSA");

            // teksto ivedimas
            Scanner scanner = new Scanner(System.in);
            System.out.println("Įveskite tekstą, kurį norite užšifruoti:");
            String inputText = scanner.nextLine();

            // sifravimas pagal viesa rakta
            byte[] inputData = inputText.getBytes();
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedData = cipher.doFinal(inputData);

            // uzsifruotas tekstas konvertuojamas i base64
            String encryptedText = Base64.getEncoder().encodeToString(encryptedData);
            System.out.println("Užšifruotas tekstas:");
            System.out.println(encryptedText);

            // desifravimas su privaciu raktu
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedData = cipher.doFinal(encryptedData);

            // desifruotas tekstas
            String decryptedText = new String(decryptedData);
            System.out.println("Atšifruotas tekstas:");
            System.out.println(decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // rakto poros generavimas
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // 2048 bitų ilgio rakto pora
        return keyPairGenerator.generateKeyPair();
    }
}
