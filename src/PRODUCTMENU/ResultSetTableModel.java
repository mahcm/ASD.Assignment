package PRODUCTMENU;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel {
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private ResultSetMetaData rsMetaData;

    private int noOfRow;
    private boolean isDbConnected = false;

    public ResultSetTableModel(
            String url,
            String username,
            String password,
            String query) throws SQLException {
        con  = DriverManager.getConnection(url, username, password);
        stmt = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        isDbConnected = true;

        setQuery(query);
    }

    public void setQuery(String query) throws IllegalStateException, SQLException {
        if (!isDbConnected) {
            throw new IllegalStateException("Not Connected to Database");
        }

        rs         = stmt.executeQuery(query);
        rsMetaData = rs.getMetaData();

        rs.last();             // move cursor to last row
        noOfRow = rs.getRow(); // get row number

        this.fireTableStructureChanged(); // notify JTable that model has changed
    }

    public Class getColumnClass(int column) throws IllegalStateException {
        if (!isDbConnected) {
            throw new IllegalStateException("Not Connected to Database");
        }

        try {
            String className = rsMetaData.getColumnClassName(column + 1);

            return Class.forName(className);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Object.class;
    }

    public String getColumnName(int column) throws IllegalStateException {
        if (!isDbConnected) {
            throw new IllegalStateException("Not Connected to Database");
        }

        try {
            return rsMetaData.getColumnName(column + 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public void disconnectDb() {
        if (isDbConnected) {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                isDbConnected = false;
            }
        }
    }

    @Override
    public int getColumnCount() {
        if (!isDbConnected) {
            throw new IllegalStateException("Not Connected to Database");
        }

        try {
            return rsMetaData.getColumnCount();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    @Override
    public int getRowCount() {
        if (!isDbConnected) {
            throw new IllegalStateException("Not Connected to Database");
        }

        return noOfRow;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) throws IllegalStateException {
        if (!isDbConnected) {
            throw new IllegalStateException("Not Connected to Database");
        }

        try {
            rs.absolute(rowIndex + 1);
            return rs.getObject(columnIndex + 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}