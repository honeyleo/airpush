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
		Jedis jedis = pool.getResource();
		try {
			jedis.set(key, value);
		} finally {
			pool.returnResource(jedis);
		}
	}
	
	public void set(String key, String value, int expire) {
		Jedis jedis = pool.getResource();
		try {
			jedis.set(key, value);
			jedis.expire(key, expire);
		} finally {
			pool.returnResource(jedis);
		}
	}
	
	public String get(String key) {
		Jedis jedis = pool.getResource();
		try {
			String value = jedis.get(key);
			return value;
		} finally {
			pool.returnResource(jedis);
		}
		
	}
	
	public void hset(String key, String field, String value) {
		Jedis jedis = pool.getResource();
		try {
			jedis.hset(key, field, value);
		} finally {
			pool.returnResource(jedis);
		}
		
	}
	public void hset(String key, String field, String value, int expire) {
		Jedis jedis = pool.getResource();
		try {
			jedis.hset(key, field, value);
			jedis.expire(key, expire);
		} finally {
			pool.returnResource(jedis);
		}
		
	}
	public String hget(String key, String field) {
		Jedis jedis = pool.getResource();
		try {
			String value = jedis.hget(key, field);
			return value;
		} finally {
			pool.returnResource(jedis);
		}
		
	}
	public Map<String,String> hgetAll(String key) {
		Jedis jedis = pool.getResource();
		try {
			return jedis.hgetAll(key);
		} finally {
			pool.returnResource(jedis);
		}
	}
	public static void main(String args[]) {
		Cache cache = Cache.getInstance();
		cache.hset("app", "1", "测试");
		cache.hset("app", "2", "融网汇智");
		String value = cache.hget("app", "1");
		System.out.println(value);
		value = cache.hget("app", "2");
		System.out.println(value);
		Map<String,String> map = cache.hgetAll("app");
		System.out.println(map);
	}
}
