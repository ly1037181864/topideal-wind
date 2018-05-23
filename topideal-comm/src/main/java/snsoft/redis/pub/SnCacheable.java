package snsoft.redis.pub;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>项目标题： TODO</p>
 * <p>项目功能： </p>
 * <p>所属模块： TODO</p>
 * <p>开发平台：Window10</p>
 * <p>开发工具：Eclipse</p>
 * <p>jar包:TODO</p>
 * <p>创建日期：2018年3月22日 下午5:00:18</p>
 * <p>项目作者：刘友</p>
 * <p>类全名：snsoft.wind.redis.SnCacheable</p>
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SnCacheable {
	public enum KeyMode{  
        DEFAULT,    //只有加了@SnCacheKey的参数,才加入key后缀中  
        BASIC,      //只有基本类型参数,才加入key后缀中,如:String,Integer,Long,Short,Boolean  
        ALL;        //所有参数都加入key后缀  
    } 
	public String key() default "";     //缓存key  
    public KeyMode keyMode() default KeyMode.DEFAULT;       //key的后缀模式  
    public int expire() default 0;      //缓存多少秒,默认无限期  
}
