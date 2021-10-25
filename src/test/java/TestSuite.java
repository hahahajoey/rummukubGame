import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { AppTest.class, TableReuseTest.class, CucumberTestSuite.class} )
public class TestSuite {
}