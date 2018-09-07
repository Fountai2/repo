package main.java.app;



import javax.servlet.http.HttpServlet;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

    /**
     * We want to be able to submit a form using a POST request that returns the stock quote
     *
     * get a request
     *
     * response to it (use getQuote and return it to the the page somehow)
     * @param args            AlphaVantageQuote quote = AlphaVantageQuote.getQuote(symbol);

     * @param symbol
     */
    public class MyFancyServletApp extends HttpServlet {
//        private void serviceRequest(final HttpServletRequest request, HttpServletResponse response){
//            symbol = request.getParameter("symbol");
//
//            final AlphaVantageQuote quote = AlphaVantageQuote.getQuote(symbol);
//
//            DocumentBuilderFactory dbf;
//            dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.newDocument();
//            Element root = doc.createElement("quote");
//            Text symbolTxt = doc.createTextNode(String.format( quote.getSymbol()));
//            doc.appendChild(symbolTxt);
//            Text priceTxt = doc.createTextNode(String.format("%6.2", quote.getPrice()));
//            doc.appendChild(priceTxt);
//
//            String respString = writer.toString();
//            response.setContentType("text/Xml");
//            response.setContentLength(respString.length());
//            response.getWriter(respString);
//        }

        private static void exec(final String tickerSymbol, final String format) {
            HttpURLConnection conn = null;
            String baseUrl = "http://localhost:8080/StockQuote";
            try {
                String urlStr = String.format("%s?tickerSymbol=%s&format=%s", baseUrl, tickerSymbol, format);
                //System.out.println(urlStr);
                URL url = new URL(urlStr);
                conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");

                //System.out.printf("Content-Type: %s%n", conn.getContentType());
                InputStream in = conn.getInputStream();
                Reader rdr = new InputStreamReader(in);
                char buf[] = new char[1024];
                int len = 0;
                while ((len = rdr.read(buf)) != -1) {
                    System.out.print(new String(buf, 0, len));
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        /**
         * @param args
         * @throws IOException
         */
        public static void main(String[] args) {
            String courtesyType = "salutation";

            System.out.println("JSON:");
            exec(courtesyType, "json");
            System.out.println();

            System.out.println();
            System.out.println("plain:");
            exec(courtesyType, "plain");

            System.out.println();
            System.out.println("HTML:");
            exec(courtesyType, "html");

            System.out.println();
            System.out.println("XML:");
            exec(courtesyType, "xml");

            System.out.println();
            System.out.println();
        }

    }


//    <quote>
//        <symbole>
//        <price>
//    </quote>
