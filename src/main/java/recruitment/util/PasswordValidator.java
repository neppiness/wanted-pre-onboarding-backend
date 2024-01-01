package recruitment.util;

import recruitment.exception.InvalidArgument;

public class PasswordValidator {

    private static final String specialCharacters = "$-_.+!*'()";

    private static final int passwordMinimumLength = 8;

    public static void validatePassword(String password) {
        lengthCheck(password.length());
        digitCheck(password);
        letterCheck(password);
        specialCharacterCheck(password);
    }

    private static void lengthCheck(int length) {
        if (length < passwordMinimumLength) {
            throw new InvalidArgument(InvalidArgument.INVALID_PASSWORD_LENGTH);
        }
    }

    private static void digitCheck(String password) {
        for (int index = 0; index < password.length(); index++) {
            char charAtIndex = password.charAt(index);
            if (Character.isDigit(charAtIndex)) {
                return;
            }
        }
        throw new InvalidArgument(InvalidArgument.PASSWORD_REQUIRES_DIGIT);
    }

    private static void letterCheck(String password) {
        boolean hasCapitalLetter = false;
        boolean hasSmallLetter = false;
        for (int index = 0; index < password.length(); index++) {
            char charAtIndex = password.charAt(index);
            if (Character.isUpperCase(charAtIndex)) {
                hasCapitalLetter = true;
            }
            if (Character.isLowerCase(charAtIndex)) {
                hasSmallLetter = true;
            }
        }
        if (!hasCapitalLetter) {
            throw new InvalidArgument(InvalidArgument.PASSWORD_REQUIRES_CAPITAL_LETTER);
        }
        if (!hasSmallLetter) {
            throw new InvalidArgument(InvalidArgument.PASSWORD_REQUIRES_SMALL_LETTER);
        }
    }

    private static void specialCharacterCheck(String password) {
        for (int index = 0; index < specialCharacters.length(); index++) {
            char specialCharAtIndex = specialCharacters.charAt(index);
            int indexOfSpecialCharacter = password.indexOf(specialCharAtIndex);
            if (indexOfSpecialCharacter != -1) {
                return;
            }
        }
        throw new InvalidArgument(InvalidArgument.PASSWORD_REQUIRES_SPECIAL_CHARACTER);
    }

}
