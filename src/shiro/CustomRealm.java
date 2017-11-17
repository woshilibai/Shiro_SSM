package shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm {

	//设置自定义realm的名字
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}
	/**
	 * 用于授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("=========授权方法==============");
		return null;
	}

	/**
	 * 用于认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("=========认证方法==============");
		//token是用户输入的
		//1.从token里获取身份信息
		String userCode = (String) token.getPrincipal();
		//2.根据用户输入的userCode从数据库查询password
		String password = "123456";
		//如果查不到，返回null
		//查到返回认证信息authenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, password , this.getName());
		return simpleAuthenticationInfo;
	}

}
