package br.com.biscoithor.FipeTable.main;

import br.com.biscoithor.FipeTable.model.Data;
import br.com.biscoithor.FipeTable.model.VehicleData;
import br.com.biscoithor.FipeTable.model.VehicleModels;
import br.com.biscoithor.FipeTable.service.ApiConsumer;
import br.com.biscoithor.FipeTable.service.DataConverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private Scanner reader = new Scanner(System.in);
    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
    private ApiConsumer apiConsumer = new ApiConsumer();
    private DataConverter converter = new DataConverter();

    public void showMenu()
    {
        var menu = """
                ***** OPTIONS ***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consultar:
                """;
        System.out.println(menu);
        var option = reader.nextLine();
        String address = "";

        if(option.toLowerCase().contains("car"))
        {
            address = BASE_URL + "carros/marcas";
        }
        else if (option.toLowerCase().contains("mot"))
        {
            address = BASE_URL + "motos/marcas";
        }
        else if(option.toLowerCase().contains("cam"))
        {
            address = BASE_URL + "caminhoes/marcas";
        }

        var json = apiConsumer.getData(address);
        System.out.println(json);
        var labels = converter.getList(json, Data.class);

        labels.stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("Inform the code of the desired label");
        var labelCode = reader.nextInt();
        reader.nextLine();

        address += "/" + labelCode + "/modelos";

        json = apiConsumer.getData(address);
        var modelList = converter.getData(json, VehicleModels.class);

        System.out.println("Selected Label Models");
        modelList.models().stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("Insert vehicle model name:");
        var modelName = reader.nextLine();

        List<Data> filteredModels = modelList.models().stream()
                .filter(m->m.name().toLowerCase().contains(modelName))
                .collect(Collectors.toList());

        System.out.println("Matching Models:");
        filteredModels.forEach(System.out::println);

        System.out.println("Insert Model Code: ");
        var modelCode = reader.nextLine();

        address += "/" + modelCode +"/anos";
        json = apiConsumer.getData(address);

        List<Data> vehiclesByYear = converter.getList(json, Data.class);
        List<VehicleData> vehicles = new ArrayList<>();

        for (int i = 0; i < vehiclesByYear.size(); i++)
        {
            var yearInfo = address + "/" + vehiclesByYear.get(i).code();
            json = apiConsumer.getData(yearInfo);
            VehicleData vehicle = converter.getData(json, VehicleData.class);
            vehicles.add(vehicle);
        }

        System.out.println("All vehicles by year");

        vehicles.forEach(System.out::println);


    }
}
