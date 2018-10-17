import static spark.Spark.*;
import spark.template.velocity.VelocityTemplateEngine;
import spark.ModelAndView;
import static spark.debug.DebugScreen.enableDebugScreen;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.http.MetaData.Request;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public"); 
    String layout = "templates/layout.vtl";
    enableDebugScreen();

    get("/", (request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.allStylist());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/new",(request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.allStylist());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist", (request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String style = request.queryParams("style");
      Stylist newStylist = new Stylist(name, style);
      newStylist.save();
      model.put("template", "templates/style-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist", (reqeust, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.allStylist());
      model.put("template", "template/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
