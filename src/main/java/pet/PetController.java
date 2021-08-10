package pet;

import meal.Meal;
import meal.MealDBService;
import owner.Owner;
import toy.Toy;
import toy.ToyDBService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PetController {
    PetDBService petDBService = new PetDBService();
    MealDBService mealDBService = new MealDBService();
    ToyDBService toyDBService = new ToyDBService();

    private String petListTitle =  "id \t name \t age \t type \t owner\n";
    private String petTypesListTitle = "id \t type\n";

    public void showAllPets(){
        ArrayList<Pet> pets = new ArrayList<>();
        try {
            pets = petDBService.getAllPets();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String message = petListTitle +
                pets.stream()
                .map(Pet::toString)
                .collect(Collectors.joining("\n"));
        JOptionPane.showMessageDialog(null, message);
    }

    public void showSinglePet() {
        String petId = JOptionPane.showInputDialog(null, "Enter pet ID:");
        Pet pet = null;
        ArrayList<Meal> meals = new ArrayList<>();
        ArrayList<Toy> toys = new ArrayList<>();

        try {
            pet = petDBService.viewSinglePet(Integer.parseInt(petId));
            toys = toyDBService.findToysByPetType(pet.getType());
            meals = mealDBService.findMealsByPetType(pet.getType());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String message = createPetProfileUI(pet, meals, toys);
        JOptionPane.showMessageDialog(null, message);
    }

    private String createPetProfileUI(Pet pet, ArrayList<Meal> meals, ArrayList<Toy> toys) {
       String petInfo = String.format("\n%s's Profile" + "\nSpecial Id: %o" + "\nAge: %o" + "\nType: %s", pet.getName(), pet.getId(), pet.getAge(), pet.getType().getValue());
       String ownerInfo = String.format("\n\nOwner Information" + "\nName: %s", pet.getOwner().getName());

       //Vēlviens variants ar Stringbuilder
       //%s = string
        //%o = integer
        /*StringBuilder message = new StringBuilder(String.format("\n%s's Profile"
                        + "\nSpecial Id: %o"
                        + "\nAge: %o"
                        + "\nType: %s"
                        + "\n\nOwner Information"
                        + "\nname: %s", pet.getName(), pet.getId(), pet.getAge(), pet.getType().getValue(),
                pet.getOwner().getName()));*/
        StringBuilder mealInfo = new StringBuilder("\n\nFood Information: \n"
        + "Id \t Name \t Weight\n");
        for (Meal meal: meals){
           mealInfo.append(" \t " + meal.getId() + " \t ")
                   .append(meal.getName() + " \t ")
                   .append(meal.getWeight() + " \n");
        }

        StringBuilder toyInfo = new StringBuilder("\n\nToy Information: \n"
        +  "Id \t Material \t Price\n");
        for (Toy toy: toys){
            toyInfo.append(" \t " + toy.getId() + " \t ")
                    .append(toy.getMaterial() + " \t ")
                    .append(toy.getPrice() + " \n");
        }

        return petInfo + ownerInfo + mealInfo + toyInfo;
    }

    public void removePetFromOwner(){
        try {
            int petId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter pet ID:"));
            petDBService.removeOwnerFromPet(petId);
            JOptionPane.showMessageDialog(null, "Sad to see you go!");
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while removing pet");
        }

    }
    public void assignPetToOwner(){
        try {
            int petId = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter pet Id"));
            int ownerId = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Owner Id"));
            petDBService.addOwnerToPet(ownerId, petId);
            JOptionPane.showMessageDialog(null, "Pet adoption complete!");
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error occurred while removing pet");
        }
    }

    public void addPet() {
        try {
            String name = JOptionPane.showInputDialog(null, "Enter Pet Name:");
            int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Pet's age"));
            int petType = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Pet's Type ID form the list", viewAllPetTypes()));
            petDBService.addPet(name, age, petType);
            JOptionPane.showMessageDialog(null,"New pet added successfully!");
        }catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while adding pet");
        }
    }

    public void createPetType(){
        try {
            PetType petType = collectPetTypeInfo();
            petDBService.addPetType(petType);
            JOptionPane.showMessageDialog(null,petType.getValue() + " Added successfully");
        }catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while adding pet type");
        }
    }

    public void removePetType() {
        try {
            int petTypeId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Pet Type Id"));
            petDBService.removePetType(petTypeId);
            JOptionPane.showMessageDialog(null, " Pet Type deleted successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error occurred while deleting Pet Type");
        }

    }

    public Object viewAllPetTypes() {
        ArrayList<PetType> petTypes = new ArrayList<>();
        try {
            petTypes = petDBService.getAllPetTypes();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        String message = petTypesListTitle +
                petTypes.stream()
                        .map(PetType::toString)
                        .collect(Collectors.joining("\n"));
        JOptionPane.showMessageDialog(null, message);
        return null;
    }

    private PetType collectPetTypeInfo() {
        String value = JOptionPane.showInputDialog(null, "Enter Pet Type:");
        return new PetType(value);
    }
}
