import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
public class newBruteForceAttack {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Lecture du hash du mot de passe à deviner fourni par l'utilisateur via la CLI
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le hash du mot de passe à deviner :");
        String targetHash = scanner.nextLine();
        scanner.close();

        // Caractères à tester pour l'attaque brute force
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ@#$%^&*()_+{}:\"<>?/";

        // Longueur maximale du mot de passe à tester
        int maxLength = 3;

        // Démarrer l'attaque brute force
        if (bruteForce(targetHash, characters, maxLength)) {
            System.out.println("Mot de passe trouvé.");
        } else {
            System.out.println("Aucun mot de passe trouvé après avoir parcouru toutes les combinaisons possibles.");
        }
    }

    // Méthode pour lancer l'attaque brute force
    private static boolean bruteForce(String targetHash, String characters, int maxLength) throws NoSuchAlgorithmException {
        return bruteForceRecursive(targetHash, characters, new StringBuilder(), maxLength);
    }

    // Méthode récursive pour générer et tester les combinaisons de mots de passe
    private static boolean bruteForceRecursive(String targetHash, String characters, StringBuilder prefix, int maxLength) throws NoSuchAlgorithmException {
        if (prefix.length() == maxLength) {
            String generatedHash = generateSHA256(prefix.toString());
            if (generatedHash.equals(targetHash)) {
                System.out.println("Mot de passe trouvé : " + prefix.toString());
                return true;
            }
            return false;
        }

        for (int i = 0; i < characters.length(); i++) {
            prefix.append(characters.charAt(i));
            if (bruteForceRecursive(targetHash, characters, prefix, maxLength)) {
                return true;
            }
            prefix.deleteCharAt(prefix.length() - 1);
        }
        return false;
    }

    // Fonction pour générer le hash SHA-256 d'un mot de passe
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
