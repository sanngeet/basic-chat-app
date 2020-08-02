package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import com.udacity.jwdnd.c1.review.spo.ChatPage;
import com.udacity.jwdnd.c1.review.spo.LoginPage;
import com.udacity.jwdnd.c1.review.spo.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewApplicationTests {

    private static WebDriver driver;
    private final String username;
    private final String password;
    private final String message;
    @LocalServerPort
    private Integer port;
    private SignUpPage signUpPage;
    private LoginPage loginPage;
    private ChatPage chatPage;

    ReviewApplicationTests() {
        this.username = "sangeet";
        this.password = "12345678";
        this.message = "Yo! people";
    }

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/signup");
        signUpPage = new SignUpPage(driver);
    }

    @Order(1)
    @Test
    public void automatedUserFlow() throws InterruptedException {
        Thread.sleep(2000);
        signUpPage.fillForm("sangeet", "sharma", username, password);
        Thread.sleep(2000);
        signUpPage.submitForm();
        Thread.sleep(2000);
        driver.get("http://localhost:" + port + "/login");
        Thread.sleep(2000);
        loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Thread.sleep(2000);
        chatPage = new ChatPage(driver);
        chatPage.sendMessage(message);
        Thread.sleep(2000);
        ChatMessage sentMessage = chatPage.getFirstMessage();
        assertEquals(username, sentMessage.getUsername());
        assertEquals(message, sentMessage.getMessage());
        Thread.sleep(2000);
        chatPage.logOut();
        Thread.sleep(2000);
    }

    @Order(2)
    @Test
    public void automatedUserFlow2() throws InterruptedException {
        String username = "Asma";
        String password = "12345678";
        String message = "Hi, sangeet";
        Thread.sleep(2000);
        signUpPage.fillForm("Asma", "khan", username, password);
        Thread.sleep(2000);
        signUpPage.submitForm();
        Thread.sleep(2000);
        driver.get("http://localhost:" + port + "/login");
        Thread.sleep(2000);

        loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Thread.sleep(2000);
        chatPage = new ChatPage(driver);

        ChatMessage sentMessage = chatPage.getFirstMessage();
        Thread.sleep(2000);
        assertEquals(this.username, sentMessage.getUsername());
        assertEquals(this.message, sentMessage.getMessage());
        Thread.sleep(2000);
        chatPage.logOut();
        Thread.sleep(2000);
    }

    @Disabled
    @Test
    void googleTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("iphone 10");
        Thread.sleep(2000);
        element.submit();
        driver.quit();
    }
}