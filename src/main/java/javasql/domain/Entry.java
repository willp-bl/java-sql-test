package javasql.domain;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Entry {

    private int id;
    private Company company;
    private String username;
    private String firstname;
    private String surname;

    public Entry(int id, Company company, String username, String firstname, String surname) {

        this.id = id;
        this.company = company;
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public static class Mapper implements ResultSetMapper<Entry> {
        public Entry map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
            return new Entry(resultSet.getInt("id"),
                    Company.valueOf(resultSet.getString("company")),
                    resultSet.getString("username"),
                    resultSet.getString("firstname"),
                    resultSet.getString("surname"));
        }
    }

}
