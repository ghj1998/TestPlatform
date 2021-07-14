import com.gao.pojo.TestInput;
import com.gao.service.TestInputService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test(){
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        final TestInputService inputService = context.getBean("TestInputService", TestInputService.class);

        for (TestInput input : inputService.queryAllTestInput()) {
            System.out.println(input);
        }

    }
}
