package managebean.general;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kevin Ivan Sanchez Valdin
 */
public class GeneradorSQL {

    public static List<String> columnas(String... nameCols) {
        List<String> columnas = new ArrayList<>();
        columnas.addAll(Arrays.asList(nameCols));
        return columnas;
    }

    public static String valuesSQL(int length) {

        StringBuilder values = new StringBuilder();
        for (int i = 0; i < length; i++) {
            values.append("?");
            if (!(i + 1 == length)) {
                values.append(",");
            }
        }
        return values.toString();
    }

    public static String camposInsertSQL(List<String> columnas) {
        return String.join(", ", columnas);
    }

    public static String camposUpdateSQL(List<String> columnas) {

        StringBuilder values = new StringBuilder();
        for (int i = 0; i < columnas.size(); i++) {
            values.append(columnas.get(i));
            values.append("=?");
            if (!(i + 1 == columnas.size())) {
                values.append(",");
            }
        }
        return values.toString();
    }

    public static List<String> camposAlias(String alias, String... campos) {
        StringBuilder s = new StringBuilder();
        List<String> nvoCampos = new ArrayList<>();
        for (String campo : campos) {
            s.append(alias);
            s.append(".");
            s.append(campo);
            nvoCampos.add(campo);

        }
        return nvoCampos;
    }

    public static void configurarPreparedStatement(PreparedStatement ps, int index, Object valor) throws SQLException {

        if (valor == null) {
            ps.setNull(index, java.sql.Types.NULL);
        } else if (valor instanceof String) {
            ps.setString(index, (String) valor);
        } else if (valor instanceof Integer) {
            ps.setInt(index, (Integer) valor);
        } else if (valor instanceof Long) {
            ps.setLong(index, (Long) valor);
        } else if (valor instanceof Float) {
            ps.setFloat(index, (Float) valor);
        } else if (valor instanceof Double) {
            ps.setDouble(index, (Double) valor);
        } else if (valor instanceof Boolean) {
            ps.setBoolean(index, (Boolean) valor);
        } else {
            throw new SQLException("Tipo de dato no soportado: " + valor.getClass().getName());
        }

    }
}
