package himedia.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	private static Logger logger = Logger.getLogger(EncodingFilter.class.getSimpleName());
	private static String encoding = "UTF-8";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("[EncodingFilter] init");
		Filter.super.init(filterConfig);
		// init-param으로부터 encoding 정보를 받아와서 encoding 속성을 변경
		String encodingParam = filterConfig.getInitParameter("encoding");
		if (encodingParam != null) {
			encoding = encodingParam;
		}
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		
		// 실제 필터가 처리하는 내용
		req.setCharacterEncoding(encoding); // 요청 정보의 인코딩 설정
		resp.setContentType("text/html");	// 응답 정보의 타입
		resp.setCharacterEncoding(encoding); // 응답 정보의 인코딩
		
		fc.doFilter(req, resp);	// 요청 처리 종료 후 다음 필터로 전달
		
		
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}


}
