package collectionAndSet;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Set extends Base {

    By setWindow = By.id("tns1-ow");
    By setHeader = By.xpath("//h2[text()='Украшения из образа']");
    By firstItemFromSet = By.xpath("//li[@id='tns1-item0']//a");

    public Set(WebDriver driver) {
        super(driver);
    }

    public void getSetWindow() {
        driver.findElement(setWindow).isDisplayed();
    }

    public String getSetHeader() {
        return driver.findElement(setHeader).getText();
    }

    public void clickOnFirstItemFromSet() {
        driver.findElement(firstItemFromSet).click();
    }

    public String getHrefFirstItemFromSet() {
        return driver.findElement(firstItemFromSet).getAttribute("href");
    }

}
