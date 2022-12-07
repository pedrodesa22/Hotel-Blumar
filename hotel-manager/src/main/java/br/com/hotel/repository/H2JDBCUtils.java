package br.com.hotel.repository;

import br.com.hotel.model.Hotel;

import java.sql.*;

public class H2JDBCUtils {

    private static String jdbcURL = "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "";

    private static final String createTableHotelSQL = "create table hotel (\r\n" + "  hotel_id  int(3) primary key,\r\n" +
            "  name varchar(20),\r\n" +
            "  address varchar(100),\r\n" +
            "  hotel_manager varchar(1000),\r\n" +
            "  available_rooms integer,\r\n" +
            "  phone varchar(20)\r\n" + "  );";

    private static final String INSERT_HOTEL_SQL = "INSERT INTO hotel" +
            "  (hotel_id, name, address, hotel_manager, available_rooms, phone) VALUES " +
            " (?, ?, ?, ?, ?, ?);";

    private static final String QUERY_HOTEL = "select * from hotel where hotel_id =1";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            System.out.println("-- Não foi possível conectar no H2 ! ---");
            e.printStackTrace();
        }
        return connection;
    }

    public static void createHotelTable() throws SQLException {

        //System.out.println(createTableHotelSQL);
        // Step 1: Establishing a Connection
        try {
            Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();

            // Step 3: Execute the query or update query
            statement.execute(createTableHotelSQL);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HOTEL_SQL);

            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Hotel Blumar");
            preparedStatement.setString(3, "Rua Siqueira Campos 43");
            preparedStatement.setString(4, "Pedro");
            preparedStatement.setString(5, "5");
            preparedStatement.setString(6, "+552121469874");

            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(QUERY_HOTEL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("hotel_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String hotel_manager = rs.getString("hotel_manager");
                String available_rooms = rs.getString("available_rooms");
                String phone = rs.getString("phone");
                //System.out.println(id + "," + name + "," + address + "," + hotel_manager + "," + hotel_manager + "," + phone);
            }

        } catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
    }

    public static String showAvailableRoomsInHotel() throws SQLException {
        String QUERY_HOTEL_ROOM = "select available_rooms from hotel where hotel_id =1";
        try {
            Connection connection = H2JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_HOTEL_ROOM);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String available_rooms = rs.getString("available_rooms");
                return available_rooms;
            }
        } catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
        return "No rooms available";
    }

    public static Hotel getHotelData() throws SQLException {
        String QUERY_HOTEL = "select * from hotel where hotel_id =1";
        try {
            Connection connection = H2JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_HOTEL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("hotel_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String hotel_manager = rs.getString("hotel_manager");
                String available_rooms = rs.getString("available_rooms");
                String phone = rs.getString("phone");
                Hotel hotel = new Hotel(
                        id,
                        name,
                        address,
                        hotel_manager,
                        phone,
                        Integer.getInteger(available_rooms)
                );
                return hotel;
            }
        } catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
        return null;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
