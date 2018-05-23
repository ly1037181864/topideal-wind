package snsoft.admin.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import snsoft.admin.util.SnImageUtil;
import snsoft.comm.controller.SnBaseController;
import snsoft.redis.pub.SnCacheable;
/**
 * <p>��Ŀ���⣺ </p>
 * <p>��Ŀ���ܣ� </p>
 * <p>����ģ�飺 </p>
 * <p>����ƽ̨��Window10</p>
 * <p>�������ߣ�Eclipse</p>
 * <p>jar��:TODO</p>
 * <p>�������ڣ�2018��5��23�� ����3:55:49</p>
 * <p>��Ŀ���ߣ�����</p>
 * <p>��ȫ����snsoft.admin.controller.SnCaptchaController</p>
 * @version 1.0
 */
@Controller
@RequestMapping("/captcha")
public class SnCaptchaController extends SnBaseController
{
	/**
	 * ����ͼƬ
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/image")
	@SnCacheable(key = "captcha", expire = 60)
	public String image() throws IOException
	{
		// ������֤��ͼƬ
		Object[] objs = SnImageUtil.createImage();
		// ����֤�����session
		HttpSession session = request.getSession();
		session.setAttribute("imgcode", objs[0]);
		// ��ͼƬ����������
		response.setContentType("image/png");
		// ͨ��response��ȡ�������������������ġ�
		OutputStream os = response.getOutputStream();
		ImageIO.write((BufferedImage) objs[1], "png", os);
		os.close();
		return (String) objs[0];
	}
}
