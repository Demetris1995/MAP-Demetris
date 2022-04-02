import java.sql.*;
import java.io.FileWriter;
import org.json.JSONArray;
import org.json.JSONObject;

public class Test {
    //Variables to be used for connection to the database server
    static final String DB_URL = "jdbc:mysql://localhost:3306/exercise";
    static final String USER = "user";
    static final String PASS = "password";
    static final String QUERY = "SELECT * FROM exercise";

    public static ResultSet RetrieveData() throws Exception {
        //Open a connection to the server
        Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Successfully Connected to the database!");

        // Create a statement
        Statement stmt = con.createStatement();

        //Retrieve the records
        ResultSet rs = stmt.executeQuery(QUERY);
        return rs;
    }


    public static void main(String[] args) throws Exception {
        //Retrieve data
        ResultSet rs = RetrieveData();

        //Creating a json array
        JSONArray array = new JSONArray();
        int counter = 0;

        //Inserting ResultSet data into the json object and creating the JSON outputs
        while(rs.next()) {
            //Creating a JSONObject record
            JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            record.put("SKU: ", rs.getInt("SKU"));
            record.put("Product: ", rs.getString("Product"));
            record.put("Quarter: ", rs.getInt("Quarter"));
            record.put("Year: ", rs.getInt("Year"));
            record.put("America: ", rs.getInt("America"));
            record.put("Europe: ", rs.getInt("Europe"));
            record.put("Asia: ", rs.getInt("Asia"));
            record.put("Australia: ", rs.getInt("Australia"));
            array.put(record);

            //Create a new JSON file for every record
            FileWriter file = new FileWriter
                    ("D:\\Demetris\\OneDrive\\Programming\\Java\\MAP\\json-output\\output-" + counter + ".json");
            counter++;
            file.write(record.toString());
            file.close();
        }
        System.out.println("JSON files created...");
    }
}