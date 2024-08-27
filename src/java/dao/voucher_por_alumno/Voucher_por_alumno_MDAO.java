/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.voucher_por_alumno;

import config.conexion.IConexion;
import dao.manipula.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import managebean.general.GeneradorSQL;
import managedbean.voucher_por_alumno.Voucher_por_alumno_MB;

/**
 *
 * @author Daniel Melendrez
 */
public class Voucher_por_alumno_MDAO implements DAO<Voucher_por_alumno_MB> {

    private static final String VOUCHER_ALUMNO = "voucher_por_alumno_tab";

    @Override
    public int registrar(IConexion conexionDB, Voucher_por_alumno_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "id_alumno",
                "id_grupo",
                "folio",
                "referencia",
                "cantidad",
                "fecha",
                "status"
        );
        String camposSQL = GeneradorSQL.camposInsertSQL(cols);
        String valuesSQL = GeneradorSQL.valuesSQL(cols.size());
        String sql = "INSERT INTO " + VOUCHER_ALUMNO
                + " (" + camposSQL + ") "
                + " VALUES (" + valuesSQL + ")";
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setInt(1, obj.getId_alumno());
        registro.setInt(2, obj.getId_grupo());
        registro.setString(3, obj.getFolio());
        registro.setString(4, obj.getReferencia());
        registro.setBigDecimal(5, obj.getCantidad());
        registro.setDate(6, obj.getFecha());
        registro.setString(7, obj.getEstatus());

        resulSet = registro.executeUpdate();

        return resulSet;
    }

    @Override
    public int editar(IConexion conexionDB, int id, Voucher_por_alumno_MB nvoObj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Voucher_por_alumno_MB eliminar(IConexion conexionDB, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Voucher_por_alumno_MB> consultar(IConexion conexionDB) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Voucher_por_alumno_MB encontrarId(IConexion conexionDB, int id) throws SQLException {
        Voucher_por_alumno_MB voucher = new Voucher_por_alumno_MB();
        String sql = "SELECT *"
                + " FROM " + VOUCHER_ALUMNO
                + " WHERE id='" + id + "'";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            voucher.setId(rs.getInt(1));
            voucher.setId_alumno(rs.getInt(2));
            voucher.setId_grupo(rs.getInt(3));
            voucher.setFolio(rs.getString(4));
            voucher.setReferencia(rs.getString(5));
            voucher.setCantidad(rs.getBigDecimal(6));
            voucher.setFecha(rs.getDate(7));
            voucher.setFecha(rs.getDate(8));
            voucher.setEstatus(rs.getString(9));
        }
        return voucher;
    }

   public String encontrarFolio(IConexion conexionDB, String folio) throws SQLException {
        String sql = "SELECT"
                + " folio"
                + " FROM " + VOUCHER_ALUMNO
                + " WHERE folio='" + folio + "'";
        String res = "";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            res = rs.getString(1);
        }
        return res;
    }

}
