package ua.cv.tim.service.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.cv.tim.model.Village;

/**
 * Created by Serhii Starovoit on 1/25/2017 in 1:25 PM.
 */

@Service(value = "parserImpl")
public class ParserImpl {

    @Autowired
    VillageParser villageParser;
    
    public void doOperation(String name, String password) {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);

        driver.get("http://ts1.travian.ru/login.php");
        driver.findElements(By.name("name")).clear();
        driver.findElements(By.name("name")).get(0).sendKeys(name);
        driver.findElements(By.name("password")).clear();
        driver.findElements(By.name("password")).get(0).sendKeys(password);
        driver.findElement(By.cssSelector("div.button-content")).click();

        Village village = villageParser.pars(driver);
        villageParser.sync(village, "1");
        driver.quit();
    }


}