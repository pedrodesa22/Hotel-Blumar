package br.com.hotel.view;

import br.com.hotel.controller.HotelController;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Menu {

    Menu() {
        super();
    }

    private static final HotelController controller = new HotelController();

    private static String periodOfTime = "";

    public static void main() {

        String line = "";
        Scanner scanner = new Scanner(System.in);

        printMenu();

        try {
            do {
                line = scanner.nextLine();

                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case '1':
                            periodOfTime = controller.findAndReserveRoom();
                            break;
                        case '2':
                            controller.checkIn(periodOfTime);
                            break;
                        case '3':
                            //createAccount();
                            break;
                        case '4':
                            System.out.println("Saída");
                            break;
                        default:
                            System.out.println("Informe uma das opções válidas\n");
                            break;
                    }
                } else {
                    System.out.println("Erro: Opção Inválida\n");
                }
            } while (line.charAt(0) != '4' || line.length() != 1);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Input vazio. Saindo do programa...");
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printMenu(){

        System.out.print("\nBem vindo a aplicação do Hotel\n" +
                "--------------------------------------------\n" +
                "1. Verificar disponibilidade de reserva\n" +
                "2. Executar Check In\n" +
                "3. Executar Check Out\n" +
                "4. Sair do Sistema\n" +
                "--------------------------------------------\n" +
                "Escolha un número:\n");
    }
}
