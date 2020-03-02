package tools;

import java.util.UUID;

public class Tools {

    static final Boolean IS_SHOWING_CONSOLE = true;

    public static void log (String myString) {
        if (myString != null && IS_SHOWING_CONSOLE){
            System.out.print("\n"+myString);
        }
    }

    public static String createUUID(){
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        return uuidString.substring(0, 8);
    }

    public static String removeCharacter(String originalString, String removeChar){
        return originalString.replace(removeChar, "" );
    }

    public static void sleepForTime (Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            //empty
        }
    }
}
