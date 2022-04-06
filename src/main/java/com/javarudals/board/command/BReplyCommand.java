package com.javarudals.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javarudals.board.dao.BDao;

public class BReplyCommand implements BCommand {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bId = request.getParameter("bid");
		String bName = request.getParameter("bname");
		String bTitle = request.getParameter("btitle");
		String bContent = request.getParameter("bcontent");		
		String bGroup = request.getParameter("bgroup");
		String bStep = request.getParameter("bstep");
		String bIndent = request.getParameter("bindent");
		
		BDao dao = new BDao();
		dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
	}

}