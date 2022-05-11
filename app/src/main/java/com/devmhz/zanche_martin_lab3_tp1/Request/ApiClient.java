package com.devmhz.zanche_martin_lab3_tp1.Request;

import android.content.Context;
import android.content.SharedPreferences;


import com.devmhz.zanche_martin_lab3_tp1.model.Usuario;

import java.util.ArrayList;


public class ApiClient {

    private static Usuario usuarioActual=null;
    private static ApiClient api=null;
    private static SharedPreferences sp;


    public ApiClient(){

    }    //Método para crear una instancia de ApiClient
    public static ApiClient getApi(){
        if (api==null){
            api=new ApiClient();
        }
        return api;

    }

    private static void conectar(Context context){
        if(sp==null){
            sp=context.getSharedPreferences("Datos",0);
        }

    }

    public static void guardar(Context context, Usuario usuario){
        conectar(context);
        SharedPreferences.Editor editor=sp.edit();
        editor.putLong("dni",usuario.getDni());
        editor.putString("nombre",usuario.getNombre());
        editor.putString("apellido",usuario.getApellido());
        editor.putString("mail",usuario.getMail());
        editor.putString("password",usuario.getPassword());
        editor.commit();
    }

    public  static  Usuario leer(Context context){
      conectar(context);
        Long dni=sp.getLong("dni",-1);
        String nombre= sp.getString("nombre","-1");
        String apellido= sp.getString("apellido","-1");
        String mail= sp.getString("mail","-1");
        String password= sp.getString("password","-1");

        Usuario usuario=new Usuario(dni,nombre,apellido,mail,password);
        return usuario;
    }

    public static Usuario login(Context context,String user, String pass){
        Usuario usuario=null;
        conectar(context);
        Long dni=sp.getLong("dni",-1);
        String nombre= sp.getString("nombre","-1");
        String apellido= sp.getString("apellido","-1");
        String mail= sp.getString("mail","-1");
        String password= sp.getString("password","-1");

        if (user.equals(mail)&&pass.equals(password)){
            usuario=new Usuario(dni,nombre,apellido,mail,password);
        }


        return usuario;
    }


}
