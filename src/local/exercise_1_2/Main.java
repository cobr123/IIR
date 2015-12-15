package local.exercise_1_2;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        final List<String[]> data = new ArrayList<>();
        data.add(new String[]{"Doc1", "breakthrough drug for schizophrenia"});
        data.add(new String[]{"Doc2", "new schizophrenia drug"});
        data.add(new String[]{"Doc3", "new approche for trearment of schizophrenia"});
        data.add(new String[]{"Doc4", "new hopes for schizophrenia patients"});

        //word1: doc1, doc2, doc3..
        final Map<String, Set<String>> idx = new TreeMap<>();
        final Set<String> docs = new HashSet<>();
        data.stream()
                .forEach(strings -> {
                    docs.add(strings[0]);

                    Arrays.asList(strings[1].split("\\s"))
                            .stream()
                            .forEach(s -> {
                                if (!idx.containsKey(s)) {
                                    idx.put(s, new TreeSet<>());
                                }
                                idx.get(s).add(strings[0]);
                            });
                });

        System.out.println("матрица инцидентности");
        final int docMaxLen = getMaxLen(docs);
        final String docPad = getPad(docMaxLen);
        System.out.print(docPad + " | ");
        idx.entrySet().stream().forEachOrdered(entry -> {
            System.out.print(entry.getKey());
            System.out.print(" | ");
        });
        System.out.print('\n');

        docs.stream().forEachOrdered(doc -> {
            System.out.print(doc);
            System.out.print(" | ");

            idx.entrySet().stream().forEachOrdered(entry -> {
                if (entry.getValue().contains(doc)) {
                    System.out.print(getCentered("1", entry.getKey().length()));
                } else {
                    System.out.print(getCentered("0", entry.getKey().length()));
                }
                System.out.print(" | ");
            });
            System.out.print('\n');
        });


        System.out.println("---------------------------------------");
        System.out.println("инвертированный индекс");
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

    public static String getPad(final int len) {
        if (len <= 0) {
            return "";
        }
        final StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; ++i) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String getCentered(final String textInCenter, final int totalLen) {
        if (totalLen <= textInCenter.length()) {
            return textInCenter;
        }
        final int remain = totalLen - textInCenter.length();
        final int leftSpace = (remain % 2 == 0) ? remain / 2 : (remain + 1) / 2;
        final int rightSpace = remain / 2;
        final StringBuilder sb = new StringBuilder(totalLen);
        for (int i = 0; i < leftSpace; ++i) {
            sb.append(" ");
        }
        sb.append(textInCenter);
        for (int i = 0; i < rightSpace; ++i) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static int getMaxLen(final Collection<String> list) {
        return list.stream().mapToInt(String::length).sorted().findFirst().orElse(0);
    }
}
