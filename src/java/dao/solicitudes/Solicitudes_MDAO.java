/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.solicitudes;

import config.conexion.IConexion;
import dao.manipula.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import managebean.general.GeneradorSQL;
import managedean.solicitudes.Solicitudes_MB;
import managedean.solicitudes.Solicitudes_tabla_MB;

/**
 *
 * @author Daniel Melendrez
 */
public class Solicitudes_MDAO implements DAO<Solicitudes_MB> {

    private static final String SOLICITUDES_TAB = "solicitudes_tab";

    @Override
    public int registrar(IConexion conexionDB, Solicitudes_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "concepto",
                "descripcion",
                "id_alumno",
                "campo_editar",
                "valor_nuevo",
                "origen",
                "status"
        );

        String camposSQL = GeneradorSQL.camposInsertSQL(cols);
        String valuesSQL = GeneradorSQL.valuesSQL(cols.size());

        String sql = "INSERT INTO " + SOLICITUDES_TAB
                + " (" + camposSQL + ") "
                + "VALUES (" + valuesSQL + ")";

        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);
        registro.setString(1, obj.getConcepto());
        registro.setString(2, obj.getDescripcion());
        registro.setInt(3, obj.getId_alumno());
        registro.setString(4, obj.getCampo_editar());
        registro.setString(5, obj.getValor_nuevo());
        registro.setString(6, obj.getOrigen());
        registro.setString(7, obj.getEstatus());

        resulSet = registro.executeUpdate();

        return resulSet;
    }

    public List<Solicitudes_tabla_MB> verPendientes(IConexion conexionDB, String status) throws SQLException {
        List<Solicitudes_tabla_MB> lstSolicitudes = new ArrayList<>();

        String sql = "SELECT solicitudes.id, solicitudes.concepto, solicitudes.descripcion, solicitudes.id_alumno, "
                + "CONCAT_WS(' ',alumno.nombre,alumno.ap_pat,alumno.ap_mat), alumno.no_control, solicitudes.campo_editar, solicitudes.valor_nuevo, solicitudes.origen, solicitudes.status "
                + "FROM sistemacle.solicitudes_tab solicitudes join sistemacle.alumnos_tab alumno on alumno.id=solicitudes.id_alumno where solicitudes.status='" + status + "'";

        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstSolicitudes.add(new Solicitudes_tabla_MB(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10)
            ));
        }

        return lstSolicitudes;
    }

    public int editar(IConexion conexionDB, String id) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "status"
        );
        String camposSQL = GeneradorSQL.camposUpdateSQL(cols);
        String sql = "UPDATE " + SOLICITUDES_TAB + " SET "
                + camposSQL
                + " WHERE id=?";
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setString(1, "APROBADA");
        registro.setString(2, id);
        resulSet = registro.executeUpdate();

        return resulSet;
    }

    @Override
    public Solicitudes_MB eliminar(IConexion conexionDB, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Solicitudes_MB> consultar(IConexion conexionDB) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Solicitudes_MB encontrarId(IConexion conexionDB, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int editar(IConexion conexionDB, int id, Solicitudes_MB nvoObj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
