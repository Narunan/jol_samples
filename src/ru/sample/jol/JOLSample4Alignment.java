package ru.sample.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

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
@SuppressWarnings("unused")
class JOLSample4Alignment {

    /**
     * Объекты выравнивниваются на 8
     *
     * JOLSample4Alignment$A41 object internals:
     *  OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
     *       0    12           (object header)                           N/A
     *      12     2      char A41.с1                                   N/A
     *      14     2      char A41.с2                                   N/A
     *      16     1   boolean A41.b                                    N/A
     *      17     7           (loss due to the next object alignment)
     * Instance size: 24 bytes
     * Space losses: 0 bytes internal + 7 bytes external = 7 bytes total
     */
    private static class A41 {
        boolean b;
        char c1;
        char c2;
    }

    /**
     * Long выравнивнивается на 8
     *
     * JOLSample4Alignment$A42 object internals:
     *  OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
     *       0    12           (object header)                           N/A
     *      12     1   boolean A42.b                                    N/A
     *      13     3           (alignment/padding gap)
     *      16     8      long A42.l                                    N/A
     * Instance size: 24 bytes
     * Space losses: 3 bytes internal + 0 bytes external = 3 bytes total
     */
    private static class A42 {
        boolean b;
        long l;
    }

    /**
     * Double выравнивается на 8
     *
     * JOLSample4Alignment$A43 object internals:
     *  OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
     *       0    12           (object header)                           N/A
     *      12     1   boolean A43.b                                    N/A
     *      13     3           (alignment/padding gap)
     *      16     8    double A43.d                                    N/A
     * Instance size: 24 bytes
     * Space losses: 3 bytes internal + 0 bytes external = 3 bytes total
     */

    private static class A43 {
        boolean b;
        double d;
    }

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(A41.class).toPrintable());
        System.out.println(ClassLayout.parseClass(A42.class).toPrintable());
        System.out.println(ClassLayout.parseClass(A43.class).toPrintable());
    }
}
