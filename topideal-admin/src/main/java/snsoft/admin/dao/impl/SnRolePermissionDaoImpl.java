package snsoft.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import snsoft.admin.dao.ISnRolePermissionDao;
import snsoft.admin.entity.SnRolePermission;
import snsoft.comm.dao.impl.SnSuperDaoImpl;


/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?14�? 下午7:16:26</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.wind.dao.impl.SnRolePermissionDaoImpl</p>
 * @version 1.0
 */
@Repository("sn-SnRolePermissionDao")
public class SnRolePermissionDaoImpl extends SnSuperDaoImpl implements ISnRolePermissionDao
{

	public List<Long> selecPermissionIdsByRoleId(Long id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public SnRolePermission query(Long id)
	{
		Session session = getSession();
		try
		{
			return session.get(SnRolePermission.class, String.valueOf(id));
		} finally
		{
			close();
		}
	}

	public SnRolePermission query(SnRolePermission rolePer)
	{
		return null;
	}

	public SnRolePermission query(String fitler, Map<String, Object> params)
	{
		if (fitler != null && params != null && params.size() > 0)
		{
			String hql = "from SnRolePermission where 1=1 and " + fitler;
			Session session = getSession();
			try
			{
				Query query = session.createQuery(hql);
				for (String key : params.keySet())
				{
					query.setParameter(key, params.get(key));
				}
				return (SnRolePermission) query.getSingleResult();
			} finally
			{
				close();
			}
		}
		return null;
	}

	public void save(SnRolePermission rolePer)
	{
		Session session = getSession();
		boolean rollback = true;
		try
		{
			session.save(rolePer);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void delete(SnRolePermission rolePer)
	{
		Session session = null;
		boolean rollback = true;
		try
		{
			session.delete(rolePer);
			rollback = false;
		} finally
		{
			commit(rollback);
		}
	}

	public void update(SnRolePermission rolePer)
	{
		Session session = null;
		boolean rollback = true;
		try
		{
			session.update(rolePer);
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

	public List<SnRolePermission> loadAll()
	{
		String hql = "from SnRolePermission";
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

	public List<SnRolePermission> queryByPage(int index, int size) {
		// TODO Auto-generated method stub
		return null;
	}
}
