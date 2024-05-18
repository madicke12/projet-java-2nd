public class BruteForcePasswordCrackerFactory implements PasswordCrackerFactory {
    @Override
    public LocalPasswordCracker createLocalPasswordCracker() {
        return new BruteForceLocalPasswordCracker();
    }

    // @Override
    // public OnlinePasswordCracker createOnLinePasswordCracker() {
    //     return new BruteForceOnlinePasswordCracker();
    // }
} 