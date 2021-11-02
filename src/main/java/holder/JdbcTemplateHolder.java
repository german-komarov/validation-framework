package holder;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcTemplateHolder {

    private static final JdbcTemplate dao = init();

    public static JdbcTemplate getJdbcTemplate() {
        return dao;
    }



    private static JdbcTemplate init() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setPassword("password");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/validation_test");
        dataSource.setUsername("postgres");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

}
