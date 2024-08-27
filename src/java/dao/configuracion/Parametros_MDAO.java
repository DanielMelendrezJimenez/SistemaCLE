/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.configuracion;

import config.conexion.IConexion;
import dao.manipula.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import managebean.general.GeneradorSQL;
import managedbean.configuracion.Parametros_MB;

/**
 *
 * @author Daniel Melendrez
 */
public class Parametros_MDAO implements DAO<Parametros_MB> {

    private static final String PARAMETROS_TAB = "parametros_tab";

    @Override
    public int registrar(IConexion conexionDB, Parametros_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "concepto",
                "fecha_inicio",
                "fecha_fin",
                "status"
        );
        String camposSQL = GeneradorSQL.camposInsertSQL(cols);
        String valuesSQL = GeneradorSQL.valuesSQL(cols.size());
        String sql = "INSERT INTO " + PARAMETROS_TAB
                + " (" + camposSQL + ") "
                + " VALUES (" + valuesSQL + ")";
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setString(1, obj.getConcepto());
        registro.setDate(2, obj.getFecha_inicio());
        registro.setDate(3, obj.getFecha_fin());
        registro.setString(4, "ACTIVO");
        resulSet = registro.executeUpdate();

        return resulSet;
    }

    @Override
    public int editar(IConexion conexionDB, int id, Parametros_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "concepto",
                "fecha_inicio",
                "fecha_fin",
                "status"
        );
        String camposSQL = GeneradorSQL.camposUpdateSQL(cols);
        String sql = "UPDATE " + PARAMETROS_TAB + " SET "
                + camposSQL
                + " WHERE id=?";
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setString(1, obj.getConcepto());
        registro.setDate(2, obj.getFecha_inicio());
        registro.setDate(3, obj.getFecha_fin());
        registro.setString(4, obj.getEstatus());
        registro.setInt(5, id);
        resulSet = registro.executeUpdate();

        return resulSet;
    }

    @Override
    public Parametros_MB eliminar(IConexion conexionDB, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Parametros_MB> consultar(IConexion conexionDB) throws SQLException {
        List<Parametros_MB> lstParametros = new ArrayList<>();

        String sql = "SELECT "
                + "id,Concepto,fecha_inicio,fecha_fin,status"
                + " FROM " + PARAMETROS_TAB + " WHERE status NOT IN ('0','INACTIVO')";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstParametros.add(new Parametros_MB(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5)));
        }

        return lstParametros;
    }

    public Parametros_MB consultar_existencia(IConexion conexionDB, String concepto) throws SQLException {
        Parametros_MB para = new Parametros_MB();
        String sql = "SELECT "
                + "id,concepto,fecha_inicio,fecha_fin,status"
                + " FROM " + PARAMETROS_TAB
                + " WHERE status='ACTIVO' AND concepto='" + concepto + "'";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            para.setId(rs.getInt(1));
            para.setConcepto(rs.getString(2));
            para.setFecha_inicio(rs.getDate(3));
            para.setFecha_fin(rs.getDate(4));
            para.setEstatus(rs.getString(5));
        }
        return para;
    }

    @Override
    public Parametros_MB encontrarId(IConexion conexionDB, int id) throws SQLException {
        Parametros_MB para = new Parametros_MB();
        String sql = "SELECT "
                + "id,concepto,fecha_inicio,fecha_fin,status"
                + " FROM " + PARAMETROS_TAB
                + " WHERE id='" + id + "'";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            para.setId(rs.getInt(1));
            para.setConcepto(rs.getString(2));
            para.setFecha_inicio(rs.getDate(3));
            para.setFecha_fin(rs.getDate(4));
            para.setEstatus(rs.getString(5));
        }
        return para;
    }

}
