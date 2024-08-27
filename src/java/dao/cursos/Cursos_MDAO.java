/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.cursos;

import config.conexion.IConexion;
import dao.manipula.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import managebean.general.GeneradorSQL;
import managedbean.configuracion.Parametros_MB;
import managedbean.cursos.Cursos_MB;

/**
 *
 * @author Daniel Melendrez
 */
public class Cursos_MDAO implements DAO<Cursos_MB> {

    private static final String CURSOS_TAB = "cursos_tab";
    private static final String GRUPOS_TAB = "grupos_tab";

    @Override
    public List<Cursos_MB> consultar(IConexion conexionDB) throws SQLException {
        List<Cursos_MB> lstCursos = new ArrayList<>();

        String sql = "SELECT "
                + "id,nombre,nivel,modalidad,hora_inicio,hora_fin,aula,status"
                + " FROM " + CURSOS_TAB + " WHERE status NOT IN ('0','INACTIVO')";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstCursos.add(new Cursos_MB(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getTime(5), rs.getTime(6), rs.getString(7), rs.getString(8)));
        }

        return lstCursos;
    }

    public List<Cursos_MB> consultarDisponibles(IConexion conexionDB) throws SQLException {
        List<Cursos_MB> lstCursos = new ArrayList<>();

        String sql = "SELECT cursos.* FROM " + CURSOS_TAB
                + " cursos left join " + GRUPOS_TAB
                + " grupos on cursos.id=grupos.id_curso where grupos.id_curso is null";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstCursos.add(new Cursos_MB(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getTime(5), rs.getTime(6), rs.getString(7), rs.getString(8)));
        }

        return lstCursos;
    }

    @Override
    public int registrar(IConexion conexionDB, Cursos_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "nombre",
                "nivel",
                "modalidad",
                "hora_inicio",
                "hora_fin",
                "aula",
                "status"
        );
        String camposSQL = GeneradorSQL.camposInsertSQL(cols);
        String valuesSQL = GeneradorSQL.valuesSQL(cols.size());
        String sql = "INSERT INTO " + CURSOS_TAB
                + " (" + camposSQL + ") "
                + " VALUES (" + valuesSQL + ")";

        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setString(1, obj.getNombre());
        registro.setInt(2, obj.getNivel());
        registro.setString(3, obj.getModalidad());
        registro.setTime(4, obj.getHora_inicio());
        registro.setTime(5, obj.getHora_fin());
        registro.setString(6, obj.getAula());
        registro.setString(7, "OFERTADO");
        resulSet = registro.executeUpdate();

        return resulSet;
    }

    @Override
    public int editar(IConexion conexionDB, int id, Cursos_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "nombre",
                "nivel",
                "modalidad",
                "hora_inicio",
                "hora_fin",
                "aula",
                "status"
        );
        String camposSQL = GeneradorSQL.camposUpdateSQL(cols);
        String sql = "UPDATE " + CURSOS_TAB + " SET "
                + camposSQL
                + " WHERE id=?";
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setString(1, obj.getNombre());
        registro.setInt(2, obj.getNivel());
        registro.setString(3, obj.getModalidad());
        registro.setTime(4, obj.getHora_inicio());
        registro.setTime(5, obj.getHora_fin());
        registro.setString(6, obj.getAula());
        registro.setString(7, obj.getEstatus());
        registro.setInt(8, id);
        resulSet = registro.executeUpdate();

        return resulSet;
    }

    @Override
    public Cursos_MB eliminar(IConexion conexionDB, int id) throws SQLException {
        int resultSet;

        Cursos_MB curso = encontrarId(conexionDB, id);
        if (curso != null) {

            String sql = "UPDATE " + CURSOS_TAB
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
    public Cursos_MB encontrarId(IConexion conexionDB, int id) throws SQLException {
        Cursos_MB curso = new Cursos_MB();
        String sql = "SELECT "
                + "id,nombre,nivel,modalidad,hora_inicio,hora_fin,aula,status"
                + " FROM " + CURSOS_TAB
                + " WHERE id='" + id + "'";
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
        return curso;
    }

    public Cursos_MB consultar_existencia(IConexion conexionDB, String aula, String hora_ini) throws SQLException {
        Cursos_MB curso = new Cursos_MB();
        String sql = "SELECT "
                + "id,nombre,nivel,modalidad,hora_inicio,hora_fin,aula,status"
                + " FROM " + CURSOS_TAB
                + " WHERE status='OFERTADO' AND aula='" + aula + "' "
                + " AND hora_inicio='" + hora_ini + "'";
        System.out.println("SQL: " + sql);
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
        return curso;
    }

    public Cursos_MB consultar_grupo(IConexion conexionDB, Integer idcurso) throws SQLException {
        Cursos_MB curso = new Cursos_MB();
        String sql = "SELECT curso.* "
                + " FROM " + GRUPOS_TAB + " grupo "
                + " JOIN " + CURSOS_TAB + " curso on curso.id=grupo.id_curso "
                + " WHERE grupo.status NOT IN ('0','INACTIVO') "
                + " AND curso.id=" + idcurso;

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
        return curso;
    }
}
