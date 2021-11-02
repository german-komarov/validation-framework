package rules;

import holder.JdbcTemplateHolder;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class DatabaseAwareRule<T> extends Rule<T> {

    protected final String tableName;
    protected final String columnName;


    public DatabaseAwareRule(String tableName, String columnName) {
        this.tableName = tableName;
        this.columnName = columnName;
    }

    protected final JdbcTemplate jdbcTemplate = JdbcTemplateHolder.getJdbcTemplate();

    protected boolean exists(Object value) {
        boolean exists =
                this.jdbcTemplate
                        .query(
                                String.format("select (case when (count(*)>0) then true else false end) from %s where %s=?",
                                        this.tableName,
                                        this.columnName),
                                ps -> ps.setObject(1, value),
                                (rs, i) -> rs.getBoolean(1)).get(0);

        return exists;
    }



}
