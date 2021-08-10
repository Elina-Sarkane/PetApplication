package database;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.ConfigurationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {
    PropertiesConfiguration databaseProperties = new PropertiesConfiguration();

    private static Connection connection;

    public DBHandler(){
        try {
            databaseProperties.load("database.properties");
        }catch (ConfigurationException ex){
            ex.printStackTrace();
        }

        String host = databaseProperties.getString("database.host");
        String port = databaseProperties.getString("database.port");
        String user =databaseProperties.getString("database.user");
        String password = databaseProperties.getString("database.password");
        String dbName = databaseProperties.getString("database.name");
        String connectionUrl = host + ":"+ port +"/"+ dbName + "?serverTimezone=GMT%2B3";

        try {
            connection = DriverManager.getConnection(connectionUrl, user, password);
        }catch (SQLException e){
            System.out.println("Unable to connect to PetManager database");
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
