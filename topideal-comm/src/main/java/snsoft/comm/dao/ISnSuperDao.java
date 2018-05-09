package snsoft.comm.dao;

import java.util.List;
import java.util.Map;


/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?14�? 下午7:36:37</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.wind.dao.ISnSuperDao</p>
 * @version 1.0
 */
public interface ISnSuperDao<T>
{
	/**通过ID查询对象
	 * @param id
	 * @return
	 */
	T query(Long id);

	/**通过给定参数查询对象
	 * @param t
	 * @return
	 */
	T query(T t);

	/**给定条件查询对象
	 * @param fitler
	 * @param params
	 * @return
	 */
	T query(String fitler, Map<String, Object> params);

	/**增加用户
	 * @param user
	 */
	void save(T t);

	/**删除对象
	 * @param id
	 */
	void delete(T t);

	/**更新用户
	 * @param t
	 */
	void update(T t);

	/**更新用户
	 * @param params
	 */
	void update(Map<String, Object> params);

	/**
	 * 加载�?�?
	 * @return
	 */
	List<T> loadAll();
	
	public List<T> queryByPage(int index, int size);
}
