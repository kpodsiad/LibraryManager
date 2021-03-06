package pl.kamil.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kamil.database.DAO.LoanDao;
import pl.kamil.database.mapping.models.Book;
import pl.kamil.database.mapping.models.Loan;
import pl.kamil.database.mapping.models.Member;
import pl.kamil.utils.Converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoanModel
{
	private final LoanDao loanDao = new LoanDao();
	private final ObservableList<LoanFx> loansFromDataBase = FXCollections.observableArrayList();

	public LoanModel()
	{
		refresh();
	}

	public void saveLoanInDataBase(Book book, Member member)
	{
		Loan loan = new Loan(book, member);
		saveLoanInDataBase(loan);
	}

	public void saveLoanInDataBase(Loan loan)
	{
		loanDao.create(loan);
		loansFromDataBase.add(Converter.convertLoanToLoanFx(loan));
	}

	public void deleteLoans(Collection<LoanFx> loans)
	{
		List<Loan> loansToDelete = loans.stream().map(Converter::convertLoanFxToLoan).collect(Collectors.toList());
		loansFromDataBase.removeAll(loans);
		loanDao.delete(loansToDelete);
	}

	public void deleteLoansByIds(Collection<Long> ids)
	{
		List<LoanFx> nonDeletedLoans = loansFromDataBase.stream().filter(loanFx -> !ids.contains(loanFx.getId())).collect(Collectors.toList());
		loansFromDataBase.clear();
		loansFromDataBase.addAll(nonDeletedLoans);
		loanDao.deleteByIds(ids);
	}

	public void deleteLoansByBooksIds(Collection<Long> booksIds)
	{
		List<LoanFx> nonDeletedLoans = loansFromDataBase.stream().filter(loanFx -> !booksIds.contains(loanFx.getBookId())).collect(Collectors.toList());
		loansFromDataBase.clear();
		loansFromDataBase.addAll(nonDeletedLoans);
		loanDao.deleteByBookIds(booksIds);
	}

	public void deleteLoansByMembersIds(Collection<Long> memberIds)
	{
		List<LoanFx> nonDeletedLoans = loansFromDataBase.stream().filter(loanFx -> !memberIds.contains(loanFx.getMemberId())).collect(Collectors.toList());
		loansFromDataBase.clear();
		loansFromDataBase.addAll(nonDeletedLoans);
		loanDao.deleteByMemberIds(memberIds);
	}

	public Collection<LoanFx> searchForBook(String partialTitle)
	{
		if(partialTitle.equals(""))
			return loansFromDataBase;
		else
			return loansFromDataBase.stream().filter(loan -> loan.getTitle().contains(partialTitle)).collect(Collectors.toList());
	}

	public void refresh()
	{
		loansFromDataBase.clear();
		Collection<Loan> xd = loanDao.queryForAll();
		loansFromDataBase.addAll(loanDao.queryForAll().stream().map(Converter::convertLoanToLoanFx).collect(Collectors.toList()));
	}

	public ObservableList<LoanFx> getLoansFromDataBase()
	{
		return loansFromDataBase;
	}
}
