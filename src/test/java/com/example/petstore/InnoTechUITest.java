package com.example.petstore;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertTrue;

public class InnoTechUITest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeSuite
    static void setup() {
        driver = new SafariDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    @AfterSuite
    static void tearDown() {
        driver.quit();
    }

    @Test
    public void findGismeteo() {
        driver.navigate().to("http://ya.ru");
        driver.manage().window().maximize();

        final By searchBar = By.id("text");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        driver.findElement(searchBar).sendKeys("gismeteo");

        final By searchButton = By.cssSelector("button[class='search3__button mini-suggest__button']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        driver.findElement(searchButton).click();

        final By firstSearchResult = By.xpath("//*[@id=\"search-result\"]/li[1]/div/div[1]/a");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstSearchResult));
        driver.findElement(firstSearchResult).click();

        String windowHandle = driver.getWindowHandle();
        driver.switchTo().window(driver.getWindowHandles().stream()
                .filter(handle -> !windowHandle.equals(handle))
                .findFirst().get());

        final String city = "Нижний Новгород";
        final By gismeteoSearchBar = By.cssSelector("input[class='input js-input']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(gismeteoSearchBar));
        driver.findElement(gismeteoSearchBar).sendKeys(city);

        final By gismeteoFirstSearchResult = By.partialLinkText(city);
        wait.until(ExpectedConditions.visibilityOfElementLocated(gismeteoFirstSearchResult));
        WebElement element = driver.findElement(gismeteoFirstSearchResult);
        if (element.isDisplayed()) {
            element.sendKeys(Keys.RETURN);
        }

        final By result = By.className("page-title");
        wait.until(ExpectedConditions.visibilityOfElementLocated(result));
        String resultString = driver.findElement(result).getText();
        Pattern pattern = Pattern.compile("Погода\\s|&nbsp;в\\s|&nbsp;.*\\s|&nbsp;сегодня");
        Matcher matcher = pattern.matcher(resultString);
        assertTrue(matcher.find());
    }
}
