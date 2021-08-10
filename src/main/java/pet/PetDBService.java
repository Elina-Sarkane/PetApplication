package pet;

import database.DBHandler;
import database.Queries;
import owner.Owner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetDBService {
    DBHandler dbHandler = new DBHandler();

    public void addPet(String name, int age, int petType) throws SQLException {
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.INSERT_PET);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, age);
        preparedStatement.setInt(3, petType);
        preparedStatement.execute();
        preparedStatement.close();
    }
    public ArrayList<Pet> getAllPets() throws SQLException {
        ArrayList<Pet> pets = new ArrayList<>();
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SELECT_PETS);
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()){
            Pet pet = new Pet(
                    result.getInt("id"),
                    new Owner(result.getInt("owner_id"), result.getString("owner_name")),
                    result.getInt("pet_age"),
                    new PetType(result.getInt("pet_type_id"), result.getString("pet_type_value")),
                    result.getString("pet_name")
            );
            pets.add(pet);
        }
        return pets;
    }

    public Pet viewSinglePet(int petId) throws SQLException {
        Pet pet = new Pet();
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SELECT_ONE_PET);
        preparedStatement.setInt(1, petId);
        ResultSet result = preparedStatement.executeQuery();

        if (result.next()) {
            pet = new Pet(
                    result.getInt("id"),
                    new Owner(result.getInt("owner_id"), result.getString("owner_name")),
                    result.getInt("pet_age"),
                    new PetType(result.getInt("pet_type_id"), result.getString("pet_type_value")),
                    result.getString("pet_name")
            );
        }
        return pet;
    }

    public ArrayList<Pet> findPetByOwnerId(int ownerId) throws SQLException{
        ArrayList<Pet> pets = new ArrayList<>();
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SELECT_PETS_BY_OWNER_ID);
        preparedStatement.setInt(1, ownerId);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()){
            Pet pet = new Pet(
              result.getInt("id"),
              null,
              result.getInt("pet_age"),
              new PetType(result.getInt("pet_type_id"), result.getString("pet_type_value")),
              result.getString("pet_name")
            );
            pets.add(pet);
        }
        return pets;
    }

    public void removeOwnerFromPet(int petId) throws SQLException{
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.REMOVE_PET_OWNER);
        preparedStatement.setInt(1, petId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void addOwnerToPet(int ownerId, int petId) throws SQLException{
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SET_PET_OWNER);
        preparedStatement.setInt(1, ownerId);
        preparedStatement.setInt(2, petId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ArrayList<PetType> getAllPetTypes() throws SQLException{
        ArrayList<PetType> petTypes = new ArrayList<>();
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SELECT_PET_TYPES);
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()){
            PetType petType = new PetType(
              result.getInt("id"),
              result.getString("type")
            );
            petTypes.add(petType);
        }
        return petTypes;

    }

    public void removePetType(int petTypeId) throws SQLException{
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.DELETE_PET_TYPE);
        preparedStatement.setInt(1, petTypeId);
        preparedStatement.executeQuery();
        preparedStatement.close();
    }

    public void addPetType(PetType petType) throws SQLException{
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.INSERT_PET_TYPE);
        preparedStatement.setString(1, petType.getValue());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
