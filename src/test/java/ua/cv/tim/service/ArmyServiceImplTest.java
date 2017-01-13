import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.model.Army;

@Autowired
    ArmyService armyService;


@Test
public void getArmyById() {
        Assert.assertNotNull(armyService.getArmyById("4046c02c-24e5-486b-8127-2848da877ba6"));
        armyService.getArmyById("4046c02c-24e5-486b-8127-2848da877ba6");

        }

@Test
public void getById() {
        Assert.assertNotNull(armyService.getArmyById("4046c02c-24e5-486b-8127-2848da877ba6"));
        }

@Test
public void add() throws Exception {
        Army army = new Army();
        army.setCount(233);
        army.setCount(23);
        armyService.add(army);
        }

@Test
public void update() throws Exception {
        Army army;
        army = armyService.getArmyById("fb82855c-13ad-4cfb-9ea9-e248207725bf");
        army.setCount(4567);
        armyService.update(army);

        }

@Test
public void delete() throws Exception {
        armyService.delete(armyService.getArmyById("fb82855c-13ad-4cfb-9ea9-e248207725bf"));

        }


