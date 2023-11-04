package software.ulpgc.kata4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.System.load;

public class SqliteCustomerLoader implements CustomerLoader {

    public final Connection connection;
    @Override
    public List<Customer> loadAll() {
        try {
            return load(resultSetOf(queryAllSql));
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    private List<Customer> load(ResultSet resultSet) throws SQLException {
        List<Customer> list = new ArrayList<>();
        while (resultSet.next()) list.add(customerFrom(resultSet));
        return list;
    }

    private ResultSet resultSetOf(String sql) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }

    public SqliteCustomerLoader(Connection connection) {
        this.connection=connection;
    }

    private final static String queryAllSql = "SELECT " +
            "customers.CustomerId AS customer_id, " +
            "customers.FirstName AS first_name, " +
            "customers.LastName AS last_name, " +
            "customers.Email AS email, " +
            "customers.Phone AS phone, " +
            "customers.Address AS address, " +
            "customers.City AS city, " +
            "customers.State AS state, " +
            "customers.Country AS country, " +
            "customers.PostalCode AS postal_code " +
            "FROM customers";


    private Customer customerFrom(ResultSet resultSet) throws SQLException {
        return new Customer(
                resultSet.getInt("customer_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("address"),
                resultSet.getString("city"),
                resultSet.getString("state"),
                resultSet.getString("country"),
                resultSet.getString("postal_code")
        );
    }
}

