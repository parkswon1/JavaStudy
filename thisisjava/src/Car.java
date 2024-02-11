public class Car {
    String company =" 현대자동차";
    String model;
    String color;
    int maxSpeed;

    Car(){

    }

    Car(String model, String color){
        this(model,color,1000);
    }

    Car(String model, String color, int maxSpeed){
        model = model + model;
        this.model =model;
        this.color =color;
        this.maxSpeed = maxSpeed;
    }
}
