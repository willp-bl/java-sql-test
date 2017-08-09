package javasql.dao;

import javasql.domain.Company;
import javasql.domain.Entry;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface MySqlAppDao {
    @SqlUpdate("drop table users")
    void dropUserTable();

    @SqlUpdate("create table users (id int primary key, company varchar(100), username varchar(100), firstname varchar(100), surname varchar(100))")
    void createUserTable();

    @SqlUpdate("create table if not exists users (id int primary key, company varchar(100), username varchar(100), firstname varchar(100), surname varchar(100))")
    void createUserTableIfAbsent();

    @SqlUpdate("insert into users (id, company, username, firstname, surname) values (:id, :company, :username, :firstname, :surname)")
    void insertUser(@BindBean Entry entry);

    @SqlUpdate("delete from users where id = :id")
    void deleteUser(@Bind("id") int id);

    @SqlUpdate("update users set username = :username where id = :id")
    void updateUser(@BindBean Entry entry);

    @SqlQuery("select * from users where id = :id")
    @Mapper(Entry.Mapper.class)
    Entry getUser(@Bind("id") int id);

    @SqlQuery("select * from users where company = :company")
    @Mapper(Entry.Mapper.class)
    List<Entry> getUsersForCompany(@Bind("company") Company company);

    @SqlQuery("select * from users")
    @Mapper(Entry.Mapper.class)
    List<Entry> getAllUsers();

    void close();
}
