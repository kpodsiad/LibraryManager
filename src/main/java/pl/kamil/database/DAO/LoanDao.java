package pl.kamil.database.DAO;

import pl.kamil.database.mapping.models.Loan;

public class LoanDao extends CommonDao<Loan, Long>
{
	public LoanDao()
	{
		super(Loan.class);
	}
}
