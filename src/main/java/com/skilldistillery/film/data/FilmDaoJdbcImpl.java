package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Film;

public class FilmDaoJdbcImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USER = "student";
	private static final String PWD = "student";

	public FilmDaoJdbcImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Film> findFilmById(int filmId) {
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT film.id AS film_id, film.title, film.description, film.release_year, film.rating, "
					+ "GROUP_CONCAT(CONCAT(actor.first_name, ' ', actor.last_name) SEPARATOR ', ') AS actors, "
					+ "language.id AS language_id, language.name AS language_name " + "FROM film "
					+ "LEFT JOIN film_actor ON film.id = film_actor.film_id "
					+ "LEFT JOIN actor ON film_actor.actor_id = actor.id "
					+ "LEFT JOIN language ON film.language_id = language.id "
					+ "WHERE film.id = ? "
					+ "GROUP BY film.id, film.title, film.description, film.release_year, film.rating, language.id, language.name";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + filmId + "%");

			ResultSet filmResult = stmt.executeQuery();

			if (!filmResult.next()) {
				System.out.println("We're sorry (sort of), but the film you are looking for does not exist."
						+ " Please try again.");
			} else {
				StringBuilder actors = new StringBuilder();

				do {
					actors.append(filmResult.getString("actors")).append(", ");
					System.out.println("Title: " + filmResult.getString("title") + "\nDescription: "
							+ filmResult.getString("description") + "\nRating: " + filmResult.getString("rating")
							+ "\nRelease Year: " + filmResult.getShort("release_year") + "\nLanguage: "
							+ filmResult.getString("language_name") + "\nActors: " + actors.toString() + "\n");
				} while (filmResult.next());

				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	public Film findFilmByKeyword(String keyword) {
		Film filmSetNumber2 = null;

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT film.id AS film_id, film.title, film.description, film.release_year, film.rating, "
					+ "GROUP_CONCAT(CONCAT(actor.first_name, ' ', actor.last_name) SEPARATOR ', ') AS actors, "
					+ "language.id AS language_id, language.name AS language_name " + "FROM film "
					+ "LEFT JOIN film_actor ON film.id = film_actor.film_id "
					+ "LEFT JOIN actor ON film_actor.actor_id = actor.id "
					+ "LEFT JOIN language ON film.language_id = language.id "
					+ "WHERE film.title LIKE ? OR film.description LIKE ? "
					+ "OR film.rating LIKE ? OR film.release_year LIKE ? "
					+ "GROUP BY film.id, film.title, film.description, film.release_year, film.rating, language.id, language.name";

			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + keyword + "%");
			preparedStatement.setString(2, "%" + keyword + "%");
			preparedStatement.setString(3, "%" + keyword + "%");
			preparedStatement.setString(4, "%" + keyword + "%");

			ResultSet filmResult = preparedStatement.executeQuery();

			if (!filmResult.next()) {
				System.out.println("We're sorry (sort of), but the film you are looking for does not exist."
						+ " Please try again.");
			} else {
				StringBuilder actors = new StringBuilder();

				do {
					actors.append(filmResult.getString("actors")).append(", ");
					System.out.println("Title: " + filmResult.getString("title") + "\nDescription: "
							+ filmResult.getString("description") + "\nRating: " + filmResult.getString("rating")
							+ "\nRelease Year: " + filmResult.getShort("release_year") + "\nLanguage: "
							+ filmResult.getString("language_name") + "\nActors: " + actors.toString() + "\n");
				} while (filmResult.next());

			}

			filmResult.close();
			preparedStatement.close();
			conn.close();

		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return filmSetNumber2;
	}

}
