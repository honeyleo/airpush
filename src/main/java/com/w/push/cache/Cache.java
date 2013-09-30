package com.w.push.cache;

import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Cache {

	private JedisPool pool = null;
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
		pool = new JedisPool(new JedisPoolConfig(), "localhost");
	}
	public void set(String key, String value) {
		Jedis jedis = null;
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
		Jedis jedis = null;
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
		Jedis jedis = null;
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
		Jedis jedis = null;
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
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			long ret = jedis.hset(key, field, value);
			if(ret == 0) {
				jedis.expire(key, expire);
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			pool.returnResource(jedis);
		}
		
	}
	public String hget(String key, String field) {
		Jedis jedis = null;
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
		Jedis jedis = null;
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
		Jedis jedis = null;
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
		Jedis jedis = null;
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
		cache.set("id_pushlog", "" + 0);
		long id = cache.incrBy("id_pushlog",3);
		System.out.println(id);
	}
}
