package pl.kamil.database.DAO;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
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
	private ConnectionSource connectionSource;
	private final Class<T> type;

	public CommonDao(Class<T> type)
	{
		this.type = type;
	}

	public void createOrUpdate(T baseModel)
	{
		try
		{
			openConnectionSource();
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.createOrUpdate(baseModel);
		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		} finally
		{
			closeConnectionSource();
		}
	}

	public void create(T baseModel)
	{
		try
		{
			openConnectionSource();
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.create(baseModel);
		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		} finally
		{
			closeConnectionSource();
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
			LOGGER.warn(e.getMessage());
		}
	}

	public void delete(T baseModel)
	{
		try
		{
			openConnectionSource();
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.delete((T) baseModel);
		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		} finally
		{
			closeConnectionSource();
		}
	}

	public void delete(Collection<T> baseModels)
	{
		try
		{
			openConnectionSource();
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.delete(baseModels);
		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		} finally
		{
			closeConnectionSource();
		}
	}

	public void deleteById(K id)
	{
		try
		{
			openConnectionSource();
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.deleteById(id);
		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		}
	}

	public void deleteByIds(Collection<K> ids)
	{
		try
		{
			openConnectionSource();
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.deleteIds(ids);
		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		}
	}

	public Optional<T> queryForId(K id)
	{
		try
		{
			openConnectionSource();
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			return Optional.ofNullable(commonDao.queryForId(id));

		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		}
		return Optional.empty();
	}

	public Optional<T> query(T baseModel)
	{
		try
		{
			openConnectionSource();
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			return Optional.ofNullable(commonDao.queryForSameId(baseModel));

		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		}
		return Optional.empty();
	}

	public Collection<T> queryForAll()
	{
		try
		{
			openConnectionSource();
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			return commonDao.queryForAll();

		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		} finally
		{
			closeConnectionSource();
		}
		return Collections.emptyList();
	}

	public QueryBuilder<T, K> getQueryBuilder()
	{
		try
		{
			openConnectionSource();
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			return commonDao.queryBuilder();
		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		}
		return null;
	}

	public DeleteBuilder<T, K> getDeleteBuilder()
	{
		try
		{
			openConnectionSource();
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			return commonDao.deleteBuilder();
		} catch(SQLException e)
		{
			LOGGER.warn(e.getMessage());
		} finally
		{
			closeConnectionSource();
		}
		return null;
	}

	private void closeConnectionSource()
	{
		DBManager.closeConnectionSource();
		connectionSource = null;
	}

	private void openConnectionSource()
	{
		connectionSource = DBManager.getConnectionSource();
	}
}
