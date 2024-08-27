package managebean.general;

import java.security.NoSuchAlgorithmException;
/**
 *
 * @author Kevin Ivan Sanchez Valdin
 */
public class Hash {

    /**
     * Retorna un hash a partir de un tipo y un texto
     *
     * @param txt texto para cifrar
     * @param hashType metodo de cifrado
     * @return una nueva cadena, cifrada
     */
    private static String getHash(String txt, String hashType) {

        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
          //  e.getMessage());
        }
        return null;
    }

    public static String sha1(String txt) {
        return getHash(txt, "SHA1");
    }
}
