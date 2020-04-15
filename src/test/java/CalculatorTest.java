import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    //JUnit提供了编写测试前准备、测试后清理的固定代码，我们称之为Fixture。
    /*
    总结出编写Fixture的套路如下：
        对于实例变量，在@BeforeEach中初始化，在@AfterEach中清理，它们在各个@Test方法中 互不影响，因为是不同的实例；
        对于静态变量，在@BeforeAll中初始化，在@AfterAll中清理，它们在各个@Test方法中均是 唯一实例，会影响各个@Test方法。
     */

    //标记为@BeforeEach和@AfterEach的方法，它们会在运行  每个@Test方法 前后自动运行。
    //通过@BeforeEach来初始化，通过@AfterEach来清理资源
    Calculator calculator;

    @BeforeEach
    void setUp() {
        this.calculator=new Calculator();
    }

    @AfterEach
    void tearDown() {
        this.calculator=null;
    }

    //JUnit还提供了@BeforeAll和@AfterAll，它们在运行所有@Test前后 仅运行一次,它们只能初始化静态变量
    //事实上，@BeforeAll和@AfterAll也只能标注在静态方法上。
    /*
    static Database db;

    @BeforeAll
    public static void initDatabase() {
        db = createDb(...);
    }

    @AfterAll
    public static void dropDatabase() {
        ...
    }
     */


    @Test
    void add() {
        assertEquals(100, this.calculator.add(100));
        assertEquals(150, this.calculator.add(50));
        assertEquals(130, this.calculator.add(-20));
    }

    @Test
    void sub() {
        assertEquals(-100, this.calculator.sub(100));
        assertEquals(-150, this.calculator.sub(50));
        assertEquals(-130, this.calculator.sub(-20));
    }
}