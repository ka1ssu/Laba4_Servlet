package com.example.laba4;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import jakarta.servlet.ServletException;

@WebServlet(name = "CarsServlet", value = "/CarsServlet")
public class CarsServlet extends HttpServlet {
    private static final String filePath = "cars.json";

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONArray CarsList = getCarsListFromFile();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Таблица</title><link href=\"css/bootstrap.min.css\" rel=\"stylesheet\"></head><body><div><table class=\"table\"><thead><tr><th scope=\"col\">Марка</th><th scope=\"col\">Модель</th><th scope=\"col\">Пробег</th> <th scope=\"col\">Цвет</th><th scope=\"col\">Цена</th></tr></thead>");
        for (Object obj : CarsList) {
            JSONObject carsJSON = (JSONObject) obj;
            Cars cars = Cars.fromJSON(carsJSON);
            out.println("<tbody> <tr><td>" + cars.getBrand() + "</td><td>" + cars.getModel() + "</td><td>" + cars.getMileage() + "</td><td>" + cars.getColor() + "</td><td>" + cars.getPrice() + "</td>");
        }
        out.println("</tbody></table></div ><script src =\"js/bootstrap.bundle.min.js \"></script ></body ></html >");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        int mileage = Integer.parseInt(request.getParameter("mileage"));
        String color = request.getParameter("color");
        int price = Integer.parseInt(request.getParameter("price"));

        Cars cars = new Cars(brand, model, mileage, color, price);
        JSONArray CarsList = getCarsListFromFile();
        CarsList.add(cars.toJSON());
        writeCarsListToFile(CarsList);
        response.sendRedirect("/Laba4_war_exploded/");
    }

    private JSONArray getCarsListFromFile() {
        try {
            JSONParser parser = new JSONParser();
            File file = new File(filePath);
            String fullPath = file.getAbsolutePath();
            System.out.println(fullPath);
            return (JSONArray) parser.parse(new FileReader(filePath));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    private void writeCarsListToFile(JSONArray CarsList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(CarsList.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}