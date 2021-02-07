package xyz.kmahyyg.eshopdemo.utils;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.MySQLCodec;

public class UserInputSanitizer {
    public static String SanitizeUserInput(String input) {
        if (!input.isEmpty()){
            Encoder esapiEncoder = ESAPI.encoder();
            String finalRes = "";
            // as lazy, as simple
            finalRes = esapiEncoder.encodeForJavaScript(input);
            finalRes = esapiEncoder.encodeForHTMLAttribute(finalRes);
            return finalRes;
        }
        return input;
    }
}
