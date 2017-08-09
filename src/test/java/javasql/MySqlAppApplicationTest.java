package javasql;

import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class MySqlAppApplicationTest {

    @ClassRule
    public static DropwizardAppRule<MySqlAppConfiguration> appRule = new DropwizardAppRule<MySqlAppConfiguration>(MySqlAppApplication.class, "mysqlapp.yml");

    @Test
    public void appStarts() {
        final Response response = appRule.client().target("http://localhost:"+appRule.getLocalPort()+"/").request().get();
        assertThat(response.getStatus()).isEqualTo(404);
    }

}