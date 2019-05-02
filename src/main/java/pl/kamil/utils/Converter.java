package pl.kamil.utils;

import pl.kamil.database.mapping.models.Book;
import pl.kamil.database.mapping.models.Loan;
import pl.kamil.database.mapping.models.Member;
import pl.kamil.models.BookFx;
import pl.kamil.models.BookModel;
import pl.kamil.models.LoanFx;
import pl.kamil.models.MemberFx;

public class Converter
{
	public static Member convertMemberFxToMember(MemberFx memberFx)
	{
		Member member = new Member();

		member.setId(memberFx.getId());
		member.setFirstName(memberFx.getFirstName());
		member.setLastName(memberFx.getLastName());
		member.setEmail(memberFx.getEmail());
		member.setPhoneNumber(memberFx.getPhoneNumber());

		return member;
	}

	public static MemberFx convertMemberToMemberFx(Member member)
	{
		MemberFx memberFx = new MemberFx();

		memberFx.setId(member.getId());
		memberFx.setFirstName(member.getFirstName());
		memberFx.setLastName(member.getLastName());
		memberFx.setEmail(member.getEmail());
		memberFx.setPhoneNumber(member.getPhoneNumber());

		return memberFx;
	}

	public static Book convertBookFxToBook(BookFx bookFx)
	{
		Book book = new Book();

		book.setId(bookFx.getId());
		book.setBookName(bookFx.getName());
		book.setAuthor(bookFx.getAuthor());
		book.setPublisher(bookFx.getPublisher());
		book.setReleasedDate(bookFx.getReleaseDate());

		return book;
	}

	public static BookFx convertBookToBookFx(Book book)
	{
		BookFx bookFx = new BookFx();

		bookFx.setId(book.getId());
		bookFx.setName(book.getBookName());
		bookFx.setAuthor(book.getAuthor());
		bookFx.setPublisher(book.getPublisher());
		bookFx.setReleaseDate(book.getReleasedDate());

		return bookFx;
	}

	public static LoanFx convertLoanToLoanFx(Loan loan)
	{
		LoanFx loanFx = new LoanFx();

		loanFx.setId(loan.getId());
		loanFx.setBookId(loan.getBookId());
		loanFx.setMemberId(loan.getMemberId());
		BookModel.getBooksFromDataBase().stream().filter(book -> book.getId() == loan.getBookId()).findFirst().ifPresent(bookFx -> loanFx.setTitle(bookFx.getName()));

		return loanFx;
	}
}
