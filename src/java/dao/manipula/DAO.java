package dao.manipula;

import config.conexion.IConexion;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Kevin Ivan Sanchez Valdin
 * @param <T> objeto generico
 */
public interface DAO<T> {

    public int registrar(IConexion conexionDB, T obj) throws SQLException;

    public int editar(IConexion conexionDB, int id, T nvoObj) throws SQLException;

    public T eliminar(IConexion conexionDB, int id) throws SQLException;

    public List<T> consultar(IConexion conexionDB) throws SQLException;

    public T encontrarId(IConexion conexionDB, int id) throws SQLException;
}
