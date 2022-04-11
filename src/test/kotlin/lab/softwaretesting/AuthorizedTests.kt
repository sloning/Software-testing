package lab.softwaretesting

import io.github.bonigarcia.wdm.WebDriverManager
import lab.softwaretesting.util.PropertiesManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class AuthorizedTests {
    private lateinit var driver: WebDriver

    companion object {
        @JvmStatic
        @BeforeAll
        private fun setupDrivers() {
            WebDriverManager.chromedriver().setup()
        }
    }

    @BeforeEach
    fun setup() {
        driver = ChromeDriver()
        driver.manage().window().maximize()
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
        driver.get("https://stackoverflow.com/")

        driver.findElement(By.xpath("//li/a[contains(@href, 'https://stackoverflow.com/users/login')]")).click()

        val emailInput = driver.findElement(By.xpath("//input[@id='email']"))
        val passwordInput = driver.findElement(By.xpath("//input[@id='password']"))
        val submitButton = driver.findElement(By.xpath("//button[@name='submit-button']"))

        emailInput.sendKeys(PropertiesManager.getProperty("email"))
        passwordInput.sendKeys(PropertiesManager.getProperty("password"))
        submitButton.click()
        val urlSuccessMatch = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(ExpectedConditions.urlToBe("https://stackoverflow.com/"))
    }

    @AfterEach
    fun teardown() {
        driver.quit()
    }

    @Test
    fun changeThemeTest() {
        driver.findElement(By.xpath("//a[@data-gps-track='profile_summary.click()']")).click()
        driver.findElement(By.xpath("//div[@id='mainbar-full']/div[2]/div[1]/a[3]")).click()

        val body = driver.findElement(By.xpath("//body"))

        driver.findElement(By.xpath("//input[@id='enableForcedLight']")).click()
        val lightThemeBodyClassMatch = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(ExpectedConditions.attributeToBe(body, "class", "user-page unified-theme"))
        assertTrue(lightThemeBodyClassMatch)

        driver.findElement(By.xpath("//input[@id='enableForcedSystemDefault']")).click()
        val systemThemeBodyClassMatch = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(ExpectedConditions.attributeToBe(body, "class", "user-page unified-theme theme-system"))
        assertTrue(systemThemeBodyClassMatch)

        driver.findElement(By.xpath("//input[@id='enableForcedDark']")).click()
        val darkThemeBodyClassMatch = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(ExpectedConditions.attributeToBe(body, "class", "user-page unified-theme theme-dark"))
        assertTrue(darkThemeBodyClassMatch)
    }

    @Test
    fun addAnswerTest() {
        driver.findElement(By.xpath("//div[@id='question-mini-list']/div/div[1]//h3[@class='s-post-summary--content-title']/a"))
            .click()

        val answerArea = driver.findElement(By.xpath("//textarea[@id='wmd-input']"))
        answerArea.sendKeys(PropertiesManager.getProperty("answerText"))
        driver.findElement(By.xpath("//button[@id='submit-button']")).click()
        driver.findElement(By.xpath("//form[@id='theForm']//button[@type='submit']")).click()

        val postedAnswer =
            driver.findElement(By.xpath("//p[text()=\"${PropertiesManager.getProperty("answerText")}\"]"))

        assertNotNull(postedAnswer)
    }

    @Test
    fun upVoteQuestion() {
        driver.findElement(By.xpath("//div[@id='question-mini-list']/div/div[1]//h3[@class='s-post-summary--content-title']/a"))
            .click()

        driver.findElement(By.xpath("//div[@class='question']//div[contains(@class, 'js-voting-container')]/button[1]"))
            .click()
        val messageAppeared = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='js-notice-toast-message']")))

        assertNotNull(messageAppeared)
    }

    @Test
    fun watchTagTest() {
        driver.findElement(By.xpath("//a[@data-gps-track='profile_summary.click()']")).click()
        driver.findElement(By.xpath("//div[@id='mainbar-full']/div[2]/div[1]/a[3]")).click()
        driver.findElement(By.xpath("//div[@id='content']/div[3]/nav/ul/li[5]/a")).click()
        driver.findElement(By.xpath("//div[@id='watching-1']/header/div/div[2]/button[1]")).click()

        val tagInput = driver.findElement(By.xpath("//div[@id='watching-1']//input[@name='tags']"))
        tagInput.sendKeys(PropertiesManager.getProperty("tagName"))
        driver.findElement(By.xpath("//div[@id='watching-1']//button[contains(@class, 'js-submit')]")).click()

        val addedTag =
            driver.findElement(By.xpath("//table[@class='-settings']//a[text()=\"${PropertiesManager.getProperty("tagName")}\"]"))

        assertNotNull(addedTag)
    }
}
