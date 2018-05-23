package snsoft.admin.constant.enums;

import com.baomidou.framework.common.IEnum;
/**
 * <p>项目标题： </p>
 * <p>项目功能： </p>
 * <p>所属模块： </p>
 * <p>开发平台：Window10</p>
 * <p>开发工具：Eclipse</p>
 * <p>jar包:TODO</p>
 * <p>创建日期：2018年5月23日 下午3:45:26</p>
 * <p>项目作者：刘友</p>
 * <p>类全名：snsoft.wind.constant.enums.SnPermissionType</p>
 * @version 1.0
 */
public enum SnPermissionType implements IEnum
{
	MENU(0, "鑿滃崟"), OPERATION(1, "鍔熻兘");
	private final int		key;
	private final String	desc;

	SnPermissionType(final int key, final String desc)
	{
		this.key = key;
		this.desc = desc;
	}

	public int key()
	{
		return this.key;
	}

	public String desc()
	{
		return this.desc;
	}
}
