package com.javarudals.board.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javarudals.board.command.BCommand;
import com.javarudals.board.command.BListCommand;
import com.javarudals.board.command.BWriteCommand;

/**
 * Servlet implementation class BFrontController
 */
@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI();//전체 주소(http://localhost:8888/mvc_board_project00/write.do)
		String conPath = request.getContextPath();//context 주소(http://localhost:8888/mvc_board_project)
		String com = uri.substring(conPath.length());//전체주소-context주소 = 실제 이동될 주소(/*.do)->/write.do
		
		if(com.equals("/write_view.do")) {
			viewPage = "write_view.jsp";
		} else if(com.equals("/write.do")) {
			command = new BWriteCommand();
			command.excute(request, response);			
			viewPage = "list.do";
		} else if(com.equals("/list.do")) {
			command = new BListCommand();
			command.excute(request, response);
			
			viewPage = "list.jsp";
//			response.sendRedirect(viewPage);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response); // 기존 request 객체를 인수로 넣어서 forward 하므로 기존 request 객체의 내용을 사용할 수 잇음
		
		
	}

}