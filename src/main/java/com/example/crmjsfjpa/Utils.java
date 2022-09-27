package com.example.crmjsfjpa;

import jakarta.servlet.http.HttpServletRequest;

public final class Utils {

    /**
     * Récupération d'un paramètre de la requête
     * @param request la requête
     * @param parameterName le nom du paramètre
     * @return null si le paramètre n'est pas renseigné, sinon sa valeur
     */
    public static String getParameterValue(HttpServletRequest request, String parameterName) {
        String value = request.getParameter(parameterName);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value;
        }
    }
}


