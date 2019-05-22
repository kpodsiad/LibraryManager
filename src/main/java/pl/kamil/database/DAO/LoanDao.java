package pl.kamil.database.DAO;

import com.j256.ormlite.stmt.DeleteBuilder;
import pl.kamil.database.mapping.models.Loan;

import java.sql.SQLException;
import java.util.Collection;

public class LoanDao extends CommonDao<Loan, Long>
{
	public LoanDao()
	{
		super(Loan.class);
	}

	public void deleteByBookIds(Collection<Long> ids)
	{
		deleteLoansByForeignIds(ids, "BOOK");
	}

	public void deleteByMemberIds(Collection<Long> ids)
	{
		deleteLoansByForeignIds(ids, "MEMBER");
	}

	private void deleteLoansByForeignIds(Collection<Long> ids, String foreignColumnName)
	{
		DeleteBuilder<Loan, Long> deleteBuilder = getDeleteBuilder();
		try
		{
			deleteBuilder.where().in(foreignColumnName, ids);
			deleteBuilder.delete();
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
