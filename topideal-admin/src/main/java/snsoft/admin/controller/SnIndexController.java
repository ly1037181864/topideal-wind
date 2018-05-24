package snsoft.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * <p>项目标题： </p>
 * <p>项目功能： </p>
 * <p>所属模块： </p>
 * <p>开发平台：Window10</p>
 * <p>开发工具：Eclipse</p>
 * <p>jar包:TODO</p>
 * <p>创建日期：2018年5月23日 下午4:11:28</p>
 * <p>项目作者：刘友</p>
 * <p>类全名：snsoft.admin.controller.SnIndexController</p>
 * @version 1.0
 */
@Controller
public class SnIndexController extends SnAdminBaseController
{
	/**
	 * 首页
	 */
	@RequestMapping("/index")
	public String index(Model model)
	{
		return "/index";
	}

	/**
	 * 主页
	 */
	@RequestMapping("/home")
	public String home()
	{
		return "/home";
	}

	/**
	 * SW 捐赠
	 */
	@RequestMapping("/donate")
	public String donate()
	{
		return "/donate";
	}
}
