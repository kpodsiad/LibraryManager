package pl.kamil.database.DAO;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import pl.kamil.database.DBManager;
import pl.kamil.database.mapping.models.MappingModel;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public abstract class CommonDao<T extends MappingModel, K>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
	private final ConnectionSource connectionSource;
	private final Class<T> type;

	public CommonDao(Class<T> type)
	{
		this.connectionSource = DBManager.getConnectionSource();
		this.type = type;
	}


	public void createOrUpdate(T baseModel)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.createOrUpdate(baseModel);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void create(T baseModel)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.create(baseModel);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void create(Collection<T> baseModels)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.create(baseModels);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void delete(T baseModel)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.delete((T) baseModel);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void delete(Collection<T> baseModels)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.delete(baseModels);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deleteById(K id)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.deleteById(id);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public Optional<T> queryForId(K id)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			return Optional.ofNullable(commonDao.queryForId(id));

		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<T> query(T baseModel)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			return Optional.ofNullable(commonDao.queryForSameId(baseModel));

		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Collection<T> queryForAll()
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			return commonDao.queryForAll();

		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
}
