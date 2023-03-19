package Module3.Task5;

import Module3.Task3.Employee;

import java.io.*;
import java.util.Arrays;

public class Sorter {
    public File sortFile(File dataFile) throws IOException {
        String tempFile = "src\\Module3\\Task5\\temp-file-";
        FileReader frStart = new FileReader(dataFile);
        BufferedReader brStart = new BufferedReader(frStart);
        int N = (int) brStart.lines().count(); // кол-во чисел в файле
        brStart.close();
        frStart.close();
        FileReader fr = new FileReader(dataFile);
        BufferedReader br = new BufferedReader(fr);

        int M = 1000_000; // размер буффера
        long[] buffer = new long[Math.min(M, N)];
        int i;
        int j;
        String line;
        int slices = (int) Math.ceil((double) N / M);
        // Основной цикл - создание N / M временных файлов с отсортированными данными
        for (i = 0; i < slices; i++) {
            // Чтение основого файла на размер буффера
            for (j = 0; j < (Math.min(M, N)); j++) {
                line = br.readLine();
                if (line != null) {
                    buffer[j] = Long.parseLong(line);
                } else {
                    break;
                }
            }
            // Сортировка элементов
            Arrays.sort(buffer);

            // Запись отсортированных чисел во временный файл
            FileWriter fw = new FileWriter(tempFile + i + ".txt");
            PrintWriter pw = new PrintWriter(fw);
            for (int k = 0; k < j; k++) {
                pw.println(buffer[k]);
            }
            pw.close();
            fw.close();
        }

        br.close();
        fr.close();

        long[] topNums = new long[slices]; // массив указателей на первое число во временном файле
        BufferedReader[] brs = new BufferedReader[slices];

        //цикл открывает на чтение все временные файлы и первое число в каждом файле пишет в массив указателей topNums
        for (i = 0; i < slices; i++) {
            brs[i] = new BufferedReader(new FileReader(tempFile + i + ".txt"));
            line = brs[i].readLine();
            if (line != null)
                topNums[i] = Long.parseLong(line);
            else
                topNums[i] = Long.MAX_VALUE;
        }

        File sortedFile = new File("src\\Module3\\Task5\\" + dataFile.getName().replace(".txt","_sorted.txt"));
        FileWriter fw = new FileWriter(sortedFile);
        PrintWriter pw = new PrintWriter(fw);

        //алгоритм слияния - находит минимальное число среди массива указателей -> пишет  его в новый файл
        //выбранный указатель заменяется на следующее число из временного файла
        for (i = 0; i < N; i++) {
            long min = topNums[0];
            int minFile = 0;

            for (j = 0; j < slices; j++) {
                if (min > topNums[j]) {
                    min = topNums[j];
                    minFile = j;
                }
            }

            pw.println(min);
            line = brs[minFile].readLine();
            if (line != null)
                topNums[minFile] = Long.parseLong(line);
            else
                topNums[minFile] = Long.MAX_VALUE;

        }
        //удаляем временные файлы и закрываем буфферы
        for (i = 0; i < slices; i++) {
            brs[i].close();
            new File(tempFile + i + ".txt").delete();
        }
        pw.close();
        fw.close();

        return sortedFile;
    }
}
