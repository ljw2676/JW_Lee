package yogi.members;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

public interface MembersService {
	//public void insertMember(MembersModel model,  HttpServletRequest request) throws Exception;
	public void insertMember(MembersModel model);
	public int checkId(String id);
	public MembersModel loginCheck(MembersModel model, HttpServletRequest request);
	public MembersModel findId(MembersModel model, HttpServletRequest request);
	public MembersModel findPW(MembersModel model, HttpServletRequest request);
	public void resetPW(MembersModel model);

}
