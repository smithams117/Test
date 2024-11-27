package test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homepage {

    @Test
    public void testfitpeo() throws InterruptedException {
       

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to FitPeo's website
            driver.get("https://www.fitpeo.com");
            driver.manage().window().maximize();
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 1000)"); 

            // Locate checkboxes the CPT codes
            WebElement cpt99091 = driver.findElement(By.xpath("//input[@value='99091']"));
            WebElement cpt99453 = driver.findElement(By.xpath("//input[@value='99453']"));
            WebElement cpt99454 = driver.findElement(By.xpath("//input[@value='99454']"));
            WebElement cpt99474 = driver.findElement(By.xpath("//input[@value='99474']"));

            // Click checkboxes to select them
            if (!cpt99091.isSelected()) cpt99091.click();
            if (!cpt99453.isSelected()) cpt99453.click();
            if (!cpt99454.isSelected()) cpt99454.click();
            if (!cpt99474.isSelected()) cpt99474.click();

            // Confirm the selection
            System.out.println("Selected CPT codes: 99091, 99453, 99454, 99474");

            // Locate the headtext
            WebElement reimbursementHeader = driver.findElement(By.id("reimbursementHeader"));
            String headerText = reimbursementHeader.getText();

            // Validation
            Assert.assertEquals(headerText, "$110,700", "Total recurring reimbursement value is incorrect!");

            // Locate the slider and text field elements
            WebElement textField = driver.findElement(By.id("sliderValue"));
            textField.click();
            textField.clear();
            textField.sendKeys("560");

           

            // validate the elements 
            WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
            String sliderValue = slider.getAttribute("value");

            // slider value matches the text field 
            Assert.assertEquals(sliderValue, "560", "Slider value did not update correctly!");

            System.out.println("Test passed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            driver.quit();
        }
    }
}