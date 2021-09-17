public class Hospital {

    public static float[] generatePatientsTemperatures(int patientsCount) {
        //TODO: напишите метод генерации массива температур пациентов
        float[] patientsTemperature = new float[patientsCount];
        for (int i = 0; i < patientsTemperature.length; i++) {
            double preTemperature = Math.round(320.0 + 80.0 * Math.random());
            patientsTemperature[i] = (float) preTemperature / 10;
        }
        return patientsTemperature;
    }

    public static String getReport(float[] temperatureData) {
        String allTemperature = "Температуры пациентов:";
        double mediana = 0;
        int healthyPatients = 0;
        for (int i = 0; i < temperatureData.length; i++) {
            mediana += temperatureData[i];
            allTemperature += " " + temperatureData[i];
            if (temperatureData[i] <= 36.91 && temperatureData[i] >= 36.19){
                ++healthyPatients;
            }
        }
        mediana = Math.round(mediana / temperatureData.length * 100);
        mediana = mediana / 100;
        /*
        TODO: Напишите код, который выводит среднюю температуру по больнице,количество здоровых пациентов,
            а также температуры всех пациентов.
        */

        String report =
                allTemperature + System.lineSeparator() +
                        "Средняя температура: " + mediana + System.lineSeparator() +
                        "Количество здоровых: " + healthyPatients;

        return report;
    }
}
