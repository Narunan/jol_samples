package ru.sample.jol;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Nechaev Mikhail
 * Since 14/11/2018.
 *
 * # Running 64-bit HotSpot VM.
 * # ...
 * # Objects are 8 bytes aligned.
 * # Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
 * # Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
 */
class JOLSample6Set {

    private static class Obj implements Comparable<Obj> {

        private final int hashCode;

        Obj(int hashCode) {
            this.hashCode = hashCode;
        }

        @SuppressWarnings("EmptyMethod")
        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        @Override
        public int compareTo(Obj other) {
            return Integer.compare(System.identityHashCode(this), System.identityHashCode(other));
        }
    }

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        //хеш-таблица с закрытой адресацией методом цепочек
        Set<Obj> hashSet = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            hashSet.add(new Obj(1));
            hashSet.add(new Obj(3));

            hashSet.add(new Obj(2));
            hashSet.add(new Obj(2));
        }
        System.out.println(GraphLayout.parseInstance(hashSet).toPrintable());
        //сбалансированное красно-чёрное дерево
        Set<Obj> treeSet = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            treeSet.add(new Obj(i));
        }
        System.out.println(GraphLayout.parseInstance(treeSet).toPrintable());

        Obj[] objects = new Obj[10];
        Arrays.setAll(objects, Obj::new);
        //неизменяемая хеш-таблица с открытой адресацией и линейным пробированием
        Set<Obj> immutableHashSet = Set.of(objects);
        System.out.println(GraphLayout.parseInstance(immutableHashSet).toPrintable());
    }
}
