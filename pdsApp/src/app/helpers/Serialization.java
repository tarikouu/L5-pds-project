package app.helpers;

import app.models.*;
import app.models.other.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Vector;

public class Serialization {

  final GsonBuilder builder;
  final Gson gson;

  public Serialization() {
    builder = new GsonBuilder();
    gson = builder.create();
  }

  public String serializeUser(Advisor p) {
    return gson.toJson(p);
  }

  public String serializeCustomer(Customer c) {
      return gson.toJson(c);
  }

  public String serializeAddress(Address a) {
      return gson.toJson(a);
  }
  
  public String serializedatasetPieChart(datasetPieChart dpd) {
      return gson.toJson(dpd);
  }

  public String serializedatasetBarChart(datasetBarChart dpd) {
      return gson.toJson(dpd);
  }

  public String serializeArrayList(ArrayList dpd) {
      return gson.toJson(dpd);
  }
  
  public String serializeLoanType(LoanType lt) {
      return gson.toJson(lt);
  }
  
  public String serializeFixedRateSimulation(FixedRateSimulation frs) {
      return gson.toJson(frs);
  }
  
  public String serializeVector(Vector v) {
      return gson.toJson(v);
  }

  public Advisor unserializeUser(String u) {
      return gson.fromJson(u, Advisor.class);
  }

  public Customer unserializeCustomer(String c) {
      return gson.fromJson(c, Customer.class);
  }

  public Address unserializeAddress(String a) {
      return gson.fromJson(a, Address.class);
  }

  public datasetPieChart unserializedatasetPieChart(String dpd) {
      return gson.fromJson(dpd, datasetPieChart.class);
  }

  public datasetBarChart unserializedatasetBarChart(String dpd) {
      return gson.fromJson(dpd, datasetBarChart.class);
  }

  public ArrayList unserializeArrayList(String dpd) {
      return gson.fromJson(dpd, ArrayList.class);
  }
  
  public LoanType unserializeLoanType(String lt) {
      return gson.fromJson(lt, LoanType.class);
  }
  
  public FixedRateSimulation unserializeFixedRateSimulation(String frs) {
      return gson.fromJson(frs, FixedRateSimulation.class);
  }
  
  public Vector unserializeVector(String v) {
      return gson.fromJson(v, Vector.class);
  }
  
  public ArrayList<LoanType> unserializeLoanTypeArrayList(String lt) {
    ArrayList<LoanType> loanTypesList = new ArrayList<>();
    System.out.println("lt ArrayList unserialization...");
    JsonParser parser = new JsonParser();
    JsonArray array = parser.parse(lt).getAsJsonArray();
    for (int i=0 ; i<array.size() ; i++) {
        loanTypesList.add(gson.fromJson(array.get(i), LoanType.class));
    }
    System.out.println("arraylist ins :");
    for (LoanType l : loanTypesList) {
        System.out.println(l);
    }
    return loanTypesList;
  }
  
  public ArrayList<Insurance> unserializeInsuranceArrayList(String ins) {
    ArrayList<Insurance> insurancesList = new ArrayList<>();
    System.out.println("ins ArrayList unserialization...");
    JsonParser parser = new JsonParser();
    JsonArray array = parser.parse(ins).getAsJsonArray();
    for (int i=0 ; i<array.size() ; i++) {
        insurancesList.add(gson.fromJson(array.get(i), Insurance.class));
    }
    System.out.println("arraylist ins :");
    for (Insurance in : insurancesList) {
        System.out.println(in);
    }
    return insurancesList;
  }
}
