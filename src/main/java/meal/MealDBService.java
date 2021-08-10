package meal;

import database.DBHandler;
import database.Queries;
import pet.PetType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MealDBService {
    DBHandler dbHandler = new DBHandler();

    public ArrayList<Meal> findMealsByPetType(PetType petType) throws SQLException {
        ArrayList<Meal> meals = new ArrayList<>();
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SELECT_MEALS_BY_FIELD_NAME);
        preparedStatement.setInt(1, petType.getId());
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {
            Meal meal = new Meal(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getDouble("weight"),
                    new PetType(result.getInt("pet_type_id"), result.getString("pet_type_value"))
            );
            meals.add(meal);
        }
        result.close();
        preparedStatement.close();
        return meals;
    }
}
