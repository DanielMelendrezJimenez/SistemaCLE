/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.alumnos_por_grupo;

import config.conexion.IConexion;
import dao.manipula.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import managebean.general.GeneradorSQL;
import managedbean.alumnos.Alumnos_MB;
import managedbean.alumnos_por_grupo.Alumnos_por_grupo_MB;

/**
 *
 * @author wltgm
 */
public class Alumnos_por_grupo_MDAO implements DAO<Alumnos_por_grupo_MB> {

    private static final String ALUMNOS_POR_GRUPO_TAB = "alumnos_por_grupo_tab";

    @Override
    public int registrar(IConexion conexionDB, Alumnos_por_grupo_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "id_grupo",
                "id_alumno",
                "status"
        );
        String camposSQL = GeneradorSQL.camposInsertSQL(cols);
        String valuesSQL = GeneradorSQL.valuesSQL(cols.size());
        String sql = "INSERT INTO " + ALUMNOS_POR_GRUPO_TAB
                + " (" + camposSQL + ") "
                + " VALUES (" + valuesSQL + ")";
        System.out.println(sql + obj.getId_alumno());
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setInt(1, obj.getId_grupo());
        registro.setInt(2, obj.getId_alumno());
        registro.setString(3, obj.getEstatus());
        resulSet = registro.executeUpdate();

        return resulSet;
    }

    @Override
    public int editar(IConexion conexionDB, int id, Alumnos_por_grupo_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "status"
        );
        String camposSQL = GeneradorSQL.camposUpdateSQL(cols);
        String sql = "UPDATE " + ALUMNOS_POR_GRUPO_TAB + " SET "
                + camposSQL
                + " WHERE id=?";
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setString(1,obj.getEstatus());
        registro.setInt(2, id);
        resulSet = registro.executeUpdate();
        return resulSet;
    }

    @Override
    public Alumnos_por_grupo_MB eliminar(IConexion conexionDB, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Alumnos_por_grupo_MB> consultar(IConexion conexionDB) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Alumnos_por_grupo_MB encontrarId(IConexion conexionDB, int id) throws SQLException {
        Alumnos_por_grupo_MB alugrupo = new Alumnos_por_grupo_MB();
        String sql = "select * from alumnos_por_grupo_tab where id='" + id + "'";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            alugrupo.setId(rs.getInt(1));
            alugrupo.setId_grupo(rs.getInt(2));
            alugrupo.setId_alumno(rs.getInt(3));
            alugrupo.setUltima_mod(rs.getDate(4));
            alugrupo.setId_usuario_mod(rs.getInt(5));
            alugrupo.setEstatus(rs.getString(6));
        }
        return alugrupo;
    }

    public Alumnos_MB encontrarIdAlumno(IConexion conexionDB, int id) throws SQLException {
        Alumnos_MB alumno = new Alumnos_MB();
        String sql = "select alumno.* from sistemacle.alumnos_por_grupo_tab grupo join sistemacle.alumnos_tab alumno on grupo.id_alumno=alumno.id where grupo.id='" + id + "'";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            alumno.setId(rs.getInt(1));
            alumno.setNombre(rs.getString(2));
            alumno.setAp_pat(rs.getString(3));
            alumno.setAp_mat(rs.getString(4));
            alumno.setGenero(rs.getString(5));
            alumno.setNo_control(rs.getString(6));
            alumno.setCorreo(rs.getString(7));
            alumno.setTipo_alumno(rs.getString(8));
            alumno.setTelefono(rs.getString(9));
            alumno.setCarrera(rs.getString(10));
            alumno.setFecha_naci(rs.getString(11));

        }
        return alumno;
    }

}
