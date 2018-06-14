package cn.sysu.comm.servlet;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.sysu.comm.entity.User;
import cn.sysu.comm.service.UserService;
/**
 * 
 * @Description: 编辑个人资料 --> 由个人资料页面提交表单后的处理
 * @CreateTime: 2018-5-8 下午12:42:14 
 * @author: bee
 */
public class EditServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //处理乱码
		UserService userService = new UserService();
		
		/*
		 * 文件上传三步
		 */
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfUpload = new ServletFileUpload(factory);
		sfUpload.setFileSizeMax(1024 * 150);//头像大小最大为15kb
		try {
			List<FileItem> fileItems = sfUpload.parseRequest(request);
			FileItem file = null;
			Map<String, String> map = new HashMap<String, String>();
			for(int i = 0; i < fileItems.size(); i++){
				if(fileItems.get(i).isFormField()){
					map.put(fileItems.get(i).getFieldName(), fileItems.get(i).getString("utf-8"));
				} else {
					 file = fileItems.get(i);
				}
			}
			//得到User对象
			User user = CommonUtils.toBean(map, User.class);
			
			
			if(file != null && file.getName()!=null &&!file.getName().trim().equals("")) {
//				FileItem file = fileItems.get(1);
				// 存放路径为：F:\apache-tomcat-7.0.85-windows-x64\apache-tomcat-7.0.85\webapps\SYSUCommunity\icon_img
				String savePath = this.getServletContext().getRealPath("/icon_img");
				
				//为防止重名,在名字前面加uuid
				String filename = CommonUtils.uuid().substring(10) + "_"  + file.getName();
				File destFile = new File(savePath, filename);
				try {
					// 写入到硬盘
					file.write(destFile);
					
					//校验图片尺寸
					//1.使用文件路径创建ImageIcon
					ImageIcon icom=new ImageIcon(destFile.getAbsolutePath());
					//2.通过ImageIcon得到Image对象
					Image image=icom.getImage();
					//3.获取宽高来进行校验
					if(image.getWidth(null)>350||image.getHeight(null)>350){
						String msg = "您的头像宽高应小于350*350";
						request.setAttribute("msg", msg);
						destFile.delete();//删除图片
						//request.getRequestDispatcher("/me.jsp").forward(request, response);
						return;
					}
					
					user.setIcon("icon_img/" + filename);
//					response.sendRedirect(request.getContextPath() + "/user.jsp");
					
					
				} catch (Exception e) {
					//throw new RuntimeException(e);
					e.printStackTrace();
					response.getWriter().write("fail");
				}
			}
			
			userService.edit(user);
			response.getWriter().write("success");

		} catch (FileUploadException e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException){
//				String msg = "您的头像大小超过了15kb";
//				request.setAttribute("msg", msg);
//				request.getRequestDispatcher("/me.jsp").forward(request, response);
				
				response.getWriter().write("fail");
			}
		}

		
	}

}
