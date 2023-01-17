package com.example.myspringproject.data20230117;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortTest {
    static Map<Integer, Integer> sets = new HashMap<Integer, Integer>() {{
        put(1, 2);
        put(2, 5);
        put(3, 4);
        put(4, 7);
        put(5, 1);
        put(6, 1);
        put(7, 3);
        put(8, 8);
        put(9, 3);
        put(10, 6);
    }};

    public static void main(String[] args) {
        //最大值 -> 数值
        Integer limiter = 20;
        //二维数组
        List<List<Integer>> subsets = new ArrayList<>();

        findAllSubsets(sets, limiter, subsets);

        System.out.println(subsets);
    }

    private static void findAllSubsets(Map<Integer, Integer> set, Integer limiter, List<List<Integer>> subsets) {
        if (set == null || set.isEmpty()) {
            return;
        }
        // 根据value进行排序
        set = sortByValue(set);
        //子集数组
        List<Integer> subset = new ArrayList<>();
        //总和
        int sum = 0;
        List<Integer> sortedSetArr = new ArrayList<>(set.keySet());
        for (int i = 0; i < sortedSetArr.size(); i++) {
            //总和≤limiter
            if (sum + set.get(sortedSetArr.get(i)) <= limiter) {
                //加总和
                sum += set.get(sortedSetArr.get(i));
                //推入子集
                subset.add(sortedSetArr.get(i));
                //删除用过的 key
                set.remove(sortedSetArr.get(i));
            }
        }
        subsets.add(subset);
        findAllSubsets(set, limiter, subsets);
    }

    /**
     * 按照值排序
     *
     * @param map
     */
    static Map<Integer, Integer> sortByValue(Map<Integer, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));
    }
}
