
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;

public class Data {
    double backpackSize;
    int numberOfItems;
    List<Double> massList;
    List<Double> valueList;

    public Data(String fileAddress) throws DataFormatException {
        this.readDataFromFile(fileAddress);
        if (Stream.of(this.backpackSize, this.numberOfItems, this.massList, this.valueList).anyMatch(Objects::isNull)) {
            throw new DataFormatException();
        }
    }

    private void readDataFromFile(String fileAddress) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileAddress));
            String line = bufferedReader.readLine();
            this.backpackSize = Double.parseDouble(line.split(" ")[0]);
            this.numberOfItems = Integer.parseInt(line.split(" ")[1]);
            this.massList = (List)Arrays.stream(bufferedReader.readLine().split(",")).map((tmp) -> {
                return Double.parseDouble(tmp);
            }).collect(Collectors.toList());
            this.valueList = (List)Arrays.stream(bufferedReader.readLine().split(",")).map((tmp) -> {
                return Double.parseDouble(tmp);
            }).collect(Collectors.toList());
            bufferedReader.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public String toString() {
        return "Data{backpackSize=" + this.backpackSize + ", numberOfItems=" + this.numberOfItems + ", massList=" + this.massList + ", valueList=" + this.valueList + "}";
    }
}
