package com.edusalguero.rexoubador.domain.shared.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IPv4Validator implements ValidatorInterface<String> {
    private static final String IPV4_REGEX =
            "^([0-9]{1,3}\\.){3}[0-9]{1,3}?$";

    private static Pattern pattern;

    static {
        pattern = Pattern.compile(IPV4_REGEX);
    }

    public boolean validate(String ip) {
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }
}
