import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        String fileLocation = "C:\\Users\\trifa\\OneDrive\\Desktop\\data.tsv";
        ConvertClass.readMovieCsvValuesAndInsertToSql(fileLocation);
    }
}