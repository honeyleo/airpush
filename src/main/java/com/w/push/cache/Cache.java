package com.w.push.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class Cache {

	ShardedJedisPool pool;
	
	private Cache() {
		init();
	}
	
	public static Cache getInstance() {
		return SingletonHolder.cache;
	}
	
	public static class SingletonHolder {
		private static Cache cache = new Cache();
	}
	
	private void init() {
		ResourceBundle bundle = ResourceBundle.getBundle("redis");  
	    if (bundle == null) {  
	        throw new IllegalArgumentException(  
	                "[redis.properties] is not found!");  
	    }  
	    JedisPoolConfig config = new JedisPoolConfig();  
	    config.setMaxActive(Integer.valueOf(bundle  
	            .getString("redis.pool.maxActive")));  
	    config.setMaxIdle(Integer.valueOf(bundle  
	            .getString("redis.pool.maxIdle")));  
	    config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));  
	    config.setTestOnBorrow(Boolean.valueOf(bundle  
	            .getString("redis.pool.testOnBorrow")));  
	    config.setTestOnReturn(Boolean.valueOf(bundle  
	            .getString("redis.pool.testOnReturn")));  
	    
		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
		list.add(new JedisShardInfo(bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port"))));
		pool = new ShardedJedisPool(config, list);
	}
	
	public void set(String key, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key, value);
		} catch(Exception e){
			System.err.println(e.getMessage());
		} finally {
			pool.returnResource(jedis);
		}
	}
	
	public void set(String key, String value, int expire) {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key, value);
			jedis.expire(key, expire);
		} catch(Exception e){
			System.err.println(e.getMessage());
		} finally {
			pool.returnResource(jedis);
		}
	}
	
	public String get(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			String value = jedis.get(key);
			return value;
		} catch(Exception e){
			System.err.println(e.getMessage());
		} finally {
			pool.returnResource(jedis);
		}
		return null;
	}
	
	public void hset(String key, String field, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hset(key, field, value);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			pool.returnResource(jedis);
		}
		
	}
	public void hset(String key, String field, String value, int expire) {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hset(key, field, value);
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} finally {
			pool.returnResource(jedis);
		}
		
	}
	public String hget(String key, String field) {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			String value = jedis.hget(key, field);
			return value;
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			pool.returnResource(jedis);
		}
		return null;
	}
	public Map<String,String> hgetAll(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.hgetAll(key);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			pool.returnResource(jedis);
		}
		return null;
	}
	
	public long incr(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.incr(key);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			pool.returnResource(jedis);
		}
		return 0;
	}
	
	public long incrBy(String key, long integer) {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.incrBy(key, integer);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			pool.returnResource(jedis);
		}
		return 0;
	}
	
	public long expire(String key, int seconds) {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.expire(key, seconds);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			pool.returnResource(jedis);
		}
		return 0;
	}
	public final static int ONE_DAY = 60 * 60 * 24;
	
	public final static int ONE_HOURS = 60 * 60;
	
	public final static int TEN_MINUTE = 60 * 10;
	
	public final static int FIVE_MINUTE = 60 * 5;
	
	public final static int THREE_MINUTE = 60 * 3;
	
	public static void main(String args[]) {
		Cache cache = Cache.getInstance();
//		cache.hset("app", "1", "测试");
//		cache.hset("app", "2", "融网汇智");
//		String value = cache.hget("app", "1");
//		System.out.println(value);
//		value = cache.hget("app", "2");
//		System.out.println(value);
//		Map<String,String> map = cache.hgetAll("app");
//		System.out.println(map);
//		cache.hset(Key.KEY_CRITERIA + 1, "" + 13, "{id:13}",20000);
//		cache.hset(Key.KEY_CRITERIA + 1, "" + 14, "{id:14}",20000);
//		map = cache.hgetAll("criteria_1");
//		System.out.println(map);
//		System.out.println(cache.hget(Key.KEY_CRITERIA + 1, "13"));
//		System.out.println(cache.hget(Key.KEY_CRITERIA + 1, "14"));
//		cache.set("id_pushlog", "" + 1);
//		cache.hset("id_pushlog-1", "1", "1", Cache.THREE_MINUTE);
//		cache.hset("id_pushlog-1", "2", "2", Cache.THREE_MINUTE);
//		cache.expire("id_pushlog-1", 2);
		Map<String,String> map = cache.hgetAll("id_pushlog-1");
		System.out.println(map);
		String value = cache.hget("id_pushlog-1","1");
		System.out.println(value);
		value = cache.hget("id_pushlog-1","2");
		System.out.println(value);
	}
}
