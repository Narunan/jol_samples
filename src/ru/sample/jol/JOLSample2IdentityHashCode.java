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
class JOLSample2IdentityHashCode {

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        Object a = new Object();
        //1184ab05
        System.out.println(Integer.toHexString(System.identityHashCode(a)));
        /*
         * java.lang.Object object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           01 05 ab 84 (00000001 00000101 10101011 10000100) (-2069166847)
         *       4     4        (object header)                           11 00 00 00 (00010001 00000000 00000000 00000000) (17)
         *
         *       8     4        (object header)                           00 10 00 00 (00000000 00010000 00000000 00000000) (4096)
         *      12     4        (loss due to the next object alignment)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */
        System.out.println(ClassLayout.parseClass(Object.class).toPrintable(a));
    }
}
