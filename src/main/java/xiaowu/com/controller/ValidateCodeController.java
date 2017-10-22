package xiaowu.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import xiaowu.com.util.ValidateCodeUtil;

@Controller
@RequestMapping("/validateCodeDemo")
public class ValidateCodeController {
	/**
	 * 响应验证码页面
	 */
	@RequestMapping("/validateCode")
	public void validateCode(HttpServletRequest request,HttpServletResponse response) throws Exception{
	    // 设置响应的类型格式为图片格式  
		response.setContentType("image/jpeg");  
	    //禁止图像缓存。  
	    response.setHeader("Pragma", "no-cache");  
	    response.setHeader("Cache-Control", "no-cache");  
	    response.setDateHeader("Expires", 0);  
	  
	    HttpSession session = request.getSession();  
	  
	    ValidateCodeUtil vCode = new ValidateCodeUtil(120,40,5,100);  
	    session.setAttribute("code", vCode.getCode());  
	    vCode.write(response.getOutputStream());  
	}
	
	@RequestMapping("/toPage")
	public String toPage() throws Exception{
		return "validateCode";
	}
	
	/**
	 * 验证验证码输入是否正确
	 */
	@RequestMapping("/checkValidateCode")
	public String checkValidateCode(ModelMap modelMap,String validateCode,HttpSession session) throws Exception{
		String code = (String) session.getAttribute("code");
		if (code.equals(validateCode)) {
			modelMap.addAttribute("message", "恭喜,验证码正确！");
		}else {
			modelMap.addAttribute("message", "抱歉，验证码错误！");
		}
		return "validateCode";
	}
}
