package snsoft.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import snsoft.admin.dao.ISnUserRoleDao;
import snsoft.admin.entity.SnUserRole;
import snsoft.comm.dao.impl.SnSuperDaoImpl;


/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?14�? 下午7:15:24</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.wind.dao.impl.SnUserRoleDaoImpl</p>
 * @version 1.0
 */
@Repository("sn-SnUserRoleDao")
public class SnUserRoleDaoImpl extends SnSuperDaoImpl implements ISnUserRoleDao
{
	public SnUserRole query(Long id)
	{
		Session session = getSession();
		try
		{
			return session.get(SnUserRole.class, String.valueOf(id));
		} finally
		{
			close();
		}
	}

	public SnUserRole query(SnUserRole userRole)
	{
		return null;
	}

	public SnUserRole query(String fitler, Map<String, Object> params)
	{
		if (fitler != null && params != null && params.size() > 0)
		{
			String hql = "from SnUserRole where 1=1 and " + fitler;
			Session session = getSession();
			try
			{
				Query query = session.createQuery(hql);
				for (String key : params.keySet())
				{
					query.setParameter(key, params.get(key));
				}
				return (SnUserRole) query.getSingleResult();
			} finally
			{
				close();
			}
		}
		return null;
	}

	public void save(SnUserRole userRole)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.save(userRole);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void delete(SnUserRole userRole)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.delete(userRole);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void update(SnUserRole userRole)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.update(userRole);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void update(Map<String, Object> params)
	{
	}

	public List<SnUserRole> loadAll()
	{
		String hql = "from SnUserRole";
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

	public List<SnUserRole> queryByPage(int index, int size) 
	{
		return null;
	}

}
