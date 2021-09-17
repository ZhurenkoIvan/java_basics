public class ReverseArray {

    //TODO: Напишите код, который меняет порядок расположения элементов внутри массива на обратный.
    public static String[] reverse (String[] strings){
        int reverseI = strings.length;
        String intermediateResult = "";
        for (int i = 0; i < strings.length / 2 + strings.length % 2; i++) {
            reverseI--;
            intermediateResult = strings[i];
            strings[i] = strings[reverseI];
            strings[reverseI] = intermediateResult;
        }
        return strings;
    }
}
