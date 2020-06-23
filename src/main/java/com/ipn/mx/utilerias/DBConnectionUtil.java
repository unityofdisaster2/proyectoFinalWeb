/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.utilerias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author unityofdisaster
 */
public class DBConnectionUtil {
    private Connection con;
    /*
    public Connection obtenerConexion(){
        String user = "ray";
        String url = "jdbc:postgresql://localhost:5432/proyectofinal";
        String pass = "T3st1ng08";
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, pass);            
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }    */
    
    
    public Connection obtenerConexion(){
        String user = "bdzyjmknbytdza";
        String url = "jdbc:postgresql://ec2-52-200-48-116.compute-1.amazonaws.com:5432/d7bsv5nsmgaaac?ssl=true";
        String pass = "0cba763791aebcb68a70f728ad6e935aa4af668e6919aeec0210ef71d67ca47f";
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, pass);            
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
