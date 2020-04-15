import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    //测试方法testFact()加上了@Test注解，这是JUnit要求的，它会把带有@Test的方法识别为测试方法。
    //assertEquals(expected, actual)是最常用的测试方法，它在Assertion类中定义。Assertion还定义了其他断言方法，例如：
    //assertTrue(): 期待结果为true
    //assertFalse(): 期待结果为false
    //assertNotNull(): 期待结果为非null
    //assertArrayEquals(): 期待结果为数组并与期望数组每个元素的值均相等
    //...
    @Test
    void fact() {
        //用assertEquals(1, Factorial.fact(1))表示，期望Factorial.fact(1)返回1
        assertEquals(1, Factorial.fact(1));
        assertEquals(2, Factorial.fact(2));
        assertEquals(6, Factorial.fact(3));
        assertEquals(3628800, Factorial.fact(10));
        assertEquals(2432902008176640000L, Factorial.fact(20));

    }

    @Test
    void negative(){
        //对异常进行测试
        //JUnit提供assertThrows()来期望捕获一个指定的异常。
        // 第二个参数Executable封装了我们要执行的会产生异常的代码。
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Factorial.fact(-1);
            }
        });
        //Java 8开始引入了函数式编程，所有单方法接口都可以简写如下：
        assertThrows(IllegalArgumentException.class, () -> {
            Factorial.fact(-1);
        });
    }

    @Test
    void bound(){

        assertThrows(ArithmeticException.class,()->{
            Factorial.fact(21);
        });

    }

    //有些时候，我们需要排出某些@Test方法，不要让它运行，这时，我们就可以给它标记一个@Disabled
    //类似@Disabled这种注解就称为条件测试，JUnit根据不同的条件注解，决定是否运行当前的@Test方法
    @Disabled
    @Test
    void floatTest(){

        //使用浮点数时，由于浮点数无法精确地进行比较，因此，我们需要调用assertEquals(double expected, double actual, double delta)这个重载方法，指定一个误差值：
        assertEquals(0.1, Math.abs(1 - 9 / 10.0), 0.0000001);

    }
}