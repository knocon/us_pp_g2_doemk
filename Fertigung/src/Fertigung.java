import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

public class Fertigung {
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private LocalDate ld;
	
	public Fertigung() {
		try {
			leseDatenbank();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void leseDatenbank() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
    		String url= "jdbc:mysql://localhost:3306/project?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    		String user = "testacc";
            String pw = "testtest";
            
            connect = DriverManager.getConnection(url, user, pw);
            
            statement = connect.createStatement();
            
            resultSet = statement.executeQuery("select * from production");
            schreibeResultSet(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
        	
        }

    }
	
	public void personZuAuftragHinzufuegen(int personID, int auftragID, String rolle) {
		String anfrage = "INSERT INTO VerbindungPersonAuftrag (personID, rolle, productionID) VALUES("
				+ "'" + personID + "',"
				+ "'" + rolle + "',"
				+ "'" + auftragID + "')";
		try {
			statement.executeUpdate(anfrage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    private void schreibeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("idproduction");
            String name = resultSet.getString("name");
            String typ = resultSet.getString("type");
            String pfad = resultSet.getString("path");
            double kosten = resultSet.getDouble("cost");
            String status = resultSet.getString("status");
            Date stempel = resultSet.getDate("stamp");
            System.out.println("id: " + id
            					+ " name: " + name
            					+ " typ: " + typ
            					+ " pfad: " + pfad
            					+ " kosten: " + kosten
            					+ " status: " + status
            					+ " datum: " + stempel);
        }
    }
    
    public void erstelleAuftrag(String name, String typ, String pfad, double kosten, String status){
    	String query = "INSERT INTO production (name, type, path, cost, status, stamp) VALUES("
    					+ "'" + name + "',"
    					+ "'" + typ + "',"
    					+ "'" + pfad + "',"
    					+ "'" + kosten + "',"
    					+ "'" + status + "',"
    					+ "'" + ld.now() + "')";
    	try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public int gebeIDzurueck(String nameAuftrag) {
    	int id = -1;
    	try {
			resultSet = statement.executeQuery("select * from production"
											+ " where name = " 
											+ "'" + nameAuftrag + "'");
			if(resultSet.next()) {
				id = resultSet.getInt("idproduction");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return id;
    }
    
    public void aktualisiereWertVonSpalte(String spalte, String wert, int id){
    	String abfrage = "UPDATE production "
					+ "SET " + spalte + " = "
					+ "'" + wert + "' "
					+ "WHERE idproduction = " + id;
    	try {
			statement.executeUpdate(abfrage);
			leseDatenbank();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public ResultSet filterNachWerten(String spalte, String wert){
    	try {
			resultSet = statement.executeQuery("select * from production "
											+ "where " + spalte +  " = "
											+ "'" + wert + "'");
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    private void schliessen() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
