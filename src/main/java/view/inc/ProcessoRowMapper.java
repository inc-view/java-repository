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
        ProcessoIlicito processoIlicito = new ProcessoIlicito();
        processoIlicito.setIdProcessoIlicito(resultSet.getInt("idSoftwarePermitido"));
        processoIlicito.setFkProcesso(resultSet.getInt("fkSoftware"));
        processoIlicito.setNomeProcesso(resultSet.getString("nomeSoftware"));
        return processoIlicito;

    }

}
