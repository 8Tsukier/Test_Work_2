package ua.telesens.io;

public class RaiTransportationNetwork {

    public static void main(String[] args) {
        TransportationNetwork<RailwayStation> railway = new TransportationNetwork<>();
        RailwayStation Kharkov = new RailwayStation("Харьков", "Южная");
        RailwayStation Novoselovka = new RailwayStation("Новоселовка", "Южная");
        RailwayStation Merefa = new RailwayStation("Мерефа", "Южная");
        RailwayStation Lyubotin = new RailwayStation("Люботин", "Южная");
        RailwayStation Krasnograd = new RailwayStation("Красноград", "Южная");
        RailwayStation Lozovaya = new RailwayStation("Лозовая", "Южная");
        RailwayStation Bogodukhov = new RailwayStation("Богодухов", "Южная");
        RailwayStation Poltava = new RailwayStation("Полтава", "Южная");
        RailwayStation Zachepilovka = new RailwayStation("Зачепиловка", "Приднепровская");
        RailwayStation Liman = new RailwayStation("Лиман", "Донецкая");
        railway.addRoute(new Route<>(Kharkov, Novoselovka, 2, 0, 5));
        railway.addRoute(new Route<>(Novoselovka, Liman, 183, 3, 0));
        railway.addRoute(new Route<>(Novoselovka, Lyubotin, 22, 0, 30));
        railway.addRoute(new Route<>(Novoselovka, Merefa, 23, 0, 30));
        railway.addRoute(new Route<>(Lyubotin, Merefa, 18, 0, 30));
        railway.addRoute(new Route<>(Lyubotin, Bogodukhov, 53, 1, 0));
        railway.addRoute(new Route<>(Lyubotin, Poltava, 116, 2, 0));
        railway.addRoute(new Route<>(Merefa, Krasnograd, 76, 2, 0));
        railway.addRoute(new Route<>(Poltava, Krasnograd, 81, 1, 20));
        railway.addRoute(new Route<>(Merefa, Lozovaya, 123, 2, 0));
        railway.addRoute(new Route<>(Krasnograd, Lozovaya, 95, 1, 20));
        railway.addRoute(new Route<>(Krasnograd, Zachepilovka, 28, 0, 40));
        railway.addRoute(new Route<>(Lozovaya, Liman, 132, 2, 30));
        System.out.println("Станции:");
        for (RailwayStation station : railway.getNodes()) {
            System.out.println(station.fullInfo() + ". " + railway.routesCount(station) + " лин.");
        }
        System.out.println("По возрастанию количества линий:");
        System.out.println(railway.sortedNodes());
        System.out.println("Самый скоростной участок: " + railway.TheMostHighSpeed());
        System.out.println("Кратчайшие пути:");
        System.out.println(railway.calcDijkstra(Kharkov, Lozovaya));
        System.out.println(railway.calcDijkstra(Poltava, Liman));
    }

}
