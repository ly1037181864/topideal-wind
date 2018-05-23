package snsoft.redis.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import snsoft.redis.service.ISnRedisService;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2018年3月26日 下午4:58:49</p>
 * <p>类全名：snsoft.wind.service.impl.SnRedisServiceImpl</p>
 * 作者：liuyou
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Service("sn-SnRedisService")
public class SnRedisServiceImpl implements ISnRedisService
{
	@Autowired
	private RedisTemplate redisTemplate;

	public void expire(String key, long time)
	{
		try
		{
			if (time > 0)
			{
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			if (time <= 0)
			{
				expire(key);
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void expire(String key)
	{
		try
		{
			redisTemplate.expire(key, TIMEOUT, TimeUnit.SECONDS);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public long getExpire(String key)
	{
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	public boolean hasKey(String key)
	{
		try
		{
			return redisTemplate.hasKey(key);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void del(String... key)
	{
		if (key != null && key.length > 0)
		{
			if (key.length == 1)
			{
				redisTemplate.delete(key[0]);
			} else
			{
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

	public Object get(String key)
	{
		return key == null ? null : redisTemplate.opsForValue().get(key);
	}

	public void set(String key, Object value)
	{
		try
		{
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void set(String key, Object value, long time)
	{
		try
		{
			if (time > 0)
			{
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			} else
			{
				set(key, value);
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public long incr(String key, long delta)
	{
		if (delta <= 0)
		{
			throw new RuntimeException("递增因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}

	public long decr(String key, long delta)
	{
		if (delta <= 0)
		{
			throw new RuntimeException("递减因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, -delta);
	}

	public Object hget(String key, String item)
	{
		return redisTemplate.opsForHash().get(key, item);
	}

	public Map<Object, Object> hmget(String key)
	{
		return redisTemplate.opsForHash().entries(key);
	}

	public void hmset(String key, Map<String, Object> map)
	{
		try
		{
			redisTemplate.opsForHash().putAll(key, map);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void hmset(String key, Map<String, Object> map, long time)
	{
		try
		{
			redisTemplate.opsForHash().putAll(key, map);
			expire(key, time);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public boolean hset(String key, String item, Object value)
	{
		try
		{
			redisTemplate.opsForHash().put(key, item, value);
			return true;
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public boolean hset(String key, String item, Object value, long time)
	{
		try
		{
			redisTemplate.opsForHash().put(key, item, value);
			expire(key, time);
			return true;
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void hdel(String key, Object... item)
	{
		redisTemplate.opsForHash().delete(key, item);
	}

	public boolean hHasKey(String key, String item)
	{
		return redisTemplate.opsForHash().hasKey(key, item);
	}

	public double hincr(String key, String item, double by)
	{
		return redisTemplate.opsForHash().increment(key, item, by);
	}

	public double hdecr(String key, String item, double by)
	{
		return redisTemplate.opsForHash().increment(key, item, -by);
	}

	public Set<Object> sGet(String key)
	{
		try
		{
			return redisTemplate.opsForSet().members(key);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void sHasKey(String key, Object value)
	{
		try
		{
			redisTemplate.opsForSet().isMember(key, value);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void sSet(String key, Object... values)
	{
		try
		{
			redisTemplate.opsForSet().add(key, values);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void sSetAndTime(String key, long time, Object... values)
	{
		try
		{
			redisTemplate.opsForSet().add(key, values);
			expire(key, time);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public long sGetSetSize(String key)
	{
		try
		{
			return redisTemplate.opsForSet().size(key);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void setRemove(String key, Object... values)
	{
		try
		{
			redisTemplate.opsForSet().remove(key, values);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public List<Object> lGet(String key, long start, long end)
	{
		try
		{
			return redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public long lGetListSize(String key)
	{
		try
		{
			return redisTemplate.opsForList().size(key);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public Object lGetIndex(String key, long index)
	{
		try
		{
			return redisTemplate.opsForList().index(key, index);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void lSet(String key, Object value)
	{
		try
		{
			redisTemplate.opsForList().rightPush(key, value);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void lSet(String key, Object value, long time)
	{
		try
		{
			redisTemplate.opsForList().rightPush(key, value);
			expire(key, time);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void lSet(String key, List<Object> value)
	{
		try
		{
			redisTemplate.opsForList().rightPushAll(key, value);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void lSet(String key, List<Object> value, long time)
	{
		try
		{
			redisTemplate.opsForList().rightPushAll(key, value);
			expire(key, time);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void lUpdateIndex(String key, long index, Object value)
	{
		try
		{
			redisTemplate.opsForList().set(key, index, value);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void lRemove(String key, long count, Object value)
	{
		try
		{
			redisTemplate.opsForList().remove(key, count, value);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
