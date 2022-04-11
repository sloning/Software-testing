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
import kotlin.test.assertTrue


class LoginTest {
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
    }

    @AfterEach
    fun teardown() {
        driver.quit()
    }

    @Test
    fun logInToAccoutWithEmailAndPassword() {
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
        val profileLink =
            driver.findElement(By.xpath("//a[@data-gps-track='profile_summary.click()']"))

        assertTrue(profileLink != null)
    }
}
