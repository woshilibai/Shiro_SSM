package controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import exception.CustomException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by codingBoy on 16/11/18.
 * 完成登陆和退出
 */
@Controller
public class LoginController
{

    //用户登陆提交方法
    @RequestMapping("/login")
    public String login(HttpServletRequest request) throws Exception
    {
    	//登陆失败的话，异常信息会保存在request中，shiroLoginFailure是shiro登陆异常类的类名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        //根据shiro返回的异常判断，抛出指定的异常信息
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			throw new CustomException("账号不存在");
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			throw new CustomException("用户名/密码错误");
		} else {
			throw new Exception();//最终在异常处理器生成未知错误
		}
        
    }

    //用户退出
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws Exception
    {
        //session失效
        session.invalidate();

        //重定向到商品查询页面
        return "redirect:/items/queryItems.action";
    }
}
