import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConvertClass {

    public static void readMovieCsvValuesAndInsertToSql(String filePosition) throws SQLException, IOException {
        BufferedReader reader = null;
        Connection connection = null;

        try {
            reader = new BufferedReader(new FileReader(filePosition));
            String line = reader.readLine();

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbUrl = "jdbc:sqlserver://DESKTOP-22CRCDK:1433;user=FullAdminControl;password=qweasd;databaseName=IMDb_FullDataset;encrypt=true;trustServerCertificate=true";
            connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();

            String[] splittedLine;
            int lineCount = 0;
            while (line != null) {
                if(lineCount == 0) {
                    lineCount++;
                    continue;
                }

                splittedLine = line.split("\t");

                System.out.println(splittedLine.length);

                String titleId = splittedLine[0].trim();
                Integer ordering = Integer.parseInt(splittedLine[1].trim());
                String title = splittedLine[2].trim();
                String region = splittedLine[3].trim();
                String lang = splittedLine[4].trim();
                String t_types = splittedLine[5].trim();
                String attributes = splittedLine[6].trim();
                String isOriginalTitle = splittedLine[7].trim();

                System.out.println(titleId);
                System.out.println(ordering);
                System.out.println(title);
                System.out.println(region);
                System.out.println(lang);
                System.out.println(t_types);
                System.out.println(attributes);
                System.out.println(isOriginalTitle);

                String queryBuilder = "INSERT INTO title_akas(titleId, ordering, title, region, lang, t_types, attributes, isOriginalTitle, posterUrl, overview) VALUES\n" +
                        "('" + titleId + "', " +
                        + ordering + ", " +
                        "'" + title + "', " +
                        "'" + region + "', " +
                        "'" + lang + "', " +
                        "'" + t_types + "', " +
                        "'" + attributes + "', " +
                        "'" + isOriginalTitle + "', " +
                        " NULL, " +
                        " NULL );";

                System.out.println(queryBuilder);
                statement.executeUpdate(queryBuilder);

                line = reader.readLine();
                lineCount++;
                if(lineCount % 10_000 == 0)
                    System.out.println("Achieved line " + lineCount);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            assert connection != null;
            connection.close();
            reader.close();
        }
    }

}