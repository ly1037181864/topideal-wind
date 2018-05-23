package snsoft.redis.pub;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import snsoft.redis.service.ISnRedisService;


/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2018年3月26日 下午3:33:02</p>
 * <p>类全名：snsoft.wind.redis.SnRedisAspect</p>
 * 作者：liuyou
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Order(1)
@Component
@Aspect
public class SnRedisAspect
{
	@Resource(name = "sn-SnRedisService")
	ISnRedisService redisService;

	@Pointcut("@annotation(SnCacheable))")
	public void pointCut()
	{
		System.out.println("进入Controller组件处理");
	}

	@AfterReturning(pointcut = "pointCut()", returning = "returnValue")
	public void excuteRedis(JoinPoint point, Object returnValue)
	{
		try
		{
			Method method = getMethod(point);
			// 获取注解对象
			SnCacheable cacheable = method.getAnnotation(SnCacheable.class);
			String key = cacheable.key();
			int expire = cacheable.expire();
			// 设置缓存失效时间
			redisService.expire(key, expire);
			// 设置缓存
			redisService.set(key, returnValue);
			System.out.println("redis缓存成功：" + redisService.get(key));
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	@Before(value = "pointCut()")
	public void checkLogin(JoinPoint point)
	{
		try
		{

		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @Title: getMethod
	 * @Description: 获取被拦截方法对象
	 * @param joinPoint
	 * @return
	 */
	protected Method getMethod(JoinPoint joinPoint) throws Exception
	{
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		return method;
	}
}
