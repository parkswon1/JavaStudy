package thisisjava.src.City;

import java.util.*;

public class PopulationManager {
    static Scanner scanner = new Scanner(System.in);
    Map<String, Integer> cities = new HashMap<>();
    //도시가 존재하는지 확인하는 함수
    private boolean isCityEsixt(String city) {
        return cities.containsKey(city);
    }

    //도시를 추가하는 함수
    void addOrUpdateCity(String city, int population){
        if (isCityEsixt(city)) {
            System.out.println("이미 등록된 도시입니다.");
        }else {
            System.out.println(city + "이(가) 등록되었습니다.");
            cities.put(city, population);
        }
    }

    // 도시를 제거하는 함수
    void removeCity(String city){
        if (isCityEsixt(city)){
            cities.remove(city);
            System.out.println("도시를 제거하였습니다.");
        }else{
            System.out.println("도시가 존재하지 않습니다.");
        }
    }

    //도시의 인구수를 출력하는 함수
    void displayPopulation(String city){
        if (isCityEsixt(city)){
            System.out.println(city + "이(가)의 인구수: " + cities.get(city).intValue());
        }
    }


    //모든 도시와 도시의 인구수를 출력하는 함수
    void displayAll() {
        System.out.println("도시명       인구수");
        for (Map.Entry<String, Integer> entry : cities.entrySet()) {
            String cityName = entry.getKey();
            int population = entry.getValue();
            String formattedString = String.format("%-10s %,d", cityName + ":", population);
            System.out.println(formattedString);
        }
    }

    public static void main(String[] args) {
        final int ADD_OR_UPDATE = 1;
        final int REMOVE = 2;
        final int DISPLAY = 3;
        final int DISPLAY_ALL = 4;
        final int EXIT = 5;

        PopulationManager manager = new PopulationManager();
        manager.addOrUpdateCity("서울", 10000000);
        manager.addOrUpdateCity("부산", 3500000);

        while (true) {
            System.out.println("명령을 입력하세요 (1: 추가/수정, 2: 삭제, 3: 조회, 4: 전체 조회, 5: 종료): ");
            int command = scanner.nextInt(); // 사용자가 명령을 숫자로 입력
            if (command == EXIT) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            String city;
            switch (command) {
                case ADD_OR_UPDATE:
                    System.out.print("도시 이름을 입력하세요: ");
                    city = scanner.next();
                    System.out.print("인구를 입력하세요: ");
                    int population = scanner.nextInt();
                    manager.addOrUpdateCity(city, population);
                    break;
                case REMOVE:
                    System.out.print("도시 이름을 입력하세요: ");
                    city = scanner.next();
                    manager.removeCity(city);
                    break;
                case DISPLAY:
                    System.out.print("도시 이름을 입력하세요: ");
                    city = scanner.next();
                    manager.displayPopulation(city);
                    break;
                case DISPLAY_ALL:
                    manager.displayAll();
                    break;
                default:
                    System.out.println("알 수 없는 명령입니다.");
            }
        }
    }
}