package ua.cv.tim.service.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.model.Army;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.User;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.service.VillageService;

import java.util.List;

/**
 * Created by Serhii Starovoit on 1/25/2017 in 1:25 PM.
 */

@Service(value = "parser")
@Transactional
public class Parser {

    @Autowired
    VillageParser villageParser;

    @Autowired
    ArmyParser armyParser;

    @Autowired
    VillageService villageService;

    @Autowired
    UserService userService;

    @Autowired
    PlayerService playerService;

    public void doOperation(String login, String password) {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);

        driver.get("http://ts1.travian.ru/login.php");
        driver.findElements(By.name("name")).clear();
        driver.findElements(By.name("name")).get(0).sendKeys(login);
        driver.findElements(By.name("password")).clear();
        driver.findElements(By.name("password")).get(0).sendKeys(password);
        driver.findElement(By.cssSelector("div.button-content")).click();

        Village village = villageParser.pars(driver);
        village.setArmies(armyParser.pars(driver, village));
        sync(village);
        driver.quit();
    }

    private void sync(Village village) {
        Village updatingVillage = villageService.getByName(village.getName());
        if (updatingVillage == null) {
          UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          User userByUsername = userService.getUserByUsername(principal.getUsername());
            Village addVillage = village;
            Player player = userByUsername.getPlayer();
            addVillage.setPlayer(player);
            player.getVillages().add(addVillage);
            playerService.update(player);
        } else {
            updatingVillage.setName(village.getName());
            updatingVillage.setPopulation(village.getPopulation());
            updatingVillage.setWall(village.getWall());
            updatingVillage.setIsCapital(village.getIsCapital());
            updatingVillage.setxCoord(village.getxCoord());
            updatingVillage.setyCoord(village.getyCoord());

            List<Army> armies = updatingVillage.getArmies();
            for (int i = 0; i < village.getArmies().size(); i++) {
                armies.get(i).setCount(village.getArmies().get(i).getCount());
            }
            villageService.update(updatingVillage);
        }
    }
}