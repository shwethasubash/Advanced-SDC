package dal.asd.catme.database;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

@Configuration
public class DatabaseAccess implements DataSource, IDatabaseAccess
{
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private int result;

    private static final String url = System.getenv("DB_URL");
    private static final String username = System.getenv("DB_USERNAME");
    private static final String password = System.getenv("DB_PASSWORD");

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DatabaseAccess.class);

    public Connection getConnection() throws SQLException
    {
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException
    {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException
    {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException
    {

    }

    @Override
    public int getLoginTimeout() throws SQLException
    {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException
    {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException
    {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException
    {
        return false;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException
    {

        return null;

    }

    @Override
    public PreparedStatement getPreparedStatement(String preparedStatementCall) throws SQLException
    {
        if (preparedStatementCall == null || preparedStatementCall.isEmpty())
        {
            throw new SQLException("Invalid procedure call");
        }
        connection = getConnection();

        return connection.prepareStatement(preparedStatementCall);
    }

    @Override
    public ResultSet executeForResultSet(PreparedStatement statement) throws SQLException
    {
        if (statement == null)
        {
            throw new SQLException("Invalid Prepared statement");
        }
        resultSet = statement.executeQuery();
        return resultSet;
    }

    @Override
    public void cleanUp()
    {
        try
        {
            if (resultSet != null && resultSet.isClosed() == false)
            {
                resultSet.close();
            }
            if (connection != null && connection.isClosed() == false)
            {
                connection.close();
            }
        } catch (SQLException throwables)
        {
            log.error("Error Closing Database Connection");
        }
    }
}
