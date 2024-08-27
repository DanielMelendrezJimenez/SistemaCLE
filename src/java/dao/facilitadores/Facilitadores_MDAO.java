/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.facilitadores;

import config.conexion.IConexion;
import dao.manipula.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import managebean.general.GeneradorSQL;
import managedbean.usuarios.Usuarios_MB;

/**
 *
 * @author wltgm
 */
public class Facilitadores_MDAO implements DAO<Usuarios_MB> {

    private static final String USUARIOS_TAB = "usuarios_tab";
    private static final String GRUPOS_TAB = "grupos_tab";

    @Override
    public List<Usuarios_MB> consultar(IConexion conexionDB) throws SQLException {
        List<Usuarios_MB> lstUusarios = new ArrayList<>();

        String sql = "SELECT "
                + "id,nombre,ap_pat,ap_mat,curp,no_control,correo,status"
                + " FROM " + USUARIOS_TAB + " WHERE tipo_usuario='FACILITADOR' AND status='ACTIVO'";

        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstUusarios.add(new Usuarios_MB(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
        }

        return lstUusarios;
    }

    public List<Usuarios_MB> consultarDisponibles(IConexion conexionDB, Time hora) throws SQLException {
        List<Usuarios_MB> lstUusarios = new ArrayList<>();

        String sql = "SELECT facilitadores.* "
                + "FROM sistemacle.usuarios_tab facilitadores "
                + "LEFT JOIN sistemacle.grupos_tab grupos ON grupos.id_facilitador = facilitadores.id "
                + "LEFT JOIN sistemacle.cursos_tab cursos ON cursos.id = grupos.id_curso "
                + "WHERE facilitadores.tipo_usuario = 'FACILITADOR' "
                + "AND facilitadores.status NOT IN ('INACTIVO', '0') "
                + "GROUP BY facilitadores.id, facilitadores.nombre "
                + "HAVING SUM(CASE WHEN cursos.hora_inicio = '" + hora + "' THEN 1 ELSE 0 END) = 0";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstUusarios.add(new Usuarios_MB(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
        }

        return lstUusarios;
    }

    @Override
    public int registrar(IConexion conexionDB, Usuarios_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "nombre",
                "ap_pat",
                "ap_mat",
                "curp",
                "no_control",
                "contrasena",
                "correo",
                "tipo_usuario",
                "status"
        );
        String camposSQL = GeneradorSQL.camposInsertSQL(cols);
        String valuesSQL = GeneradorSQL.valuesSQL(cols.size());
        String sql = "INSERT INTO " + USUARIOS_TAB
                + " (" + camposSQL + ") "
                + " VALUES (" + valuesSQL + ")";

        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setString(1, obj.getNombre());
        registro.setString(2, obj.getAp_pat());
        registro.setString(3, obj.getAp_mat());
        registro.setString(4, obj.getCurp());
        registro.setString(5, obj.getNo_control());
        registro.setString(6, obj.getContrasena());
        registro.setString(7, obj.getCorreo());
        registro.setString(8, obj.getTipo_usuario());
        registro.setString(9, obj.getEstatus());
        resulSet = registro.executeUpdate();

        return resulSet;
    }

    @Override
    public Usuarios_MB eliminar(IConexion conexionDB, int id) throws SQLException {
        int resultSet;
        Usuarios_MB curso = encontrarId(conexionDB, id);
        if (curso != null) {

            String sql = "UPDATE " + USUARIOS_TAB
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
    public int editar(IConexion conexionDB, int id, Usuarios_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "nombre",
                "ap_pat",
                "ap_mat",
                "no_control",
                "contrasena",
                "correo",
                "status"
        );
        String camposSQL = GeneradorSQL.camposUpdateSQL(cols);
        String sql = "UPDATE " + USUARIOS_TAB + " SET "
                + camposSQL
                + " WHERE id=?";
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setString(1, obj.getNombre());
        registro.setString(2, obj.getAp_pat());
        registro.setString(3, obj.getAp_mat());
        registro.setString(4, obj.getNo_control());
        registro.setString(5, obj.getContrasena());
        registro.setString(6, obj.getCorreo());
        registro.setString(7, obj.getEstatus());
        registro.setInt(8, id);
        resulSet = registro.executeUpdate();

        return resulSet;
    }

    @Override
    public Usuarios_MB encontrarId(IConexion conexionDB, int id) throws SQLException {
        Usuarios_MB facilitador = new Usuarios_MB();
        String sql = "SELECT "
                + "id,nombre,ap_pat,ap_mat,no_control,correo,status,contrasena"
                + " FROM " + USUARIOS_TAB
                + " WHERE id='" + id + "'";
        System.out.println(sql);
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            facilitador.setId(rs.getInt(1));
            facilitador.setNombre(rs.getString(2));
            facilitador.setAp_pat(rs.getString(3));
            facilitador.setAp_mat(rs.getString(4));
            facilitador.setNo_control(rs.getString(5));
            facilitador.setCorreo(rs.getString(6));
            facilitador.setEstatus(rs.getString(7));
            facilitador.setContrasena(rs.getString(8));
        }
        return facilitador;
    }

    public String encontrarCurp(IConexion conexionDB, String curp) throws SQLException {
        String sql = "SELECT"
                + " curp"
                + " FROM " + USUARIOS_TAB
                + " WHERE curp='" + curp + "'";
        String res = "";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            res = rs.getString(1);
        }
        return res;
    }

    public String encontrarNo_control(IConexion conexionDB, String no_control) throws SQLException {
        String sql = "SELECT"
                + " no_control"
                + " FROM " + USUARIOS_TAB
                + " WHERE no_control='" + no_control + "'";
        String res = "";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            res = rs.getString(1);
        }
        return res;
    }

    public Integer verificarEliminar(IConexion conexionDB, int id_facilitador) throws SQLException {
        String sql = "SELECT"
                + " cast(count(*) as signed)"
                + " FROM " + GRUPOS_TAB
                + " WHERE id_facilitador=" + id_facilitador + " AND status='ACTIVO'";
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
