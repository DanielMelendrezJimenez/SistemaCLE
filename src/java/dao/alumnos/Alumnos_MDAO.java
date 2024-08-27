/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.alumnos;

import config.conexion.IConexion;
import dao.manipula.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import managebean.general.GeneradorSQL;
import managedbean.alumnos.Alumnos_MB;
import managedbean.inscripciones.Preinscritos_MB;

/**
 *
 * @author Daniel Melendrez
 */
public class Alumnos_MDAO implements DAO<Alumnos_MB> {

    private static final String ALUMNOS_TAB = "alumnos_tab";

    @Override
    public int registrar(IConexion conexionDB, Alumnos_MB obj) throws SQLException {
        int idGenerado = -1; // Inicializamos a -1 por si no se genera correctamente el ID
        List<String> cols = GeneradorSQL.columnas(
                "nombre",
                "ap_pat",
                "ap_mat",
                "genero",
                "correo",
                "telefono",
                "status"
        );
        String camposSQL = GeneradorSQL.camposInsertSQL(cols);
        String valuesSQL = GeneradorSQL.valuesSQL(cols.size());
        String sql = "INSERT INTO " + ALUMNOS_TAB
                + " (" + camposSQL + ") "
                + " VALUES (" + valuesSQL + ")";

        // Indicamos que queremos obtener el ID generado automáticamente
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        registro.setString(1, obj.getNombre());
        registro.setString(2, obj.getAp_pat());
        registro.setString(3, obj.getAp_mat());
        registro.setString(4, obj.getGenero());
        registro.setString(5, obj.getCorreo());
        registro.setString(6, obj.getTelefono());
        registro.setString(7, obj.getEstatus());

        int resulSet = registro.executeUpdate();

        // Si se insertó correctamente, obtenemos el ID generado
        if (resulSet > 0) {
            ResultSet generatedKeys = registro.getGeneratedKeys();
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1); // Obtenemos el ID generado
            } else {
                throw new SQLException("No se pudo obtener el ID generado para el alumno.");
            }
        }

        return idGenerado;
    }

    @Override
    public int editar(IConexion conexionDB, int id, Alumnos_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "nombre",
                "ap_pat",
                "ap_mat",
                "genero",
                "correo",
                "tipo_alumno",
                "telefono",
                "carrera",
                "ultima_modificacion"
        );
        String camposSQL = GeneradorSQL.camposUpdateSQL(cols);
        String sql = "UPDATE " + ALUMNOS_TAB + " SET "
                + camposSQL
                + " WHERE id=?";
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setString(1, obj.getNombre());
        registro.setString(2, obj.getAp_pat());
        registro.setString(3, obj.getAp_mat());
        registro.setString(4, obj.getGenero());
        registro.setString(5, obj.getCorreo());
        registro.setString(6, obj.getTipo_alumno());
        registro.setString(7, obj.getTelefono());
        registro.setString(8, obj.getCarrera());
        registro.setDate(9, obj.getUltima_modificacion());
        registro.setInt(10, id);
        resulSet = registro.executeUpdate();
        return resulSet;
    }

    public int editarA(IConexion conexionDB, String noControl, Alumnos_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "correo",
                "telefono",
                "status"
        );
        String camposSQL = GeneradorSQL.camposUpdateSQL(cols);
        String sql = "UPDATE " + ALUMNOS_TAB + " SET "
                + camposSQL
                + " WHERE no_control=?";
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setString(1, obj.getCorreo());
        registro.setString(2, obj.getTelefono());
        registro.setString(3, obj.getEstatus());
        registro.setString(4, noControl);
        resulSet = registro.executeUpdate();

        return resulSet;
    }

    public int editAtemporal(IConexion conexionDB, String id) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "status"
        );
        String camposSQL = GeneradorSQL.camposUpdateSQL(cols);
        String sql = "UPDATE " + ALUMNOS_TAB + " SET "
                + camposSQL
                + " WHERE id=?";
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setString(1, "PREINSCRITO");
        registro.setString(2, id);
        resulSet = registro.executeUpdate();

        return resulSet;
    }

