package snsoft.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import snsoft.admin.service.ISnUserService;
import snsoft.comm.controller.SnBaseController;
import snsoft.comm.interceptor.SnBaseInterceptor;
/**
 * <p>��Ŀ���⣺ </p>
 * <p>��Ŀ���ܣ� </p>
 * <p>����ģ�飺 </p>
 * <p>����ƽ̨��Window10</p>
 * <p>�������ߣ�Eclipse</p>
 * <p>jar��:TODO</p>
 * <p>�������ڣ�2018��5��24�� ����10:45:51</p>
 * <p>��Ŀ���ߣ�����</p>
 * <p>��ȫ����snsoft.admin.controller.SnAdminBaseController</p>
 * @version 1.0
 */
@Component
public class SnAdminBaseController extends SnBaseController implements SnBaseInterceptor
{
	@Resource(name = "sn-SnUserService")
	private ISnUserService userService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
		/*
		 * �������ú���ø÷������������ݸ�����ҳ
		 */
		if (isLegalView(modelAndView))
		{
			if (redisService.get("uid") != null)
			{
				modelAndView.addObject("currentUser", userService.selectById((Long) redisService.get("uid")));
			}
		}
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		// TODO Auto-generated method stub
	}
}
