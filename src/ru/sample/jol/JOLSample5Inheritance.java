package ru.sample.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * Created by Nechaev Mikhail
 * Since 14/11/2018.
 *
 * # Java 11
 * # Running 64-bit HotSpot VM.
 * # ...
 * # Objects are 8 bytes aligned.
 * # Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
 * # Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
 */
@SuppressWarnings("unused")
class JOLSample5Inheritance {

    private static class A51 {
        boolean f1;
    }

    private static class A52 extends A51 {
        boolean f2;
    }

    private static class A53 extends A52 {
        boolean f3;
    }

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        /*
         * JOLSample5Inheritance$A53 object internals:
         *  OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
         *       0    12           (object header)                           N/A
         *      12     1   boolean A51.f1                                   N/A
         *      13     3           (alignment/padding gap)
         *      16     1   boolean A52.f2                                   N/A
         *      17     3           (alignment/padding gap)
         *      20     1   boolean A53.f3                                   N/A
         *      21     3           (loss due to the next object alignment)
         * Instance size: 24 bytes
         * Space losses: 6 bytes internal + 3 bytes external = 9 bytes total
         */
        System.out.println(ClassLayout.parseClass(A53.class).toPrintable());
    }
}
