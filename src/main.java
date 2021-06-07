import java.text.DecimalFormat;


public class main {
    public static synchronized void main(String[] args) throws Exception {
        GUI  gui = null;
        Data data = new Data(args[0]);
        int [] bestVector = new int[data.numberOfItems];
        double bestValue = 0;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        for (long i = 0; i < Math.pow(2, data.numberOfItems); i++) {
            int [] currentVector = new int[data.numberOfItems];

            for (int j = 0; j < Long.toBinaryString(i).length() ; j++)
                currentVector[currentVector.length-1-j] = Integer.parseInt(String.valueOf(Long.toBinaryString(i)
                        .charAt(Long.toBinaryString(i).length()-1-j)));

            for (int j = 0; j < currentVector.length ; j++) {
                if (isSummaryMassBelowThreshold(currentVector,data)) {
                    if (getValueOfVector(currentVector, data) > bestValue) {
                        bestValue = getValueOfVector(currentVector, data);
                        bestVector = currentVector;
                    }
                }
            }
            System.out.println(decimalFormat.format(((double)(i+1)*100)/Math.pow(2, data.numberOfItems))+" %");
            StringBuilder tmp = new StringBuilder();

            for (int k : bestVector) tmp.append(k).append(" ");


            DataToShow newestData = new DataToShow(tmp.toString(), String.valueOf(getMassOfVector(bestVector,data)),String.valueOf(data.backpackSize),String.valueOf(bestValue),( (double) ((i+1)*100) / Math.pow(2, data.numberOfItems)));
            if (gui == null)
                gui = new GUI("Knapsack");

            gui.updateData(newestData);

        }
        //gui.finish = true;
        System.out.print("best value : " +  bestValue+" best vector : "  );
        for (int j : bestVector) System.out.print(j);
        System.out.print(" : used : "+ getMassOfVector(bestVector,data)+ "/"+data.backpackSize);

    }



    public static synchronized boolean isSummaryMassBelowThreshold(int [] vector, Data data){
        double mass = 0;
        for (int i = 0; i < vector.length ; i++) {
            if (vector[i] == 1)
                mass += data.massList.get(i);

            if (mass > data.backpackSize)
                return false;
        }
        return true;
    }

    public static synchronized double getMassOfVector(int [] vector, Data data){
        double mass = 0;
        for (int i = 0; i < vector.length ; i++) {
            if (vector[i] == 1)
                mass += data.massList.get(i);
        }
        return mass;
    }

    public static synchronized double getValueOfVector(int [] vector, Data data){
        double value = 0;
        for (int i = 0; i < vector.length ; i++)
            if (vector[i] == 1)
                value += data.valueList.get(i);

        return value;
    }
}
