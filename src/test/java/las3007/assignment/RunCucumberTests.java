package las3007.assignment;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        strict = true,
        features = "src/test/resources/features",
        glue="las3007.assignment.tasks"
//        tags = {"@mySelectedTest"}
)
public class RunCucumberTests {
}
