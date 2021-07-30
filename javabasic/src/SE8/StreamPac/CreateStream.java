package SE8.StreamPac;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/2/16 19:37
 * @Modified By：
 * <p>
 * 创建Stream的几种方式
 */
public class CreateStream {

    public static void main(String[] args) {
//        createStreamFromCollection().forEach(System.out::println);
//        createStreamFromValues().forEach(System.out::println);
//        createStreamFromArrays().forEach(System.out::println);
        createStreamFromFile();
//        createStreamFromIterator().forEach(System.out::println);
//        createStreamFromGenerate().forEach(System.out::println);
//        createStreamFromGenerateWithObject().forEach(System.out::println);
    }



    /**
     * Generate the Stream object from collection.
     *
     * @return
     */
    private static Stream<String> createStreamFromCollection() {
        List<String> list = Arrays.asList("hello", "Sim", "World", "Josh");
        return list.stream();
    }

    /**
     * 通过Stream 的静态方法
     *
     * @return
     */
    private static Stream<String> createStreamFromValues() {
        return Stream.of("hello", "Sim", "World", "Josh");
    }

    /**
     * 通过数组创建Stream
     *
     * @return
     */
    private static Stream<String> createStreamFromArrays() {
        String[] str = {"hello", "Sim", "World", "Josh"};
        return Arrays.stream(str);
    }

    /**
     * 通过读取文件，进行创建Stream
     *
     * @return
     */
    private static Stream<String> createStreamFromFile() {
        Path path = Paths.get("D:\\IdeaProjects\\JavaBase\\javabasic\\src\\SE8\\StreamPac\\CreateStream.java");
        try (Stream<String> fileStream = Files.lines(path)) {
            fileStream.forEach(System.out::println);
            return fileStream;
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }

    /**
     * 使用 iterator
     */
    private static Stream<Integer> createStreamFromIterator(){
        Stream<Integer> iterate = Stream.iterate(0, n -> n + 2).limit(10);
        return iterate;
    }

    /**
     * 使用generate
     */
    private static Stream<Double> createStreamFromGenerate(){
        return Stream.generate(Math::random).limit(10);
    }

    /**
     * 使用Generate 创建Stream 获取对象的值。
     */
    private static Stream<Obj> createStreamFromGenerateWithObject(){
        return Stream.generate(new ObjImpSuplier()).limit(10);
    }

    static class ObjImpSuplier implements Supplier<Obj>{

        private int index = 0;
        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index = random.nextInt(100);
            return new Obj(index,"Name - > " + index);
        }

    }

    static class Obj {
        private int rm_num;

        private String name;

        public Obj(int rm_num, String name) {
            this.rm_num = rm_num;
            this.name = name;
        }

        @Override
        public String toString() {
            return "obj->{" +
                    "rm_num=" + rm_num +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
