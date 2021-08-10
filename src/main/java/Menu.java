import owner.OwnerController;
import pet.PetController;

import javax.swing.*;

public class Menu {
    PetController petController = new PetController();
    OwnerController ownerController = new OwnerController();

    public void start() {
        String[] menu = {"View All Pets", "Add Pet", "View Pet Profile", "Remove Pet from Owner",
                "Assign Pet to Owner", "View All Owners", "View Owner Profile", "Add Owner",
                "Remove Owner", "Add Pet Type", "Remove Pet Type", "View All Pet Types", "Exit"};
        ImageIcon pet = new ImageIcon("pet.jpg");
        String chooseOption = (String) JOptionPane.showInputDialog(
                null,
                "Choose option:",
                "Welcome to the Pet Application!",
                JOptionPane.INFORMATION_MESSAGE,
                pet,
                menu,
                menu[0]
        );

        if (chooseOption == menu[0]) {
            petController.showAllPets();
        } else if (chooseOption == menu[1]) {
            petController.addPet();
        } else if (chooseOption == menu[2]) {
            petController.showSinglePet();
        } else if (chooseOption == menu[3]) {
            petController.removePetFromOwner();
        } else if (chooseOption == menu[4]) {
            petController.assignPetToOwner();
        } else if (chooseOption == menu[5]) {
            ownerController.showAllOwners();
        } else if (chooseOption == menu[6]) {
            ownerController.viewOwner();
        } else if (chooseOption == menu[7]) {
            ownerController.createOwner();
        } else if (chooseOption == menu[8]) {
            ownerController.removeOwner();
        } else if (chooseOption == menu[9]) {
            petController.createPetType();
        } else if (chooseOption == menu[10]) {
            petController.removePetType();
        } else if (chooseOption == menu[11]) {
            petController.viewAllPetTypes();
        } else {
            System.exit(0);
        }
        start();
    }
    /*public void start(){
        String userChoice = JOptionPane.showInputDialog(null,
                "Welcome to Pet Manager\n"
        + "\n1. View All Pets"
        + "\n2. Add Pet"
        + "\n3. View Pet Profile"
        + "\n4. Remove Pet from Owner"
        + "\n5. Assign Pet to Owner"
        + "\n6. View All Owners"
        + "\n7. View Owner Profile"
        + "\n8. Add Owner"
        + "\n9. Remove Owner"
        + "\n10. Add Pet Type"
        + "\n11. Remove Pet Type"
        + "\n12. View All Pet Types"
        + "\n13. Exit");

        switch (userChoice){
            case "1":
                petController.showAllPets();
                break;
            case "2":
                break;
            case "3":
                petController.showSinglePet();
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
                break;
            case "8":
                ownerController.createOwner();
                break;
            case "9":
                break;
            case "10":
                break;
            case "11":
                break;
            case "12":
                break;
            case "13":
                System.exit(0);
                break;
            default:
                break;
        }
        start();
    }*/
}
