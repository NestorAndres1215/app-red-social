package com.red_social.api_gateway.filter;

import com.red_social.api_gateway.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        String method = exchange.getRequest().getMethod().name();

        System.out.println("🔹 [Filtro JWT] Método: " + method + " | Ruta: " + path);

        // 🟡 1. Permitir solicitudes OPTIONS (preflight)
        if (HttpMethod.OPTIONS.matches(method)) {
            System.out.println("🟠 Petición OPTIONS detectada → devolviendo 200 OK (Preflight).");
            exchange.getResponse().setStatusCode(HttpStatus.OK);
            return exchange.getResponse().setComplete();
        }

        // 🟢 2. Rutas públicas (no requieren JWT)
        if (isPublicPath(path)) {
            System.out.println("✅ Ruta pública detectada: " + path);
            return chain.filter(exchange);
        }

        // 🔴 3. Validar header Authorization
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        System.out.println("🔍 Encabezado Authorization: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("❌ Falta token o formato incorrecto (Debe iniciar con 'Bearer ').");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 🧩 4. Extraer y validar token
        String token = authHeader.substring(7);
        System.out.println("🔑 Token recibido: " + token);

        if (!jwtUtil.validateToken(token)) {
            System.out.println("❌ Token inválido o expirado.");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 🟢 5. Token válido → obtener el usuario
        String username = jwtUtil.getUsernameFromToken(token);
        System.out.println("✅ Token válido. Usuario extraído: " + username);

        // 🟢 6. Pasar el usuario al microservicio destino como header
        System.out.println("➡️ Redirigiendo la petición al microservicio con cabecera X-User-Name: " + username);
        return chain.filter(exchange.mutate()
                .request(exchange.getRequest().mutate()
                        .header("X-User-Name", username)
                        .build())
                .build());
    }

    /**
     * Determina si la ruta actual es pública (no requiere autenticación).
     */
    private boolean isPublicPath(String path) {
        boolean isPublic = path.startsWith("/security/api/v1/auth/") ||
                path.equals("/red-social-app/auth-service/api/v1/auth/generate-token") ||
                path.startsWith("/red-social-app/auth-service/api/v1/google/") || // ✅ cambiado
                path.startsWith("/security/api/v1/users/register") ||
                path.contains("/v3/api-docs") ||
                path.contains("/swagger-ui");

        if (isPublic) {
            System.out.println("🟢 Ruta marcada como pública: " + path);
        } else {
            System.out.println("🔒 Ruta protegida: " + path);
        }

        return isPublic;
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
