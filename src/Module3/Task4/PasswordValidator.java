package Module3.Task4;

public class PasswordValidator {
    public static Boolean checkAuth(String login, String password, String confirmPassword) {
        try {
            if (!login.matches("[A-Za-z0-9_]*")) {
                throw new WrongLoginException("Логин содержит недопустимые символы");
            }
            if (login.length() > 19) {
                throw new WrongLoginException("Логин слишком длинный");
            }
            if (!password.matches("[A-Za-z0-9_]*")) {
                throw new WrongPasswordException("Пароль содержит недопустимые символы");
            }
            if (password.length() > 19) {
                throw new WrongPasswordException("Пароль слишком длинный");
            }
            if (!password.equals(confirmPassword)) {
                throw new WrongPasswordException("Пароль и подтверждение не совпадают");
            }
            return true;
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
