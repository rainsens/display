package com.rainsen.display.util;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
import java.net.URISyntaxException;

public class URIUtil {

    public static URI getHost(HttpServletRequest httpServletRequest) {
        URI aimURI;
        try {
            URI uri = new URI(httpServletRequest.getRequestURL() + "");
            aimURI = new URI(
                    uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(),
                    null, null, null);
        } catch (URISyntaxException e) {
            aimURI = null;
        }
        return aimURI;
    }
}
