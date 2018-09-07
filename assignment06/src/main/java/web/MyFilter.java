package web;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.logging.Logger;

public class MyFilter implements Filter {

    private ServletContext ctx;

    public void init(FilterConfig filterCfg) throws ServletException {
        ctx = filterCfg.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final CharResponseWrapper responseWrapper = new CharResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseWrapper);

        ctx.log("initializing  : " + responseWrapper.toString());

        StringWriter output = new StringWriter();
        String format = request.getParameter("format");
        StringBuilder builder = new StringBuilder();
        ctx.log("format is set to " + format);

        String symbol = null;
        String price = null;
        try {
            ctx.log("entered the try ");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource src = new InputSource();
            src.setCharacterStream(new StringReader(responseWrapper.toString()));
            Document doc = db.parse(src);
            symbol = doc.getElementsByTagName("Symbol").item(0).getTextContent();
            price = doc.getElementsByTagName("Price").item(0).getTextContent();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        ctx.log("We should have a node list?");
        builder.append(responseWrapper.toString());

        if (!format.equals("xml")) {
            switch (format) {
                case "plain":
                    response.setContentType("text/plain");
                    builder.delete(0, responseWrapper.toString().length());
                    builder.append(String.format("Stock %s is currently selling for: %s", symbol, price));
                    break;
                case "json":
                    builder.delete(0, responseWrapper.toString().length());
                    response.setContentType("application/json");
                    builder.append(String.format("{\"Symbol\":\"%s\", \"Price\":\"%s\"}", symbol, price));
                    break;
                case "html":
                    builder.delete(0, responseWrapper.toString().length());
                    response.setContentType("text/html");
                    builder.append(String.format("<strong>%s</strong> is selling for: %s", symbol, price));
                    break;
                default:
                    response.setContentType("text/xml");
            }
        }
        output.append(builder.toString());
        String respStr = output.toString();
        response.setContentLength(respStr.length());
        response.getWriter().write(output.toString());
    }

    @Override
    public void destroy() {

    }
}
