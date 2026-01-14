package com.google.sso.google_sso.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Value("${GOOGLE_CLIENT_ID}")
    private String clientId;
    
    @Value("${GOOGLE_CLIENT_SECRET}")
    private String clientSecret;

    
    @GetMapping("/")
    public String publicPage() {
        final StringBuffer html = new StringBuffer();
        html.append( "Client ID: " + clientId + "<br>" );
        html.append( "Client Secret: " + clientSecret + "<br>");
        html.append( "Página Pública. <a href='/user'>Clique aqui para logar com Google</a>" );
        
        return html.toString();
    }
    
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
//        // Retorna os detalhes do perfil do Google (nome, e-mail, foto)
        System.out.println( "/user" );
//        final Map<String,Object> attributes = principal.getAttributes();
//        attributes
//            .entrySet()
//            .stream()
//            .forEach( entry ->
//                System.out.println( "key = " + entry.getKey() + " value = " + entry.getValue() )
//            );
//        return attributes;
        return principal.getAttributes();
    }
    
//    @GetMapping("/login/oauth2/code/google")
//    public String userOk(@AuthenticationPrincipal OAuth2User principal) {
//        System.out.println( "/login/oauth2/code/google" );
//        if (principal == null) {
//            return "Erro: Usuário não autenticado.";
//        }
//        String nome = principal.getAttribute("name");
//        return "Login efetuado com sucesso! Bem-vindo, " + nome;
//    }
}