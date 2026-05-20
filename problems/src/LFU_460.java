import java.util.*;

public class LFU_460 {

    class LFUCache {
        private final int capacity;
        private int minFreq;  // 记录的是 当前缓存中所有 key 的最小访问频率
        private final Map<Integer, Integer> valueMap = new HashMap<>();
        private final Map<Integer, Integer> freqMap  = new HashMap<>();
        private final Map<Integer, LinkedHashSet<Integer>> freqListMap  = new HashMap<>();

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.minFreq = 0;
        }

        public int get(int key) {    // 相当于访问一次
            // 1 如果不存在
            if (!valueMap.containsKey(key)) {
                return -1;
            }

            // 2 如果存在
            // 2.1 更新freqMap中当前 key 的访问频率
            int freq = freqMap.get(key);
            freqMap.put(key, freq + 1);


            // 2.2 从旧的freqListMap中删除
            freqListMap.get(freq).remove(key);
            if (freqListMap.get(freq).isEmpty()) {
                freqListMap.remove(freq);
                if (minFreq == freq) {
                    minFreq++;
                }
            }

            // 2.3 添加到新的freqListMap中
            freqListMap.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>()).add(key);

            return valueMap.get(key);
        }



        public void put(int key, int value) {
            if (capacity == 0) return;

            // 3 如果存在
            if (valueMap.containsKey(key)) {
                valueMap.put(key, value);
                get(key); // 增加频率
                return;
            }


            // 4 如果不存在
            // 4.1 判断capacity是否
            if (valueMap.size() >= capacity) {
                // 淘汰 minFreq 对应的最旧 key
                LinkedHashSet<Integer> minFreqKeys = freqListMap.get(minFreq);
                int evictKey = minFreqKeys.iterator().next();
                minFreqKeys.remove(evictKey);
                if (minFreqKeys.isEmpty()) {
                    freqListMap.remove(minFreq);
                }
                valueMap.remove(evictKey);
                freqMap.remove(evictKey);
            }

            // 4.2 插入新 key
            valueMap.put(key, value);
            freqMap.put(key, 1);
            freqListMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
            minFreq = 1;
        }
    }


}
