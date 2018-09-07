package web;

import com.google.gson.Gson;
import edu.uw.ext.quote.AlphaVantageQuote;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

public class StockQuoteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ServletContext ctx;

// create the quote object
//give me some xml

        /**
         * Default constructor.
         */
        public StockQuoteServlet() {
        }
//    public void init(ServletConfig servletCfg) throws ServletException {
//        ctx = servletCfg.getServletContext();
//        String path = servletCfg.getInitParameter("format");
//        String realPath = ctx.getRealPath(path);
//        File f = new File(realPath);
//        try (DataInputStream dataIn = new DataInputStream(new FileInputStream(f))) {
//            byte[] bytes = new byte[(int)f.length()];
//            dataIn.readFully(bytes);
//            salutationXml = new String(bytes, "UTF8");
//        } catch (IOException e) {
//            ctx.log("Error reading salutation file.", e);
//        }
//
//        path = servletCfg.getInitParameter("valediction");
//        realPath = ctx.getRealPath(path);
//        f = new File(realPath);
//        try (DataInputStream dataIn = new DataInputStream(new FileInputStream(f))) {
//            byte[] bytes = new byte[(int)f.length()];
//            dataIn.readFully(bytes);
//            valedictionXml = new String(bytes, "UTF8");
//        } catch (IOException e) {
//            ctx.log("Error reading valediction file.", e);
//        }
//
//        path = servletCfg.getInitParameter("default");
//        realPath = ctx.getRealPath(path);
//        f = new File(realPath);
//        try (DataInputStream dataIn = new DataInputStream(new FileInputStream(f))) {
//            byte[] bytes = new byte[(int)f.length()];
//            dataIn.readFully(bytes);
//            defaultXml = new String(bytes, "UTF8");
//        } catch (IOException e) {
//            ctx.log("Error reading default file.", e);
//        }
//    }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            serviceRequest(request, response);
        }

        void serviceRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String ticker = request.getParameter("stockQuote");
            String format = request.getParameter("format");

            AlphaVantageQuote newQuote = null;
            try {
                newQuote = AlphaVantageQuote.getQuote(ticker);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String symbol = newQuote.getSymbol();
            int price = newQuote.getPrice();

            StringBuilder xmlBuilder = new StringBuilder();
            xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlBuilder.append("<stockQuote>");
            xmlBuilder.append("<Symbol>" + symbol + "</Symbol>");
            xmlBuilder.append("<Price>" + price + "</Price>");
            xmlBuilder.append("</stockQuote>\r\n");

            String xmlString = xmlBuilder.toString();
            response.setContentType("text/xml");
            response.setContentLength(xmlString.length());
            response.getWriter().print(xmlString);
        }
}

