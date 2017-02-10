package ua.cv.tim.parser;

import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.configuration.WebSocketConfiguration;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.VillageService;
import ua.cv.tim.service.impl.VillageParser;

/**
 * Created by Serhii Starovoit on 1/25/2017 in 10:52 PM.
 */

@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class, WebSocketConfiguration.class})
public class VillageParserTest  extends AbstractTestNGSpringContextTests {

    @Autowired
    VillageParser villageParser ;

    @Autowired
    VillageService villageService;

    @Test
    public void testPars() throws Exception {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);

        driver.get("http://ts1.travian.ru/login.php");
        driver.findElements(By.name("name")).clear();
        driver.findElements(By.name("name")).get(0).sendKeys("Star");
        driver.findElements(By.name("password")).clear();
        driver.findElements(By.name("password")).get(0).sendKeys("321654aaa");
        driver.findElement(By.cssSelector("div.button-content")).click();

        Village village = villageParser.pars(driver);
        System.out.println(village.getName());
        System.out.println(village.getWall());
        System.out.println(village.getPopulation());
        System.out.println(village.getxCoord());
        System.out.println(village.getyCoord());
        System.out.println(village.getIsCapital());
        driver.quit();
    }
}