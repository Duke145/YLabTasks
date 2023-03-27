package Module4.io.ylab.intensive.lesson04.movie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class MovieLoaderImpl implements MovieLoader {
    private DataSource dataSource;

    public MovieLoaderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadData(File file) {
        // РЕАЛИЗАЦИЮ ПИШЕМ ТУТ
        String tempLine = null;
        String writeToDb = "insert into movie (year,length,title,subject,actors,actress,director,popularity,awards) " +
                "values (?,?,?,?,?,?,?,?,?)";
        Movie movie = null;
        int counter = 1;
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr);
             Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(writeToDb);
        ) {
            while ((tempLine = br.readLine()) != null) {
                if (counter > 2) {
                    List<String> list = List.of(tempLine.split(";"));
                    movie = new Movie();
                    if (!list.get(0).isEmpty()) {
                        movie.setYear(Integer.parseInt(list.get(0)));
                        preparedStatement.setInt(1, movie.getYear());
                    } else {
                        preparedStatement.setNull(1, Types.INTEGER);
                    }
                    if (!list.get(1).isEmpty()) {
                        movie.setLength(Integer.parseInt(list.get(1)));
                        preparedStatement.setInt(2, movie.getLength());
                    } else {
                        preparedStatement.setNull(2, Types.INTEGER);
                    }
                    if (!list.get(2).isEmpty()) {
                        movie.setTitle(list.get(2));
                        preparedStatement.setString(3, movie.getTitle());
                    } else {
                        preparedStatement.setNull(3, Types.VARCHAR);
                    }
                    if (!list.get(3).isEmpty()) {
                        movie.setSubject(list.get(3));
                        preparedStatement.setString(4, movie.getSubject());
                    } else {
                        preparedStatement.setNull(4, Types.VARCHAR);
                    }
                    if (!list.get(4).isEmpty()) {
                        movie.setActors(list.get(4));
                        preparedStatement.setString(5, movie.getActors());
                    } else {
                        preparedStatement.setNull(5, Types.VARCHAR);
                    }
                    if (!list.get(5).isEmpty()) {
                        movie.setActress(list.get(5));
                        preparedStatement.setString(6, movie.getActress());
                    } else {
                        preparedStatement.setNull(6, Types.VARCHAR);
                    }
                    if (!list.get(6).isEmpty()) {
                        movie.setDirector(list.get(6));
                        preparedStatement.setString(7, movie.getDirector());
                    } else {
                        preparedStatement.setNull(7, Types.VARCHAR);
                    }
                    if (!list.get(7).isEmpty()) {
                        movie.setPopularity(Integer.parseInt(list.get(7)));
                        preparedStatement.setInt(8, movie.getPopularity());
                    } else {
                        preparedStatement.setNull(8, Types.INTEGER);
                    }
                    if (!list.get(8).isEmpty()) {
                        if (list.get(8).toLowerCase().equals("yes")) {
                            movie.setAwards(true);
                        } else {
                            movie.setAwards(false);
                        }
                        preparedStatement.setBoolean(9, movie.getAwards());
                    } else {
                        preparedStatement.setNull(9, Types.BOOLEAN);
                    }
                    preparedStatement.executeUpdate();
                } else {
                    counter++;
                }
            }
            br.close();
            fr.close();
            connection.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
