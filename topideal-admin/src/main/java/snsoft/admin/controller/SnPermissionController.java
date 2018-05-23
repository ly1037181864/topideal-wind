package snsoft.admin.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import snsoft.admin.entity.SnPermission;
import snsoft.admin.service.ISnPermissionService;
import snsoft.admin.service.ISnRolePermissionService;
import snsoft.comm.controller.SnBaseController;
/**
 * <p>��Ŀ���⣺ </p>
 * <p>��Ŀ���ܣ� </p>
 * <p>����ģ�飺 </p>
 * <p>����ƽ̨��Window10</p>
 * <p>�������ߣ�Eclipse</p>
 * <p>jar��:TODO</p>
 * <p>�������ڣ�2018��5��23�� ����4:12:19</p>
 * <p>��Ŀ���ߣ�����</p>
 * <p>��ȫ����snsoft.admin.controller.SnPermissionController</p>
 * @version 1.0
 */
@Controller
@RequestMapping("/perm/permission")
public class SnPermissionController extends SnBaseController
{
	@Resource(name = "sn-SnPermissionService")
	private ISnPermissionService		permissionService;
	@Resource(name = "sn-SnRolePermissionService")
	private ISnRolePermissionService	rolePermissionService;

	@RequestMapping("/list")
	public String list(Model model)
	{
		return "/permission/list";
	}

	@ResponseBody
	@RequestMapping("/getPermissionList")
	public String getPermissionList()
	{
		int _size = 10, _index = 0;
		if (request.getParameter("_size") != null)
		{
			_size = Integer.parseInt(request.getParameter("_size"));
		}
		if (request.getParameter("_index") != null)
		{
			_index = Integer.parseInt(request.getParameter("_index"));
		}
		List<SnPermission> sions = permissionService.queryByPage(_index, _size);
		if (sions == null || sions.size() <= 0)
		{
			throw new RuntimeException("δ��ѯ����Ч����Ϣ");
		}
		JSONObject jo = new JSONObject();
		jo.put("total", sions.size());
		jo.put("rows", sions);
		return toJson(jo);
	}

	@ResponseBody
	@RequestMapping("/delete/{permId}")
	public String delete(@PathVariable Long permId)
	{
		boolean exist = rolePermissionService.existRolePermission(permId);
		if (exist)
		{
			return "false";
		}
		permissionService.deleteById(permId);
		return "true";
	}
}
