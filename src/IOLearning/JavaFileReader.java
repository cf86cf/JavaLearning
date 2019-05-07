package IOLearning;

import java.io.*;

/**
 * 读取txt文件，以及读取文件夹中的文件目录
 */
public class JavaFileReader {
    public static void  main(String[] args){
        String  string;
        try {
            File file = new File("F:\\JavaDaily\\src\\hello.txt");  //按行读取txt文件中的内容
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((string = reader.readLine())!= null){
                System.out.println(string);
            }

            File f = new File("E:\\File_package"+File.separator) ;
            String str[] = f.list() ;                                           // 列出给定目录中的内容
            File files[] = f.listFiles();                                       // 列出包含完整目录的内容
//            for(int i=0;i<str.length;i++){
//                System.out.println(str[i]) ;
//            }
            for(int i=0;i<str.length;i++){
                System.out.println(files[i]) ;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
