

public class DictionnaryLocalPasswordCracker implements LocalPasswordCracker {
    private static final String[] dictionary = {"password", "123456", "qwerty", "abc123","hty"};

    Hasher hasher = new Hasher() ;
    
    @Override
    public String crack(String hash) {
        for (String word : dictionary) {
            String hashedPassword = hasher.hashPassword(word);
            System.out.println(hashedPassword);
            if (hashedPassword.equals(hash)) {
                return word;
            }
        }
        return null;
    }
}

