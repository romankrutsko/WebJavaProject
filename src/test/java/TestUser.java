import com.example.workshop.services.UserService;
import org.junit.Before;

public class TestUser {
    private final UserService userService;

    public TestUser(UserService userService) {
        this.userService = userService;
    }

    @Before
    public void setup() {

    }
}
