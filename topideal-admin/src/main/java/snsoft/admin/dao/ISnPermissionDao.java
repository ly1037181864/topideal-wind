package snsoft.admin.dao;

import java.util.List;

import snsoft.admin.entity.SnPermission;
import snsoft.comm.dao.ISnSuperDao;

/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?6�? 下午8:23:07</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.springwind.dao.ISnPermissionDao</p>
 * @version 1.0
 */
public interface ISnPermissionDao extends ISnSuperDao<SnPermission>
{
	//List<SnMenuVO> selectMenuByUserId(Long userId, Long pid);

	List<SnPermission> selectAllByUserId(Long userId);

}
