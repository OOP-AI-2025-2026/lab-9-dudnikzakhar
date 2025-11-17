package ua.opnu;

import java.util.*;

public class Task {
    public static void main(String[] args) {

    }

    public void removeShorterStrings(List<String> list) {
        Comparator<String> shortStringComparator = (s1, s2) -> {
            int lengthDifference = s1.length() - s2.length();
            if (lengthDifference < 0) {
                return -1;
            }
            if (lengthDifference > 0) {
                return 1;
            }

            return -1;
        };

        int startIndex;
        if (list.size() % 2 == 0) {
            startIndex = list.size() - 2;
        } else {
            startIndex = list.size() - 3;
        }

        for (int i = startIndex; i >= 0; i -= 2) {
            String first = list.get(i);
            String second = list.get(i + 1);

            if (shortStringComparator.compare(first, second) < 0) {
                list.remove(i);
            } else {
                list.remove(i + 1);
            }
        }
    }

    public void stutter(List<String> list) {
        int i = 0;
        while (i < list.size()) {
            String copy = list.get(i);
            list.add(i + 1, copy);
            i += 2;
        }
    }

    public void switchPairs(List<String> list) {
        int i = 0;
        while (i + 1 < list.size()) {
            Collections.swap(list, i, i + 1);

            i += 2;
        }
    }

    public void removeDuplicates(List<String> list) {
        int i = 0;
        while (i + 1 < list.size()) {
            if (Objects.equals(list.get(i), list.get(i + 1))) {
                list.remove(i + 1);
            } else {
                i++;
            }
        }
    }

    public void markLength4(List<String> list) {
        int i = list.size() - 1;
        while (i >= 0) {
            if (list.get(i).length() == 4) {
                list.add(i, "****");
                i--;
            }
        }
    }

    public boolean isPalindrome(Queue<Integer> queue) {
        if (queue == null || queue.size() <= 1) {
            return true;
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int originalSize = queue.size();

        for (int i = 0; i < originalSize; i++) {
            int el = queue.remove();
            stack.push(el);
            queue.add(el);
        }

        boolean isPalindrome = true;
        for (int i = 0; i < originalSize; i++) {
            int queueEl = queue.remove();
            int stackEl = stack.pop();
            if (queueEl != stackEl) {
                isPalindrome = false;
            }

            queue.add(queueEl);
        }
        return isPalindrome;
    }

    public void reorder(Queue<Integer> queue) {
        if (queue == null || queue.size() <= 1) {
            return;
        }
        int originalSize = queue.size();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int positiveIndex = 0;
        for (int i = 0; i < originalSize; i++) {
            int num = queue.remove();
            if (num < 0) {
                stack.push(num);
            } else {
                queue.add(num);
                positiveIndex++;
            }
        }

        while (!stack.isEmpty())
            queue.add(stack.pop());
        for (int i = 0; i < positiveIndex; i++)
            queue.add(queue.remove());

    }

    public void rearrange(Queue<Integer> queue) {
        if (queue == null || queue.size() <= 1) {
            return;
        }
        int originalSize = queue.size();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int evenCount = 0;
        for (int i = 0; i < originalSize; i++) {
            int num = queue.remove();
            if (num % 2 == 0) {
                queue.add(num);
                evenCount++;
            } else {
                stack.push(num);
            }
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        for (int i = 0; i < evenCount; i++) {
            queue.add(queue.remove());
        }

        int oddCount = originalSize - evenCount;
        for (int i = 0; i < oddCount; i++) {
            stack.push(queue.remove());
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }

    public int maxLength(Set<String> set) {
        if (set.isEmpty()) return 0;

        int maxLen = 0;
        for (String s : set) {
            int len = s.length();
            if (len > maxLen)
                maxLen = len;
        }
        return maxLen;
    }

    public void removeEvenLength(Set<String> set) {
        if (set.isEmpty()) return;
        set.removeIf(s -> s != null && s.length() % 2 == 0);
    }

    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        if (list1.isEmpty() || list2.isEmpty()) return 0;
        Set<Integer> firstList = new HashSet<>(list1);
        Set<Integer> secondList = new HashSet<>(list2);
        int count = 0;

        for (Integer value : firstList) {
            if (secondList.contains(value)) {
                count++;
            }
        }

        return count;
    }

    public boolean isUnique(Map<String, String> map) {
        if (map.isEmpty()) {
            return true;
        }
        Set<String> seenValues = new HashSet<>();

        for (String value : map.values()) {
            if (seenValues.contains(value)) {
                return false;
            }
            seenValues.add(value);
        }
        return true;
    }

    public Map<String, Integer> intersect(Map<String, Integer> map1,
                                          Map<String, Integer> map2) {
        Map<String, Integer> intersectionRes = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (map2.containsKey(key) && value.equals(map2.get(key))) {
                intersectionRes.put(key, value);
            }
        }

        return intersectionRes;
    }

    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> reverseRes = new HashMap<>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            reverseRes.put(value, key);
        }

        return reverseRes;
    }

    public int rarest(Map<String, Integer> map) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer value : map.values()) {
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }
        int minFreq = Integer.MAX_VALUE;
        int rarestVal = Integer.MAX_VALUE;


        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int currentVal = entry.getKey();
            int currentFreq = entry.getValue();

            if (currentFreq < minFreq) {
                minFreq = currentFreq;
                rarestVal = currentVal;
            } else if (currentFreq == minFreq) {
                if (currentVal < rarestVal) {
                    rarestVal = currentVal;
                }
            }
        }

        return rarestVal;
    }

    public int maxOccurrences(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (Integer value : list) {
            Integer old = freqMap.get(value);
            if (old == null) {
                freqMap.put(value, 1);
            } else {
                freqMap.put(value, old + 1);
            }
        }

        int maxOccurrences = 0;
        for (Integer occurrence : freqMap.values()) {
            if (occurrence > maxOccurrences) {
                maxOccurrences = occurrence;
            }
        }

        return maxOccurrences;
    }

}
