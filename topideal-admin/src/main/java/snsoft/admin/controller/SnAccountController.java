package snsoft.admin.controller;

import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.baomidou.kisso.common.encrypt.SaltEncoder;
import com.baomidou.kisso.common.util.RandomUtil;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import snsoft.admin.constant.SnBaseConstant;
import snsoft.admin.constant.enums.SnUserType;
import snsoft.admin.entity.SnUser;
import snsoft.admin.service.ISnUserService;
import snsoft.admin.util.SnSaltEncoderUtil;
import snsoft.comm.controller.SnBaseController;
import snsoft.redis.service.ISnRedisService;
/**
 * <p>��Ŀ���⣺ </p>
 * <p>��Ŀ���ܣ� </p>
 * <p>����ģ�飺 </p>
 * <p>����ƽ̨��Window10</p>
 * <p>�������ߣ�Eclipse</p>
 * <p>jar��:TODO</p>
 * <p>�������ڣ�2018��5��23�� ����3:39:33</p>
 * <p>��Ŀ���ߣ�����</p>
 * <p>��ȫ����snsoft.admin.controller.SnAccountController</p>
 * @version 1.0
 */
@Controller
@RequestMapping("/account")
public class SnAccountController extends SnBaseController
{
	@Resource(name = "sn-SnUserService")
	private ISnUserService	userService;
	@Resource(name = "sn-SnRedisService")
	ISnRedisService			redisService;

	@RequestMapping(value = "/login")
	public String index(Model model)
	{
		if (isPost())
		{
			String errorMsg = "";
			// ���� XSS SQL ע��
			WafRequestWrapper wr = new WafRequestWrapper(request);
			String auto = wr.getParameter("auto");
			if (auto != null)
			{
				String loginName = wr.getParameter("loginName");
				if (loginName != null)
				{
					return redirectTo("/index.html");
				}
			}
			String captcha = wr.getParameter("captcha");
			if (StringUtils.isNotBlank(captcha))
			{
				String re_captcha = (String) redisService.get("captcha");
				re_captcha = re_captcha.toLowerCase();
				if (captcha.equals(re_captcha))
				{
					String loginName = wr.getParameter("loginName");
					String password = wr.getParameter("password");
					if (loginName == null || password == null)
					{
						errorMsg += "�û��������벻��Ϊ��";
					} else
					{
						SnUser user = userService.selectByLoginName(loginName);
						if (user != null && SnSaltEncoderUtil.md5SaltValid(loginName, user.getPassword(), password))
						{
							//���û���Ϣע�뵽redis��
							redisService.set("loginName", loginName);
							redisService.expire("loginName", 7200);
							redisService.set("uid", user.getId());
							redisService.expire("uid", 7200);
							String rememberMe = wr.getParameter("rememberMe");
							if ("on".equals(rememberMe))
							{
								Cookie cookie = new Cookie("loginName", String.valueOf(user.getId()));
								cookie.setMaxAge(2 * 3600 * 1000);
								cookie.setPath("/");
								response.addCookie(cookie);
								redisService.set(SnBaseConstant.COOKIE_MAXAGE, 3600);
								redisService.expire(SnBaseConstant.COOKIE_MAXAGE, 3600);
							}
							return redirectTo("/index.html");
						} else
						{
							errorMsg += "��ǰ�û�������";
						}
					}
				} else
				{
					errorMsg += "��֤�����";
				}
			} else
			{
				errorMsg += "��֤���ѹ���,ˢ�º������µ�¼";
			}
			if (errorMsg != null)
			{
				model.addAttribute("errorMsg", errorMsg);
			}
		}
		model.addAttribute(SnBaseConstant.CAPTCHA_TOKEN, RandomUtil.get32UUID());
		return "/login";
	}

	/**
	 * ע��
	 */
	@RequestMapping(value = "/register", method = { RequestMethod.POST })
	public String register(Model model, SnUser user)
	{
		if (isPost())
		{
			SnUser existUser = userService.selectByLoginName(user.getLoginName());
			if (existUser == null)
			{
				user.setPassword(SaltEncoder.md5SaltEncode(user.getLoginName(), user.getPassword()));
				user.setType(SnUserType.MEMBER.key());
				user.setCrTime(new Date());
				user.setLastTime(user.getCrTime());
				userService.insert(user);
				return redirectTo("/index.html");
			}
		}
		model.addAttribute("tipMsg", "ע���û�����" + user.getLoginName() + "���Ѵ��ڣ�");
		return "/register";
	}

	/**
	 * �˳�
	 */
	@RequestMapping("/logout")
	public String logout(Model model)
	{
		return redirectTo("/account/login.html");
	}

	/**
	 * ����
	 */
	@RequestMapping("/lockscreen")
	public String lockscreen(Model model, String password)
	{
		HttpSession session = request.getSession();
		String loginName = (String) session.getAttribute(SnBaseConstant.LOCKSCREEN_USER_FLAG);
		if (StringUtils.isBlank(loginName))
		{
			//session.setAttribute(SnBaseConstant.LOCKSCREEN_USER_FLAG, loginName);
		} else if (StringUtils.isNotBlank(password) && isPost())
		{
			SnUser user = userService.selectByLoginName(loginName);
			if (user != null && SaltEncoder.md5SaltValid(loginName, user.getPassword(), password))
			{
				return redirectTo("/index.html");
			}
		}
		model.addAttribute("loginName", loginName);
		return "/lockscreen";
	}
}
