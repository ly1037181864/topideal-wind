package snsoft.admin.constant.enums;

import com.baomidou.framework.common.IEnum;
/**
 * <p>��Ŀ���⣺ </p>
 * <p>��Ŀ���ܣ� </p>
 * <p>����ģ�飺 </p>
 * <p>����ƽ̨��Window10</p>
 * <p>�������ߣ�Eclipse</p>
 * <p>jar��:TODO</p>
 * <p>�������ڣ�2018��5��23�� ����3:45:26</p>
 * <p>��Ŀ���ߣ�����</p>
 * <p>��ȫ����snsoft.wind.constant.enums.SnPermissionType</p>
 * @version 1.0
 */
public enum SnPermissionType implements IEnum
{
	MENU(0, "菜单"), OPERATION(1, "功能");
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
