
public class BruteForceLocalPasswordCracker implements LocalPasswordCracker {
    


    @Override
    public String crack(String hash) {
    String CHARACTERS =  "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456";
    int MAX_LENGTH = 3;
        bruteForce(hash, CHARACTERS, MAX_LENGTH);
        return null;
    }

    private static boolean bruteForce(String targetHash, String characters, int maxLength)  {
        return bruteForceRecursive(targetHash, characters, new StringBuilder(), maxLength);
    }

   private static boolean bruteForceRecursive(String targetHash, String characters, StringBuilder prefix, int maxLength) {
    Hasher hasher = new Hasher();

        if (prefix.length() == maxLength) {
            String generatedHash = hasher.hash(prefix.toString());
            System.out.println(generatedHash);
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
    //   private static String (String input)  {
    //         MessageDigest md = MessageDigest.getInstance("SHA-256");
    //         byte[] messageDigest = md.digest(input.getBytes());
    //         StringBuilder sb = new StringBuilder();
    //         for (byte b : messageDigest) {
    //             sb.append(String.format("%02x", b));
    //         }
    //         return sb.toString();
        
        
    // };
}
