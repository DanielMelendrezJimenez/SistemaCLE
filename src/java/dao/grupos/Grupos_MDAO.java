/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.grupos;

import config.conexion.IConexion;
import dao.manipula.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import managebean.general.GeneradorSQL;
import managedbean.cursos.Cursos_MB;
import managedbean.grupos.Grupos_Consultar_MB;
import managedbean.grupos.Grupos_MB;

/**
 *
 * @author wltgm
 */
public class Grupos_MDAO implements DAO<Grupos_MB> {

    private static final String GRUPOS_TAB = "grupos_tab";
    private static final String ALU_GRUPOS_TAB = "alumnos_por_grupo_tab";

    @Override
    public int registrar(IConexion conexionDB, Grupos_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "id_curso",
                "id_facilitador",
                "trimestre",
                "ultima_modificacion",
                "id_usuario_modifico",
                "status"
        );
        String camposSQL = GeneradorSQL.camposInsertSQL(cols);
        String valuesSQL = GeneradorSQL.valuesSQL(cols.size());
        String sql = "INSERT INTO " + GRUPOS_TAB
                + " (" + camposSQL + ") "
                + " VALUES (" + valuesSQL + ")";

        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setInt(1, obj.getId_curso());
        registro.setInt(2, obj.getId_facilitador());
        registro.setString(3, obj.getTrimestre());
        registro.setDate(4, obj.getUltima_modificacion());
        registro.setInt(5, obj.getId_usuario_modifico());
        registro.setString(6, "ACTIVO");
        resulSet = registro.executeUpdate();

        return resulSet;
    }

    public List<Grupos_Consultar_MB> consultarGrupos(IConexion conexionDB) throws SQLException {
        List<Grupos_Consultar_MB> lstUusarios = new ArrayList<>();

        String sql = "SELECT grupos.id, CONCAT_WS(\" \", cursos.nombre, cursos.nivel) as Curso, "
                + "CONCAT_WS(\" \", facilitadores.nombre, facilitadores.ap_pat, facilitadores.ap_mat) as Facilitador, "
                + "cursos.modalidad as Modalidad, CONCAT_WS(\"-\", cursos.hora_inicio,cursos.hora_fin) as Horario , "
                + "CONCAT_WS(\" \", trimestre.concepto, trimestre.fecha_inicio,trimestre.fecha_fin) as trimestre, grupos.status as Status  "
                + "FROM sistemacle.grupos_tab grupos "
                + "join usuarios_tab facilitadores on grupos.id_facilitador=facilitadores.id "
                + "join cursos_tab cursos on grupos.id_curso=cursos.id "
                + "join parametros_tab trimestre on grupos.trimestre=trimestre.id "
                + "WHERE grupos.status NOT IN(\"INACTIVO \",\"0\") "
                + "order by grupos.id;";

        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstUusarios.add(new Grupos_Consultar_MB(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getNString(7)));
        }

        return lstUusarios;
    }

    public Grupos_Consultar_MB consultarGrupoPorId(IConexion conexionDB, int id_grupo) throws SQLException {
        Grupos_Consultar_MB grupo = null;

        String sql = "SELECT grupos.id, CONCAT_WS(\" \", cursos.nombre, cursos.nivel) as Curso, "
                + "CONCAT_WS(\" \", facilitadores.nombre, facilitadores.ap_pat, facilitadores.ap_mat) as Facilitador, "
                + "cursos.modalidad as Modalidad, CONCAT_WS(\"-\", cursos.hora_inicio,cursos.hora_fin) as Horario , "
                + "CONCAT_WS(\" \", trimestre.concepto, trimestre.fecha_inicio,trimestre.fecha_fin) as trimestre, grupos.status as Status  "
                + "FROM sistemacle.grupos_tab grupos "
                + "join usuarios_tab facilitadores on grupos.id_facilitador=facilitadores.id "
                + "join cursos_tab cursos on grupos.id_curso=cursos.id "
                + "join parametros_tab trimestre on grupos.trimestre=trimestre.id "
                + "WHERE grupos.status NOT IN(\"INACTIVO \",\"0\") AND grupos.id=" + id_grupo + " "
                + "order by grupos.id";

        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            grupo = new Grupos_Consultar_MB(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getNString(7));
        }
        return grupo;
    }

    @Override
    public int editar(IConexion conexionDB, int id, Grupos_MB nvoObj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Grupos_MB eliminar(IConexion conexionDB, int id) throws SQLException {
        int resultSet;

        Grupos_MB curso = encontrarId(conexionDB, id);
        if (curso != null) {

            String sql = "UPDATE " + GRUPOS_TAB
                    + " SET status='0' WHERE id=?";
            PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);
            registro.setInt(1, id);
            resultSet = registro.executeUpdate();
            if (resultSet > 0) {
                return curso;
            } else {
                return null;
            }

        }
        return curso;
    }

    @Override
    public List<Grupos_MB> consultar(IConexion conexionDB) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Grupos_MB encontrarId(IConexion conexionDB, int id) throws SQLException {
        Grupos_MB grupo = new Grupos_MB();
        String sql = "SELECT "
                + "id,id_curso,id_facilitador,trimestre,ultima_modificacion,id_usuario_modifico,status"
                + " FROM " + GRUPOS_TAB
                + " WHERE id='" + id + "'";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            grupo.setId(rs.getInt(1));
            grupo.setId_curso(rs.getInt(2));
            grupo.setId_facilitador(rs.getInt(3));
            grupo.setTrimestre(rs.getString(4));
            grupo.setUltima_modificacion(rs.getDate(5));
            grupo.setId_usuario_modifico(rs.getInt(6));
            grupo.setEstatus(rs.getString(7));
        }
        return grupo;
    }

    public Cursos_MB encontrarCurso(IConexion conexionDB, int id) throws SQLException {
        Cursos_MB curso = new Cursos_MB();
        String sql = "SELECT "
                + "cursos.* "
                + "FROM " + GRUPOS_TAB + " grupos "
                + "join cursos_tab cursos on cursos.id = grupos.id_curso "
                + "WHERE grupos.id='" + id + "'";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            curso.setId(rs.getInt(1));
            curso.setNombre(rs.getString(2));
            curso.setNivel(rs.getInt(3));
            curso.setModalidad(rs.getString(4));
            curso.setHora_inicio(rs.getTime(5));
            curso.setHora_fin(rs.getTime(6));
            curso.setAula(rs.getString(7));
            curso.setEstatus(rs.getString(8));
        }
        System.out.println("SQL: " + sql);
        return curso;
    }

    public Integer verificarEliminar(IConexion conexionDB, int id_grupo) throws SQLException {
        String sql = "SELECT"
                + " cast(count(*) as signed)"
                + " FROM " + ALU_GRUPOS_TAB
                + " WHERE id_grupo=" + id_grupo;
        int res = 0;
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            res = rs.getInt(1);
        }
        return res;
    }
}
