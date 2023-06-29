import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.pages.AuthPage;
import org.pages.HomePage;
import org.pages.MorePage;
import org.pages.UsersPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    WebDriver driver;


    @BeforeEach
    public void setUp() {


        WebDriverManager wdm = WebDriverManager.chromedriver().browserInDocker()
                .enableVnc().enableRecording();
        wdm.setup();
        driver = wdm.create();
        driver.get("http://185.67.95.60/");
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }


    @Test
    public void authTest() throws InterruptedException {


        AuthPage authPage = new AuthPage(driver);
        HomePage homePage = authPage.authorization("test@protei.ru", "test");
        Thread.sleep(2000);
        assertEquals(homePage.getTitle(), "Добро пожаловать!");
        assertEquals(homePage.getUrl(), "http://185.67.95.60/main");


    }

    @Test
    public void transferToUsersPage() throws InterruptedException {
        AuthPage authPage = new AuthPage(driver);
        HomePage homePage = authPage.authorization("test@protei.ru", "test");
        Thread.sleep(2000);
        UsersPage usersPage = homePage.transferToUserPage();
        Thread.sleep(2000);
        assertEquals(usersPage.getUrl(), "http://185.67.95.60/users");

    }

    @Test
    public void addUser() throws InterruptedException {
        AuthPage authPage = new AuthPage(driver);
        HomePage homePage = authPage.authorization("test@protei.ru", "test");
        Thread.sleep(2000);
        UsersPage usersPage = homePage.transferToUserPage();
        int oldSize = usersPage.getUsers().size();
        usersPage.addUser();

        Thread.sleep(2000);
        usersPage.transferToTable();
        Thread.sleep(2000);
        int numberUser = usersPage.getUsers().size();
        System.out.println(numberUser);
        assertEquals(numberUser, oldSize + 1);
//        assertAll(
//
//                () -> assertEquals(usersPage.getUsers().get(numberUser).getEmail(numberUser), "a@a"),
//                () -> assertEquals(usersPage.getUsers().get(numberUser).getName(numberUser), "KuPuK"),
//                () -> assertEquals(usersPage.getUsers().get(numberUser).getGender(numberUser), "Мужской"),
//                () -> assertEquals(usersPage.getUsers().get(numberUser).getChoose1(numberUser), "1.2"),
//                () -> assertEquals(usersPage.getUsers().get(numberUser).getChoose2(numberUser), "2.1, 2.2")
//
//
//
//        );
    }


    @Test
    public void transferToMorePage() throws InterruptedException {
        AuthPage authPage = new AuthPage(driver);
        HomePage homePage = authPage.authorization("test@protei.ru", "test");
        Thread.sleep(2000);
        MorePage morePage = homePage.transferToMorePage();
        Thread.sleep(2000);
        assertEquals(morePage.getUrl(), "http://185.67.95.60/more");
        assertEquals(morePage.getTitle(), "НТЦ ПРОТЕЙ");

    }


}
