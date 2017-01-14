package Linear;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by rliu on 12/5/16.
 * Top K frequent elements
 * Given a non-empty array of integers, return the k most frequent elements.
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * [I am stuck here]: can not think of using a private class to wrap the val and count, but think of hashset
 * Analysis: Two thing we can not avoid: 1. scan all number, 2. count the frequency
 * key:1. create a private num class include val and count
 * 2. top k problem will make you think of heap, in this case, is min heap
 * (keep the biggest number using min heap, as all the number in heap is bigger than heap top, keep minimum number using max heap)
 * Complexity: O(nlogk)
 * <p>
 * Improved: using quick sort but not sort the whole array, so amortized O(n) time
 * <p>
 * Improved: really O(n) bucket sort: as we know the frequency is definitely smaller than n, we can initial a array with length n, can count k from right to left
 * <p>
 * Conclusion: when the question is associate top k, think of heap, partly quick sort
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5,};
        int k = 3;
        pre(arr, k);

    }

    public static void pre(int[] arr, int k) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!counts.containsKey(arr[i]))
                counts.put(arr[i], 1);
            else
                counts.put(arr[i], counts.get(arr[i]) + 1);
        }
        Num[] c = new Num[counts.size()];
        int i = 0;
        for (Integer val : counts.keySet()) {
            Num temp = new Num(val, counts.get(val));
            c[i++] = temp;
        }


        heapSolution(c, k);
        bucketSort(c, k, arr.length);
        quickSortSolution(c, k);

    }

    //time: O(NlogK)
    public static void heapSolution(Num[] counts, int k) {
        PriorityQueue<Num> minheap = new PriorityQueue<>(k, new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                return o2.count - o1.count;
            }
        });
        for (Num num : counts) {
            minheap.add(num);
        }

        IntStream.range(0, k).forEach(i -> {
            System.out.print(minheap.poll().val + " ");
        });
    }

    //Time:atomerized O(N)
    public static void quickSortSolution(Num[] counts, int k) {
        int start = 0;
        int end = counts.length - 1;
        ArrayList<Integer> rt = new ArrayList<>();
        while (true) {
            int pivotIndex = quickSortHelper(counts, start, end);
            if (pivotIndex + 1 == k) {
                copy(counts, start, pivotIndex, rt);
                break;
            } else if (pivotIndex + 1 < k) {
                copy(counts, start, pivotIndex, rt);
                start = pivotIndex + 1;
            } else {
                end = pivotIndex;
            }
        }
        System.out.print(rt);

    }

    public static void copy(Num[] arr, int i, int j, ArrayList<Integer> rs) {
        for (int k = i; k <= j; k++) {
            rs.add(arr[k].val);
        }
    }

    public static int quickSortHelper(Num[] arr, int i, int j) {
        int start = i;
        int end = j;
        Num pivot = arr[i];
        while (start < end) {
            if (arr[end].count >= pivot.count)
                swap(arr, ++start, end);
            else
                end--;
        }
        swap(arr, i, start);
        return start;
    }

    public static void swap(Num[] arr, int i, int j) {
        Num temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bucketSort(Num[] counts, int k, int size) {
        //TODO: why ArrayList<Num> [] bucket=new ArrayList<Num> [size]; is not valid?
        //new ArrayList<> is used to define a type called ArrayList。
        //new ArrayList[3] is used to define a array with the content type ArrayList, same as you define new Num[3]
        //if you use ArrayList<>[3] will be have compiler error "generic array creation" it does not know you want to create arraylist or array
        List<Integer>[] bucket = new List[size];
        //List<List<Integer>> list=new ArrayList<List<Integer>>();   V
        //List<List<Integer>> list=new ArrayList<ArrayList<Integer>>; X
        //ArrayList is implementation of List does not means ArrayList<ArrayList> is implementation of List<List<>>
        //list Integer inherit from Number, but does not mean Box<Integer> inherit Box<Number>
        for (Num num : counts) {
            if (bucket[num.count] == null) {
                bucket[num.count] = new ArrayList<>();
            }
            bucket[num.count].add(num.val);
        }
        int j = 0;
        ArrayList<Integer> rt = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                rt.addAll(bucket[i]);
                j += bucket[i].size();
                if (j == k)
                    break;
            }
        }
        System.out.print(rt);
    }

    public static class Num {
        int val;
        int count;

        public Num(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }
}
