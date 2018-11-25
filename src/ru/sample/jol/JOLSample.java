package ru.sample.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Nechaev Mikhail
 * Since 12/11/2018.
 *
 * see:
 * https://openjdk.java.net/projects/code-tools/jol/
 * https://shipilev.net/#object-layout
 *
 * https://gist.github.com/greyby/6a522c2cad2307b58b02eb55413a3b69 — Java Object Header
 *
 * Field sizes by type: oop | boolean | byte | char | short | int | float | long | double
 * Array element sizes: Object[] | boolean[] | byte[] | char[] | short[] | int[] | float[] | long[] | double[]
 */
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
class JOLSample {

    private static final int SIZE = 10;
    private static final int HASH_TABLE_INITIAL_CAP = 16;

    public static void main(String[] args) {
        System.out.println(VM.current().details());

        int[] array = new int[SIZE];
        List<Integer> arrayList = new ArrayList<>(SIZE);
        List<Integer> linkedList = new LinkedList<>();
        Set<Integer> hashSet = new HashSet<>(HASH_TABLE_INITIAL_CAP);
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < SIZE; i++) {
            array[i] = i;
            arrayList.add(i);
            linkedList.add(i);
            hashSet.add(i);
            treeSet.add(i);
        }
        System.out.println("int[] - 1");
        System.out.println(ClassLayout.parseClass(int[].class).toPrintable());
        System.out.println("int[] - 2");
        System.out.println(ClassLayout.parseClass(int[].class).toPrintable(array));


        System.out.println("arrayList - 1");
        System.out.println(ClassLayout.parseClass(ArrayList.class).toPrintable());
        System.out.println("arrayList - 2");
        System.out.println(ClassLayout.parseClass(ArrayList.class).toPrintable(arrayList));
        System.out.println("arrayList - 3");
        System.out.println(GraphLayout.parseInstance(arrayList).toPrintable());
        System.out.println("arrayList - 4");
        System.out.println(GraphLayout.parseInstance(arrayList).toFootprint());


        System.out.println("hashSet - 1");
        System.out.println(ClassLayout.parseClass(HashSet.class).toPrintable());
        System.out.println("hashSet - 2");
        System.out.println(ClassLayout.parseClass(HashSet.class).toPrintable(hashSet));
        System.out.println("hashSet - 3");
        System.out.println(GraphLayout.parseInstance(hashSet).toPrintable());
        System.out.println("hashSet - 4");
        System.out.println(GraphLayout.parseInstance(hashSet).toFootprint());

        System.out.println("treeSet - 1");
        System.out.println(ClassLayout.parseClass(TreeSet.class).toPrintable());
        System.out.println("treeSet - 2");
        System.out.println(ClassLayout.parseClass(TreeSet.class).toPrintable(treeSet));
        System.out.println("treeSet - 3");
        System.out.println(GraphLayout.parseInstance(treeSet).toPrintable());
        System.out.println("treeSet - 4");
        System.out.println(GraphLayout.parseInstance(treeSet).toFootprint());

    }
}
