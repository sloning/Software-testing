package lab.softwaretesting

import io.github.bonigarcia.wdm.WebDriverManager
import lab.softwaretesting.util.PropertiesManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import kotlin.test.assertTrue

class UnauthorizedTests {
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
    fun searchQueryTest() {
        val searchInput = driver.findElement(By.xpath("//form[@id='search']//input[@name='q']"))

        searchInput.sendKeys(PropertiesManager.getProperty("searchQuery"))
        searchInput.sendKeys(Keys.ENTER)
        val urlSuccessMatch = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(
            ExpectedConditions.or(
                ExpectedConditions.urlContains("https://stackoverflow.com/search"),
                ExpectedConditions.urlContains("https://stackoverflow.com/questions/tagged")
            )
        )

        assertTrue(urlSuccessMatch)
    }

    @Test
    fun openQuestionPageTest() {
        val menu = driver.findElement(By.xpath("//header/div/div[1]"))
        driver.findElement(By.xpath("//header/div/a[1]")).click()
        val isMenuOpened = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(ExpectedConditions.attributeContains(menu, "style", "display: block"))
        driver.findElement(By.xpath("//a[@id='nav-questions']")).click()
        driver.findElement(By.xpath("//div[@id='questions']/div[1]//h3[@class='s-post-summary--content-title']/a"))
            .click()
        val urlSuccessMatch = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(ExpectedConditions.urlMatches("https:\\/\\/stackoverflow.com\\/questions\\/\\d+\\/.*"))

        assertTrue(urlSuccessMatch)
    }

    @Test
    fun changeQuestionsTabTest() {
        val menu = driver.findElement(By.xpath("//header/div/div[1]"))
        driver.findElement(By.xpath("//header/div/a[1]")).click()
        val isMenuOpened = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(ExpectedConditions.attributeContains(menu, "style", "display: block"))
        driver.findElement(By.xpath("//a[@id='nav-questions']")).click()

        val newestLink = driver.findElement(By.xpath("//a[@data-nav-value='Newest']"))
        newestLink.click()
        val urlNewestSuccessMatch = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(ExpectedConditions.urlToBe("https://stackoverflow.com/questions?tab=Newest"))

        val activeLink = driver.findElement(By.xpath("//a[@data-nav-value='Active']"))
        activeLink.click()
        val urlActiveSuccessMatch = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(ExpectedConditions.urlToBe("https://stackoverflow.com/questions?tab=Active"))

        assertTrue(urlNewestSuccessMatch && urlActiveSuccessMatch)
    }

    @Test
    fun filterQuestionsTest() {
        val menu = driver.findElement(By.xpath("//header/div/div[1]"))
        driver.findElement(By.xpath("//header/div/a[1]")).click()
        val isMenuOpened = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(ExpectedConditions.attributeContains(menu, "style", "display: block"))
        driver.findElement(By.xpath("//a[@id='nav-questions']")).click()

        val filterMenu = driver.findElement(By.xpath("//form[@id='uql-form']"))
        val tagInput = driver.findElement(By.xpath("//form[@id='uql-form']/div/div/div[1]/div/div[3]/div/div/input"))
        driver.findElement(By.xpath("//button[@data-se-uql-target='toggleFormButton']")).click()
        val isFilterMenuOpened = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(ExpectedConditions.attributeContains(filterMenu, "class", "is-expanded"))
        tagInput.sendKeys(PropertiesManager.getProperty("searchQuery"))
        tagInput.sendKeys(Keys.ENTER)
        val urlSuccessMatch = WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(
            ExpectedConditions.urlMatches(
                "https:\\/\\/stackoverflow.com\\/questions\\/tagged\\/${
                    PropertiesManager.getProperty(
                        "searchQuery"
                    )
                }.*"
            )
        )

        assertTrue(urlSuccessMatch)
    }
}
