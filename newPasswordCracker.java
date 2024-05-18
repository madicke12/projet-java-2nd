import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class newPasswordCracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez une méthode pour craquer le mot de passe :");
        System.out.println("1. Attaque brute force");
        System.out.println("2. Attaque par dictionnaire");

        // Lecture du choix de l'utilisateur
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                bruteForceAttack(scanner);
                break;
            case 2:
                dictionaryAttack(scanner);
                break;
            default:
                System.out.println("Choix invalide.");
        }

        // scanner.close();
    }

    public static void bruteForceAttack(Scanner scanner) {
        System.out.println("Entrez le mot de passe cible : ");
        String motDePasseCible = scanner.next();

        String motDePasseHash = hashPassword(motDePasseCible);

        int longueurMaximale = 3;

        for (char c1 = 'a'; c1 <= 'z'; c1++) {
            for (char c2 = 'a'; c2 <= 'z'; c2++) {
                for (char c3 = 'a'; c3 <= 'z'; c3++) {
                    String tentative = "" + c1 + c2 + c3;
                    String tentativeHash = hashPassword(tentative);
                    // System.out.println(tentativeHash);
                    if (tentativeHash.equals(motDePasseHash)) {
                        System.out.println(tentativeHash);
                        System.out.println("Attaque brute force : Mot de passe trouvé : " + tentative);
                        return;
                    }
                }
            }
        }
        System.out.println("Attaque brute force : Mot de passe non trouvé.");
    }

    public static void dictionaryAttack(Scanner scanner) {
        System.out.println("Entrez le mot de passe cible : ");
        String motDePasseCible = scanner.nextLine();

        String motDePasseHash = hashPassword(motDePasseCible);

        String[] dictionnaire = {"aaa", "abc", "xyz", "123", "password","mdp"};

        for (String mot : dictionnaire) {
            String motHash = hashPassword(mot);
            if (motHash.equals(motDePasseHash)) {
                System.out.println("Attaque par dictionnaire : Mot de passe trouvé : " + mot);
                return;
            }
        }
        System.out.println("Attaque par dictionnaire : Mot de passe non trouvé dans le dictionnaire.");
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
