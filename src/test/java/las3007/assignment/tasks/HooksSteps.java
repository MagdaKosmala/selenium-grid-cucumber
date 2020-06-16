package las3007.assignment.tasks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.net.MalformedURLException;

public class HooksSteps extends BaseTest {
    @Before
    public void setup() throws MalformedURLException {
        browserName = System.getenv("browserName");
        if (browserName == null) browserName = "chrome";

        getDriver(browserName);
    }

    @After
    private void tearDownDriver() {
        tearDown();
    }
}
