package flights.backend;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
class BackendApplicationTests {
    @Test
    void contextLoads() {
        BackendApplication app = mock(BackendApplication.class);
        verify(app, atLeast(1)).main(new String[]{});
    }
}
