package json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 16.11.2016.
 */
public class CarFleet {

    private String name;

    private List<Car> cars = new ArrayList<Car>();


    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarFleet{" +
                "name='" + name + '\'' +
                ", cars=" + cars +
                '}';
    }
}

