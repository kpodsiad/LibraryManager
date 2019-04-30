package pl.kamil.database.DAO;

import pl.kamil.database.mapping.models.Member;

public class MemberDao extends CommonDao<Member, Long>
{
	public MemberDao()
	{
		super(Member.class);
	}
}
