package Quiz;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreateQuestionsFile {
    public static void main(String[] args) {
        Questions questions = new Questions();

        questions.question1 = "Столица Китая?";
        questions.response1 = new String[]{"Шанхай", "Пекин", "Сеул"};
        questions.goodResponseIndex1 = 1;

        questions.question2 = "Сколько лап у паука";
        questions.response2 = new String[]{"6", "8", "12"};
        questions.goodResponseIndex2 = 1;

        questions.question3 = "Какой самый сложный язык в мире?";
        questions.response3 = new String[]{"китайский", "русский", "арабский"};
        questions.goodResponseIndex3 = 0;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("questions.dat"))) {
            oos.writeObject(questions);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
