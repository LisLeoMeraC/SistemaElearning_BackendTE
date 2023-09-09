package com.sistema.elearning.Excepciones;

public class UsuarioFoundException extends Exception {

    public UsuarioFoundException(){
        super("El usuario con ese username ya existe en la base de datos, vuelva a intentar con otro username!!");
    }
    public UsuarioFoundException(String mensaje){
        super(mensaje);
    }
}
