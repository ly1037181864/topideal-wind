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
 * <p>项目标题： </p>
 * <p>项目功能： </p>
 * <p>所属模块： </p>
 * <p>开发平台：Window10</p>
 * <p>开发工具：Eclipse</p>
 * <p>jar包:TODO</p>
 * <p>创建日期：2018年5月24日 上午10:45:51</p>
 * <p>项目作者：刘友</p>
 * <p>类全名：snsoft.admin.controller.SnAdminBaseController</p>
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
		 * 方法调用后调用该方法，返回数据给请求页
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
