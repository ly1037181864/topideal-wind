package snsoft.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * <p>��Ŀ���⣺ </p>
 * <p>��Ŀ���ܣ� </p>
 * <p>����ģ�飺 </p>
 * <p>����ƽ̨��Window10</p>
 * <p>�������ߣ�Eclipse</p>
 * <p>jar��:TODO</p>
 * <p>�������ڣ�2018��5��23�� ����4:11:28</p>
 * <p>��Ŀ���ߣ�����</p>
 * <p>��ȫ����snsoft.admin.controller.SnIndexController</p>
 * @version 1.0
 */
@Controller
public class SnIndexController extends SnAdminBaseController
{
	/**
	 * ��ҳ
	 */
	@RequestMapping("/index")
	public String index(Model model)
	{
		return "/index";
	}

	/**
	 * ��ҳ
	 */
	@RequestMapping("/home")
	public String home()
	{
		return "/home";
	}

	/**
	 * SW ����
	 */
	@RequestMapping("/donate")
	public String donate()
	{
		return "/donate";
	}
}
