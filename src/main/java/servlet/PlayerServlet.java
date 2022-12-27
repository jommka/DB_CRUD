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
import java.util.Map;
import static database.JDBCPostgreSQL.command;

@WebServlet(name = "PlayerServlet", urlPatterns = {"player"})
public class PlayerServlet extends HttpServlet{

    private static String sql;

    @Serial
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            for (Map.Entry<Integer, String> entry: Database.getPlayer().entrySet()) {
                response.getWriter().println("playerId:" + entry.getKey() + "; nickname:" + entry.getValue());
            }
        } catch (SQLException e) {
            doSetError(response);
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String playerId = request.getParameter("playerId");
        String nickname = request.getParameter("nickname");

        try {
            if (!Database.getPlayer().containsKey(Integer.valueOf(playerId))){
                sql = "INSERT INTO player VALUES (" + playerId + ",'" +  nickname + "')";
                command(sql);

                if (Database.getPlayer().containsKey(Integer.valueOf(playerId))){
                    doSetResult(response, "The INSERT was successful");
                }

            } else {
                response.setStatus(400);
                response.getWriter().print("The player " + playerId + " already exists");
            }
        } catch (SQLException e) {
            doSetError(response);
            e.printStackTrace();
        }

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String playerId = request.getParameter("playerId");

        sql = "DELETE FROM player WHERE playerId=" + playerId;
        command(sql);

        try {
            if (!Database.getPlayer().containsKey(Integer.valueOf(playerId))){
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
        String playerId = request.getParameter("playerId");
        String nickname = request.getParameter("nickname");

        sql = "UPDATE player SET nickname='" + nickname + "' WHERE playerId=" + playerId;
        command(sql);

        try {
            if (Database.getPlayer().get(Integer.valueOf(playerId)).equals(nickname)) {
                doSetResult(response, "The UPDATE was successful");
            } else {
                response.setStatus(400);
                doSetError(response);
            }
        } catch (SQLException e) {
            doSetError(response);
            e.printStackTrace();
        }
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
