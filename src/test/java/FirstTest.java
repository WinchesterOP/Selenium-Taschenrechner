import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class FirstTest {

    @Test
    public void calling_calculator(){
        //GeckoDriver ist ein Treiber der für die Verwendung von Firefox genutzt wird
        System.setProperty("webdriver.gecko.driver",".\\libs\\geckodriver.exe");

        FirefoxDriver driver = new FirefoxDriver();

        driver.get("https://web2.0rechner.de/");
        driver.close();
    }

    @Test
    public void push_button(){
        System.setProperty("webdriver.gecko.driver",".\\libs\\geckodriver.exe");

        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://web2.0rechner.de/");

        //WebElement button = driver.findElement(By.xpath("//*[@id=\"cookieconsentallowall\"]"));
        WebElement button1 = driver.findElement(By.id("cookieconsentallowall"));
        button1.click();

        //WebElement button2 = driver.findElement(By.xpath("//*[@id=\"Btn1\"]"));
        WebElement button2 = driver.findElement(By.id("Btn1"));
        button2.click();

        driver.close();
    }

    @Test
    public void write_something(){

    }


}
