package view.inc;
import org.springframework.jdbc.core.RowMapper;
import view.inc.model.ProcessoIlicito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProcessoRowMapper implements RowMapper<ProcessoIlicito> {
    @Override
    public ProcessoIlicito mapRow(ResultSet resultSet, int i) throws SQLException {
        ProcessoIlicito processoIlicito = null;
        try {
            processoIlicito = new ProcessoIlicito();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        processoIlicito.setIdProcessoIlicito(resultSet.getInt("idSoftwarePermitido"));
        processoIlicito.setFkProcesso(resultSet.getInt("fkSoftware"));
        processoIlicito.setNomeProcesso(resultSet.getString("nomeSoftware"));
        return processoIlicito;

    }

}
