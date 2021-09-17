public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

    //TODO: напишите ваш код, результат вывести в консоль
    String charsetVasya = "5000";
    String charsetPetya = "7563";
    String charsetMasha = "30000";
    int startVasya = text.indexOf(charsetVasya);
    int endVasya = text.indexOf(charsetVasya) + charsetVasya.length();
    int startPetya = text.indexOf(charsetPetya);
    int endPetya = text.indexOf(charsetPetya) + charsetPetya.length();
    int startMasha = text.indexOf(charsetMasha);
    int endMasha = text.indexOf(charsetMasha) + charsetMasha.length();

    int sum = Integer.parseInt(text.substring(startVasya, endVasya)) +
            Integer.parseInt(text.substring(startMasha, endMasha)) +
            Integer.parseInt(text.substring(startPetya, endPetya));

    System.out.println(sum);


  }

}