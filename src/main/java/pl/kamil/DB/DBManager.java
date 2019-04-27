package pl.kamil.DB;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.kamil.models.Book;

import java.io.IOException;
import java.sql.SQLException;

public class DBManager
{

	private static final Logger LOGGER = LoggerFactory.getLogger(DBManager.class);

	private static final String JDBC_DRIVER_HD = "jdbc:h2:./LibraryManagerDB";
	private static final String USER = "admin";
	private static final String PASS = "admin";

	private static ConnectionSource connectionSource;

	public static void initDatabase()
	{
		createConnectionSource();
		createTable();
		closeConnectionSource();
	}

	private static void createConnectionSource()
	{
		try
		{
			connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD, USER, PASS);
		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		}
	}

	public static ConnectionSource getConnectionSource()
	{
		if(connectionSource == null)
		{
			createConnectionSource();
		}
		return connectionSource;
	}

	public static void closeConnectionSource()
	{
		if(connectionSource != null)
		{
			try
			{
				connectionSource.close();
			} catch(IOException e)
			{
				LOGGER.warn(e.getMessage());
			}
		}
	}

	private static void createTable()
	{
		try
		{
			TableUtils.createTableIfNotExists(connectionSource, Book.class);
		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		}
	}

}

