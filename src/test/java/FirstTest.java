import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;


public class FirstTest {

    String URLtoTest = "https://web2.0rechner.de/";
    FirefoxDriver driver;

    @BeforeEach
    public void openPage(){
        //GeckoDriver ist ein Treiber der für die Verwendung von Firefox genutzt wird
        System.setProperty("webdriver.gecko.driver",".\\libs\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(URLtoTest);

        //Cookie Fenster wegklicken
        //WebElement button = driver.findElement(By.xpath("//*[@id=\"cookieconsentallowall\"]"));
        WebElement button1 = driver.findElement(By.id("cookieconsentallowall"));
        button1.click();
    }

    @Test
    public void push_button(){
        //WebElement button1 = driver.findElement(By.xpath("//*[@id=\"Btn1\"]"));
        WebElement button1 = driver.findElement(By.id("Btn1"));
        button1.click();
    }

    @Test
    public void write_something(){
        WebElement numberField = driver.findElement(By.id("input"));
        numberField.sendKeys("255");
    }

    @Test
    public void calculate_addition() throws IOException {
        //Vorbereitung
        String result = "";

        //Button suchen
        WebElement button1 = driver.findElementById("Btn1");
        WebElement button5 = driver.findElementById("Btn5");
        WebElement buttonPlus = driver.findElementById("BtnPlus");
        WebElement buttonCalc = driver.findElementById("BtnCalc");

        //Button betätigen
        button1.click();
        button1.click();
        buttonPlus.click();
        button5.click();
        button5.click();
        button5.click();
        buttonCalc.click();

        //Ergebnis wird als Bild angezeigt. Von daher wird das Bild rausgesucht
        WebElement resultField = driver.findElementByXPath("//*[@id=\"result\"]");
        String resultSRC = resultField.getAttribute("src");
        resultSRC = resultSRC.substring(resultSRC.indexOf(",")+1);

        // Die Source decoden um daraus ein PNG zu machen
        byte[] decodedBytes = Base64.getDecoder().decode(resultSRC);
        BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(decodedBytes));
        File imgOutFile = new File("E:\\_Engineering\\git_projects\\FirstSelenium\\images\\download_result.png");
        ImageIO.write(bufImg, "png", imgOutFile);

        //PNG wird ausgelesen mit Tess4J
        File imageFile = new File("E:\\_Engineering\\git_projects\\FirstSelenium\\images\\download_result.png");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setDatapath("E:\\_Engineering\\git_projects\\FirstSelenium\\tessdata"); // path to tessdata directory
        try {
            result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }

        //Eregbnis verglichen
        result = result.substring(result.indexOf("=")+1);
        result.replaceAll("\\s","");
        System.out.println(result);
        Assert.assertTrue(result.contains("566"));
    }



    @AfterEach
    public void closePage(){
        driver.close();
    }



}
