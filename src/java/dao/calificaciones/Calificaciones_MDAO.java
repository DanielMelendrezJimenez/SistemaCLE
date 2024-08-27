/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.calificaciones;

import config.conexion.IConexion;
import dao.manipula.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import managebean.general.GeneradorSQL;
import managedbean.alumnos.Alumnos_MB;
import managedbean.calificaciones.Calificaciones_MB;
import managedbean.calificaciones.Calificaciones_consultar_MB;
import managedbean.grupos.Grupos_Consultar_MB;

/**
 *
 * @author wltgm
 */
public class Calificaciones_MDAO implements DAO<Calificaciones_MB> {

    private static final String GRUPOS_TAB = "grupos_tab";
    private static final String CALIFICACIONES_TAB = "calificaciones_tab";

    @Override
    public int registrar(IConexion conexionDB, Calificaciones_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "id_grupo",
                "id_alumno",
                "listening",
                "reading",
                "writing",
                "speaking",
                "promedio",
                "status"
        );
        String camposSQL = GeneradorSQL.camposInsertSQL(cols);
        String valuesSQL = GeneradorSQL.valuesSQL(cols.size());
        String sql = "INSERT INTO " + CALIFICACIONES_TAB
                + " (" + camposSQL + ") "
                + " VALUES (" + valuesSQL + ")";
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setInt(1, obj.getId_grupo());
        registro.setInt(2, obj.getId_alumno());
        registro.setInt(3, obj.getListening());
        registro.setInt(4, obj.getReading());
        registro.setInt(5, obj.getWriting());
        registro.setInt(6, obj.getSpeaking());
        registro.setInt(7, obj.getPromedio());
        registro.setString(8, "ACTIVO");
        resulSet = registro.executeUpdate();

