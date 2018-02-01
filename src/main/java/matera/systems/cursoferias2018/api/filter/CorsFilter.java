package matera.systems.cursoferias2018.api.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Filtro CORS para aceitar requisições de outros domínios.
 *
 * O Angular/ionic realiza uma chamada HTTP OPTIONS antes do GET ou POST, para a mesma
 * URL de destino, para saber se o CORS está habilitado. E esse OPTIONS está
 * sendo bloqueado pelo oauth2, pois eu ainda não me autentiquei.
 *
 * Este filtro trata essa questão.
 *
 * Mais detalhes:
 * https://spring.io/blog/2015/01/20/the-resource-server-angular-js-and-spring-
 * security-part-iii#cors-negotiation
 * 
 * https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(CorsFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers",
				"X-Requested-With, Authorization, Accept-Language, Content-Type");
		response.setHeader("Access-Control-Allow-Methods", "GET, DELETE, OPTIONS, POST, PUT");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Max-Age", "3600");

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) {
		LOG.info("Iniatilizing CorsFilter");
	}

	@Override
	public void destroy() {
		LOG.info("Destroying CorsFilter");
	}
}
