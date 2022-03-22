package com.mytest.utils.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands.Tuple;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Component
public class RedisService {
    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    /**
     * 将数据存入缓存
     */
    public void saveString(String key, String val) {

        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        vo.set(key, val);
    }

    /**
     * 将数据存入缓存的集合中
     */
    public void saveToSet(String key, String val) {

        SetOperations<String, String> so = redisTemplate.opsForSet();

        so.add(key, val);
    }

    /**
     * key 缓存Key
     */
    public String getFromSet(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 将 key的值保存为 value ，当且仅当 key 不存在。 若给定的 key 已经存在，则 SETNX 不做任何动作。 SETNX 是『SET if Not
     * eXists』(如果不存在，则 SET)的简写。 <br> 保存成功，返回 true <br> 保存失败，返回 false
     */
    public boolean saveNX(String key, String val) {

        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setNX(key.getBytes(), val.getBytes()));

    }

    /**
     * 将 key的值保存为 value ，当且仅当 key 不存在。 若给定的 key 已经存在，则 SETNX 不做任何动作。 SETNX 是『SET if Not
     * eXists』(如果不存在，则 SET)的简写。 <br> 保存成功，返回 true <br> 保存失败，返回 false
     *
     * @param expire 超时时间
     * @return 保存成功，返回 true 否则返回 false
     */
    public boolean saveNX(String key, String val, int expire) {

        boolean ret = saveNX(key, val);
        if (ret) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return ret;
    }

    /**
     * 将数据存入缓存（并设置失效时间）
     */
    public void saveString(String key, String val, int seconds) {

        redisTemplate.opsForValue().set(key, val, seconds, TimeUnit.SECONDS);
    }

    /**
     * 将自增变量存入缓存
     */
    public void saveSeq(String key, long seqNo) {

        redisTemplate.delete(key);
        redisTemplate.opsForValue().increment(key, seqNo);
    }

    /**
     * 将递增浮点数存入缓存
     */
    public void saveFloat(String key, float data) {

        redisTemplate.delete(key);
        redisTemplate.opsForValue().increment(key, data);
    }

    /**
     * 保存复杂类型数据到缓存
     */
    public void saveBean(String key, Object obj) {

        redisTemplate.opsForValue().set(key, JSON.toJSONString(obj));
    }

    /**
     * 保存复杂类型数据到缓存（并设置失效时间）
     */
    public void saveBean(String key, Object obj, int seconds) {

        redisTemplate.opsForValue()
                .set(key, JSON.toJSONString(obj), seconds, TimeUnit.SECONDS);
    }

    /**
     * 存到指定的队列中
     */
    public void saveToQueue(String key, String val, long size) {

        ListOperations<String, String> lo = redisTemplate.opsForList();

        if (size > 0 && lo.size(key) >= size) {
            lo.rightPop(key);
        }
        lo.leftPush(key, val);
    }

    /**
     * 保存到hash集合中
     *
     * @param hName 集合名
     */
    public void hashSet(String hName, String key, String value) {

        redisTemplate.opsForHash().put(hName, key, value);
    }

    /**
     * 从hash集合中删除
     *
     * @param hName 集合名
     */
    public void hashDel(String hName, String key) {
        if (key != null && redisTemplate.opsForHash().hasKey(hName, key)) {
            redisTemplate.opsForHash().delete(hName, key);
        }
    }

    /**
     * 根据key获取所以值
     */
    public Map<Object, Object> hgetAll(String key) {

        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 保存到hash集合中
     *
     * @param hName 集合名
     */
    public <T> void hashSet(String hName, String key, T t) {
        hashSet(hName, key, JSON.toJSONString(t));
    }

    /**
     * 取得复杂JSON数据
     */
    public <T> T getBean(String key, Class<T> clazz) {

        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        return JSON.parseObject(value, clazz);
    }

    /**
     * 从缓存中取得字符串数据
     *
     * @return 数据
     */
    public String getString(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 从指定队列里取得数据
     */
    public List<String> getFromQueue(String key, long size) {

        boolean flag = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            return connection.exists(key.getBytes());
        });

        if (flag) {
            return new ArrayList<>();
        }
        ListOperations<String, String> lo = redisTemplate.opsForList();
        if (size > 0) {
            return lo.range(key, 0, size - 1);
        } else {
            return lo.range(key, 0, lo.size(key) - 1);
        }
    }

