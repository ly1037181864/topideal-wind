package snsoft.admin.service;

import java.util.List;

import snsoft.admin.entity.SnPermission;


/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?6�? 下午8:40:14</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.springwind.service.ISnPermissionService</p>
 * @version 1.0
 */
public interface ISnPermissionService
{
	//List<SnMenuVO> selectMenuVOByUserId(Long userId);

	List<SnPermission> selectAllByUserId(Long userId);

	/**
	 * <p>是否为可操作的权�?</p>
	 * @param token SSO 票据顶级父类
	 * @param permission 操作权限编码
	 * @return
	 */
	// boolean isActionable(Token token, String permission);
	public void deleteById(Long id);

	public List<SnPermission> queryByPage(int index, int size);

	public List<SnPermission> loadAll();
}
