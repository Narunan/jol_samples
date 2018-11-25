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
class JOLSample1Array {

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        checkBooleans();
//        checkObjects();
//        checkInts();
    }

    private static void checkBooleans() {
//        boolean[] booleans = new boolean[0b01000001_00001111_11111111_11111111];
        boolean[] booleans = new boolean[0x41_0f_ff_ff];
        /*
         * [Z object internals:
         *  OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
         *       0    16           (object header)                           N/A
         *      16     0   boolean [Z.<elements>                             N/A
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
        System.out.println(ClassLayout.parseClass(boolean[].class).toPrintable());
        /*
         * [Z object internals:
         *  OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
         *       0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4           (object header)                           40 00 00 00 (01000000 00000000 00000000 00000000) (64)
         *      12     4           (object header)                           ff ff 0f 41 (11111111 11111111 00001111 01000001) (1091567615)
         *      16     0   boolean [Z.<elements>                             N/A
         *      16 1091567616           (loss due to the next object alignment)
         * Instance size: 1091567632 bytes
         * Space losses: 0 bytes internal + 1091567616 bytes external = 1091567616 bytes total
         */
        System.out.println(ClassLayout.parseClass(boolean[].class).toPrintable(booleans));
    }

    private static void checkObjects() {
        Object[] objects = new Object[0b00000000_00001111_11111111_11111111];
        /*
         * [Ljava.lang.Object; object internals:
         *  OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
         *       0    16                    (object header)                           N/A
         *      16     0   java.lang.Object Object;.<elements>                        N/A
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
        System.out.println(ClassLayout.parseClass(Object[].class).toPrintable());
        /*
         * [Ljava.lang.Object; object internals:
         *  OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
         *       0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4                    (object header)                           80 3e 01 00 (10000000 00111110 00000001 00000000) (81536)
         *      12     4                    (object header)                           ff ff 0f 00 (11111111 11111111 00001111 00000000) (1048575)
         *      16     0   java.lang.Object Object;.<elements>                        N/A
         *      16 4194304                    (loss due to the next object alignment)
         * Instance size: 4194320 bytes
         * Space losses: 0 bytes internal + 4194304 bytes external = 4194304 bytes total
         */
        System.out.println(ClassLayout.parseClass(Object[].class).toPrintable(objects));
    }

    private static void checkInts() {
        int[] array = new int[0b00000000_00001111_11111111_11111111];
        /*
         * [I object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0    16        (object header)                           N/A
         *      16     0    int [I.<elements>                             N/A
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
        System.out.println(ClassLayout.parseClass(int[].class).toPrintable());
        /*
         * [I object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           10 0c 00 00 (00010000 00001100 00000000 00000000) (3088)
         *      12     4        (object header)                           ff ff 0f 00 (11111111 11111111 00001111 00000000) (1048575)
         *      16     0    int [I.<elements>                             N/A
         *      16 4194304        (loss due to the next object alignment)
         * Instance size: 4194320 bytes
         * Space losses: 0 bytes internal + 4194304 bytes external = 4194304 bytes total
         */
        System.out.println(ClassLayout.parseClass(int[].class).toPrintable(array));
    }
}
