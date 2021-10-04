package com.dharani.homepage.validate;


import com.dharani.homepage.model.User;
import com.dharani.homepage.exception.InvalidNameException;
import com.dharani.homepage.exception.InvalidPhNoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public boolean validateName(final String name) {
        char[] ch = name.toCharArray();
        for (char c : ch) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public boolean validatePhoneNumber(final User user) {
        int length = String.valueOf(user.getPhoneNo()).length();
        if (length == 10) {
            return true;
        }
        return false;
    }
    public boolean validateEmail(final String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPassword(final String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
    public void checkException(final User user) throws InvalidPhNoException, InvalidNameException {
        if (String.valueOf(user.getPhoneNo()).length() == 10) {
            throw new InvalidPhNoException("Controller Exception");
        }
        if (validateName(user.getName())) {
            throw new InvalidNameException("Please enter valid Name");
        }
        if (user == null) {
            throw new NullPointerException("Null Pointer Exception");
        }
    }


}
