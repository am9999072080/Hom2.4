import exception.WrongLoginException;
import exception.WrongPasswordException;

public class Main {
    private static final String validatePattern = "^[a-zA-Z0-9_]*$";
    public static void main(String[] args) {
        check("Login_1", "Password_1", "Password_1");
        check("$$$", "Password_1", "Password_1");
        check("Login_1", "*/-++#", "Password_1");
        check("Login_1", "Password_1", "Password_2");
        check("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "Password_1", "Password_1");
        check("Login_1", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "Password_1");
    }
    private static boolean check(String login, String password,String confirmPassword){
        boolean isValid = true;

        try {
            checkLogin(login);
            checkPassword(password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println("ошибка в введенном логине:" + e.getMessage());
            isValid = false;
        } catch (WrongPasswordException e) {
            System.out.println("ошибка в введенном пароле:" + e.getMessage());
            isValid = false;
        }
        return isValid;

    }
    private static void checkLogin(String login) throws WrongLoginException {
        if(!login.matches(validatePattern)) {
            throw new WrongLoginException("Login должен содержать в себе только латинские буквы, цифры и знак подчеркивания");
        } else if (login.length()>20) {
            throw new WrongLoginException("Login должен быть равен или меньше 20 символов");
        }
    }
    private static void checkPassword(String password,String confirmPassword) throws WrongPasswordException {
        if(!password.matches(validatePattern)) {
            throw new WrongPasswordException("Password должен содержать в себе только латинские буквы, цифры и знак подчеркивания");
        } else if (password.length()>20) {
            throw new WrongPasswordException("Password должен быть равен или меньше 20 символов");
        }else if(!password.equals(confirmPassword)){
            throw new WrongPasswordException("Password и confirmPassword не равны");
        }
    }
}