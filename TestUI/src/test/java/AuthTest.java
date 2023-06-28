
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.pages.AuthPage;
import org.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest {

    WebDriver driver;

    @BeforeEach
    public void setUp(){


        WebDriverManager wdm = WebDriverManager.chromedriver().browserInDocker()
                .enableVnc().enableRecording();
        wdm.setup();
        driver= wdm.create();
        driver.get("http://185.67.95.60/");
    }




    @Test
    public void test() throws InterruptedException {


        AuthPage authPage = new AuthPage(driver);
        HomePage homePage = authPage.authorization("test@protei.ru", "test");
        Thread.sleep(2000);
        assertEquals(homePage.getUrl(), "http://185.67.95.60/main");











    }
}
