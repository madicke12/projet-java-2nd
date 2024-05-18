
import java.util.Scanner;

class Application {
    public static void main(String[] args){
        // Utilisation de la fabrique pour la force brute
        PasswordCrackerFactory dictionaireFactory = new DictionaryPasswordCrackerFactory();
        PasswordCrackerFactory bruteForceFactoy = new BruteForcePasswordCrackerFactory();
        LocalPasswordCracker dictionnaireLocalCracker = dictionaireFactory.createLocalPasswordCracker();
        LocalPasswordCracker bruteforceLocalCracker = bruteForceFactoy.createLocalPasswordCracker();
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenue  , Ici nous crackons des mot de passe ..");
        System.out.println("Veuillez Choisir l'option qui vous convient ..");
        System.out.println("Attaque locale : Appuyez sur 1");
        System.out.println("Attaque online : Appuyez sur 2");
        int choice = sc.nextInt();
        if (choice == 1) {
            System.out.println("Veuillez Choisir l'option qui vous convient ..");
            System.out.println("Attaque par dictionnaire locale : Appuyez sur 1");
            System.out.println("Attaque par brute force locale  : Appuyez sur 2");
            int option = sc.nextInt();
            System.out.println("Renseigner un hash");
            String hash = sc.next();
            if (option == 1) {
                String password = dictionnaireLocalCracker.crack(hash);
                System.out.println("Mot de passe trouvé (attaque par dictionnaire locale) : " + password);
            } else {
                bruteforceLocalCracker.crack(hash);
            }
        }
        // else{
        //     System.out.println("Renseigner un hash");
        //     String hash = sc.next();
        //     if (option == 1) {
        //         String password = dictionnaireLocalCracker.crack(hash);
        //         System.out.println("Mot de passe trouvé (attaque par dictionnaire locale) : " + password);
        //     } else {
        //         String password = bruteforceLocalCracker.crack(hash);
        //         System.out.println("Mot de passe trouvé (attaque par Brute locale) : " + password);
        //     }
        // }

    }
}