package snsoft.admin.service;

import java.util.List;

import snsoft.admin.entity.SnRole;


/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?6�? 下午8:37:45</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.springwind.service.ISnRoleService</p>
 * @version 1.0
 */
public interface ISnRoleService
{
	public List<SnRole> queryByPage(int index, int size);

	public void deleteById(Long id);

	public void update(SnRole role);

	public void insert(SnRole role);

	public SnRole selectById(Long id);

	public List<SnRole> loadAll();
}
