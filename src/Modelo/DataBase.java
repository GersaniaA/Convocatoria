/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import java.util.*;
/**
 *
 * @author UNANCH21
 */
public class DataBase {
    
    private final String URL="jdbc:sqlserver://localhost:1433;databaseName=Publicaciones;"+
            "integratedSecurity=true;"+"trustServerCertificate=true;";
    
    private Connection conexion;

    public DataBase() {
        try{
            conexion=DriverManager.getConnection(URL);
            System.out.println("Conexion Establecida");
        
        
    }catch(SQLException e){
        System.out.println("Error de conexion");
        e.printStackTrace();
    }
    }
        public int Actualizar(String consulta){
           
            try{
                Statement st=conexion.createStatement();
                return st.executeUpdate(consulta);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return 0;
        }    
        
    private List OrganizarDatos(ResultSet rs){
        List filas=new ArrayList();
        try{
            
            int numColumnas=rs.getMetaData().getColumnCount();
            while(rs.next()){
                Map<String, Object> renglon=new HashMap();
                for(int i=1; i<=numColumnas; i++){
                    String nombreCampo=rs.getMetaData().getColumnName(i);
                    Object valor=rs.getObject(nombreCampo);
                    renglon.put(nombreCampo,valor);
                }
                filas.add(renglon);    
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return filas;
    }
    public List Listar(String consulta){
        ResultSet rs=null;
        List resultado=new ArrayList();
        try{
            Statement st=conexion.createStatement();
            rs=st.executeQuery(consulta);
            resultado=OrganizarDatos(rs);
            
        }catch(SQLException e){
            System.out.println("No se realizo la consulta");
            e.printStackTrace();
        }
        return resultado;
    }
    public void cerrarConexion(){
        try{
            conexion.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
        
}  
    
    