    /**
     * 从指定队列里取得数据
     */
    public String popQueue(String key) {

        return redisTemplate.opsForList().rightPop(key);

    }

    /**
     * 取得序列值的下一个
     */
    public Long getSeqNext(String key) {

        return redisTemplate.execute((RedisCallback<Long>) connection -> {

            return connection.incr(key.getBytes());

        });
    }

    /**
     * 取得序列值的下一个
     */
    public Long getSeqNext(String key, long value) {

        return redisTemplate.execute((RedisCallback<Long>) connection -> {

            return connection.incrBy(key.getBytes(), value);

        });

    }

    /**
     * 将序列值回退一个
     */
    public void getSeqBack(String key) {

        redisTemplate
                .execute((RedisCallback<Long>) connection -> connection.decr(key.getBytes()));

    }

    /**
     * 从hash集合里取得
     */
    public Object hashGet(String hName, String key) {

        return redisTemplate.opsForHash().get(hName, key);
    }

    public <T> T hashGet(String hName, String key, Class<T> clazz) {

        return JSON.parseObject((String) hashGet(hName, key), clazz);
    }

    /**
     * 从hash取得所有对象
     */
    public Map<Object, Object> hashGetAll(String hName) {
        return redisTemplate.opsForHash().entries(hName);
    }

    /**
     * @param key
     * @return
     */
    public Long hashSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 增加浮点数的值
     */
    public Double incrFloat(String key, double incrBy) {

        return redisTemplate.execute((RedisCallback<Double>) connection -> {

            return connection.incrBy(key.getBytes(), incrBy);

        });
    }

