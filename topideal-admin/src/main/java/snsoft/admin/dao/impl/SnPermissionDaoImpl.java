package snsoft.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import snsoft.admin.dao.ISnPermissionDao;
import snsoft.admin.entity.SnPermission;
import snsoft.comm.dao.impl.SnSuperDaoImpl;

/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?14�? 下午7:17:51</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.wind.dao.impl.SnPermissionDaoImpl</p>
 * @version 1.0
 */
@Repository("sn-SnPermissionDao")
public class SnPermissionDaoImpl extends SnSuperDaoImpl implements ISnPermissionDao
{


	public List<SnPermission> selectAllByUserId(Long userId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public SnPermission query(Long id)
	{
		Session session = getSession();
		try
		{
			return session.get(SnPermission.class, String.valueOf(id));
		} finally
		{
			close();
		}
	}

	public SnPermission query(SnPermission per)
	{
		return null;
	}

	public SnPermission query(String fitler, Map<String, Object> params)
	{
		if (fitler != null && params != null && params.size() > 0)
		{
			String hql = "from SnPermission where 1=1 and " + fitler;
			Session session = getSession();
			try
			{
				Query query = session.createQuery(hql);
				for (String key : params.keySet())
				{
					query.setParameter(key, params.get(key));
				}
				return (SnPermission) query.getSingleResult();
			} finally
			{
				close();
			}
		}
		return null;
	}

	public void save(SnPermission per)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.save(per);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void delete(SnPermission per)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.delete(per);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void update(SnPermission per)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.update(per);
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

	public List<SnPermission> queryByPage(int index, int size)
	{
		String hql = "from SnPermission";
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

	public List<SnPermission> loadAll()
	{
		String hql = "from SnPermission";
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
