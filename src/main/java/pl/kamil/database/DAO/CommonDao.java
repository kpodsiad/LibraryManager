package pl.kamil.database.DAO;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import pl.kamil.database.DBManager;
import pl.kamil.database.mapping.models.BaseModel;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public abstract class CommonDao<T extends BaseModel, K>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
	private final ConnectionSource connectionSource;
	private final Class<T> type;

	public CommonDao(ConnectionSource connectionSource, Class<T> type)
	{
		this.connectionSource = connectionSource;
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public void createOrUpdate(BaseModel baseModel)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, type);
			commonDao.createOrUpdate((T) baseModel);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseModel, K> void create(BaseModel baseModel)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, (Class<T>) baseModel.getClass());
			commonDao.create((T) baseModel);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseModel, K> void create(Collection<? extends BaseModel> baseModels, Class<? extends BaseModel> cls)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, (Class<T>) cls);
			commonDao.create((Collection<T>) baseModels);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseModel, K> void delete(BaseModel baseModel)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, (Class<T>) baseModel.getClass());
			commonDao.delete((T) baseModel);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseModel, K> void delete(Collection<? extends BaseModel> baseModels, Class<? extends BaseModel> cls)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, (Class<T>) cls);
			commonDao.delete((Collection<T>) baseModels);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseModel, K> void deleteById(Class<T> cls, Long id)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, cls);
			commonDao.deleteById((K) id);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseModel, K> Optional<T> query(BaseModel baseModel)
	{
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, (Class<T>) baseModel.getClass());
			return Optional.ofNullable(commonDao.queryForSameId((T) baseModel));

		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseModel, K> Collection<T> queryForAll(Class<T> cls)
	{
		T queriedItem = null;
		try
		{
			Dao<T, K> commonDao = DaoManager.createDao(connectionSource, (Class<T>) cls);
			return commonDao.queryForAll();

		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	}

	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		DBManager.closeConnectionSource();
	}
}
