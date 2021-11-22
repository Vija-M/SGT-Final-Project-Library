package menu;

        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;


    public class LibraryCollection {
        public static void main(String[] args)
        {
            try {
                Statement statement = MainMenu.helper.getStatment();
                statement.execute("SELECT Books.id, Books.title, Books.authorID, Books.yearPublished, Books.publisher, Books.edition, Books.orderID, Authors.authorName FROM Books INNER JOIN Authors;");
                ResultSet rs = statement.getResultSet();

                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ". " + rs.getString("authorName") + " (" + rs.getInt("yearPublished") + ") " + rs.getString("title") + ", the " + rs.getString("edition") + " edition, by " + rs.getString("publisher") + "\n");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
