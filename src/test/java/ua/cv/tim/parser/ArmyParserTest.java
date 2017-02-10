package ua.cv.tim.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.configuration.HibernateConfiguration;
<<<<<<< HEAD:src/test/java/ua/cv/tim/parser/ArmyParserTest.java
import ua.cv.tim.configuration.WebSocketConfiguration;
import ua.cv.tim.model.Army;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.VillageService;
import ua.cv.tim.service.impl.ArmyParser;

import java.util.List;
=======
import ua.cv.tim.service.impl.VillageParser;
>>>>>>> 51cab00598b63370587e1c73b136ecdffb967584:src/test/java/ua/cv/tim/parser/ParserImplTest.java

/**
 * Created by Serhii Starovoit on 2/6/2017 in 01:08PM.
 */

@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class, WebSocketConfiguration.class})
public class ArmyParserTest  extends AbstractTestNGSpringContextTests {

    @Autowired
    ArmyParser armyParser;

    @Autowired
    VillageService villageService;

<<<<<<< HEAD:src/test/java/ua/cv/tim/parser/ArmyParserTest.java
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
        Village village = villageService.getById("1");
        List<Army> armies = armyParser.pars(driver, village) ;
        System.out.println(armies);
    }
=======
//    @Test
//    public void testDoOperation() throws Exception {
//        HtmlUnitDriver driver = new HtmlUnitDriver();
//        driver.setJavascriptEnabled(true);
//
//        driver.get("http://ts1.travian.ru/login.php");
//        driver.findElements(By.name("name")).clear();
//        driver.findElements(By.name("name")).get(0).sendKeys("Star");
//        driver.findElements(By.name("password")).clear();
//        driver.findElements(By.name("password")).get(0).sendKeys("321654aaa");
//        driver.findElement(By.cssSelector("div.button-content")).click();
//
//        Village village = villageParser.pars(driver);
//        villageParser.sync(village, "1");
//        driver.quit();
//
//    }
>>>>>>> 51cab00598b63370587e1c73b136ecdffb967584:src/test/java/ua/cv/tim/parser/ParserImplTest.java

}
