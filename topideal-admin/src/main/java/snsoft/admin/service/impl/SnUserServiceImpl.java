package snsoft.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import snsoft.admin.dao.ISnUserDao;
import snsoft.admin.entity.SnUser;
import snsoft.admin.service.ISnUserService;

/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?7�? 上午9:46:22</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.springwind.service.impl.SnUserServiceImpl</p>
 * @version 1.0
 */
@Service("sn-SnUserService")
public class SnUserServiceImpl implements ISnUserService
{
	@Resource(name = "sn-SnUserDao")
	ISnUserDao userDao;

	public SnUser selectByLoginName(String loginName)
	{
		String fitler = "loginName=:loginName";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginName", loginName);
		return userDao.query(fitler, params);
	}

	public void deleteUser(Long userId)
	{
		SnUser user = new SnUser();
		user.setId(userId);
		userDao.delete(user);
	}

	public SnUser selectById(Long userId)
	{
		return userDao.query(userId);
	}

	public void insert(SnUser user)
	{
		userDao.save(user);
	}

	public void update(SnUser user)
	{
		userDao.update(user);
	}

	public List<SnUser> queryByPage(int index, int size)
	{
		return userDao.queryByPage(index, size);
	}
}
