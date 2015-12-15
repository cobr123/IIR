package local.exercise_1_1;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        final List<String[]> data = new ArrayList<>();
        data.add(new String[]{"Doc1", "new home sales top forecasts"});
        data.add(new String[]{"Doc2", "home sales rise in july"});
        data.add(new String[]{"Doc3", "increase in home sales in july"});
        data.add(new String[]{"Doc4", "july new home sales rise"});

        //word1: doc1, doc2, doc3..
        final Map<String, Set<String>> idx = new TreeMap<>();
        data.stream()
                .forEach(strings -> {
                    Arrays.asList(strings[1].split("\\s"))
                            .stream()
                            .forEach(s -> {
                                if (!idx.containsKey(s)) {
                                    idx.put(s, new TreeSet<>());
                                }
                                idx.get(s).add(strings[0]);
                            });
                });

        idx.entrySet().stream().forEachOrdered(entry -> {
            System.out.print(entry.getKey());
            System.out.print(": ");
            entry.getValue().stream().forEachOrdered(value -> {
                System.out.print(value);
                System.out.print(", ");
            });
            System.out.print('\n');
        });
    }
}
