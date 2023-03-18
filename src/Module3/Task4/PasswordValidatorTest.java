package Module3.Task4;

public class PasswordValidatorTest {
    public static void main(String[] args) {
        System.out.println(PasswordValidator.checkAuth("", "", ""));//условию не противоречит
        System.out.println(PasswordValidator.checkAuth("asd", "wsadzxc", "wsadzxc"));
        System.out.println(PasswordValidator.checkAuth("as#d", "wsa@#dzxc", "wsadzxc"));
        System.out.println(PasswordValidator.checkAuth("asd", "wsa@#dzxc", "wsadzxc"));
        System.out.println(PasswordValidator.checkAuth("asdasdasdasdasdasdasdasdasd", "2134124141342342345234wsa@#dzxc", "wsadzxc"));
        System.out.println(PasswordValidator.checkAuth("asdasd2", "2134124141342342345234wsa@#dzxc", "wsadzxc"));
        System.out.println(PasswordValidator.checkAuth("asdasd2", "2134124141342342345234wsadzxc", "wsadzxc"));
        System.out.println(PasswordValidator.checkAuth("asdasd2", "2345", "2345 "));
    }
}
