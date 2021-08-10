package toy;

import database.DBHandler;
import database.Queries;
import pet.PetType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToyDBService {
    DBHandler dbHandler = new DBHandler();
    public ArrayList<Toy> findToysByPetType(PetType petType) throws SQLException {
        ArrayList<Toy> toys = new ArrayList<>();
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SELECT_TOYS_BY_FIELD_NAME);
        preparedStatement.setInt(1, petType.getId());
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {
            Toy toy = new Toy(
                    result.getInt("id"),
                    result.getString("material"),
                    new PetType(result.getInt("pet_type_id"), result.getString("pet_type_value")),
                    result.getDouble("price")
                    );
            toys.add(toy);
        }
        result.close();
        preparedStatement.close();
        return toys;
    }
}

