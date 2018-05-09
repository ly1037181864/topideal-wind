package snsoft.comm.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?14�? 下午7:49:40</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.wind.dao.impl.SnSuperDaoImpl</p>
 * @version 1.0
 */
@Component
public class SnSuperDaoImpl
{
	@Autowired
	private SessionFactory sessionFactory;
	public static final ThreadLocal<Session> local = new ThreadLocal<Session>();

	protected Session getSession()
	{
		Session session = sessionFactory.openSession();
		if (session == null)
		{
			throw new RuntimeException("初始化session异常！！�?");
		}
		local.set(session);
		return session;
	}

	protected void commit(boolean rollback)
	{
		Session session = local.get();
		if (session != null)
		{
			try
			{
				Transaction transaction = session.getTransaction();
				if (rollback)
				{
					transaction.rollback();
				} else
				{
					transaction.commit();
				}
			} finally
			{
				close();
			}
		}
	}

	protected void close()
	{
		Session session = local.get();
		if (session != null)
		{
			session.close();
		}
		local.remove();
	}
}
