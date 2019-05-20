package pl.kamil.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kamil.database.DAO.MemberDao;
import pl.kamil.database.mapping.models.Member;
import pl.kamil.utils.Converter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemberModel
{
	private final ObservableList<MemberFx> membersFromDataBase = FXCollections.observableArrayList();
	private final MemberDao memberDao = new MemberDao();
	private final ObjectProperty<MemberFx> memberFxObjectProperty = new SimpleObjectProperty<>(new MemberFx());
	private final ObjectProperty<MemberFx> memberFxObjectPropertyEdit = new SimpleObjectProperty<>(new MemberFx());

	public MemberModel()
	{
		refresh();
	}

	public void refresh()
	{
		membersFromDataBase.clear();
		membersFromDataBase.addAll(memberDao.queryForAll().stream().map(Converter::convertMemberToMemberFx).collect(Collectors.toList()));
	}

	public Collection<MemberFx> searchForMember(String partialName)
	{
		if(partialName.equals(""))
			return membersFromDataBase;
		else
			return membersFromDataBase.stream().filter(member -> member.getFirstName().concat(" ").concat(member.getLastName()).contains(partialName)).collect(Collectors.toList());
	}

	public Optional<MemberFx> searchForMemberById(Long id)
	{
		return membersFromDataBase.stream().filter(memberFx -> memberFx.getId() == id).findAny();
	}

	public void saveMemberInDataBase()
	{
		MemberFx memberFx = memberFxObjectProperty.get();
		Member member = Converter.convertMemberFxToMember(memberFx);
		memberDao.create(member);
		membersFromDataBase.add(Converter.convertMemberToMemberFx(member));
	}

	public void deleteBookInDataBase()
	{
		MemberFx memberFx = memberFxObjectPropertyEdit.get();
		Member member = Converter.convertMemberFxToMember(memberFx);
		memberDao.delete(member);
		membersFromDataBase.remove(memberFx);
	}

	public void deleteBooksInDataBase(Collection<MemberFx> members)
	{
		List<Member> membersToDelete = members.stream().map(Converter::convertMemberFxToMember).collect(Collectors.toList());
		membersFromDataBase.removeAll(members);
		memberDao.delete(membersToDelete);
	}

	public ObservableList<MemberFx> getMembersFromDataBase()
	{
		return membersFromDataBase;
	}

	public MemberFx getMemberFxObjectProperty()
	{
		return memberFxObjectProperty.get();
	}

	public ObjectProperty<MemberFx> memberFxObjectPropertyProperty()
	{
		return memberFxObjectProperty;
	}

	public MemberFx getMemberFxObjectPropertyEdit()
	{
		return memberFxObjectPropertyEdit.get();
	}

	public ObjectProperty<MemberFx> memberFxObjectPropertyEditProperty()
	{
		return memberFxObjectPropertyEdit;
	}
}
