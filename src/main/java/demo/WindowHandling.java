package demo;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
///

public class WindowHandling {
    ChromeDriver driver;

    public WindowHandling() {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        // Open URL https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");
        // Switch to 2nd frame Using Locator "ID" iframeResult
        driver.switchTo().frame("iframeResult");
        // Click on "Try it" Button Using Locator "XPath" //button[text()='Try it']
        WebElement tryItButton = driver.findElementByXPath("//button[text()='Try it']");
        tryItButton.click();
        // Switch to child window driver.switchTo().window(child_window)
        String parent=driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();
        Iterator<String> I1 = s.iterator();
        while (I1.hasNext()) {

            String child_window = I1.next();

            if (!parent.equals(child_window)) {
        
                driver.switchTo().window(child_window);
                // Get the URL driver.switchTo().window(child_window)
                System.out.println("Current Url is" + driver.switchTo().window(child_window).getCurrentUrl());
                // Get the Title driver.switchTo().window(child_window).getTitle()
                System.out.println("Title of the Url is" + driver.switchTo().window(child_window).getTitle());

                // Take the screenshot 
                File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                System.out.println("Screenshot "+screenshot);
                // Close window driver.close()
                driver.close();
            }
        }
        // Switch to parent window driver.switchTo().window(parent)
        driver.switchTo().window(parent);

    }

}
