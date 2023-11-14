package view.inc;
import org.springframework.jdbc.core.RowMapper;
import view.inc.model.ProcessoIlicito;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcessoRowMapper implements RowMapper<ProcessoIlicito> {

    @Override
    public ProcessoIlicito mapRow(ResultSet resultSet, int i) throws SQLException {
        ProcessoIlicito processoIlicito = new ProcessoIlicito();
        processoIlicito.setIdProcessoIlicito(resultSet.getInt("idProcessoIlicito"));
        processoIlicito.setFkProcesso(resultSet.getInt("fkProcesso"));
        processoIlicito.setNomeProcesso(resultSet.getString("nomeProcesso"));
        return processoIlicito;

    }

}
