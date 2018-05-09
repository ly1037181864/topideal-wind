package snsoft.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import snsoft.admin.dao.ISnPermissionDao;
import snsoft.admin.entity.SnPermission;
import snsoft.admin.service.ISnPermissionService;

/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?6�? 下午8:43:35</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.springwind.service.impl.SnPermissionServiceImpl</p>
 * @version 1.0
 */
@Service("sn-SnPermissionService")
public class SnPermissionServiceImpl implements ISnPermissionService
{
	@Resource(name = "sn-SnPermissionDao")
	ISnPermissionDao snPermissionDao;

	public List<SnPermission> selectAllByUserId(Long userId)
	{
		return null;
	}

	public void deleteById(Long id)
	{
		SnPermission sion = new SnPermission();
		sion.setId(id);
		snPermissionDao.delete(sion);
	}

	public List<SnPermission> queryByPage(int index, int size)
	{
		return snPermissionDao.queryByPage(index, size);
	}

	public List<SnPermission> loadAll()
	{
		return snPermissionDao.loadAll();
	}
}
