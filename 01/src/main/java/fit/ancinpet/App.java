package fit.ancinpet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class App {

    public static void main(String[] args) {
        for (String param : args) {
            File order = new File(param);
            try (Scanner sc = new Scanner(order)) {

                String tripRow = sc.findWithinHorizon("Trip id: \"\\d+\"", (int) order.length());
                String locationRow = sc.findWithinHorizon("Location: \".*\"", (int) order.length());
                String personRow = sc.findWithinHorizon("Person: \".*\"", (int) order.length());

                if (tripRow == null || locationRow == null || personRow == null) {
                    throw new IllegalArgumentException("Invalid format for file: " + order.getName());
                }

                String tripId = tripRow.substring("Trip id: \"".length(), tripRow.length() - 1);
                String location = locationRow.substring("Location: \"".length(), locationRow.length() - 1);
                String person = personRow.substring("Person: \"".length(), personRow.length() - 1);
                int lastSpace = person.lastIndexOf(' ');
                String name = person.substring(0, lastSpace);
                String lastName = person.substring(lastSpace + 1);

                Order parsedOrder = new Order(tripId, location, name, lastName);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                try (Writer wr = new FileWriter("results/" + order.getName().substring(0, order.getName().length() - 4) + "-out.json")) {
                    gson.toJson(parsedOrder, wr);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println("Unable to open file.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } 
        }
    }
}