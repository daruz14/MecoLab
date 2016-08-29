package com.example.david.applicationconnect.Networking;

import com.example.david.applicationconnect.Models.Message;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by daruz14 on 21-08-16.
 */
public abstract class Request {

    public static final String API_URL = "http://192.168.1.166:3000/api/v1/messages";
    public static final String API_URL_CONTACTS = "http://192.168.1.166:3000/api/v1/contactos";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";

    /**
     * Factory que crea un {@link GetRequest} o un {@link PostRequest} dependiendo del modo que
     * es pasado de parámetro.
     * @param mode Puede ser {@link #GET} o {@link #POST}.
     * @return Nuevo request.
     */
    public static Request createRequest(String mode) {
        if (mode.equals(GET)) {
            return new GetRequest();
        }
        else if (mode.equals(POST)) {
            return new PostRequest();
        }
        return null;
    }

    /**
     * Metodo que debe ser 'overrided'. Realiza el request.

     * @return String responses.
     * @throws IOException
     */
    public ArrayList<String> perform(Message token) throws IOException {
        return null;
    }

}
