package cn.xp.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author jozdoo
 * @company 杭州尚尚签网络科技有限公司
 * @date 16/9/2
 * @since 3.0
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static String redisCode = "utf-8";

    public void set(String key, String value, Integer expireTime) {
        redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
            StringRedisConnection connection = (StringRedisConnection) redisConnection;
            // TTL设置为0会报错
            connection.set(key, value, Expiration.from(expireTime <=0 ? 1 : expireTime, TimeUnit.SECONDS),
                    RedisStringCommands.SetOption.UPSERT);
            return null;
        });
    }
    public void set(String key, byte[] value, Integer expireTime) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) redisConnection;
                connection.set(key.getBytes(), value, Expiration.from(expireTime<=0?0:expireTime, TimeUnit.SECONDS),
                        RedisStringCommands.SetOption.UPSERT);
                return null;
            }
        });
    }

    public String get(String key) {
        String execute = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) redisConnection;
                return connection.get(key);
            }
        });
        return execute;
    }
    public byte[] getByte(String key) {
        byte[] execute = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) redisConnection;
                return connection.get(key.getBytes());
            }
        });
        return execute;
    }

    public void delete(String key){
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) redisConnection;
                connection.del(key);
                return null;
            }
        });
    }

    public Boolean hasKey(String key){
        Boolean execute = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) redisConnection;
                return connection.get(key)!=null;
            }
        });
        return execute;
    }

    public Set<Object> getList(String key){
        return redisTemplate.opsForHash().keys(key);
    }

}