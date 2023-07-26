package BaseDatos;
import Logs.Log;
import org.apache.log4j.Logger;

import java.sql.*;

public class Conexion {
    //conexion local
    private static final Logger LOG = Log.getLogger(Conexion.class);


    public static Connection conectar(){
        LOG.debug("DEBUG:: conexion");

        try {
            LOG.debug("INFO:: conectando base datos");

            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_hm", "root","");
            return cn;

        }catch (SQLException e){
            LOG.debug("ERROR:: no se pudo contactar base datos" + e);

            System.out.println("Error en la conexion local"+e);
        }
        return (null);
    }
}