//    public int completarAlumno(IConexion conexionDB, int id, Alumnos_MB obj) throws SQLException {
//        int resulSet;
//
//        List<String> cols = GeneradorSQL.columnas(
//                "correo",
//                "tipo_alumno",
//                "telefono",
//                "carrera",
//                "ultima_modificacion",
//                "status"
//        );
//        String camposSQL = GeneradorSQL.camposUpdateSQL(cols);
//        String sql = "UPDATE " + ALUMNOS_TAB + " SET "
//                + camposSQL
//                + " WHERE id=?";
//        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);
//
//        registro.setString(1, obj.getCorreo());
//        registro.setString(2, obj.getTipo_alumno());
//        registro.setString(3, obj.getTelefono());
//        registro.setString(4, obj.getCarrera());
//        registro.setDate(5, obj.getUltima_modificacion());
//        registro.setString(6, obj.getEstatus());
//        registro.setInt(7, id);
//        resulSet = registro.executeUpdate();
//        return resulSet;
//    }
    public int completarAlumno(IConexion conexionDB, int id, Alumnos_MB obj) throws SQLException {
        int resulSet;

        // Obtener el siguiente número de control
        String numeroControl = null;
        String sqlNumeroControl = "SELECT GenerarNumeroControl() AS siguiente_numero_control";
        try (Statement stmt = conexionDB.getConexion().createStatement();
                ResultSet rs = stmt.executeQuery(sqlNumeroControl)) {
            if (rs.next()) {
                numeroControl = rs.getString("siguiente_numero_control");
            }
        }

        // Prepare the SQL for update
        List<String> cols = GeneradorSQL.columnas(
                "correo",
                "tipo_alumno",
                "telefono",
                "carrera",
                "ultima_modificacion",
                "status",
                "no_control" // Include the new column for control number
        );
        String camposSQL = GeneradorSQL.camposUpdateSQL(cols);
        String sql = "UPDATE " + ALUMNOS_TAB + " SET "
                + camposSQL
                + " WHERE id=?";

        try (PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql)) {
            // Set the values for the update
            registro.setString(1, obj.getCorreo());
            registro.setString(2, obj.getTipo_alumno());
            registro.setString(3, obj.getTelefono());
            registro.setString(4, obj.getCarrera());
            registro.setDate(5, obj.getUltima_modificacion());
            registro.setString(6, obj.getEstatus());
            registro.setString(7, numeroControl); // Set the control number
            registro.setInt(8, id); // The ID for the WHERE clause

            resulSet = registro.executeUpdate();
        }

        return resulSet;
    }

    @Override
    public Alumnos_MB eliminar(IConexion conexionDB, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Alumnos_MB> consultar(IConexion conexionDB) throws SQLException {
        List<Alumnos_MB> lstAlumnos = new ArrayList<>();
        String sql = "SELECT id, nombre, ap_pat, ap_mat, no_control, correo, tipo_alumno, status"
                + " FROM " + ALUMNOS_TAB + " WHERE status NOT IN ('0','INACTIVO')";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstAlumnos.add(new Alumnos_MB(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
        }

        return lstAlumnos;
    }

    @Override
    public Alumnos_MB encontrarId(IConexion conexionDB, int id) throws SQLException {
        Alumnos_MB alumno = new Alumnos_MB();
        String sql = "SELECT *"
                + " FROM " + ALUMNOS_TAB
                + " WHERE id=" + id;
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
            alumno.setEstatus(rs.getString(14));
        }
        return alumno;
    }

    public Alumnos_MB encontrarNoControl(IConexion conexionDB, String numero) throws SQLException {
        Alumnos_MB alumno = new Alumnos_MB();
        String sql = "SELECT "
                + "id,nombre,ap_pat,ap_mat,no_control"
                + " FROM " + ALUMNOS_TAB
                + " WHERE no_control='" + numero + "'";
        System.out.println(sql + numero);
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            alumno.setId(rs.getInt(1));
            alumno.setNombre(rs.getString(2));
            alumno.setAp_pat(rs.getString(3));
            alumno.setAp_mat(rs.getString(4));
            alumno.setNo_control(rs.getString(5));
        }
        return alumno;
    }

    public List<Preinscritos_MB> consultarInscritos(IConexion conexionDB) throws SQLException {
        List<Preinscritos_MB> lstUusarios = new ArrayList<>();

        String sql = "SELECT alugrupos.id, CONCAT_WS(\" \", alumnos.nombre, alumnos.ap_pat, alumnos.ap_mat) as Alumno, "
                + "alumnos.no_control as Num_Control, alumnos.status, "
                + "alugrupos.id_grupo, CONCAT_WS(\" \", cursos.nombre, cursos.nivel) as Grupo, "
                + "cursos.aula as Aula, cursos.modalidad, CONCAT_WS(\"-\", cursos.hora_inicio, cursos.hora_fin) as Horario "
                + "FROM sistemacle.alumnos_por_grupo_tab alugrupos "
                + "join sistemacle.grupos_tab grupos on grupos.id=alugrupos.id_grupo "
                + "join sistemacle.alumnos_tab alumnos on alumnos.id=alugrupos.id_alumno "
                + "join sistemacle.cursos_tab cursos on grupos.id_curso=cursos.id "
                + "WHERE alumnos.status IN ('INSCRITO') AND grupos.status NOT IN ('INACTIVO','0');";

        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstUusarios.add(new Preinscritos_MB(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getNString(7),
                    rs.getString(8),
                    rs.getString(9)));
        }

        return lstUusarios;
    }
}
