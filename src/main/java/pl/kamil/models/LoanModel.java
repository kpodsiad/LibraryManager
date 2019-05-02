package pl.kamil.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kamil.database.DAO.LoanDao;
import pl.kamil.database.mapping.models.Loan;
import pl.kamil.utils.Converter;

import java.util.stream.Collectors;

public class LoanModel
{
	private static final LoanDao loanDao = new LoanDao();
	private final ObservableList<LoanFx> loans = FXCollections.observableArrayList();

	public LoanModel()
	{
	}

	public void addLoan(Long bookID, Long memberID)
	{
		Loan loan = new Loan(bookID, memberID);
		LoanModel.loanDao.create(loan);
	}

	public void deleteLoan()
	{

	}

	public void refresh()
	{
		queryForAll();
	}

	private void queryForAll()
	{
		loans.clear();
		loans.addAll(loanDao.queryForAll().stream().map(Converter::convertLoanToLoanFx).collect(Collectors.toList()));
	}

	public ObservableList<LoanFx> getLoans()
	{
		return loans;
	}
}
