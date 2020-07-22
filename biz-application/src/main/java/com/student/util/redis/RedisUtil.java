package com.student.util.redis;

import com.student.service.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

public  class RedisUtil {

    private static JedisPool jedisPool;

    public static void setJedisPool(JedisPool jedisPool) {
        RedisUtil.jedisPool = jedisPool;
    }
    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    public static String  redisGet(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            close(jedis);
        }
    }

    public static void  redisPut(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, val);
        } finally {
            close(jedis);
        }
    }

    private static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
    /**
     * 添加一条记录 如果map_key存在 则更新value
     * hset 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果字段已经存在于哈希表中，旧值将被覆盖
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static long  set(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        long returnStatus = jedis.hset(key, field, value);
        close(jedis);
        return returnStatus;
    }

    /**
     * 批量添加记录
     * hmset 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * 此命令会覆盖哈希表中已存在的域。
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     *
     * @param key
     * @param map
     * @return
     */
    public static String  setAll( String key, Map<String, String> map) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.hmset(key, map);
            return result;
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 删除hash中 field对应的值
     * hdel 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略
     *
     * @param key
     * @param field
     * @return
     */
    public static Long  delete(String key, String... field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long returnStatus = jedis.hdel(key, field);
            return returnStatus;
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 获取hash中 指定的field的值
     * hmget 返回哈希表 key 中，一个或多个给定域的值。
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
     * 不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
     *
     * @param key
     * @param field
     * @return
     */
    public  List<String> get( String key, String... field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> result = jedis.hmget(key, field);
            return result;
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 获取hash中 所有的field value
     *
     * @param key
     * @return 在返回值里，紧跟每个字段名(field name)之后是字段的值(value)，所以返回值的长度是哈希表大小的两倍。
     */
    public static  Map<String, String> getAll( String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Map<String, String> result = jedis.hgetAll(key);
            return result;
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 判断hash中 指定的field是否存在
     *
     * @param key
     * @param field
     * @return 如果哈希不包含字段或key不存在 返回0，如果哈希包含字段 返回1
     */
    public static boolean ifExist( String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            boolean result = jedis.hexists(key, field);
            return result;
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
        return false;
    }

    /**
     * 获取hash 的size
     * hlen 获取哈希表中字段的数量

     * @param key
     * @return
     */
    public static Long  size(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long size = jedis.hlen(key);
            return size;
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 获取key的数量
     * hlen 获取哈希表中字段的数量
     * @param key
     * @return
     */
    public static Integer keySize(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Set<String> keys = jedis.keys(key);
            return keys.size();
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 往List中插入数据
     * @param key
     * @param value
     * @return
     */
    public static Long setList(String key,String value){
      Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long size= jedis.rpush(key,value);
            return size;
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 根据start,end获取list中的值
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static List<String> getList(String key,Long start,Long end){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> list = jedis.lrange(key, start, end);
            return list;
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 获得SortSet中的元素数量
     * @param key
     * @return
     */
    public static Long getSortSetSize(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long size = jedis.zcard(key);
            return size;
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 根据key删除一条hash
     * @param key
     */
    public static void deleteHash(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
    }

    /**
     * 从列表中删除value这一条数据
     * @param key
     * @param value
     */
    public static void deleteList(String key,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lrem(key,0,value);
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
    }

    /**
     * 往SortSet在添加数据，score为分数，影响排序
     * @param name
     * @param score
     * @param value
     */
    public static void setSortSet(String name,Integer score,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.zadd(name,score,value);
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
    }

    /**
     * 根据start,end从SortSet中获取数据
     * @param name
     * @param start
     * @param end
     * @return
     */
    public static Set<String> getSortSet(String name,Long start,Long end){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Set<String> set=jedis.zrevrange(name,start,end);
            return set;
        } catch (Exception e) {
            logger.info("getSortSet出错"+e.getMessage(),e);
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 根据value从SortSet中删除
     * @param name
     * @param value
     */
    public static void removeSortSet(String name,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.zrem(name,value);
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
    }

}
