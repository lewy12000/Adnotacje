package beans;

import enums.CarType;

public class Car {
    String name;
    String model;
    Enum cartype;

    public Car() {
        name = "Nissan";
        model = "R34";
        cartype = CarType.OSOBOWY;
    }

    public Car(String name, String model, Enum cartype) {
        this.name = name;
        this.model = model;
        this.cartype = cartype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Enum getCartype() {
        return cartype;
    }

    public void setCartype(Enum cartype) {
        this.cartype = cartype;
    }
}
