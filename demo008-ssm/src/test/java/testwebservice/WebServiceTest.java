package testwebservice;

import com.awaion.demo008.cxf.ICalculateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-cxf-client.xml")
public class WebServiceTest {

    @Autowired
    ICalculateService calculateServic;

    @Test
    public void test() {
        int sum = calculateServic.sum(11, 13);
        System.out.println("计算结果为:" + sum);
    }

}
