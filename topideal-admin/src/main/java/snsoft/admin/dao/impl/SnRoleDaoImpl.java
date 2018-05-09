package snsoft.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import snsoft.admin.dao.ISnRoleDao;
import snsoft.admin.entity.SnRole;
import snsoft.comm.dao.impl.SnSuperDaoImpl;


/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?14�? 下午7:17:23</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.wind.dao.impl.SnRoleDaoImpl</p>
 * @version 1.0
 */
@Repository("sn-SnRoleDao")
public class SnRoleDaoImpl extends SnSuperDaoImpl implements ISnRoleDao
{

	public SnRole query(Long id)
	{
		Session session = getSession();
		try
		{
			return session.get(SnRole.class, String.valueOf(id));
		} finally
		{
			close();
		}
	}

	public SnRole query(SnRole t)
	{
		return null;
	}

	public SnRole query(String fitler, Map<String, Object> params)
	{
		if (fitler != null && params != null && params.size() > 0)
		{
			String hql = "from SnRole where 1=1 and " + fitler;
			Session session = getSession();
			try
			{
				Query query = session.createQuery(hql);
				for (String key : params.keySet())
				{
					query.setParameter(key, params.get(key));
				}
				return (SnRole) query.getSingleResult();
			} finally
			{
				close();
			}
		}
		return null;
	}

	public void save(SnRole role)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.save(role);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void delete(SnRole role)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.delete(role);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void update(SnRole role)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.update(role);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void update(Map<String, Object> params)
	{
		// TODO Auto-generated method stub
	}

	public List<SnRole> queryByPage(int index, int size)
	{
		String hql = "from SnRole";
		Session session = getSession();
		try
		{
			Query query = session.createQuery(hql);
			//得到滚动结果�?
			ScrollableResults scroll = query.scroll();
			//滚动到最后一�?
			scroll.last();
			//设置分页位置
			query.setFirstResult(index);
			query.setMaxResults(size);
			return query.list();
		} finally
		{
			close();
		}
	}

	public List<SnRole> loadAll()
	{
		String hql = "from SnRole";
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
}
