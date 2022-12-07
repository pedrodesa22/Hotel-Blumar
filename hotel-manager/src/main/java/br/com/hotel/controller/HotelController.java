package br.com.hotel.controller;

import br.com.hotel.model.Guest;
import br.com.hotel.model.Hotel;
import br.com.hotel.model.Room;
import br.com.hotel.repository.H2JDBCUtils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static br.com.hotel.repository.H2JDBCUtils.showAvailableRoomsInHotel;

public class HotelController {

    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

    private static final H2JDBCUtils connectionH2 = new H2JDBCUtils();

    public String findAndReserveRoom() throws ParseException, SQLException {

        final Scanner scanner = new Scanner(System.in);

        System.out.println("Informe a data de Check-In dd/mm/yyyy exemplo 01/01/2022");
        Date checkIn = getDate(scanner);

        System.out.println("Informe a data de Check-Out exemplo 31/01/2022");
        Date checkOut = getDate(scanner);

        if (checkIn != null && checkOut != null) {

            H2JDBCUtils.createHotelTable();

            System.out.println("Número de quartos disponíveis : " + showAvailableRoomsInHotel());
        }

        return checkIn + " ã " + checkOut;
    }

    private static Date getDate(final Scanner scanner) throws ParseException {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(scanner.nextLine());
    }

    public void checkIn(String periodOfTime) throws SQLException {

        // Crio um Guest
        String name = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o Nome do Responsável : ");
        name = scanner.nextLine();

        Room room = new Room(
              "Quarto 35",
              3,
              true
        );

        // Crio um Room
        Guest guest = new Guest(
                name,
                "Rua Correa Dutra 52",
                "+552199758562",
                room
        );


        System.out.println("/*--------------------------------------------------------------------------------------*/");
        System.out.println("/*--------------------  VOUCHER --------------------------------------------------------*/");
        System.out.println("/*--------------------------------------------------------------------------------------*/");

        Hotel recordSaved = H2JDBCUtils.getHotelData();

        System.out.println(" Nome do Hotel : " + recordSaved.getName());
        System.out.println(" Número do Quarto : " + room.getNumber());
        System.out.println(" Nome do Responsável : " + guest.getName());
        System.out.println("Período de estadia : " + periodOfTime);
        System.out.println("/*--------------------------------------------------------------------------------------*/");


    }
}
