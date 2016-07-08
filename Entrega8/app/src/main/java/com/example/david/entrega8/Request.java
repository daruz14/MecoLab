package com.example.david.entrega8;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by david on 4/07/16.
 */
public abstract class Request {
    public static final String API_URL = "http://10.201.127.129:3000/api/v1/messages";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String ACCEPT = "Accept";

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
     * @param token Authorization token.
     * @return String responses.
     * @throws IOException
     */
    public ArrayList<String> perform(Item info) throws IOException, JSONException {
        return null;
    }
}
