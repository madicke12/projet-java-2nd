public class DictionaryPasswordCrackerFactory implements PasswordCrackerFactory {

    public DictionaryPasswordCrackerFactory() {
    }

    @Override
    public LocalPasswordCracker createLocalPasswordCracker() {
        return new DictionnaryLocalPasswordCracker();
    }

    // @Override
    // public OnlinePasswordCracker createOnLinePasswordCracker() {
    //     return new DictionnaryOnLinePasswordCracker();
    // }
}