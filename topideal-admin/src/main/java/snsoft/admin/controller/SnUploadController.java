package snsoft.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.framework.common.util.DateUtil;
import com.baomidou.framework.upload.UploadFile;
import com.baomidou.framework.upload.UploadMsg;
import com.baomidou.framework.upload.UploadMultipartRequest;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
/**
 * <p>��Ŀ���⣺ </p>
 * <p>��Ŀ���ܣ� </p>
 * <p>����ģ�飺 </p>
 * <p>����ƽ̨��Window10</p>
 * <p>�������ߣ�Eclipse</p>
 * <p>jar��:TODO</p>
 * <p>�������ڣ�2018��5��23�� ����4:16:33</p>
 * <p>��Ŀ���ߣ�����</p>
 * <p>��ȫ����snsoft.admin.controller.SnUploadController</p>
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/upload")
public class SnUploadController extends SnAdminBaseController
{
	/* ��������ϴ� 3M */
	private final static int MAX_POST_SIZE = 3 * 1024 * 1024;

	/**
	 * <p>
	 * �ϴ��ļ�<br>  ����ʾ demo �ϴ�����ļ������ڵ�ǰ��Ŀ�ĸ�Ŀ¼��
	 * </p>
	 */
	@ResponseBody
	@Permission(action = Action.Skip)
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public String file()
	{
		UploadMsg msg = new UploadMsg();
		try
		{
			UploadMultipartRequest umr = new UploadMultipartRequest(request, getSaveDir(), MAX_POST_SIZE);
			umr.setFileHeaderExts("ffd8ff.jpg");
			umr.upload();
			Enumeration<?> files = umr.getFileNames();
			while (files.hasMoreElements())
			{
				String name = (String) files.nextElement();
				UploadFile cf = umr.getUploadFile(name);
				if (cf != null)
				{
					/**
					 * �ϴ��ɹ�
					 */
					if (cf.isSuccess())
					{
						msg.setSuccess(true);
						msg.setUrl(cf.getFileUrl());
						msg.setSize(cf.getSize());
						System.err.println("�ϴ��ļ���ַ��" + msg.getUrl());
					}
					msg.setMsg(cf.getUploadCode().desc());
				}
			}
		} catch (IOException e)
		{
			logger.error("uploadFile error. ", e);
		}
		return toJson(msg);
	}

	/**
	 * <p>
	 * �ϴ��ļ����Ŀ¼
	 * </p>
	 */
	private static String getSaveDir()
	{
		StringBuffer filePath = new StringBuffer(System.getProperty("user.dir"));
		filePath.append(File.separator);
		filePath.append(DateUtil.format(new Date(), "yyyy"));
		filePath.append(File.separator);
		File file = new File(filePath.toString());
		if (!file.exists())
		{
			file.mkdirs();
		}
		return file.getPath();
	}
}