    /**
     * 判断是否缓存了数据
     *
     * @param key 数据KEY
     * @return 判断是否缓存了
     */
    public boolean isCached(String key) {

        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.exists(key.getBytes()));
    }

    /**
     * 判断hash集合中是否缓存了数据
     *
     * @param key 数据KEY
     * @return 判断是否缓存了
     */
    public boolean hashCached(String hName, String key) {

        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            return connection.hExists(key.getBytes(), key.getBytes());
        });
    }

    /**
     * 判断是否缓存在指定的集合中
     *
     * @param key 数据KEY
     * @param val 数据
     * @return 判断是否缓存了
     */
    public boolean isMember(String key, String val) {

        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            return connection.sIsMember(key.getBytes(), val.getBytes());
        });
    }

    /**
     * 从缓存中删除数据
     */
    public void delKey(String key) {

        redisTemplate
                .execute((RedisCallback<Long>) connection -> connection.del(key.getBytes()));
    }

    /**
     * 设置超时时间
     */
    public void expire(String key, int seconds) {
        redisTemplate
                .execute((RedisCallback<Boolean>) connection -> connection.expire(key.getBytes(), seconds));

    }

    /**
     * 列出hash中所有成员
     *
     * @param keyName key名
     */
    public Set<Object> listSet(String keyName) {

        return redisTemplate.opsForHash().keys(keyName);

    }

    /**
     * 列出set中所有成员
     *
     * @param setName set名
     */
    public Set<String> setMembers(String setName) {

        return redisTemplate.opsForSet().members(setName);

    }

    /**
     * 列出set大小
     *
     * @param setName set名
     */
    public Long setSize(String setName) {

        return redisTemplate.opsForSet().size(setName);

    }

    /**
     * 向set中追加一个值
     *
     * @param setName set名
     */
    public void setSave(String setName, String value) {

        redisTemplate
                .execute((RedisCallback<Long>) connection -> connection
                        .sAdd(setName.getBytes(), value.getBytes()));

    }

    /**
     * 逆序列出sorted set包括分数的set列表
     *
     * @param key   set名
     * @param start 开始位置
     * @param end   结束位置
     * @return 列表
     */
    public Set<Tuple> listSortedsetRev(String key, int start, int end) {

        return redisTemplate
                .execute((RedisCallback<Set<Tuple>>) connection -> {
                    return connection.zRevRangeWithScores(key.getBytes(), start, end);
                });
    }


    /**
     * 逆序取得sorted sort排名
     *
     * @param key    set名
     * @param member 成员名
     * @return 排名
     */
    public Long getRankRev(String key, String member) {

        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            return connection.zRevRank(key.getBytes(), member.getBytes());
        });

    }

    /**
     * 根据成员名取得sorted sort分数
     *
     * @param key    set名
     * @param member 成员名
     * @return 分数
     */
    public Double getMemberScore(String key, String member) {

        return redisTemplate.execute((RedisCallback<Double>) connection -> {
            return connection.zScore(key.getBytes(), member.getBytes());
        });
    }

    /**
     * 向sorted set中追加一个值
     *
     * @param key    set名
     * @param score  分数
     * @param member 成员名称
     */
    public void saveToSortedset(String key, Double score, String member) {

        redisTemplate.execute(
                (RedisCallback<Boolean>) connection -> connection
                        .zAdd(key.getBytes(), score, member.getBytes()));
    }

    /**
     * 从sorted set删除一个值
     *
     * @param key    set名
     * @param member 成员名称
     */
    public void delFromSortedset(String key, String member) {
        redisTemplate
                .execute(
                        (RedisCallback<Long>) connection -> connection.zRem(key.getBytes(), member.getBytes()));

    }

    /**
     * 从hash map中取得复杂JSON数据
     */
    public <T> T getBeanFromMap(String key, String field, Class<T> clazz) {

        byte[] input = redisTemplate.execute((RedisCallback<byte[]>) connection -> {
            return connection.hGet(key.getBytes(), field.getBytes());
        });
        return JSON.parseObject(input, clazz, Feature.AutoCloseSource);
    }

    /**
     * 从hashmap中删除一个值
     *
     * @param key   map名
     * @param field 成员名称
     */
    public void delFromMap(String key, String field) {
        redisTemplate
                .execute(
                        (RedisCallback<Long>) connection -> connection.hDel(key.getBytes(), field.getBytes()));

    }

    /**
     * 从hashmap中删除一个值
     *
     * @param key   map名
     * @param field 成员名称
     */
    public void hDel(String key, String field) {

        redisTemplate
                .execute(
                        (RedisCallback<Long>) connection -> connection.hDel(key.getBytes(), field.getBytes()));

    }

    /**
     * @Description: 根据key增长 ，计数器
     * @author clg
     * @date 2016年6月30日 下午2:37:52
     */
    public long incr(String key) {

        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            return connection.incr(key.getBytes());
        });
    }


    /**
     * 根据key获取当前计数结果
     */
    public String getCount(String key) {

        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 将指定的值插入到存于列表的头部
     */
    public <T> Long lpush(String key, T value) {

        return redisTemplate.opsForList().leftPush(key, JSON.toJSONString(value));
    }

    /**
     * 将所有指定的值插入到存于 key 的列表的头部。如果 key 不存在，那么在进行 push 操作前会创建一个空列表
     */
    public Long rpush(String key, String value) {

        return redisTemplate.opsForList().rightPush(key, value);
    }

    public Long lpush(String key, String value) {

        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 只有当 key 已经存在并且存着一个 list 的时候，在这个 key 下面的 list 的头部插入 value。 与 LPUSH 相反，当 key 不存在的时候不会进行任何操作
     */
    public <T> Long lpushx(String key, T value) {

        return redisTemplate.opsForList().leftPushIfPresent(key, JSON.toJSONString(value));
    }

    /**
     * 返回存储在 key 里的list的长度。 如果 key 不存在，那么就被看作是空list，并且返回长度为 0
     */
    public String llen(String key) {

        return redisTemplate.opsForList().size(key).toString();
    }

    /**
     * 返回存储在 key 的列表里指定范围内的元素。 start 和 end 偏移量都是基于0的下标，即list的第一个元素下标是0（list的表头），第二个元素下标是1，以此类推
     */
    public List<String> lrange(String key, long start, long end) {

        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除并且返回 key 对应的 list 的第一个元素
     */
    public String lpop(String key) {

        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移除并且返回 key 对应的 list 的第一个元素, 等待 5 s，若无则退出
     */
    public String brpop(String key, int waitSecs) {
        return redisTemplate.opsForList().rightPop(key, waitSecs, TimeUnit.SECONDS);
    }

    /**
     * 保存到hash集合中 只在 key 指定的哈希集中不存在指定的字段时，设置字段的值。如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 key 关联。如果字段已存在，该操作无效果。
     *
     * @param hName 集合名
     */
    public void hsetnx(String hName, String key, String value) {

        redisTemplate
                .execute((RedisCallback<Boolean>) connection -> connection.hSetNX(key.getBytes(),
                        key.getBytes(), value.getBytes()));

    }

    /**
     * 保存到hash集合中 只在 key 指定的哈希集中不存在指定的字段时，设置字段的值。如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 key 关联。如果字段已存在，该操作无效果。
     *
     * @param hName 集合名
     */
    public <T> void hsetnx(String hName, String key, T t) {
        hsetnx(hName, key, JSON.toJSONString(t));
    }

    /**
     * 保存到分页表
     *
     * @param key    zset名称
     * @param member 成员
     * @param score  排序值
     */
    public void saveToPaging(String key, String member, double score) {
        redisTemplate.opsForZSet().add(key, member, score);
    }

    /**
     * 逆序sorted 分页查询 由weight从大到小排序
     *
     * @param resource 分页hash名称
     * @param target   目标set
     * @param page     page
     * @param limit    limit
     * @return 列表
     */
    public <T> List<T> getPagingToSortedset(String resource, String target, int page,
                                            int limit, Class<T> t) {

        int start = (page - 1) * limit;
        int end = (page) * limit - 1;

        // 分页取出zset中的值
        Set<String> sortSet = redisTemplate.opsForZSet()
                .reverseRange(resource, Long.valueOf(start), Long.valueOf(end));

        List<T> objlist = new ArrayList<>();

        for (String item : sortSet) {
            T object = hashGet(target, item, t);
            objlist.add(object);
        }
        return objlist;
    }

    /**
     * 逆序sorted 分页查询 由weight从大到小排序
     *
     * @param resource 分页hash名称
     * @param target   目标set
     * @param page     page
     * @param limit    limit
     * @return 列表
     */
    public List<Object> getPagingToSortedsetObject(String resource, String target, int page,
                                                   int limit) {

        int start = (page - 1) * limit;
        int end = (page) * limit - 1;

        // 分页取出zset中的值
        Set<String> sortSet = redisTemplate.opsForZSet()
                .reverseRange(resource, Long.valueOf(start), Long.valueOf(end));

        List<Object> objlist = new ArrayList<>();

        for (String item : sortSet) {
            Object object = hashGet(target, item);
            objlist.add(object);
        }
        return objlist;
    }

    /**
     * 获取zset的长度
     *
     * @param key zset
     */
    public long getSortedsetSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 将数据保存到zset
     */
    public <T> void saveToSortedset(String hName, Double key, T t) {
        redisTemplate.opsForZSet().add(hName, JSON.toJSONString(t), key);
    }

    public Set<String> getAllSortedSet(String key) {
        return redisTemplate.opsForZSet().range(key, 0, -1);
    }

    /**
     * 模糊查询
     *
     * @param pattern
     * @return
     */
    public Set<String> querylikeByFont(String pattern) {
        return redisTemplate.keys(pattern + "*");
    }

    public Map<String, Object> batchQueryByKeys(List<String> keys) {
        if (keys.isEmpty()) {
            return null;
        }
        List<Object> results = redisTemplate.executePipelined(
                (RedisCallback<Object>) connection -> {
                    StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
                    for (String key : keys) {
                        stringRedisConn.get(key);
                    }
                    return null;
                });
        if (results.isEmpty()) {
            return null;
        }
        Map<String, Object> resultMap = Collections.synchronizedMap(new HashMap<>(16));
        keys.parallelStream().forEach(t -> resultMap.put(t, results.get(keys.indexOf(t))));
        return resultMap;
    }

    /**
     * 通过rank获取对应的member
     */
    public Set<String> getMemberByRank(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public Long sortedSetDel(String key, Object member) {
        return redisTemplate.opsForZSet().remove(key, member);
    }

    public Long setDel(String key, Object member) {
        return redisTemplate.opsForSet().remove(key, member);
    }

    /**
     * scan 实现
     * @param pattern    表达式
     * @param consumer    对迭代到的key进行操作
     */
    private void scan(String pattern, Consumer<byte[]> consumer) {
        redisTemplate.execute((RedisConnection connection) -> {
            try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().count(Long.MAX_VALUE).match(pattern).build())) {
                cursor.forEachRemaining(consumer);
                return null;
            }
        });
    }

    /**
     * 获取符合条件的key
     */
    public List<String> keys(String key) {
        List<String> keys = new ArrayList<>();
        //正则获取keys
        this.scan(String.format("*%s*",key), item -> {
            //符合条件的key
            String record = new String(item, StandardCharsets.UTF_8);
            keys.add(record);
        });
        return keys;
    }

}
