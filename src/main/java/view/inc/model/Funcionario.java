package view.inc.model;
import view.inc.dao.FuncionarioDao;
import view.inc.dao.FuncionarioSQLDao;

import java.sql.SQLException;
import java.util.List;

public class Funcionario {

    private Integer idFuncionario;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private Integer fkGestor;
    private Integer fkEmpresa;
    private FuncionarioDao  funcionarioDao = new FuncionarioDao();
    private FuncionarioSQLDao funcionarioSQLDao = new FuncionarioSQLDao();

    public Funcionario() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    }


    /* MÉTODOS */
    public Funcionario logar(String email, String senha){

        if(!email.isEmpty() || !senha.isEmpty()){

            List<Funcionario> dadosFuncionario = funcionarioDao.selectLogin(email, senha);
            if(!dadosFuncionario.isEmpty()){
                return dadosFuncionario.get(0);
            }

        }
        return null;

    }

    public Funcionario logarSql(String email, String senha) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(!email.isEmpty() || !senha.isEmpty()){
            return funcionarioSQLDao.selectLogin(email, senha);
        }
        return null;

    }


    /* GETTERS E SETTERS */
    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getFkGestor() {
        return fkGestor;
    }

    public void setFkGestor(Integer fkGestor) {
        this.fkGestor = fkGestor;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }
}
