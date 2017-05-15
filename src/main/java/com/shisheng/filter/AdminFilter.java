package com.shisheng.filter;

import com.shisheng.entity.User;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Magic on 2017/5/15.
 */
public class AdminFilter extends OncePerRequestFilter {


    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        User user = (User)request.getSession().getAttribute("editor");
        if (null == user || user.getProperty()<1) {

            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            String loginPage = "/admin/login.html";
            StringBuilder builder = new StringBuilder();
            builder.append("<script type=\"text/javascript\" charset='utf-8'>");
            builder.append("alert('网页过期，请重新登录！');");
            builder.append("window.top.location.href='");
            builder.append(loginPage);
            builder.append("';");
            builder.append("</script>");
            out.print(builder.toString());
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
