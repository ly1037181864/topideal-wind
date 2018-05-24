package snsoft.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import snsoft.admin.entity.SnPermission;
import snsoft.admin.entity.SnRole;
import snsoft.admin.service.ISnPermissionService;
import snsoft.admin.service.ISnRolePermissionService;
import snsoft.admin.service.ISnRoleService;
import snsoft.admin.service.ISnUserRoleService;
/**
 * <p>��Ŀ���⣺ </p>
 * <p>��Ŀ���ܣ� </p>
 * <p>����ģ�飺 </p>
 * <p>����ƽ̨��Window10</p>
 * <p>�������ߣ�Eclipse</p>
 * <p>jar��:TODO</p>
 * <p>�������ڣ�2018��5��23�� ����4:13:21</p>
 * <p>��Ŀ���ߣ�����</p>
 * <p>��ȫ����snsoft.admin.controller.SnRoleController</p>
 * @version 1.0
 */
@Controller
@RequestMapping("/perm/role")
public class SnRoleController extends SnAdminBaseController
{
	@Resource(name = "sn-SnRoleService")
	private ISnRoleService				roleService;
	@Resource(name = "sn-SnPermissionService")
	private ISnPermissionService		permissionService;
	@Resource(name = "sn-SnRolePermissionService")
	private ISnRolePermissionService	rolePermissionService;
	@Resource(name = "sn-SnUserRoleService")
	private ISnUserRoleService			userRoleService;

	@RequestMapping("/list")
	public String list(Model model)
	{
		return "/role/list";
	}

	@ResponseBody
	@RequestMapping("/getRoleList")
	public String getUserList()
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
		List<SnRole> roles = roleService.queryByPage(_index, _size);
		if (roles == null || roles.size() <= 0)
		{
			throw new RuntimeException("δ��ѯ����Ч����Ϣ");
		}
		JSONObject jo = new JSONObject();
		jo.put("total", roles.size());
		jo.put("rows", roles);
		return toJson(jo);
	}

	@ResponseBody
	@RequestMapping("/delete/{roleId}")
	public String delete(@PathVariable Long roleId)
	{
		boolean exist = userRoleService.existRoleUser(roleId);
		if (exist)
		{
			return "false";
		}
		roleService.deleteById(roleId);
		return "true";
	}

	@Permission("2002")
	@RequestMapping("/edit")
	public String edit(Model model, Long id)
	{
		if (id != null)
		{
			model.addAttribute("role", roleService.selectById(id));
		}
		return "/role/edit";
	}

	@ResponseBody
	@RequestMapping("/editRole")
	public String editRole(SnRole role)
	{
		boolean rlt = false;
		if (role != null)
		{
			if (role.getId() != null)
			{
				roleService.update(role);
			} else
			{
				roleService.insert(role);
			}
			rlt = true;
		}
		return rlt == true ? "true" : "false";
	}

	/**
	 * ��ת��Ȩ�޷���ҳ��
	 * @param model
	 * @param id
	 * @return
	 */
	@Permission("2003")
	@RequestMapping("/assigning")
	public String assigning(Model model, Long id)
	{
		model.addAttribute("roleId", id);
		return "/role/assigning";
	}

	/**
	 * ��ȡȨ����
	 * @param model
	 * @param roleId
	 * @return
	 */
	@Permission("2003")
	@RequestMapping("/right")
	@ResponseBody
	public String right(Model model, @RequestParam(value = "roleId", required = true) Long roleId)
	{
		List<SnPermission> list = permissionService.loadAll();
		List<Long> roleRightList = rolePermissionService.selecPermissionIdsByRoleId(roleId);
		List<Map<String,String>> rightList = new ArrayList<Map<String,String>>();
		for (SnPermission r : list)
		{
			Map<String,String> map = new HashMap<String,String>();
			map.put("id", r.getId().toString());
			map.put("pId", r.getPid().toString());
			map.put("name", r.getTitle());
			// Ĭ��չ����
			map.put("open", "true");
			// �����ɫ���и�Ȩ�ޣ���Ĭ��ѡ��
			if (roleRightList.contains(r.getId()))
			{
				map.put("checked", "true");
			}
			rightList.add(map);
		}
		return JSONObject.toJSONString(rightList);
	}

	/**
	 * ����Ȩ���б�
	 * @param right
	 * @param page
	 * @param rows
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("updateRoleRight")
	@ResponseBody
	public String updateRoleRight(HttpServletResponse response, HttpServletRequest request, @RequestParam(value = "roleId", required = false) Long roleId,
			@RequestParam(value = "rights", required = false) String rights) throws Exception
	{
		try
		{
			return "true";
		} catch (Exception e)
		{
			return "false";
		}
	}
}
