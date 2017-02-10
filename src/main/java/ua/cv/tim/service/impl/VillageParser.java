package ua.cv.tim.service.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.VillageService;

/**
 * Created by Serhii Starovoit on 1/24/2017 in 10:02 PM.
 */
@Service(value = "villageParser")
public class VillageParser {



    public Village pars(HtmlUnitDriver driver) {

        Village village = new Village();
        driver.navigate().to("http://ts1.travian.ru/dorf2.php");

        String villageNameString = driver.findElement(By.cssSelector("#villageNameField")).getText();
        village.setName(villageNameString);

        String xCordString = driver.findElement(By.cssSelector("span.coordinateX")).getText();
        xCordString = xCordString.replaceAll("[^0-9.-]", "");
        int xCordInt;
        xCordInt = Integer.parseInt(xCordString);
        village.setxCoord((short) xCordInt);

        String yCordString = driver.findElement(By.cssSelector("span.coordinateY")).getText();
        yCordString = yCordString.replaceAll("[^0-9.-]", "");
        int yCordInt;
        yCordInt = Integer.parseInt(yCordString);
        village.setyCoord((short) yCordInt);

        driver.findElementByClassName("wall").click();
        String valueWallSting = driver.findElement(By.cssSelector("span.level")).getText();
        valueWallSting = valueWallSting.replaceAll("[^0-9.]", "");
        int valueWallInteger = Integer.parseInt(valueWallSting);
        village.setWall((byte) valueWallInteger);

        driver.findElement(By.cssSelector("div.playerName > a:nth-of-type(2)")).click();
        String populationSting = driver.findElement(By.cssSelector("td.inhabitants")).getText();
        populationSting = populationSting.replaceAll("[^0-9.]", "");
        int populationInt = Integer.parseInt(populationSting);
        village.setPopulation((short) populationInt);
        village.setIsCapital(true);
        return village;
    }

}