package snsoft.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import snsoft.admin.dao.ISnUserDao;
import snsoft.admin.entity.SnUser;
import snsoft.comm.dao.impl.SnSuperDaoImpl;


/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?14�? 下午7:13:57</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.wind.dao.SnUserDaoImpl</p>
 * @version 1.0
 */
@Repository("sn-SnUserDao")
public class SnUserDaoImpl extends SnSuperDaoImpl implements ISnUserDao
{

	public SnUser query(Long id)
	{
		Session session = getSession();
		try
		{
			return session.get(SnUser.class, id);
		} finally
		{
			close();
		}
	}

	public SnUser query(SnUser user)
	{
		return null;
	}

	public SnUser query(String fitler, Map<String, Object> params)
	{
		if (fitler != null && params != null && params.size() > 0)
		{
			String hql = "from SnUser where 1=1 and " + fitler;
			Session session = getSession();
			try
			{
				Query query = session.createQuery(hql);
				for (String key : params.keySet())
				{
					query.setParameter(key, params.get(key));
				}
				return (SnUser) query.getSingleResult();
			} finally
			{
				close();
			}
		}
		return null;
	}

	public void save(SnUser user)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.save(user);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void delete(SnUser user)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.delete(user);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void update(SnUser user)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.update(user);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void update(Map<String, Object> params)
	{
	}

	public List<SnUser> loadAll()
	{
		String hql = "from SnUser";
		Session session = getSession();
		try
		{
			Query query = session.createQuery(hql);
			return query.list();
		} finally
		{
			close();
		}
	}

	public List<SnUser> queryByPage(int index, int size)
	{
		String hql = "from SnUser";
		Session session = getSession();
		try
		{
			Query query = session.createQuery(hql);
			//设置分页位置
			query.setFirstResult(index);
			query.setMaxResults(size);
			return query.list();
		} finally
		{
			close();
		}
	}
}
