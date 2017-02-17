package ua.cv.tim.service.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.cv.tim.model.*;
import ua.cv.tim.service.ArmyService;
import ua.cv.tim.service.VillageService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Serhii Starovoit on 2/6/2017 in 11:09PM.
 */
@Service(value = "armyParser")
public class ArmyParser {

    @Autowired
    ArmyService armyService;
    @Autowired
    VillageService villageService;

    public List<Army> pars(HtmlUnitDriver driver, Village village) {
        List<Army> armies = armyInit(village);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("http://ts1.travian.ru/build.php?gid=16&tt=1&filter=3");
        String unitT = driver.findElement(By.cssSelector("#unitRowAtTown > td.uniticon.t1 > img")).getAttribute("class");

        int n = 0;
        if (unitT == "unit u11") {
            n = 9;
        }
        if (unitT == "unit u1") {
            n = 18;
        }
        List<Integer> parslist = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            parslist.add(Integer.parseInt(
                    driver.findElement(By.cssSelector("tbody.units.last > tr > td:nth-of-type(" + i + ")")).getText()));
        }
        for (int i = 0; i < 9; i++) {
            armies.get(i + n).setCount(parslist.get(i));
        }
        return armies;
    }

    public List<Army> armyInit(Village village) {
        List<Army> armies = new ArrayList<>();
        for (UnitType type : UnitType.values()) {
            Army army = new Army();
            army.setType(UnitType.valueOf(type.getName()));
            army.setCount(0);
            army.setOwnUnit(true);
            army.setOwningVillage(village);
            army.setArmyRequestVillage(village);
            armies.add(army);
        }
        return armies;
    }
}




