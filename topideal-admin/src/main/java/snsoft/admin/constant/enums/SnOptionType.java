package snsoft.admin.constant.enums;

/**
 * <p>项目标题�? TODO</p>
 * <p>项目功能�? </p>
 * <p>�?属模块： TODO</p>
 * <p>�?发平台：Window10</p>
 * <p>�?发工具：Eclipse</p>
 * <p>jar�?:TODO</p>
 * <p>创建日期�?2018�?3�?7�? 上午10:00:01</p>
 * <p>项目作�?�：刘友</p>
 * <p>类全名：snsoft.springwind.common.enums.SnOptionType</p>
 * @version 1.0
 */
public enum SnOptionType
{
	OP_INSERT(1, "插入"), OP_EDIT(2, "更新");
	private final int op;
	private final String desc;

	SnOptionType(int op, String desc)
	{
		this.op = op;
		this.desc = desc;
	}

	public int getOp()
	{
		return op;
	}

	public String getDesc()
	{
		return desc;
	}
}
