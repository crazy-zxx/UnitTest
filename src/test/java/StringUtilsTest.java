import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilsTest {

    //一种传入测试参数的方法是通过@MethodSource注解，它允许我们编写一个同名的静态方法来提供测试参数：
    //如果静态方法和测试方法的名称不同，@MethodSource也允许指定方法名。但使用默认同名方法最方便。
    @ParameterizedTest
    @MethodSource
    void testCapitalize1(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }

    //静态方法testCapitalize()返回了一组测试参数，每个参数都包含两个String，正好作为测试方法的两个参数传入。
    private static List<Arguments> testCapitalize1() {
        return List.of(Arguments.arguments("abc", "Abc"),
                Arguments.arguments("APPLE", "Apple"),
                Arguments.arguments("gooD", "Good"));
    }


    //另一种传入测试参数的方法是使用@CsvSource，它的每一个字符串表示一行，一行包含的若干参数用,分隔，
    // 因此，上述测试又可以改写如下：
    @ParameterizedTest
    @CsvSource({ "abc, Abc", "APPLE, Apple", "gooD, Good" })
    void testCapitalize2(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }


    //可以把测试数据提到一个独立的CSV文件中，然后标注上@CsvFileSource
    //JUnit只在classpath中查找指定的CSV文件，因此，test-capitalize.csv这个文件要放到resources目录下
    @ParameterizedTest
    @CsvFileSource(resources = { "/test-capitalize.csv" })
    void testCapitalizeUsingCsvFile(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }

}