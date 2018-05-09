package snsoft.admin.service;

import java.util.List;

/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?6�? 下午8:38:43</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.springwind.service.ISnRolePermissionService</p>
 * @version 1.0
 */
public interface ISnRolePermissionService
{
	/**
	 * <p>判断是否存在角色对应的权�?</p>
	 * @param permissionId 权限ID
	 * @return
	 */
	boolean existRolePermission(Long permissionId);

	/**
	 * 根据角色ID获取对应的所有关联权限的ID
	 * @param id
	 * @return
	 */
	List<Long> selecPermissionIdsByRoleId(Long id);
}
