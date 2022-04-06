//: data_structure/src/func/Print.java
// Print methods that can be used without qualifiers
package func;

import basic_datastructure.DoubleLinkedList;
import basic_datastructure.List;
import java.io.*;

public class Print {
    // Print with a newline
    public static void print(Object obj) {
        System.out.println(obj);
    }

    // Print a newline by itself
    public static void print() {
        System.out.println();
    }

    // Print with no newline
    public static void printnb(Object obj) {
        System.out.print(obj);
    }

    public static void printwt(Object obj) { System.out.print(obj); System.out.print("\s");}

    // The new Java SE5 printf() (from C)
    public static PrintStream printf(String format, Object... args) {
        return System.out.printf(format, args);
    }

    // Print array
    public static void printArray (Object[] a) {
        for (Object o : a) {
            printwt(o);
        }
        print();
    }


}
