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
import java.util.stream.Collectors;

public class MemberModel
{
	private static final ObservableList<MemberFx> membersFromDataBase = FXCollections.observableArrayList();
	private static final MemberDao memberDao = new MemberDao();
	private final ObjectProperty<MemberFx> memberFxObjectProperty = new SimpleObjectProperty<>(new MemberFx());
	private final ObjectProperty<MemberFx> memberFxObjectPropertyEdit = new SimpleObjectProperty<>(new MemberFx());

	public MemberModel()
	{
		MemberModel.membersFromDataBase.clear();
		MemberModel.membersFromDataBase.addAll(memberDao.queryForAll().stream().map(Converter::convertMemberToMemberFx).collect(Collectors.toList()));
	}

	public void saveMemberInDataBase()
	{
		MemberFx memberFx = memberFxObjectProperty.get();
		Member member = Converter.convertMemberFxToMember(memberFx);
		memberDao.create(member);
		MemberModel.membersFromDataBase.add(memberFx);
	}

	public void deleteBookInDataBase()
	{
		MemberFx memberFx = memberFxObjectPropertyEdit.get();
		Member member = Converter.convertMemberFxToMember(memberFx);
		memberDao.delete(member);
		MemberModel.membersFromDataBase.remove(memberFx);
	}

	public void deleteBooksInDataBase(Collection<MemberFx> members)
	{
		List<Member> membersToDelete = members.stream().map(Converter::convertMemberFxToMember).collect(Collectors.toList());
		MemberModel.membersFromDataBase.removeAll(members);
		memberDao.delete(membersToDelete);
	}

	public static ObservableList<MemberFx> getMembersFromDataBase()
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
