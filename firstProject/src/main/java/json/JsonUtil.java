package json;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 16.11.2016.
 */
public class JsonUtil {

    public static void main(String[] args) {
        JsonUtil testSer = new JsonUtil();

        testSer.writeJson();
        testSer.readJson();


    }

    public boolean writeJson () {

        CarFleet carFleet = new CarFleet();

        List<Car> cars = new ArrayList<>();

        Car car1 = new Car();
        car1.setName("car1Name");
        car1.setCost(111111);
        CarEngine engine = new CarEngine();
        engine.setPower("Power");
        engine.setType("Type");
        car1.setEngine(engine);
        car1.setModel("Car1 Model");

        Car car2 = new Car();
        car2.setName("car1Name");
        car2.setCost(222222);
        CarEngine engine2 = new CarEngine();
        engine2.setPower("Power2");
        engine2.setType("Type2");
        car2.setEngine(engine);
        car2.setModel("Car1 Model");

        cars.add(car1);
        cars.add(car2);

        carFleet.setCars(cars);
        carFleet.setName("Test name");


        ObjectMapper mapper = new ObjectMapper();

            try {

                try {
                    mapper.writeValue(new File("result.json"), carFleet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
             catch (Exception  e) {
                e.printStackTrace();
            }


      return true;
    }

    public boolean readJson(){


        CarFleet value = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            value = mapper.readValue(new File("result.json"), CarFleet.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CarFleet carFleet = new CarFleet();
        carFleet.setCars(value.getCars());
        carFleet.setName(value.getName());

        System.out.println(carFleet.getName());
        System.out.println(carFleet.getCars().get(1).getCost());


        return true;
    }


}
