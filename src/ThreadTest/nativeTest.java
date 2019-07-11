package ThreadTest;

/**
 * 使用native 方法 加载 c语言的函数
 * 首先 声明一个native的接口
 * ---------- 命令行键入
 * 使用javac 创建该.java文件的.class文件
 * 通过javah .\nativeTest  创建该类的.h文件  （nativeTest.h)
 *
 * 创建.c文件并实现 .h文件中的函数
 * .c文件要包括 .h文件
 * ---------- 命令行键入
 * gcc -I "path1" -I "path2" -o test.dll nativeTest.c
 *
 * path, 为Java\jdk\include  path2,  Java\jdk\include\win32
 * 两个path分别是为了获得这两个路径下的jni.h 和jni_md.h
 * 然后通过System.load 该.dll 路径 即可运行该程序
 * (因为我gcc安装的是32位, Java 62位,导致不能运行...
 */
public class nativeTest
{
    public native void displayHelloWorld();

    static
    {
        System.load("F:\\JavaLearning\\out\\production\\JavaLearning\\ThreadTest\\test.dll");
    }

    public static void main(String[] args) {
        new nativeTest().displayHelloWorld();
    }
}
