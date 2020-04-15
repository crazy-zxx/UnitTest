import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    Config config;

    @BeforeEach
    void setUp() {
        this.config = new Config();
    }

    @AfterEach
    void tearDown() {
        this.config = null;
    }

    //只能在Windows上跑
    @Test
    @EnabledOnOs({OS.WINDOWS})
    void testWindows() {
        assertEquals("C:\\test.ini", config.getConfigFile("test.ini"));
    }

    //只能在Mac/Linux上跑
    @Test
    @EnabledOnOs({OS.LINUX,OS.MAC})
    void testLinuxAndMac() {
        assertEquals("/usr/local/test.cfg", config.getConfigFile("test.cfg"));
    }

    //不在Windows平台执行的测试，可以加上@DisabledOnOs(OS.WINDOWS)
    //只能在Java 9或更高版本执行的测试，可以加上@DisabledOnJre(JRE.JAVA_8)
    //只能在64位操作系统上执行的测试，可以用@EnabledIfSystemProperty判断
    //需要传入环境变量DEBUG=true才能执行的测试，可以用@EnabledIfEnvironmentVariable

    //万能的@EnableIf可以执行任意Java语句并根据返回的boolean决定是否执行测试
    //应尽可能避免使用已弃用的 @EnableIf API，因为此类API可能会在即将发布的版本中删除
    @Test
    @EnabledIf("java.time.LocalDate.now().getDayOfWeek()==java.time.DayOfWeek.SUNDAY")
    void testOnlyOnSunday() {
        // TODO: this test is only run on Sunday
    }

}