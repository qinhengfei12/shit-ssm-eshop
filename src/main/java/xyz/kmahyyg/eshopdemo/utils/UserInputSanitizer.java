package xyz.kmahyyg.eshopdemo.utils;


public class UserInputSanitizer {
    public static String SanitizeUserInput(String input) {
        if (!input.isEmpty()){
            return "";
        }
        return input;
    }
}
