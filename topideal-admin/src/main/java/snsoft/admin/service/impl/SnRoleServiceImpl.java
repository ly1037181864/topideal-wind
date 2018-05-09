package snsoft.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import snsoft.admin.dao.ISnRoleDao;
import snsoft.admin.entity.SnRole;
import snsoft.admin.service.ISnRoleService;

/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?6�? 下午8:59:36</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.springwind.service.impl.SnRoleServiceImpl</p>
 * @version 1.0
 */
@Service("sn-SnRoleService")
public class SnRoleServiceImpl implements ISnRoleService
{
	@Resource(name = "sn-SnRoleDao")
	private ISnRoleDao roleDao;

	public List<SnRole> queryByPage(int index, int size)
	{
		return roleDao.queryByPage(index, size);
	}

	public void deleteById(Long id)
	{
		SnRole role = new SnRole();
		role.setId(id);
		roleDao.delete(role);
	}

	public void update(SnRole role)
	{
		roleDao.update(role);
	}

	public void insert(SnRole role)
	{
		roleDao.save(role);
	}

	public SnRole selectById(Long id)
	{
		return roleDao.query(id);
	}

	public List<SnRole> loadAll()
	{
		return roleDao.loadAll();
	}
}
