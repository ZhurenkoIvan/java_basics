public class Main {

    public static void main(String[] args) {
        String line = "Каждый охотник желает знать, где сидит фазан";
        String[] lineArray = line.split(",?\\s+");
        for (String lineArrayNumber : lineArray){
            System.out.println(lineArrayNumber);
        }
        String[] reverseLineArray = ReverseArray.reverse(lineArray);
        for (String reverseLineArrayNumber : reverseLineArray){
            System.out.println(reverseLineArrayNumber);
        }
    }
}
