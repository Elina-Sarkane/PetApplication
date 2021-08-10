package meal;

import pet.PetType;

public class Meal {
    private int id;
    private String name;
    private double weight;
    private PetType pet_type;

    public Meal(int id, String name, double weight, PetType pet_type) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.pet_type = pet_type;
    }

    public Meal(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public PetType getType() {
        return pet_type;
    }

}
