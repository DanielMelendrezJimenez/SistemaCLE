/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.inscripciones;

import config.conexion.IConexion;
import dao.manipula.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import managedbean.inscripciones.Preinscritos_MB;

/**
 *
 * @author wltgm
 */
public class Inscripciones_MDAO implements DAO<Preinscritos_MB> {

    public List<Preinscritos_MB> consultarPreinscritos(IConexion conexionDB) throws SQLException {
        List<Preinscritos_MB> lstUusarios = new ArrayList<>();

        String sql = "SELECT alugrupos.id, CONCAT_WS(\" \", alumnos.nombre, alumnos.ap_pat, alumnos.ap_mat) as Alumno, "
                + "alumnos.no_control as Num_Control, alumnos.status, "
                + "alugrupos.id_grupo, CONCAT_WS(\" \", cursos.nombre, cursos.nivel) as Grupo, "
                + "cursos.aula as Aula, cursos.modalidad, CONCAT_WS(\"-\", cursos.hora_inicio, cursos.hora_fin) as Horario "
                + "FROM sistemacle.alumnos_por_grupo_tab alugrupos "
                + "join sistemacle.grupos_tab grupos on grupos.id=alugrupos.id_grupo "
                + "join sistemacle.alumnos_tab alumnos on alumnos.id=alugrupos.id_alumno "
                + "join sistemacle.cursos_tab cursos on grupos.id_curso=cursos.id "
                + "WHERE alumnos.status IN ('PREINSCRITO') AND grupos.status NOT IN ('INACTIVO','0');";

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
   
    
        public List<Preinscritos_MB> consultarAtemporales(IConexion conexionDB) throws SQLException {
        List<Preinscritos_MB> lstUusarios = new ArrayList<>();

        String sql = "SELECT alugrupos.id, CONCAT_WS(\" \", alumnos.nombre, alumnos.ap_pat, alumnos.ap_mat) as Alumno, "
                + "alumnos.no_control as Num_Control, alumnos.status, "
                + "alugrupos.id_grupo, CONCAT_WS(\" \", cursos.nombre, cursos.nivel) as Grupo, "
                + "cursos.aula as Aula, cursos.modalidad, CONCAT_WS(\"-\", cursos.hora_inicio, cursos.hora_fin) as Horario "
                + "FROM sistemacle.alumnos_por_grupo_tab alugrupos "
                + "join sistemacle.grupos_tab grupos on grupos.id=alugrupos.id_grupo "
                + "join sistemacle.alumnos_tab alumnos on alumnos.id=alugrupos.id_alumno "
                + "join sistemacle.cursos_tab cursos on grupos.id_curso=cursos.id "
                + "WHERE alumnos.status IN ('ATEMPORAL') AND grupos.status NOT IN ('INACTIVO','0');";

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

    @Override
    public int registrar(IConexion conexionDB, Preinscritos_MB obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int editar(IConexion conexionDB, int id, Preinscritos_MB nvoObj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Preinscritos_MB eliminar(IConexion conexionDB, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Preinscritos_MB> consultar(IConexion conexionDB) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Preinscritos_MB encontrarId(IConexion conexionDB, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
