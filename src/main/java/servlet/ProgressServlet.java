package servlet;

import database.Database;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static database.JDBCPostgreSQL.command;

@WebServlet(name = "ProgressServlet", urlPatterns = {"progress"})
public class ProgressServlet extends HttpServlet {

    public static String sql;

    @Serial
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            for (Map.Entry<Integer, List<String>> entry: Database.getProgress().entrySet()) {
                response.getWriter().println("id:" + entry.getKey() + "; value:" + entry.getValue());
            }
        } catch (SQLException e) {
            response.setStatus(400);
            doSetError(response);
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, String> map = getValue(request, "playerId", "id", "resourceId", "score", "maxscore");

        try {
            if (!Database.getProgress().containsKey(Integer.valueOf(map.get("id")))){
                sql = "INSERT INTO progress VALUES (" + map.get("playerId") + "," +  map.get("id")
                        + "," + map.get("resourceId") + "," + map.get("score") + "," + map.get("maxscore") + ")";
                command(sql);

                if (Database.getProgress().containsKey(Integer.valueOf(map.get("id")))){
                    doSetResult(response, "The INSERT was successful");
                }

            } else {
                response.setStatus(400);
                response.getWriter().print("The progress " + map.get("id") + " already exists");
            }
        } catch (SQLException e) {
            doSetError(response);
            e.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        sql = "DELETE FROM progress WHERE id=" + id;
        command(sql);

        try {
            if (!Database.getProgress().containsKey(Integer.valueOf(id))){
                doSetResult(response, "The DELETE was successful");
            }else{
                response.setStatus(400);
                doSetError(response);
            }
        } catch (SQLException e) {
            doSetError(response);
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> map = getValue(request, "id", "resourceId", "score", "maxscore");

        if (map.size() > 1){
            sql = "UPDATE progress SET ";
            int i = 0;
            for (String key: map.keySet()){
                if ((i < map.size() - 1)){
                    if (!key.equals("id")){
                        sql += key + "=" + map.get(key) + ", ";
                    }
                    i++;
                } else {
                    sql += key + "=" + map.get(key) + " WHERE id=" + map.get("id");
                }
            }
            command(sql);

            try {
                List<String> lst = Database.getProgress().get(Integer.valueOf(map.get("id")));
                if (Database.getProgress().get(Integer.valueOf(map.get("id"))).equals(lst)) {
                    doSetResult(response, "The UPDATE was successful");
                } else {
                    response.setStatus(400);
                    doSetError(response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(400);
            response.getWriter().print("Param cannot be null");
        }
    }

    protected Map<String, String> getValue(HttpServletRequest request, String ... str){
        Map<String, String> map = new LinkedHashMap<>();
        for (String s : str) {
            if (request.getParameter(s) != null) {
                map.put(s, request.getParameter(s));
            }
        }
        return map;
    }

    protected void doSetResult(HttpServletResponse response, String result) throws IOException {
        String reply = "{\"error\":0,\"result\":" + result + "}";
        response.getOutputStream().write( reply.getBytes(StandardCharsets.UTF_8) );
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus( HttpServletResponse.SC_OK );
    }

    protected void doSetError(HttpServletResponse response) throws IOException {
        String reply = "{\"error\":1}";
        response.getOutputStream().write( reply.getBytes(StandardCharsets.UTF_8) );
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus( HttpServletResponse.SC_OK );
    }
}
