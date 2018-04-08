<%@page import="com.microsoft.applicationinsights.TelemetryClient"%>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form method="POST" action="">
            <input type="number" name="o1" id="o1" placeholder="1º Numero" />
            <input type="number" name="o2" id="o2" placeholder="2º Numero" />
            <select name="operador">
                <option value="+">+ Suma</option>
                <option value="-">- Resta</option>
                <option value="*">* Multiplicacion</option>
                <option value="%">% Division</option>
            </select>
            <br />
            <input type="submit" name="envio" value="Calcular"/>
        </form>
        <%
            if (request.getParameter("envio") != null) {
                int resultado = 0;

                try {
                    int operador1 = Integer.parseInt(request.getParameter("o1"));
                    int operador2 = Integer.parseInt(request.getParameter("o2"));

                    if (request.getParameter("operador").equals("+")) {
                        resultado = operador1 + operador2;
                    } else if (request.getParameter("operador").equals("-")) {
                        resultado = operador1 - operador2;
                    } else if (request.getParameter("operador").equals("*")) {
                        resultado = operador1 * operador2;
                    } else if (request.getParameter("operador").equals("%")) {
                        resultado = operador1 / operador2;
                    }
                } catch (ArithmeticException e) {
                    
                    TelemetryClient tc = new TelemetryClient();
                    tc.getContext().setInstrumentationKey("c2d08a94-9a97-4f4c-9835-1d5e2cb538dd");
                    tc.trackEvent("divisionPorCero");
                    out.write("<p>ERROR: Division entre 0!</p>");
                }
                out.write("<p>Resultado: " + resultado + "</p>");
            }
        %>

    </body>
</html>