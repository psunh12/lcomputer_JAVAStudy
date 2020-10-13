package com.lcomputerstudy.testmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.service.BoardService;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;
import com.lcomputerstudy.testmvc.vo.Board;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID=1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view =null;
		String idx=null;
		String pw = null;
		command = checkSession(request, response, command);

		int page=1;
		UserService userService = null;
		BoardService boardService = null;
		
		switch(command) {
			case "/user-list.do":
				String reqPage = request.getParameter("page");
				if (reqPage != null) {
					page = Integer.parseInt(reqPage);
				}
				userService = UserService.getInstance();
				int userCount = userService.getUsersCount();
				Pagination pagination =new Pagination(page, userCount);
				userService = UserService.getInstance();
				ArrayList<User> list = userService.getUsers(page);
				
				request.setAttribute("userList",list);
				request.setAttribute("pagination",pagination);
				
				view = "user/list";
				break;
			
			case "/user-insert.do":
				view = "user/insert";
				break;
			case "/user-insert-process.do":
				User user = new User();
				user.setU_id(request.getParameter("id"));
				user.setU_pw(request.getParameter("password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1")+"-"+request.getParameter("tel2")+"-"+request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				
				userService=UserService.getInstance();
				userService.insertUser(user);
				
				view = "user/insert-result";
				break;
				
				
			case "/user-login.do":
				view = "user/login";
				break;
			
			case "/user-login-process.do":
				idx = request.getParameter("login_id");
				pw = request.getParameter("login_password");
				
				userService= UserService.getInstance();
				user = userService.loginUser(idx,pw);
				
				if(user != null) {
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					
					view="user/login-result";
				}else {
					view="user/login-fail";
				}
				break;
			
			case "/logout.do":
				HttpSession session = request.getSession();
				session.invalidate();
				view = "user/login";
				break;	
			case "/access-denied.do":
				view = "user/access-denied";
				break;
				
			case "/user-write-process.do":
				Board board = new Board();
				board.setB_title(request.getParameter("title"));
				board.setB_content(request.getParameter("content"));
				board.setB_writer(request.getParameter("writer"));
				board.setU_idx(request.getParameter("u_idx"));
				//board.setB_date(request.getParameter("date"));
				
				userService = UserService.getInstance();
				userService.insertBoard(board);
				
				view = "user/write-result";
				break;
				
			case "/write.do":
				view="user/write";
				break;
				
			case "/write-list.do":
				String reqPage2 = request.getParameter("page");
				if (reqPage2 != null) { 
					page = Integer.parseInt(reqPage2);
				}
				
				boardService = BoardService.getInstance();
				int boardCount = boardService.getBoardsCount();
				Pagination pagination2 =new Pagination(page, boardCount);
				
				ArrayList<Board> list1 = boardService.getBoards(page);
				
				request.setAttribute("list1",list1);
				request.setAttribute("pagination",pagination2);
				view="user/write-list";
				
				break;	
				
		}
			
		
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}


String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
	HttpSession session = request.getSession();
	
	String[] authList = {
			"/user-list.do"
			,"/user-insert.do"
			,"/user-insert-process.do"
			,"/user-detail.do"
			,"/user-edit.do"
			,"/user-edit-process.do"
			,"/logout.do"
			,"/write.do"
			,"/write-list.do"
	};
	for (String item : authList) {
		if(item.equals(command)) {
			if(session.getAttribute("user")==null) {
				command = "/access-denied.do";
			}
		}
	}
	return command;
} 
}