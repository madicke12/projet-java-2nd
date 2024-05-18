import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class bestBruteForceAttack {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le hash du mot de passe à deviner :");
        String targetHash = scanner.nextLine();
        scanner.close();

        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$%^&*()_+{}:\"<>?/";
        int minLength = 1;
        int maxLength = 4; // Augmentez cette valeur si nécessaire

        if (bruteForce(targetHash, characters, minLength, maxLength)) {
            System.out.println("Mot de passe trouvé.");
        } else {
            System.out.println("Aucun mot de passe trouvé après avoir parcouru toutes les combinaisons possibles.");
        }
    }

    private static boolean bruteForce(String targetHash, String characters, int minLength, int maxLength) throws NoSuchAlgorithmException {
        for (int length = minLength; length <= maxLength; length++) {
            if (bruteForceRecursive(targetHash, characters, new StringBuilder(), length)) {
                return true;
            }
        }
        return false;
    }

    private static boolean bruteForceRecursive(String targetHash, String characters, StringBuilder prefix, int length) throws NoSuchAlgorithmException {
        if (length == 0) {
            return false;
        }

        for (int i = 0; i < characters.length(); i++) {
            prefix.append(characters.charAt(i));
            if (generateSHA256(prefix.toString()).equals(targetHash)) {
                System.out.println("Mot de passe trouvé : " + prefix.toString());
                return true;
            }
            if (bruteForceRecursive(targetHash, characters, prefix, length - 1)) {
                return true;
            }
            prefix.setLength(prefix.length() - 1); // Réinitialiser le prédicat pour la prochaine itération
        }
        return false;
    }

    private static String generateSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
