package Quiz;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите ваше имя: ");
        String name = sc.nextLine();
        Questions questions = null;

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("questions.dat"))) {
            questions = (Questions) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка загрузки вопросов");
            return;
        }
        int score = 0;
        System.out.println(questions.question1);
        for (int i = 0; i < questions.response1.length; i++) {
            System.out.println((i + 1) + ") " + questions.response1[i]);
        }
        int ans1 = getValidAnswer(sc, questions.response1.length);
        if (ans1 == questions.goodResponseIndex1) score++;

        System.out.println("\n" + questions.question2);
        for (int i = 0; i < questions.response2.length; i++) {
            System.out.println((i + 1) + ") " + questions.response2[i]);
        }
        int ans2 = getValidAnswer(sc, questions.response2.length);
        if (ans2 == questions.goodResponseIndex2) score++;

        System.out.println("\n" + questions.question3);
        for (int i = 0; i < questions.response3.length; i++) {
            System.out.println((i + 1) + ") " + questions.response3[i]);
        }
        int ans3 = getValidAnswer(sc, questions.response3.length);
        if (ans3 == questions.goodResponseIndex3) score++;
        System.out.println("\nРезультат: " + score + " из 3");

        Raiting raiting;
        File file = new File("raiting.dat");
        try {
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    raiting = (Raiting) ois.readObject();
                }
            } else {
                raiting = new Raiting();
                raiting.games = new ArrayList<>();
            }

            Game game = new Game();
            game.gamer = name;
            game.raiting = score;
            game.gameDate = new Date();
            raiting.games.add(game);
            raiting.games.sort((g1, g2) -> g2.raiting.compareTo(g1.raiting));
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(raiting);
            }
            System.out.println("\nИстория игр (отсортировано по рейтингу):");
            for (Game g : raiting.games) {
                System.out.println(g);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка работы с рейтингом");
        }
        sc.close();
    }

    private static int getValidAnswer(Scanner sc, int max) {
        int answer;
        do {
            System.out.print("Введите номер ответа ");
            while (!sc.hasNextInt()) {
                System.out.println("Введите число");
                sc.next();
            }
            answer = sc.nextInt() - 1;
        } while (answer < 0 || answer >= max);
        return answer;
    }
}