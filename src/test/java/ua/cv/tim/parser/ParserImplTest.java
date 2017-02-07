package ua.cv.tim.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.service.impl.VillageParser;

/**
 * Created by Serhii Starovoit on 1/26/2017 in 12:03 AM.
 */

@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class ParserImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    VillageParser villageParser ;

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

}