package com.example.almeida.indexlogistics;

import android.app.Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InfoApp extends Application {
    Socket socket;
    ObjectOutputStream saida;
    ObjectInputStream entrada;
    int USUARIO_NO_SISTEMA;

    @Override
    public void onCreate(){
        super.onCreate();
    }
}
