package software.ulpgc.kata4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:chinook.db")) {
            CustomerLoader loader = new SqliteCustomerLoader(connection);
            List<Customer> customers = loader.loadAll();
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }
}