        return resulSet;
    }

    public List<Grupos_Consultar_MB> consultarGruposFacilitador(IConexion conexionDB, int id_facilitador) throws SQLException {
        List<Grupos_Consultar_MB> lstUusarios = new ArrayList<>();

        String sql = "SELECT grupos.id, CONCAT_WS(\" \", cursos.nombre, cursos.nivel) as Curso, "
                + "CONCAT_WS(\" \", facilitadores.nombre, facilitadores.ap_pat, facilitadores.ap_mat) as Facilitador, "
                + "cursos.modalidad as Modalidad, CONCAT_WS(\"-\", cursos.hora_inicio,cursos.hora_fin) as Horario , "
                + "CONCAT_WS(\" \", trimestre.concepto, trimestre.fecha_inicio,trimestre.fecha_fin) as trimestre, grupos.status as Status  "
                + "FROM sistemacle.grupos_tab grupos "
                + "join usuarios_tab facilitadores on grupos.id_facilitador=facilitadores.id "
                + "join cursos_tab cursos on grupos.id_curso=cursos.id "
                + "join parametros_tab trimestre on grupos.trimestre=trimestre.id "
                + "WHERE grupos.status NOT IN(\"INACTIVO \",\"0\") AND grupos.id_facilitador=" + id_facilitador
                + " order by grupos.id;";

        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstUusarios.add(new Grupos_Consultar_MB(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getNString(7)));
        }

        return lstUusarios;
    }

    public List<Grupos_Consultar_MB> consultarGruposCoorinador(IConexion conexionDB) throws SQLException {
        List<Grupos_Consultar_MB> lstUusarios = new ArrayList<>();

        String sql = "SELECT grupos.id, CONCAT_WS(\" \", cursos.nombre, cursos.nivel) as Curso, "
                + "CONCAT_WS(\" \", facilitadores.nombre, facilitadores.ap_pat, facilitadores.ap_mat) as Facilitador, "
                + "cursos.modalidad as Modalidad, CONCAT_WS(\"-\", cursos.hora_inicio,cursos.hora_fin) as Horario , "
                + "CONCAT_WS(\" \", trimestre.concepto, trimestre.fecha_inicio,trimestre.fecha_fin) as trimestre, grupos.status as Status  "
                + "FROM sistemacle.grupos_tab grupos "
                + "join usuarios_tab facilitadores on grupos.id_facilitador=facilitadores.id "
                + "join cursos_tab cursos on grupos.id_curso=cursos.id "
                + "join parametros_tab trimestre on grupos.trimestre=trimestre.id "
                + "WHERE grupos.status NOT IN(\"INACTIVO \",\"0\")"
                + " order by grupos.id;";

        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstUusarios.add(new Grupos_Consultar_MB(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getNString(7)));
        }

        return lstUusarios;
    }

    public List<Calificaciones_consultar_MB> consultarCalificacionesGrupos(IConexion conexionDB, int id_grupo) throws SQLException {
        List<Calificaciones_consultar_MB> lstUusarios = new ArrayList<>();

        String sql = "SELECT cal.id, CONCAT_WS(' ',alumno.nombre,alumno.ap_pat,alumno.ap_mat) as nombre, "
                + "alumno.no_control, cal.listening, cal.reading, cal.writing, "
                + "cal.speaking,cal.promedio "
                + "FROM sistemacle.calificaciones_tab as cal "
                + "join sistemacle.alumnos_tab as alumno on alumno.id=cal.id_alumno "
                + "WHERE cal.id_grupo=" + id_grupo;

        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstUusarios.add(new Calificaciones_consultar_MB(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
        }

        return lstUusarios;
    }

    public List<Calificaciones_consultar_MB> consultarCalificacionesGruposCoordi(IConexion conexionDB, int id_grupo) throws SQLException {
        List<Calificaciones_consultar_MB> lstUusarios = new ArrayList<>();

        String sql = "SELECT cal.id, CONCAT_WS(' ',alumno.nombre,alumno.ap_pat,alumno.ap_mat) as nombre, "
                + "alumno.no_control, alumno.correo,cal.listening, cal.reading, cal.writing, "
                + "cal.speaking, cal.promedio "
                + "FROM sistemacle.calificaciones_tab as cal "
                + "join sistemacle.alumnos_tab as alumno on alumno.id=cal.id_alumno "
                + "WHERE cal.id_grupo=" + id_grupo;

        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstUusarios.add(new Calificaciones_consultar_MB(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
        }

        return lstUusarios;
    }

    public List<Alumnos_MB> consultaralumnosGrupo(IConexion conexionDB, int id_grupo) throws SQLException {
        List<Alumnos_MB> lstUusarios = new ArrayList<>();

        String sql = "SELECT alumnos.* from sistemacle.alumnos_por_grupo_tab grupos "
                + "join sistemacle.alumnos_tab alumnos on alumnos.id=grupos.id_alumno "
                + "where alumnos.status='INSCRITO' and grupos.id_grupo=" + id_grupo;

        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            lstUusarios.add(new Alumnos_MB(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getNString(7),
                    rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                    rs.getDate(12), rs.getInt(13), rs.getString(14)));
        }
        return lstUusarios;
    }

    @Override
    public int editar(IConexion conexionDB, int id, Calificaciones_MB obj) throws SQLException {
        int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "listening",
                "reading",
                "writing",
                "speaking",
                "promedio"
        );
        String camposSQL = GeneradorSQL.camposUpdateSQL(cols);
        String sql = "UPDATE " + CALIFICACIONES_TAB + " SET "
                + camposSQL
                + " WHERE id=?";
        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);

        registro.setInt(1, obj.getListening());
        registro.setInt(2, obj.getReading());
        registro.setInt(3, obj.getWriting());
        registro.setInt(4, obj.getSpeaking());
        registro.setInt(5, obj.getPromedio());
        registro.setInt(6, id);
        resulSet = registro.executeUpdate();

        return resulSet;
    }

    @Override
    public Calificaciones_MB eliminar(IConexion conexionDB, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Calificaciones_MB> consultar(IConexion conexionDB) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Calificaciones_MB encontrarId(IConexion conexionDB, int id) throws SQLException {
        Calificaciones_MB para = new Calificaciones_MB();
        String sql = "SELECT * "
                + " FROM " + CALIFICACIONES_TAB
                + " WHERE id='" + id + "'";
        PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
        ResultSet rs;
        rs = ps.executeQuery();
        while (rs.next()) {
            para.setId(rs.getInt(1));
            para.setId_grupo(rs.getInt(2));
            para.setId_alumno(rs.getInt(3));
            para.setListening(rs.getInt(4));
            para.setReading(rs.getInt(5));
            para.setWriting(rs.getInt(6));
            para.setSpeaking(rs.getInt(7));
            para.setPromedio(rs.getInt(8));
        }
        return para;
    }
}
