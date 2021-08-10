package toy;

import pet.PetType;

public class Toy {
    private int id;
    private String material;
    private PetType pet_type;
    private double price;

    public Toy(int id, String material, PetType pet_type, double price) {
        this.id = id;
        this.material = material;
        this.pet_type = pet_type;
        this.price = price;
    }
    public Toy(){}

    public int getId() {
        return id;
    }

    public String getMaterial() {
        return material;
    }

    public PetType getType() {
        return pet_type;
    }

    public double getPrice() {
        return price;
    }

}
