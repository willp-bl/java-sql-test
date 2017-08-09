package javasql;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import javasql.dao.MySqlAppDao;
import javasql.domain.Company;
import javasql.domain.Entry;
import org.skife.jdbi.v2.DBI;

import java.util.UUID;

public class MySqlAppApplication extends Application<MySqlAppConfiguration> {
    public void run(MySqlAppConfiguration mySqlAppConfiguration, Environment environment) throws Exception {

        DBIFactory dbiFactory = new DBIFactory();
        DBI dbi = dbiFactory.build(environment, mySqlAppConfiguration.getDataSourceFactory(), "postgresql");
        final MySqlAppDao mySqlAppDao = dbi.open(MySqlAppDao.class);
        mySqlAppDao.dropUserTable();
        mySqlAppDao.createUserTable();
        mySqlAppDao.createUserTableIfAbsent();
        mySqlAppDao.insertUser(new Entry(1, Company.Foo, "ShouldBeDeleted-"+UUID.randomUUID(), "f", "s"));
        mySqlAppDao.deleteUser(1);
        mySqlAppDao.insertUser(new Entry(1, Company.Foo, "ShouldBeThere-"+UUID.randomUUID(), "f", "s"));
        mySqlAppDao.insertUser(new Entry(2, Company.Bar, "oldName-"+UUID.randomUUID(), "f", "s"));
        mySqlAppDao.updateUser(new Entry(2, Company.Bar, "UpdatedName-"+UUID.randomUUID(), "f", "s"));
        System.out.println("All users: "+mySqlAppDao.getAllUsers());
        for(Company c:Company.values()) {
            System.out.println(c+" users: "+mySqlAppDao.getUsersForCompany(c));
        }
        mySqlAppDao.close();

    }

    public static void main(String[] args) throws Exception {
        new MySqlAppApplication().run("server", "mysqlapp.yml");
    }
}
