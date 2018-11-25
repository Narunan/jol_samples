package ru.sample.jol;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
class JOLSample3Reference {

    private static final Path DIR_PATH = Paths.get("img", JOLSample3Reference.class.getSimpleName());

    public static void main(String[] args) throws IOException {
        Files.createDirectories(DIR_PATH);
        VirtualMachine vm = VM.current();
        System.out.println(vm.details());
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        check(vm, list, 1); //0x787611f80
        for (int i = 0; i < 1000000; i++) {
            list.add(new Object());
        }
        check(vm, list, 2); //0x780d71ef0
        System.gc();
        check(vm, list, 3); //0x78056e3e0
        Collections.fill(list.subList(100, 500000), null);
        check(vm, list, 4); //0x78056e3e0
        System.gc();
        check(vm, list, 5); //0x780500630
        list.subList(1, list.size()).clear();
        check(vm, list, 6); //0x780500630
        System.gc();
        check(vm, list, 7); //0x780500000
    }

    private static void check(VirtualMachine vm, List list, int count) throws IOException {
        System.out.println("0x" + Long.toHexString(vm.addressOf(list.get(0))));
        /*
         * RED — list
         * GREEN — list.elementData
         * BLUE — list.elementData[*]
         * WHITE — nothing
         */
        GraphLayout.parseInstance(list).toImage(DIR_PATH.resolve("gc_" + count + ".png").toString());
    }
}
