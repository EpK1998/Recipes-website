package server;
import dao.RecipeDaoImpl;
import dao.UserDaoImpl;
import services.RecipeService;
import java.net.*;

public class Server {
    public void run() {
        try {
            ServerSocket server = new ServerSocket(123);
            ControllerC recipecontroller = new ControllerC(new RecipeService(new RecipeDaoImpl(), new UserDaoImpl()));
            while (true) {
                Socket someClient = server.accept();
                RequestHandler request = new RequestHandler(someClient,recipecontroller);
                request.process();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}