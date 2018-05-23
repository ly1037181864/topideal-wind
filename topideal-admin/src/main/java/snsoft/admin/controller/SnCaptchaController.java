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
 * <p>项目标题： </p>
 * <p>项目功能： </p>
 * <p>所属模块： </p>
 * <p>开发平台：Window10</p>
 * <p>开发工具：Eclipse</p>
 * <p>jar包:TODO</p>
 * <p>创建日期：2018年5月23日 下午3:55:49</p>
 * <p>项目作者：刘友</p>
 * <p>类全名：snsoft.admin.controller.SnCaptchaController</p>
 * @version 1.0
 */
@Controller
@RequestMapping("/captcha")
public class SnCaptchaController extends SnBaseController
{
	/**
	 * 生成图片
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/image")
	@SnCacheable(key = "captcha", expire = 60)
	public String image() throws IOException
	{
		// 生成验证码图片
		Object[] objs = SnImageUtil.createImage();
		// 将验证码存入session
		HttpSession session = request.getSession();
		session.setAttribute("imgcode", objs[0]);
		// 将图片输出给浏览器
		response.setContentType("image/png");
		// 通过response获取的流，是输出给浏览器的。
		OutputStream os = response.getOutputStream();
		ImageIO.write((BufferedImage) objs[1], "png", os);
		os.close();
		return (String) objs[0];
	}
}
